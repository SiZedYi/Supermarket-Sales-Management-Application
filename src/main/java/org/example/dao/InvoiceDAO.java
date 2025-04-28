package org.example.dao;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.example.model.*;

import java.util.ArrayList;
import java.util.Date;
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

        String sql = "SELECT i.InvoiceID, c.ContactName, u.Name, i.orderDate " +
                "FROM Invoices i " +
                "JOIN Customers c ON i.CustomerID = c.CustomerID " +
                "JOIN User u ON i.UserID = u.UserID " +
                "ORDER BY i.orderDate DESC";
        Query query = em.createNativeQuery(sql);
        @SuppressWarnings("unchecked")
        List<Object[]> rows = query.getResultList();

        List<Invoice> result = new ArrayList<>();
        System.out.println(result);

        for (Object[] row : rows) {
            Long id = ((Number) row[0]).longValue();
            String contactName = (String) row[1];
            String userName = (String) row[2];
            Date orderDate = (Date) row[3];

            Invoice inv = new Invoice();
            inv.setInvoiceId(id);

            Customer c = new Customer();
            c.setContactName(contactName);
            inv.setCustomer(c);

            User u = new User();
            u.setHoTen(userName);
            inv.setUser(u);

            inv.setOrderDate(orderDate);
            result.add(inv);
        }
        return result;
    }


    public List<InvoiceDetail> findInvoiceDetails(Long invoiceId) {
        String sql = "SELECT d.ProductID, p.ProductName, d.Quantity, d.unitPrice " +
                "FROM InvoiceDetails d " +
                "JOIN Products p ON d.ProductID = p.ProductID " +
                "WHERE d.InvoiceID = ?";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, invoiceId);
        @SuppressWarnings("unchecked")
        List<Object[]> rows = query.getResultList();

        List<InvoiceDetail> result = new ArrayList<>();
        for (Object[] row : rows) {
            Long prodId = ((Number) row[0]).longValue();
            String prodName = (String) row[1];
            Integer quantity = ((Number) row[2]).intValue();
            Double unitPrice = ((Number) row[3]).doubleValue();
            Double discount = 0.0;

            // Map vào InvoiceDetail model cơ bản
            InvoiceDetail detail = new InvoiceDetail();
            Product p = new Product();
            p.setProductId(prodId);
            p.setProductName(prodName);
            detail.setProduct(p);
            detail.setQuantity(quantity);
            detail.setUnitPrice(unitPrice);
            detail.setDiscount(discount);

            result.add(detail);
        }
        return result;
    }


    public boolean saveInvoice(Invoice invoice, List<InvoiceDetail> details) throws Exception {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            // 1) Chèn Invoice
            String insertInvoiceSql =
                    "INSERT INTO Invoices (customerid, orderDate, userid) VALUES (?, ?, ?)";
            Query insertInv = em.createNativeQuery(insertInvoiceSql);
            insertInv.setParameter(1, invoice.getCustomer().getCustomerId());
            insertInv.setParameter(2, invoice.getOrderDate());
            insertInv.setParameter(3, invoice.getUser().getUserId());
            insertInv.executeUpdate(); // dùng executeUpdate cho INSERT

            // 2) Lấy invoiceId mới sinh
            Number lastId = (Number) em.createNativeQuery("SELECT LAST_INSERT_ID()")
                    .getSingleResult();
            Long generatedInvoiceId = lastId.longValue();
            invoice.setInvoiceId(generatedInvoiceId);

            // 3) Chèn từng InvoiceDetail
            String insertDetailSql =
                    "INSERT INTO InvoiceDetails (invoiceid, productid, quantity, unitPrice, discount) VALUES (?, ?, ?, ?, ?)";
            for (InvoiceDetail d : details) {
                Query insertDet = em.createNativeQuery(insertDetailSql);
                insertDet.setParameter(1, generatedInvoiceId);
                insertDet.setParameter(2, d.getProduct().getProductId());
                insertDet.setParameter(3, d.getQuantity());
                insertDet.setParameter(4, d.getUnitPrice());
                insertDet.setParameter(5, d.getDiscount());
                insertDet.executeUpdate();
            }

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new Exception("Failed to save invoice: " + e.getMessage(), e);
        }
    }




}