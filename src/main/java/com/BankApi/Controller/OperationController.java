package com.BankApi.Controller;

import com.BankApi.Entity.Bill;
import com.BankApi.Entity.Operation;
import com.BankApi.Entity.User;
import com.BankApi.Exception.BillNotFoundException;
import com.BankApi.Service.Implementation.OperationService;
import com.BankApi.SpringRealization.ApplicationContext;
import com.BankApi.Tools.Mapper;
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
public class OperationController implements HttpHandler {

    private Mapper mapper= ApplicationContext.getInstance().getBean(Mapper.class);
    private OperationService operationService=ApplicationContext.getInstance().getBean(OperationService.class);;

    @Override
    public void handle(HttpExchange exchange) {
        OutputStream outputStream=null;
        try {
            if (exchange.getRequestMethod().equals("GET")) {
                Map<String, String> params = mapper.getParamValue(exchange.getRequestURI().getRawQuery());
                if (params.get("id") != null) {

                    //If we have id field , then get Operation by this id
                    try {
                        Operation operation = operationService.getOperation(Long.parseLong(params.get("id")));
                        exchange.sendResponseHeaders(200, mapper.EntityToJson(operation).getBytes().length);
                        outputStream = exchange.getResponseBody();
                        outputStream.write(mapper.EntityToJson(operation).getBytes());

                    }
                    //a more specific exception needs to be highlighted
                    catch (Exception e) {
                        e.printStackTrace();
                        exchange.sendResponseHeaders(404, -1);
                    }

                } else if (params.isEmpty()) {
                    //Else get all Operations
                    List<Operation> operationList =
                            operationService.getAllOperation();
                    exchange.sendResponseHeaders(200, mapper.EntityListToJson(operationList).getBytes().length);
                    outputStream = exchange.getResponseBody();
                    outputStream.write(mapper.EntityListToJson(operationList).getBytes());
                } else {
                    exchange.sendResponseHeaders(404, -1);
                }
            }




            //add operation
            else if (exchange.getRequestMethod().equals("POST")) {
                Operation operation = mapper.JsonToEntity(exchange,Operation.class);
               if (operationService.addOperation(operation.getsenderBill(),
                       operation.getRecipientBill(),
                       operation.getSum())) {
                   String r = "Successful adding";
                   exchange.sendResponseHeaders(200, r.length());
                   outputStream = exchange.getResponseBody();
                   outputStream.write(r.getBytes());
               }else{
                   exchange.sendResponseHeaders(405, -1);
               }
            }


            //change status
            else if (exchange.getRequestMethod().equals("PUT")) {

                long id=mapper.JsonToEntity(exchange,long.class);
                if(operationService.submitOperation(id)) {
                    String r = "Successful change";
                    exchange.sendResponseHeaders(200, r.length());
                    outputStream = exchange.getResponseBody();
                    outputStream.write(r.getBytes());
                }else {
                    exchange.sendResponseHeaders(412, -1);

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

        }

    }
}
