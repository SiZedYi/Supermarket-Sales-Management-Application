package org.example.dao;

import org.example.model.Customer;
import org.example.model.Supplier;

import java.util.List;

// CustomerDAO.java
public class CustomerDAO extends BaseDAO {
    public Customer find(String id) {
        return em.find(Customer.class, id);
    }
    public List<Customer> findAll() {
        return em.createQuery("SELECT cs from Customer cs", Customer.class).getResultList();
    }
}