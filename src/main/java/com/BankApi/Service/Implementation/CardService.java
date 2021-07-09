package com.BankApi.Service.Implementation;

import com.BankApi.Dao.Api.BillDao;
import com.BankApi.Dao.Api.CardDao;
import com.BankApi.Dao.Implementation.CardDaoImlp;
import com.BankApi.Entity.Bill;
import com.BankApi.Entity.Card;
import com.BankApi.Exception.BillNotFoundException;
import com.BankApi.Exception.CardNotFoundException;
import com.BankApi.SpringRealization.ApplicationContext;


import java.util.List;
/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class CardService {

    private CardDao cardDao = ApplicationContext.getInstance().getBean(CardDao.class);
    private BillService billService = ApplicationContext.getInstance().getBean(BillService.class);

    public CardService() {
    }

    public CardService(CardDao cardDao , BillService billService) {
        this.cardDao = cardDao;
        this.billService = billService;
    }


    public boolean addCard( long billId) throws BillNotFoundException {
            Bill bill = billService.getBillById(billId);
            if (bill!=null) {
                return cardDao.addCard( new Card(billId));
            } else {
                return false;
            }
    }


    public Card getCardById(long id)
            throws CardNotFoundException {
        Card card = cardDao.getCardById(id);
        if (card!=null) {
            return card;
        } else {
            throw new CardNotFoundException("Card with such data is not exist. Check the correctness of the data entered");
        }
    }

    public boolean changeCardStatus(long id, Boolean status) {
            return cardDao.changeCardStatus(id, status);
    }


    public List<Card> getAllCardsByBill(long billId ) throws BillNotFoundException {
        try {
            Bill bill = billService.getBillById(billId);
            if (bill!=null) {
                return cardDao.getAllCards(billId);
            }
            return null;
        } catch (BillNotFoundException e) {
            throw new BillNotFoundException("Bill with such data is not exist. Check the correctness of the data entered");
        }

    }

}
