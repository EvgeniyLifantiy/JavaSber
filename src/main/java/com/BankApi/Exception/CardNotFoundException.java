package com.BankApi.Exception;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class CardNotFoundException extends Exception {
    public CardNotFoundException(String message) {
        super(message);
    }
}
