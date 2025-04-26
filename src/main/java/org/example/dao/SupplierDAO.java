package org.example.dao;

import org.example.model.Supplier;

// SupplierDAO.java
public class SupplierDAO extends BaseDAO {
    public Supplier find(Long id) {
        return em.find(Supplier.class, id);
    }
}