package org.example.dao;

// CategoryDAOImpl.java
import org.example.model.Category;
import org.example.model.Supplier;

import java.sql.*;
import java.util.*;

// CategoryDAO.java
public class CategoryDAO extends BaseDAO {
    public void save(Category category) {
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        Category c = em.find(Category.class, id);
        if (c != null) em.remove(c);
        em.getTransaction().commit();
    }

    public List<Category> findAll() {
        return em.createQuery("SELECT s FROM Category s", Category.class).getResultList();
    }
}
