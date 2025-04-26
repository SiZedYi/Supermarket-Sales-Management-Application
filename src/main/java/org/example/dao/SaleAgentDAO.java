package org.example.dao;

import org.example.model.Invoice;
import org.example.model.SaleAgent;

// SaleAgentDAO.java
public class SaleAgentDAO extends BaseDAO {
    public SaleAgent findByUserId(String userId) {
        return em.createQuery("SELECT s FROM SaleAgent s WHERE s.user.userId = :uid", SaleAgent.class)
                .setParameter("uid", userId)
                .getSingleResult();
    }

    public void payInvoice(Invoice invoice) {
        em.getTransaction().begin();
        em.persist(invoice);
        em.getTransaction().commit();
    }
}
