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


    /**
     * Add card to database
     * @param card Card data
     * @return true, if adding successful. Else - false
     */
    boolean addCard(Card card);

    /**
     *
     * @param cardId id of Card
     * @param status true- make card active,else - inactive
     * @return true, if adding successful. Else - false
     */
    boolean changeCardStatus(long cardId, Boolean status);

    /**
     * Get all cards of Bill by billId
     * @param billId id of Bill
     * @return list of Cards if found. Else - null.
     */
    @Nullable
    List<Card> getAllCards(long billId);

    /**
     * Get Card by Id
     * @param cardId id of Card
     * @return Card, if was found. Else - null
     */
    @Nullable
    Card getCardById(long cardId);



}
