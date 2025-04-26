package org.example.dao;

import org.example.model.Invoice;
import org.example.model.InvoiceDetail;
import org.example.model.SaleAgent;

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

    public List<InvoiceDetail> findInvoiceDetails(Long invoiceId) {
        return em.createQuery("SELECT s FROM InvoiceDetails s WHERE s.InvoiceID = :invoiceId", InvoiceDetail.class)
                .setParameter("invoiceId", invoiceId)
                .getResultList();
    }
}