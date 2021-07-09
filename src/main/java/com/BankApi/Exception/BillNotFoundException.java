package com.BankApi.Exception;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class BillNotFoundException extends Exception {
    public BillNotFoundException(String message) {
        super(message);
    }
}
