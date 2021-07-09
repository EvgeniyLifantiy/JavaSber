package com.BankApi.Dao.Api;

import com.BankApi.Entity.User;
import org.jetbrains.annotations.Nullable;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public interface UserDao {


     boolean addUser(User user);


     @Nullable
     User getUserByPhone(String phone);

}
