package org.example.dao;

import org.example.model.Product;
import org.example.model.Supplier;

import java.util.List;

// SupplierDAO.java
public class SupplierDAO extends BaseDAO {
    public Supplier find(Long id) {
        return em.find(Supplier.class, id);
    }

    public List<Supplier> findAll() {
        return em.createQuery("SELECT s FROM Supplier s", Supplier.class).getResultList();
    }
}