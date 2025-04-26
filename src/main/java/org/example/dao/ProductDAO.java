package org.example.dao;


// ProductDAOImpl.java
import java.sql.*;
import java.util.*;
import org.example.model.*;

// ProductDAO.java
public class ProductDAO extends BaseDAO {
    public void save(Product product) {
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        Product p = em.find(Product.class, id);
        if (p != null) em.remove(p);
        em.getTransaction().commit();
    }

    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }
}