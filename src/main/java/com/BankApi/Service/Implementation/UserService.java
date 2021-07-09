package com.BankApi.Service.Implementation;

import com.BankApi.Dao.Api.BillDao;
import com.BankApi.Dao.Api.UserDao;
import com.BankApi.Dao.Implementation.UserDaoImpl;
import com.BankApi.Entity.User;
import com.BankApi.Exception.UserNotFoundException;
import com.BankApi.Role;
import com.BankApi.SpringRealization.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class UserService   {

    private UserDao userDao = ApplicationContext.getInstance().getBean(UserDao.class);

    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    public UserService() {
    }

    public UserService(UserDao userRepository) {
        this.userDao = userRepository;
    }


    public boolean addUser(User user) {
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.addUser(user);
    }



    public User getUserByPhone(String phone) throws UserNotFoundException {
        User user = userDao.getUserByPhone(phone);
        if (user!=null) {
            return user;
        }
        throw new UserNotFoundException("User with such data is not exist. Check the correctness of the data entered");
    }
}
