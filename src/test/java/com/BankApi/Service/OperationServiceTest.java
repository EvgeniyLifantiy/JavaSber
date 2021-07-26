package com.BankApi.Service;

import com.BankApi.Dao.Api.OperationDao;
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
}
