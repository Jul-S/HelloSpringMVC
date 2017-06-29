package com.javarush.intern.service;

import com.javarush.intern.dao.UserDao;
import com.javarush.intern.model.User;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by yul on 24.06.17.
 */
@Service
public class UserServiceImpl implements UserService {
private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
this.userDao.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
this.userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
this.userDao.removeUser(id);
    }

    @Override
    @Transactional
    public User getUserbyId(int id) {
        return this.userDao.getUserbyId(id);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return this.userDao.getUsers();
    }

    @Override
    @Transactional
    public List<User> getUserbyName(String name) {
        return this.userDao.getUserbyName(name);
    }

}
