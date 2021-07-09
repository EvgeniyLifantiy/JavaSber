package com.BankApi.Tools;

import com.BankApi.Dao.Api.BillDao;
import com.BankApi.Dao.Api.UserDao;
import com.BankApi.Dao.Implementation.UserDaoImpl;
import com.BankApi.Entity.User;
import com.BankApi.SpringRealization.ApplicationContext;
import com.sun.net.httpserver.BasicAuthenticator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class AdminAuthenticator extends BasicAuthenticator {
    UserDao userDao= ApplicationContext.getInstance().getBean(UserDao.class);

    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    public AdminAuthenticator() {
        super("realm");
    }
    @Override
    public boolean checkCredentials(String username, String password) {
        User user= userDao.getUserByPhone(username);
        if (bCryptPasswordEncoder.matches(password,user.getPassword())){
            if(user.getRole().name().equals("ADMIN")){
                System.out.println("is Admin");
                return true;
            }

        }
        System.out.println("Not Enough rights");
        return false;

    }
}
