package com.BankApi.Service;

import com.BankApi.Dao.Api.BillDao;
import com.BankApi.Dao.Implementation.BillDaoImpl;
import com.BankApi.Entity.Bill;
import com.BankApi.Entity.User;
import com.BankApi.Exception.BillNotFoundException;
import com.BankApi.Exception.UserNotFoundException;
import com.BankApi.Role;
import com.BankApi.Service.Implementation.BillService;
import com.BankApi.Service.Implementation.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class BillServiceTest {
    @Mock
    private final BillDaoImpl billRepository = Mockito.mock(BillDaoImpl.class);
    @Mock
    private final UserService userService = Mockito.mock(UserService.class);
    @InjectMocks
    private BillService billService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addBillTrue() {
        Mockito.when(billRepository.addBill(1)).thenReturn(true);
        assertTrue(billService.addBill(1));
    }

    @Test
    void addBillFalse() {
        Mockito.when(billRepository.addBill(1)).thenReturn(false);
        assertFalse(billService.addBill(1));
    }

    @Test
    public void getBillByIdSuccess() throws BillNotFoundException {
        Mockito.when(billRepository.getBillById(1)).thenReturn(new Bill());
        assertEquals(new Bill(), billService.getBillById(1));

    }

    @Test
    public void getBillByIdException() throws BillNotFoundException {
        Mockito.when(billRepository.getBillById(2)).thenReturn(null);
        assertThrows(BillNotFoundException.class, () -> billService.getBillById(2));
    }


    @Test
    public void getAllBillsByUserSuccess() {
        List<Bill> billList = new ArrayList<>();
        billList.add(new Bill());
        billList.add(new Bill());
        Mockito.when(billRepository.getAllBillsByUserId(1)).thenReturn(billList);
        assertEquals(billList, billService.getAllBillsByUserId(1));
    }

    @Test
    public void getAllBillsByUserNull() {
        Mockito.when(billRepository.getAllBillsByUserId(1)).thenReturn(null);
        assertNull(billService.getAllBillsByUserId(1));
    }

    @Test
    public void getBalanceoFBillSuccess() throws UserNotFoundException, BillNotFoundException, SQLException {
        Bill bill = new Bill(1, 1, 0, 1);
        User user = new User(
                1, "d", "f", "l",
                "m",  Role.ADMIN);
        Mockito.when(billRepository.getBillById(1)).thenReturn(bill);
        Mockito.when(userService.getUserByPhone("d")).thenReturn(user);
        assertEquals(0, billService.getBalanceOfBill(1, "d"));
    }

    @Test
    public void getBalanceNotFound() {
        Mockito.when(billRepository.getBillById(anyLong())).thenReturn(null);
        assertThrows(BillNotFoundException.class, () -> billService.getBalanceOfBill(1, "d"));
    }

    @Test
    public void getBalanceNull() throws UserNotFoundException {
        Bill bill = null;
        User user = new User(
                1, "d", "f", "l",
                "m",  Role.ADMIN);
        Mockito.when(billRepository.getBillById(1)).thenReturn(bill);
        Mockito.when(userService.getUserByPhone("d")).thenReturn(user);
        assertThrows(BillNotFoundException.class, () -> billService.getBalanceOfBill(1, "d"));
    }
}
