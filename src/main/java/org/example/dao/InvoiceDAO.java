package org.example.dao;

import jakarta.persistence.EntityTransaction;
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

    public void saveInvoice(Invoice invoice, List<InvoiceDetail> details) throws Exception {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(invoice);
            for (InvoiceDetail detail : details) {
                detail.setInvoice(invoice); // Gán invoice cho detail trước khi persist
                em.persist(detail);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new Exception("Failed to save invoice: " + e.getMessage(), e);
        }
    }
}