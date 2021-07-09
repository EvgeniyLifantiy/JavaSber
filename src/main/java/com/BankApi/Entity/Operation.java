package com.BankApi.Entity;

import java.util.Objects;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class Operation {
    long id;
    long senderBill;
    long recipientBill;
    double sum;
    boolean status=false;

    public Operation(long id, long senderBill, long recipientBill, double sum, boolean status) {
        this.id = id;
        this.senderBill = senderBill;
        this.recipientBill = recipientBill;
        this.sum = sum;
        this.status = status;
    }

    public Operation(long senderBill, long recipientBill, double sum) {
        this.senderBill = senderBill;
        this.recipientBill = recipientBill;
        this.sum = sum;
    }

    public Operation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getsenderBill() {
        return senderBill;
    }

    public void setsenderBill(long senderBill) {
        this.senderBill = senderBill;
    }

    public long getRecipientBill() {
        return recipientBill;
    }

    public void setRecipientBill(long recipientBill) {
        this.recipientBill = recipientBill;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return id == operation.id && senderBill == operation.senderBill && recipientBill == operation.recipientBill && Double.compare(operation.sum, sum) == 0 && status == operation.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, senderBill, recipientBill, sum, status);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", senderBill=" + senderBill +
                ", recipientBill=" + recipientBill +
                ", sum=" + sum +
                ", status=" + status +
                '}';
    }
}
