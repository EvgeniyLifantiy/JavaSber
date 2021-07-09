package com.BankApi.Tools;

import com.BankApi.Dao.Api.BillDao;
import com.BankApi.Dao.Api.UserDao;
import com.BankApi.Dao.Implementation.UserDaoImpl;
import com.BankApi.SpringRealization.ApplicationContext;
import com.sun.net.httpserver.BasicAuthenticator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class UserAuthenticator extends BasicAuthenticator {

    UserDao userDao= ApplicationContext.getInstance().getBean(UserDao.class);

    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    public UserAuthenticator() {
        super("realm");
    }
    @Override
    public boolean checkCredentials(String username, String password) {

        if (bCryptPasswordEncoder.matches(password,userDao.getUserByPhone(username).getPassword())){
            System.out.println("YESS");
            return true;
        }
        System.out.println("NOO");
        return false;

    }


}
