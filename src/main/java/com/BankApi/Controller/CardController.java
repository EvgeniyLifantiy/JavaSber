package com.BankApi.Controller;

import com.BankApi.Entity.Card;
import com.BankApi.Exception.BillNotFoundException;
import com.BankApi.Exception.CardNotFoundException;
import com.BankApi.SpringRealization.ApplicationContext;
import com.BankApi.Tools.Mapper;
import com.BankApi.Service.Implementation.CardService;
import com.BankApi.Service.Implementation.UserService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class CardController implements HttpHandler {
    private UserService userServiceImpl = ApplicationContext.getInstance().getBean(UserService.class);
    private CardService cardServiceImpl = ApplicationContext.getInstance().getBean(CardService.class);
    private Mapper mapper = ApplicationContext.getInstance().getBean(Mapper.class);

    public CardController() {
    }

    public CardController(UserService userServiceImpl, CardService cardServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.cardServiceImpl = cardServiceImpl;
    }

    @Override
    public void handle(HttpExchange exchange) {
        OutputStream outputStream=null;
        try {
            if (exchange.getRequestMethod().equals("GET")) {
                    Map<String, String> requestQuery = mapper.getParamValue(exchange.getRequestURI().getRawQuery());
                    if (requestQuery.get("billId") != null) {
                        //If exist billId- get all cards by this Id

                        try {
                            List<Card> cardList = cardServiceImpl.getAllCardsByBill(
                                    Long.parseLong(requestQuery.get("billId")));
                            if (!cardList.isEmpty()) {
                                exchange.sendResponseHeaders(200,
                                        mapper.EntityListToJson(cardList).getBytes().length);
                                 outputStream = exchange.getResponseBody();
                                outputStream.write(mapper.EntityListToJson(cardList).getBytes());

                            } else {
                                exchange.sendResponseHeaders(404, -1);
                            }
                        }

                        catch (BillNotFoundException e) {
                            System.out.println("Bill not found");
                            exchange.sendResponseHeaders(404, -1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (requestQuery.get("id") != null) {
                        //If not exist billId, then get cardById

                        try {
                            Card card = cardServiceImpl.getCardById(Long.parseLong(requestQuery.get("id")));
                            byte[] cardJson=mapper.EntityToJson(card).getBytes();
                            exchange.sendResponseHeaders(200, cardJson.length);
                             outputStream = exchange.getResponseBody();
                            outputStream.write(cardJson);

                        } catch (CardNotFoundException e) {
                            System.out.println("Card not found");
                            exchange.sendResponseHeaders(404, -1);
                        }
                    } else {
                        exchange.sendResponseHeaders(404, -1);
                    }
                    exchange.close();

            }






            else if (exchange.getRequestMethod().equals("POST")) {

                        boolean status=cardServiceImpl.addCard(Long.parseLong(
                                exchange.getRequestHeaders().getFirst("billId")));
                        if (status) {
                            String r="Successful adding";
                            exchange.sendResponseHeaders(200,r.length());
                             outputStream = exchange.getResponseBody();
                            outputStream.write(r.getBytes());

                        } else {
                            exchange.sendResponseHeaders(406, -1);
                        }

            }







            else if (exchange.getRequestMethod().equals("PUT") ) {

                if (exchange.getRequestHeaders().getFirst("billId") != null) {
                        long id=Long.parseLong(exchange.getRequestHeaders().getFirst("billId"));

                        if (cardServiceImpl.changeCardStatus
                                (id, !cardServiceImpl.getCardById(id).getStatus()) //reverse current status
                        ) {
                            String r="Successful edit";
                            exchange.sendResponseHeaders(200,r.length());
                             outputStream = exchange.getResponseBody();
                            outputStream.write(r.getBytes());

                        } else {
                            exchange.sendResponseHeaders(406, -1);
                        }
                    } else {
                        exchange.sendResponseHeaders(404, -1);
                    }
            }





            else {
                exchange.sendResponseHeaders(405, -1);
            }
            outputStream.flush();
            outputStream.close();
            exchange.close();
        } catch (IOException | BillNotFoundException e) {
            System.out.println("IO error");
        } catch (CardNotFoundException e) {
            e.printStackTrace();
        }

    }
}
