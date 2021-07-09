package com.BankApi.Controllers;

import com.BankApi.Controller.BillController;
import com.BankApi.Entity.Bill;
import com.BankApi.Entity.User;
import com.BankApi.Exception.UserNotFoundException;
import com.BankApi.Role;
import com.BankApi.Service.Implementation.BillService;
import com.BankApi.Service.Implementation.UserService;
import com.BankApi.Tools.AdminAuthenticator;
import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class BillControllerTest {

    AdminAuthenticator authenticator=new AdminAuthenticator();
    BillController billController=new BillController();
    @Mock
    private final BillService billService = Mockito.mock(BillService.class);
    @Mock
    private final UserService userService = Mockito.mock(UserService.class);
    private HttpServer server;

    @BeforeEach
    void setUp() throws IOException {

        server = HttpServer.create(new InetSocketAddress(8080), 0);
        assert server != null;
        server.createContext("/api/test/bills",billController).setAuthenticator(authenticator);
        server.start();
    }

    @AfterEach
    void shutDown() {
        server.stop(0);
    }

    @Test
    void handleEmpty() throws IOException, UserNotFoundException {

    }
}
