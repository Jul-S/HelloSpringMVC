package com.javarush.intern.dao;

import com.javarush.intern.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yul on 24.06.17.
 */
@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User added: " + user);
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User updated: " + user);
    }

    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        if (user != null) {
            session.delete(user);
        }
        logger.info("User removed: " + user);
    }

    @Override
    public User getUserbyId(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        logger.info("User loaded: " + user);
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();
        for (User user : userList) {
            logger.info("User list: " + user);
        }
        return userList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getUserbyName(String searchName) {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User where name =:name")
                .setParameter("name", searchName)
                .list();
        for (User user : userList) {
            logger.info("User list: " + user);
        }
        return userList;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
