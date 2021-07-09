package com.BankApi.Service.Implementation;

import com.BankApi.Dao.Api.BillDao;
import com.BankApi.Dao.Implementation.BillDaoImpl;
import com.BankApi.Entity.Bill;
import com.BankApi.Entity.User;
import com.BankApi.Exception.BillNotFoundException;
import com.BankApi.Exception.UserNotFoundException;
import com.BankApi.SpringRealization.ApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class BillService   {
    private BillDao billDao = ApplicationContext.getInstance().getBean(BillDao.class);
    private UserService userService = ApplicationContext.getInstance().getBean(UserService.class);

    public BillService() {
    }

    public BillService(BillDao billDao, UserService userService) {
        this.billDao = billDao;
        this.userService = userService;
    }


    public boolean addBill(long userId) {
        return billDao.addBill(userId);
    }



    public Bill getBillById(long billId) throws BillNotFoundException {
        Bill bill = billDao.getBillById(billId);
        if (bill!=null) {
            return bill;
        } else {
            throw new BillNotFoundException("Bill with such data is not exist. Check the correctness of the data entered");
        }
    }


    public List<Bill> getAllBillsByUserId(long userId) {
        return billDao.getAllBillsByUserId(userId);
    }

    //bigdecimal
    public double getBalanceOfBill(long billId, String phone)
            throws BillNotFoundException, UserNotFoundException, SQLException {
        User user = userService.getUserByPhone(phone);
        Bill bill = billDao.getBillById(billId);
        if (bill!=null) {
            if (user.getId() == bill.getUserId())
                return billDao.getBalanceOfBill(billId);
        }
        throw new BillNotFoundException("Bill with such data is not exist. Check the correctness of the data entered");

    }
}
