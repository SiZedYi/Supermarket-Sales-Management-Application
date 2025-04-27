package org.example.dao;

import org.example.model.Employee;
import org.example.model.User;

import java.util.List;

// EmployeeDAO.java
public class EmployeeDAO extends BaseDAO {
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public void delete(String userId) {
        em.getTransaction().begin();
        User user = em.find(User.class, userId);
        System.out.println("Deleting user: " + userId);
        if (user != null) {
            em.remove(user);
        }
        em.getTransaction().commit();
    }
}