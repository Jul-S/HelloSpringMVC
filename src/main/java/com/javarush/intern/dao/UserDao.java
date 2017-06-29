package com.javarush.intern.dao;

import com.javarush.intern.model.User;

import java.util.List;

/**
 * Created by yul on 24.06.17.
 */
public interface UserDao {
    public void addUser(User user);

    public void updateUser(User user);

    public void removeUser(int id);

    public User getUserbyId(int id);

    public List<User> getUsers();

    List<User> getUserbyName(String name);
}
