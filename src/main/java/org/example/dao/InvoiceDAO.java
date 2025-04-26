package org.example.dao;

import org.example.model.Invoice;
import org.example.model.InvoiceDetail;

import java.util.List;

public class InvoiceDAO extends BaseDAO {
    public void save(Invoice invoice) {
        em.getTransaction().begin();
        em.persist(invoice);
        em.getTransaction().commit();
    }

    public void cancelInvoiceDetail(Long detailId) {
        em.getTransaction().begin();
        InvoiceDetail detail = em.find(InvoiceDetail.class, detailId);
        if (detail != null) em.remove(detail);
        em.getTransaction().commit();
    }

    public List<Invoice> findAll() {
        return em.createQuery("SELECT i FROM Invoice i", Invoice.class).getResultList();
    }
}