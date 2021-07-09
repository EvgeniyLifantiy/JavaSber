package com.BankApi.Exception;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}

