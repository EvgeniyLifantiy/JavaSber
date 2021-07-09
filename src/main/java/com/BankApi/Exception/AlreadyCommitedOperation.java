package com.BankApi.Exception;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class AlreadyCommitedOperation extends Exception {
    public AlreadyCommitedOperation(String message) {
        super(message);
    }
}
