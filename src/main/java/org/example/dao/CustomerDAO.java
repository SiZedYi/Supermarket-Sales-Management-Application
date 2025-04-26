package org.example.dao;

import org.example.model.Customer;

// CustomerDAO.java
public class CustomerDAO extends BaseDAO {
    public Customer find(Long id) {
        return em.find(Customer.class, id);
    }
}