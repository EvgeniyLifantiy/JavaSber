package com.BankApi.Dao.Api;

import com.BankApi.Entity.Operation;
import com.BankApi.Exception.AlreadyCommitedOperation;
import java.util.List;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public interface OperationDao {
    /**
     *
     * @param senderBill bill of sender-person
     * @param recipientBill bill of recipient-person
     * @param sum amount money in operation
     * @return true ,if adding successful
     */
     boolean addOperation(long senderBill,long recipientBill,double sum);

    /**
     * Get all operations
     * @return list of all existent operations
     */
     List<Operation> getAllOperations();

    /**
     * Get operation by id
     * @param id id of operation
     * @return operation if exist
     */
     Operation getOperationById(long id);

    /**
     * Submit existent operation
     * @param id id of operation
     * @return true, if commit success, else- false
     * @throws AlreadyCommitedOperation - if this operation was completed early
     */
     boolean submitOperation(long id) throws AlreadyCommitedOperation;


}
