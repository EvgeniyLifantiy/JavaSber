package com.BankApi.Controller;

import com.BankApi.Entity.User;
import com.BankApi.SpringRealization.ApplicationContext;
import com.BankApi.Tools.Mapper;
import com.BankApi.Service.Implementation.UserService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class UserController implements HttpHandler {

    private Mapper mapper= ApplicationContext.getInstance().getBean(Mapper.class);;
    private UserService userService=ApplicationContext.getInstance().getBean(UserService.class);;

//
//    new User("89286259097","admin","Evgeniy",
//                     "Lifantiy", Role.ADMIN))
    @Override
    public void handle(HttpExchange exchange)   {
        OutputStream outputStream=null;
        try {
            if (exchange.getRequestMethod().equals("POST")) {
                //add new user
                        User user = mapper.JsonToEntity(exchange,User.class);
                        if (userService.addUser(user)) {
                            String r="Successful adding";
                            exchange.sendResponseHeaders(200,r.length());
                             outputStream = exchange.getResponseBody();
                            outputStream.write(r.getBytes());
                        } else {
                            exchange.sendResponseHeaders(406, -1);
                        }
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
            outputStream.flush();
            outputStream.close();
            exchange.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("User not found");
        }
    }


}
