package com.BankApi.Dao.Implementation;

import com.BankApi.ConnectInstallService.DBConnector;
import com.BankApi.Dao.Api.BillDao;
import com.BankApi.Entity.Bill;
import com.BankApi.SpringRealization.ApplicationContext;
import org.jetbrains.annotations.Nullable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class BillDaoImpl implements BillDao {

    private DBConnector dbConnector= ApplicationContext.getInstance().getBean(DBConnector.class);

    @Override
    public boolean addBill(long userId) {
        try (PreparedStatement preparedStatement=dbConnector.getConnection().prepareStatement(
                "INSERT INTO BILLS(USER_ID) VALUES (?)"
        ))  {
            preparedStatement.setLong(1, userId);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    @Override
    @Nullable
    public Bill getBillById(long billId) {
        try (PreparedStatement preparedStatement=dbConnector.getConnection().prepareStatement(
                "SELECT * FROM BILLS WHERE BILL_NUMBER = ?")) {
            preparedStatement.setLong(1, billId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Bill(
                    resultSet.getLong(1),
                    resultSet.getLong(2),
                    resultSet.getDouble(3),
                    resultSet.getLong(4)
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Nullable
    public List<Bill> getAllBillsByUserId(long userId) {
        try (PreparedStatement preparedStatement=dbConnector.getConnection().prepareStatement(
                "SELECT * FROM BILLS WHERE USER_ID = ?")) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Bill> bills = new ArrayList<>();
            while (resultSet.next()) {
                Bill bill = new Bill(
                        resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getDouble(3),
                        resultSet.getLong(4)
                );
                bills.add(bill);
            }
            return bills;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public double getBalanceOfBill(long billId) throws SQLException {
       PreparedStatement preparedStatement=dbConnector.getConnection().prepareStatement(
                "SELECT BALANCE FROM BILLS WHERE BILL_NUMBER = ?");
            preparedStatement.setLong(1, billId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getDouble(1);

    }


}
