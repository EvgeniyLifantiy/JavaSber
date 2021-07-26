package com.BankApi.Dao.Implementation;

import com.BankApi.ConnectInstallService.DBConnector;
import com.BankApi.Dao.Api.CardDao;
import com.BankApi.Entity.Card;
import com.BankApi.SpringRealization.ApplicationContext;
import org.jetbrains.annotations.Nullable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class CardDaoImlp implements CardDao {

    private DBConnector dbConnector= ApplicationContext.getInstance().getBean(DBConnector.class);

    @Override
    public boolean addCard(Card card) {
        try (PreparedStatement preparedStatement=dbConnector.getConnection().prepareStatement(
                "INSERT INTO CARDS (CARD_NUMBER,BILL_ID) VALUES ( ?,?)"
        ))  {
            preparedStatement.setLong(1, card.getCardNumber());
            preparedStatement.setLong(2, card.getBillId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public @Nullable List<Card> getAllCards(long billId) {

        try (PreparedStatement preparedStatement=dbConnector.getConnection().prepareStatement(
                "SELECT * FROM CARDS WHERE BILL_ID = ?"
        )) {
            preparedStatement.setLong(1, billId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Card> cards = new ArrayList<>();
            while (resultSet.next()) {
                Card card = new Card(
                        resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getLong(3),
                        resultSet.getBoolean(4)
                );
                cards.add(card);
            }
            return cards;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public @Nullable Card getCardById(long cardId) {
        try (PreparedStatement preparedStatement=dbConnector.getConnection().prepareStatement(
                "SELECT * FROM CARDS WHERE CARD_NUMBER = ?"
        )) {
            preparedStatement.setLong(1, cardId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Card(
                    resultSet.getLong(1),
                    resultSet.getLong(2),
                    resultSet.getLong(3),
                    resultSet.getBoolean(4)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean changeCardStatus(long cardId, Boolean status) {
        try (PreparedStatement preparedStatement=dbConnector.getConnection().prepareStatement(
                "UPDATE CARDS SET ISACTIVE = ? WHERE CARD_NUMBER = ?"
        )) {
            preparedStatement.setBoolean(1, status);
            preparedStatement.setLong(2, cardId);
            if (preparedStatement.executeUpdate()>0){
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

