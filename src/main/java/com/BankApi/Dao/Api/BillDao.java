package com.BankApi.Dao.Api;

import com.BankApi.Entity.Bill;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public interface BillDao {



    boolean addBill(long userId);


    @Nullable
    Bill getBillById(long billId);


    double getBalanceOfBill(long billId) throws SQLException;


    @Nullable
    List<Bill> getAllBillsByUserId(long userId);

}
