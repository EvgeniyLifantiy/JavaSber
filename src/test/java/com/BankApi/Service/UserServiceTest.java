package com.BankApi.Service;

import com.BankApi.Dao.Api.UserDao;
import com.BankApi.Entity.User;
import com.BankApi.Exception.UserNotFoundException;
import com.BankApi.Role;
import com.BankApi.Service.Implementation.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class UserServiceTest {
    @Mock
    UserDao userdao = Mockito.mock(UserDao.class);
    @InjectMocks
    UserService userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addUser()  {
        User u=new User("1","2","2","2",Role.ADMIN);
        Mockito.when(userdao.addUser(u)).thenReturn(false);
        assertFalse(userService.addUser(new User("1","2","2","2",Role.ADMIN)));
    }

    @Test
    void getUserByLogin() throws UserNotFoundException {
        User u=new User("1","2","2","2",Role.ADMIN);
        Mockito.when(userdao.getUserByPhone("1")).thenReturn(u);
        assertEquals(u, userService.getUserByPhone("1"));
    }

    @Test
    void getUserByLoginNull() {
        Mockito.when(userdao.getUserByPhone("1")).thenReturn(null);
        assertThrows(UserNotFoundException.class, () -> userService.getUserByPhone("1"));
    }
}
