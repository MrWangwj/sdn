package com.sdn.service;

import com.sdn.model.User;
import com.sdn.dao.UserDao;

import java.sql.SQLException;

public class UserService {
    public User login(User user) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.login(user);
    }
}
