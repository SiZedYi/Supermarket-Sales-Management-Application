package org.example.dao;

// UserDAOImpl.java
import java.rmi.RemoteException;
import java.sql.*;
import java.util.*;
import org.example.model.*;

// UserDAO.java
public class UserDAO extends BaseDAO {
    public User findById(String userId) {
        return em.find(User.class, userId);
    }

    public void update(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public boolean addUser(User user, String password) {
        try {
            em.getTransaction().begin();
            em.persist(user);

            Account acc = new Account(user.getUserId(), password);
            em.persist(acc);

            em.getTransaction().commit();
            return true;
        } catch(Exception e) {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}