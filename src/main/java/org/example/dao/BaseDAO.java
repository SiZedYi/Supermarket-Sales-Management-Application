package org.example.dao;

// BaseDAO.java
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class BaseDAO {
    public EntityManager em = Persistence.createEntityManagerFactory("JPA_ORM_MySQL").createEntityManager();
}