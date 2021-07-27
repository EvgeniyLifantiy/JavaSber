package com.BankApi.Service;

import com.BankApi.Dao.Implementation.OperationDao;
import com.BankApi.Dao.Api.UserDao;
import com.BankApi.Entity.Card;
import com.BankApi.Entity.Operation;
import com.BankApi.Service.Implementation.OperationService;
import com.BankApi.Service.Implementation.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class OperationServiceTest {
    @Mock
    OperationDao operationDao = Mockito.mock(OperationDao.class);
    @InjectMocks
    OperationService operationService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getOperationSuccess(){
        Mockito.when(operationDao.getOperationById(1))
                .thenReturn(new Operation(1,1,2));

        assertEquals(new Operation(1,1,2),
                         operationService.getOperation(1));
    }

    @Test
    public void getOperationFailed(){
        Mockito.when(operationDao.getOperationById(Mockito.anyLong()))
                .thenReturn(null);

        assertEquals(null, operationService.getOperation(Mockito.anyLong()));
    }

    @Test
    public void getAllOperationSuccess(){
        Mockito.when(operationDao.getAllOperations())
                .thenReturn(new ArrayList<>());

        assertEquals(new ArrayList<>(),
                operationService.getAllOperation());
    }

    @Test
    public void getAllOperationFailed(){
        Mockito.when(operationDao.getAllOperations())
                .thenReturn(null);

        assertEquals(null, operationService.getAllOperation());
    }

    @Test
    public void addOperationSuccess(){
        Operation anyOperation=new Operation();
        Mockito.when(operationDao.addOperation(anyOperation.getsenderBill(),
                anyOperation.getRecipientBill(),
                anyOperation.getSum()))
                .thenReturn(true);

        assertTrue(operationService.addOperation(
                anyOperation.getsenderBill(),
                anyOperation.getRecipientBill(),
                anyOperation.getSum())
        );
    }

    @Test
    public void addOperationFailed(){
        Operation anyOperation=new Operation();
        Mockito.when(operationDao.addOperation(anyOperation.getsenderBill(),
                anyOperation.getRecipientBill(),
                anyOperation.getSum()))
                .thenReturn(false);

        assertFalse(operationService.addOperation(
                anyOperation.getsenderBill(),
                anyOperation.getRecipientBill(),
                anyOperation.getSum())
        );
    }

    @Test
    public void submitOperationSuccess(){
        Mockito.when(operationService.submitOperation(Mockito.anyLong())).thenReturn(true);

        assertTrue(operationService.submitOperation(1));
    }

    @Test
    public void submitOperationFailed(){
        Mockito.when(operationService.submitOperation(Mockito.anyLong())).thenReturn(false);
        assertFalse(operationService.submitOperation(1));
    }

}
