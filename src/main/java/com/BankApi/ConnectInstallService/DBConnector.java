package com.BankApi.ConnectInstallService;

import com.BankApi.SpringRealization.ApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class DBConnector {

    private final PropertyService propertyService= ApplicationContext.getInstance().getBean(PropertyService.class);

    private Connection connection;

    {
        try {
            connection = DriverManager.getConnection(
                    propertyService.getProperties().getProperty("DB_URL"),
                            propertyService.getProperties().getProperty("USER"),
                            propertyService.getProperties().getProperty("PASS"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    ;



    public DBConnector() {

    }

    public Connection getConnection() {

        return connection;
    }
}
