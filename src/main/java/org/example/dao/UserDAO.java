package org.example.dao;

// UserDAOImpl.java
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
}