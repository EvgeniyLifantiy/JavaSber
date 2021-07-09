package com.BankApi.ConnectInstallService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class PropertyService {
    public String getPropertyPath() {
        return propertyPath;
    }

    private String propertyPath = "src/main/resources/Connection.properties";
    private Properties properties=new Properties();

    public PropertyService()  {
        try {
            properties.load(new FileInputStream(propertyPath));

        } catch (IOException e) {
            System.out.println("Error while try connect to property file");
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
