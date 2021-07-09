package com.BankApi.Dao.Api;

import com.BankApi.Entity.Card;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */

public interface CardDao {

    boolean addCard(Card card);

    boolean changeCardStatus(long cardId, Boolean status);

    @Nullable
    List<Card> getAllCards(long billId);

    @Nullable
    Card getCardById(long cardId);



}
