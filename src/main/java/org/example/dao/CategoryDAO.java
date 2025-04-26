package org.example.dao;

// CategoryDAOImpl.java
import org.example.model.Category;

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
}
