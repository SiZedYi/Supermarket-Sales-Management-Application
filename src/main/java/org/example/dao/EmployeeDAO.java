package org.example.dao;

import org.example.model.Employee;

import java.util.List;

// EmployeeDAO.java
public class EmployeeDAO extends BaseDAO {
    public List<Employee> findAll() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }
}