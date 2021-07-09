package com.BankApi.Dao.Api;

import com.BankApi.Entity.User;
import org.jetbrains.annotations.Nullable;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public interface UserDao {

    /**
     * Add user to database
     * @param user- User data
     * @return true, if adding successful. Else - false
     */
     boolean addUser(User user);

    /**
     * Get user from database by phone
     * @param phone - entered phone number
     * @return User, if was found. Else - null
     */
     @Nullable
     User getUserByPhone(String phone);

}
