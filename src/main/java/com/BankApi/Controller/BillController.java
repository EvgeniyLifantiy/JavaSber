package com.BankApi.Controller;

import com.BankApi.Entity.Bill;
import com.BankApi.Entity.Card;
import com.BankApi.Exception.BillNotFoundException;
import com.BankApi.Exception.UserNotFoundException;
import com.BankApi.SpringRealization.ApplicationContext;
import com.BankApi.Tools.Mapper;
import com.BankApi.Service.Implementation.BillService;
import com.BankApi.Service.Implementation.UserService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class BillController implements HttpHandler {
    private BillService billService = ApplicationContext.getInstance().getBean(BillService.class);
    private UserService userService =  ApplicationContext.getInstance().getBean(UserService.class);
    private Mapper mapper= ApplicationContext.getInstance().getBean(Mapper.class);

    public BillController() {
    }



    public BillController(BillService billService, UserService userService) {
        this.billService = billService;
        this.userService = userService;
    }

    @Override
    public void handle(HttpExchange exchange) {
        OutputStream outputStream=null;
        try {

            if (exchange.getRequestMethod().equals("GET")) {
                    Map<String, String> params = mapper.getParamValue(exchange.getRequestURI().getRawQuery());
                    if (params.get("id") != null) {

                        //If we have id field , then get Bill by this id
                        try {
                            Bill bill = billService.getBillById(Long.parseLong(params.get("id")));
                            exchange.sendResponseHeaders(200, mapper.EntityToJson(bill).getBytes().length);
                             outputStream = exchange.getResponseBody();
                            outputStream.write(mapper.EntityToJson(bill).getBytes());

                        }
                            catch (BillNotFoundException e) {
                                e.printStackTrace();
                            exchange.sendResponseHeaders(404, -1);
                        }
                    } else if (params.get("billId") != null) {

                        // /balance?billId=
                        // if exist billId-field, then get balance
                        // key - phone
                        try {

                            String user=exchange.getPrincipal().getUsername();
                            try{
                                user=userService.getUserByPhone(exchange.getRequestHeaders().getFirst("phone"))
                                        .getPhone();
                            }catch (Exception e){
                                System.out.println("Такого пользователя нет, будет выдан ,баланс текущего пользователя");
                            }
                            double balance = billService.getBalanceOfBill(Long.parseLong(params.get("billId")),
                                    user);
                            exchange.sendResponseHeaders(200,
                                    mapper.EntityToJson(balance).getBytes().length);
                             outputStream = exchange.getResponseBody();
                            outputStream.write(mapper.EntityToJson(balance).getBytes());

                        } catch (BillNotFoundException e) {
                            e.printStackTrace();
                            exchange.sendResponseHeaders(404, -1);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }


                    } else if (params.isEmpty()) {
                        //Else get all Bills by UserID
                        String user=exchange.getPrincipal().getUsername();
                        try{
                            user=userService.getUserByPhone(mapper.JsonToEntity(exchange,String.class)).getPhone();
                        }catch (Exception e){
                            System.out.println("Такого пользователя нет, будет выдан список текущего пользователя");
                        }

                        List<Bill> billList =
                                billService.getAllBillsByUserId
                                        (userService.getUserByPhone(user)
                                                .getId());
                        exchange.sendResponseHeaders(200, mapper.EntityListToJson(billList).getBytes().length);
                         outputStream = exchange.getResponseBody();
                        outputStream.write(mapper.EntityListToJson(billList).getBytes());
                    } else {
                        exchange.sendResponseHeaders(404, -1);
                    }
            }





            else if (exchange.getRequestMethod().equals("POST")) {
                //Add new Bill
                        if (billService.addBill(mapper.JsonToEntity(exchange,long.class))) {
                            String r="Successful adding";
                            exchange.sendResponseHeaders(200,r.length());
                             outputStream = exchange.getResponseBody();
                            outputStream.write(r.getBytes());
                        } else {
                            exchange.sendResponseHeaders(406, -1);
                        }

            }

            else {
                exchange.sendResponseHeaders(405, -1);
            }
            outputStream.flush();
            outputStream.close();
            exchange.close();
        } catch (IOException e) {
            e.printStackTrace();

        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

    }
}
