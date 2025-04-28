package org.example.dao;

import org.example.model.Invoice;
import org.example.model.SalesAgent;

// SaleAgentDAO.java
public class SaleAgentDAO extends BaseDAO {
    public SalesAgent findByUserId(String userId) {
        return em.createQuery("SELECT s FROM SalesAgent s WHERE s.user.userId = :uid", SalesAgent.class)
                .setParameter("uid", userId)
                .getSingleResult();
    }

    public void payInvoice(Invoice invoice) {
        em.getTransaction().begin();
        em.persist(invoice);
        em.getTransaction().commit();
    }
}
