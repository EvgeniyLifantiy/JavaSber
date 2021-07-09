package com.BankApi.Service.Implementation;

import com.BankApi.Dao.Implementation.BillDaoImpl;
import com.BankApi.Dao.Implementation.OperationDao;
import com.BankApi.Entity.Operation;
import com.BankApi.Exception.AlreadyCommitedOperation;

import java.util.List;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class OperationService {
    OperationDao operationDao=new OperationDao();

    public Operation getOperation(long id){
        return operationDao.getOperationById(id);
    }


    public List<Operation> getAllOperation(){
        return operationDao.getAllOperations();
    }


    public boolean addOperation(long sender,long recipient,double sum){
        return operationDao.addOperation(sender,recipient,sum);
    }

    public boolean submitOperation(long id){

        try {
            return operationDao.submitOperation(id);
        }catch (AlreadyCommitedOperation e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("Проверьте корректность введенных данных и остаток на балансе");
        }
        return false;
    }
}
