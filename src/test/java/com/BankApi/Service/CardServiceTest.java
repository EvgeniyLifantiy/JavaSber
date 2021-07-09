package com.BankApi.Service;

import com.BankApi.Dao.Implementation.CardDaoImlp;
import com.BankApi.Entity.Bill;
import com.BankApi.Entity.Card;
import com.BankApi.Entity.User;
import com.BankApi.Exception.BillNotFoundException;
import com.BankApi.Exception.CardNotFoundException;
import com.BankApi.Exception.UserNotFoundException;
import com.BankApi.Role;
import com.BankApi.Service.Implementation.BillService;
import com.BankApi.Service.Implementation.CardService;
import com.BankApi.Service.Implementation.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class CardServiceTest {
    @Mock
    CardDaoImlp cardDaoImlp = Mockito.mock(CardDaoImlp.class);
    @Mock
    UserService userService = Mockito.mock(UserService.class);
    @Mock
    BillService billService = Mockito.mock(BillService.class);
    @InjectMocks
    CardService cardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addCardBad() throws BillNotFoundException {
        Bill bill = new Bill(1, 1, 0, 1);

        Card card = new Card();
        Mockito.when(billService.getBillById(1)).thenReturn(bill);
        Mockito.when(cardDaoImlp.addCard(card)).thenReturn(true);
        assertFalse(cardService.addCard(1));
    }

    @Test
    void addCard() throws BillNotFoundException {
        Bill bill = new Bill(2, 2, 0, 1);
        Card card = new Card(2);
        Mockito.when(billService.getBillById(2)).thenReturn(bill);
        Mockito.when(cardDaoImlp.addCard(card)).thenReturn(true);
        assertTrue(cardService.addCard(2));
    }


    @Test
    void addCardNotFoundBill() throws BillNotFoundException, UserNotFoundException {
        Mockito.when(billService.getBillById(1)).thenThrow(BillNotFoundException.class);
        assertThrows(BillNotFoundException.class, () -> cardService.addCard(1));
    }

    @Test
    void getCardById() throws BillNotFoundException, UserNotFoundException, CardNotFoundException {

        Mockito.when(cardDaoImlp.getCardById(1)).thenReturn(new Card(1));
        assertEquals(new Card(1), cardService.getCardById(1));
    }

    @Test
    void getCardNotFoundBill() throws BillNotFoundException {
        Mockito.when(billService.getBillById(1)).thenThrow(BillNotFoundException.class);
        assertThrows(CardNotFoundException.class, () -> cardService.getCardById(1));
    }

    @Test
    void getCardBad() throws CardNotFoundException {
        Mockito.when(cardDaoImlp.getCardById(1)).thenReturn(new Card(1));
        assertNotEquals(new Card(2), cardService.getCardById(1));
    }


    @Test
    void getCardNull() {
        Mockito.when(cardDaoImlp.getCardById(1)).thenReturn(null);
        assertThrows(CardNotFoundException.class, () -> cardService.getCardById(1));
    }

    @Test
    void getAllCardsByBill() throws BillNotFoundException, UserNotFoundException {
        Bill bill = new Bill(1, 1, 0, 1);

        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card());
        cardList.add(new Card());
        Mockito.when(billService.getBillById(1)).thenReturn(bill);
    }

    @Test
    void getAllCardsNull() throws BillNotFoundException {
        Bill bill = new Bill(1, 1, 0, 1);

        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card());
        cardList.add(new Card());
        Mockito.when(billService.getBillById(1)).thenReturn(bill);
    }

    @Test
    void changeCardStatus() {
        Mockito.when(cardDaoImlp.changeCardStatus(1, true)).thenReturn(true);
        assertTrue(cardService.changeCardStatus(1, true));
    }

    @Test
    void changeCardStatusActiveFalse() {
        Mockito.when(cardDaoImlp.changeCardStatus(1, true)).thenReturn(false);
        assertFalse(cardService.changeCardStatus(1, true));
    }

    @Test
    void changeCardStatusNotActive() {
        Mockito.when(cardDaoImlp.changeCardStatus(1, false)).thenReturn(false);
        assertFalse(cardService.changeCardStatus(1, false));
    }

    @Test
    void changeCardStatusNotCorrect() {
        assertFalse(cardService.changeCardStatus(1, true));
}
    }


