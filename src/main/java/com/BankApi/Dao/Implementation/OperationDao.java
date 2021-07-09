package com.BankApi.Dao.Implementation;

import com.BankApi.ConnectInstallService.DBConnector;
import com.BankApi.Entity.Bill;
import com.BankApi.Entity.Operation;
import com.BankApi.Entity.User;
import com.BankApi.Exception.AlreadyCommitedOperation;
import com.BankApi.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class OperationDao implements com.BankApi.Dao.Api.OperationDao {
    private DBConnector dbConnector=new DBConnector();

    public boolean addOperation(long senderBill,long recipientBill,double sum){
        try (PreparedStatement preparedStatement=dbConnector.getConnection().prepareStatement(
                "INSERT INTO OPERATIONS (SENDER_BILL, RECIPIENT_BILL, SUM) " +
                        "VALUES (?, ?, ?)"
        )) {
            preparedStatement.setLong(1, senderBill);
            preparedStatement.setLong(2, recipientBill);
            preparedStatement.setDouble(3, sum);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Operation> getAllOperations(){
        try (PreparedStatement preparedStatement=dbConnector.getConnection()
                .prepareStatement("SELECT * FROM OPERATIONS")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Operation> operationList = new ArrayList<>();
            while (resultSet.next()) {
                Operation operation = new Operation(
                        resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getLong(3),
                        resultSet.getDouble(4),
                        resultSet.getBoolean(5)
                );
                operationList.add(operation);
            }
            return operationList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Operation getOperationById(long id){
        try (PreparedStatement preparedStatement=dbConnector.getConnection()
                .prepareStatement("SELECT * FROM OPERATIONS WHERE ID = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Operation(
                    resultSet.getLong(1),
                    resultSet.getLong(2),
                    resultSet.getLong(3),
                    resultSet.getDouble(4),
                    resultSet.getBoolean(5));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean submitOperation(long id) throws AlreadyCommitedOperation {
        Operation operation=getOperationById(id);

        if(operation.getStatus()){
            throw new AlreadyCommitedOperation("this operation already completed");
        }

        Connection connection=dbConnector.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Не получилось установить AutoCommitFalse");
            return false;
        }

        //takeOffFromBalanceByBillId
        try (PreparedStatement preparedStatement=connection.prepareStatement(
                "UPDATE BILLS SET BALANCE = (SELECT BALANCE - ?) WHERE BILL_NUMBER = ?")) {
            preparedStatement.setDouble(1, operation.getSum());
            preparedStatement.setLong(2, operation.getsenderBill());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                System.out.println("Ошибка в rollback");
                throwables.printStackTrace();
            }
            return false;
        }

        //replenishBalanceByBillId
        try (PreparedStatement preparedStatement=connection.prepareStatement(
                "UPDATE BILLS SET BALANCE = (SELECT BALANCE + ?) WHERE BILL_NUMBER = ?")) {
            preparedStatement.setDouble(1, operation.getSum());
            preparedStatement.setLong(2, operation.getRecipientBill());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                System.out.println("Ошибка в rollback");
                e.printStackTrace();
            }
            return false;
        }

        //replenishBalanceByBillId
        try (PreparedStatement preparedStatement=connection.prepareStatement(
                "UPDATE OPERATIONS SET COMMITED = TRUE WHERE ID = ?")) {
            preparedStatement.setLong(1, operation.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                System.out.println("Ошибка в rollback");
                e.printStackTrace();
            }
            return false;
        }

    }

}
