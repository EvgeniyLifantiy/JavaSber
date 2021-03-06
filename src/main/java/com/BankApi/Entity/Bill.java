package com.BankApi.Entity;

import java.util.Objects;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class Bill {
    private long id;
    private long billNumber;
    private double balance;
    private long userId;

    public Bill() {
    }

    public Bill(long id, long billNumber, double balance, long userId) {
        this.id = id;
        this.billNumber = billNumber;
        this.balance = balance;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(long billNumber) {
        this.billNumber = billNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserPhone(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return id == bill.id && billNumber == bill.billNumber &&
                Double.compare(bill.balance, balance) == 0 && userId == bill.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, billNumber, balance, userId);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", billNumber=" + billNumber +
                ", balance=" + balance +
                ", userId=" + userId +
                '}';
    }
}
