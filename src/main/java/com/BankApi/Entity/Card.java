package com.BankApi.Entity;

import java.util.Objects;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class Card {

    private long id;
    private long cardNumber;
    private boolean isActive;
    private long billId;

    public Card() {
    }

    public Card(long id, long cardNumber, long billId,boolean isActive) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.billId = billId;
        this.isActive=isActive;
    }
    public Card(long billId) {
        this.billId = billId;
    }

    public Card(long billId,boolean status) {
        this.isActive=status;
        this.billId = billId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public boolean getStatus() {
        return isActive;
    }

    public void setStatus(boolean status) {
        this.isActive = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id && cardNumber == card.cardNumber && isActive == card.isActive && billId == card.billId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNumber, isActive, billId);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber=" + cardNumber +
                ", status=" + isActive +
                ", billId=" + billId +
                '}';
    }
}
