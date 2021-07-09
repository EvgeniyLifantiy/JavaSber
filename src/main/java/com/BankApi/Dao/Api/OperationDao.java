package com.BankApi.Dao.Api;

import com.BankApi.Entity.Operation;
import com.BankApi.Exception.AlreadyCommitedOperation;
import java.util.List;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public interface OperationDao {

     boolean addOperation(long senderBill,long recipientBill,double sum);

     List<Operation> getAllOperations();


     Operation getOperationById(long id);


     boolean submitOperation(long id) throws AlreadyCommitedOperation;


}
