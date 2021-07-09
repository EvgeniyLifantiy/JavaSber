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


    /**
     * Add Bill to database
     * @param userId- user who opens an account
     * @return true, if adding successful. Else - false
     */
    boolean addBill(long userId);

    /**
     * Get bill from database by Id
     * @param billId id of Bill
     * @return Bill if was found. Else - null
     */
    @Nullable
    Bill getBillById(long billId);

    /**
     * Get balance of Bill by Id
     * @param billId id of Bill
     * @return balance of Bill if found. Else - sql exception
     * @throws SQLException if Bill was not found
     */
    double getBalanceOfBill(long billId) throws SQLException;

    /**
     *Get all bills of user by userId
     * @param userId id of User
     * @return list of Bills if found. Else - null.
     */
    @Nullable
    List<Bill> getAllBillsByUserId(long userId);

}
