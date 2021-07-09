package com.BankApi.Dao.Implementation;

import com.BankApi.ConnectInstallService.DBConnector;
import com.BankApi.Dao.Api.UserDao;
import com.BankApi.Role;
import com.BankApi.Entity.User;
import org.jetbrains.annotations.Nullable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class UserDaoImpl implements UserDao {

    private DBConnector dbConnector=new DBConnector();
    @Override
    public boolean addUser(User user) {
        try (PreparedStatement preparedStatement=dbConnector.getConnection().prepareStatement(
                "INSERT INTO USERS (PHONE, PASSWORD, FIRST_NAME, LAST_NAME, ROLE) " +
                        "VALUES (?, ?, ?, ?, ?)"
        )) {
            preparedStatement.setString(1, user.getPhone());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getRole().toString());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Nullable
    public User getUserByPhone(String phone) {
        try (PreparedStatement preparedStatement=dbConnector.getConnection().prepareStatement("SELECT * FROM USERS WHERE PHONE = ?")) {
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    Role.valueOf(resultSet.getString(6)));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
