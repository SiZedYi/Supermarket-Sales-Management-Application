package org.example.dao;

import org.example.model.Account;

// AccountDAO.java
public class AccountDAO extends BaseDAO {
    public Account login(String userId, String password) {
        try {
            return em.createQuery("SELECT a FROM Account a WHERE a.userId = :uid AND a.password = :pw", Account.class)
                    .setParameter("uid", userId)
                    .setParameter("pw", password)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void updatePassword(String userId, String newPassword) {
        em.getTransaction().begin();
        Account acc = em.find(Account.class, userId);
        if (acc != null) {
            acc.setPassword(newPassword);
        }
        em.getTransaction().commit();
    }
}