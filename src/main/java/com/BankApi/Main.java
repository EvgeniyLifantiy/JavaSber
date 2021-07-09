package com.BankApi;

import com.BankApi.Controller.BillController;
import com.BankApi.Controller.CardController;
import com.BankApi.Controller.OperationController;
import com.BankApi.Controller.UserController;
import com.BankApi.Dao.Implementation.OperationDao;
import com.BankApi.SpringRealization.ApplicationContext;
import com.BankApi.Tools.AdminAuthenticator;
import com.BankApi.Tools.UserAuthenticator;
import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

        public static void main(String[] args) throws Exception {

            UserController userController = ApplicationContext.getInstance().getBean(UserController.class);
            BillController billController = ApplicationContext.getInstance().getBean(BillController.class);
            CardController cardController=ApplicationContext.getInstance().getBean(CardController.class);
            OperationController operationController=ApplicationContext.getInstance().getBean(OperationController.class);
            BasicAuthenticator bankAuthenticator=ApplicationContext.getInstance().getBean(UserAuthenticator.class);
            BasicAuthenticator adminAuthenticator=ApplicationContext.getInstance().getBean(AdminAuthenticator.class);

            HttpServer server = null;
            try {
                server = HttpServer.create(new InetSocketAddress(8080), 0);
            } catch (IOException e) {
                System.out.println("IO error");
            }


            server.createContext("/bills", billController).setAuthenticator(bankAuthenticator);
            server.createContext("/balance", billController).setAuthenticator(bankAuthenticator);
            server.createContext("/cards", cardController).setAuthenticator(bankAuthenticator);

            //add or change context
            server.createContext("/api/user", userController).setAuthenticator(adminAuthenticator);
            server.createContext("/api/bills", billController).setAuthenticator(adminAuthenticator);
            server.createContext("/api/cards", cardController).setAuthenticator(adminAuthenticator);
            server.createContext("/api/operation", operationController).setAuthenticator(adminAuthenticator);




            server.start();

        }

}
