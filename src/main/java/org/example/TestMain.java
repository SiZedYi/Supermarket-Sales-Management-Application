package org.example;


// TestMain.java

import org.example.dao.*;
import org.example.model.*;

import java.util.Date;

public class TestMain {
    public static void main(String[] args) {
        AccountDAO accountDAO = new AccountDAO();
        UserDAO userDAO = new UserDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        ProductDAO productDAO = new ProductDAO();
        InvoiceDAO invoiceDAO = new InvoiceDAO();
        SaleAgentDAO saleAgentDAO = new SaleAgentDAO();

        // Login - Sales Agent
//        Account acc = accountDAO.login("1", "matkhau1");
//        if (acc != null) {
//            System.out.println("Sales Agent login success");
//            accountDAO.updatePassword("1", "newpass123");
//            System.out.println("Password updated");
//        } else {
//            System.out.println("Login failed");
//        }

        // View Sales Agent Info
//        User agentUser = userDAO.findById("1");
//        System.out.println("Agent Info: " + agentUser.getHoTen());
//
        // Pay Invoice
//        CustomerDAO customerDAO = new CustomerDAO();
//        Customer customer = customerDAO.find(1L);
//        EmployeeDAO employeeDAO = new EmployeeDAO();
//        Employee employee = employeeDAO.findAll().getFirst();
//
//        Invoice invoice = new Invoice();
//        invoice.setCustomer(customer);
//        invoice.setEmployee(employee);
//        invoice.setOrderDate(new Date());
//        invoiceDAO.save(invoice);
//        System.out.println("Invoice paid");
//
//        // Manager Login and Change Password
//        Account managerAcc = accountDAO.login("manager1", "adminpass");
//        if (managerAcc != null) {
//            accountDAO.updatePassword("manager1", "adminnewpass");
//            System.out.println("Manager password updated");
//        }
//
//        // Manage Categories
        Category cat = new Category();
        cat.setCategoryName("Beverages");
        cat.setDescription("All kinds of drinks");
        categoryDAO.save(cat);
        System.out.println("Category added");
//
//        // Manage Products
//        Product prod = new Product();
//        prod.setProductName("Coca Cola");
//        prod.setUnitPrice(10.5);
//        prod.setUnitsInStock(50);
//        prod.setCategory(cat);
//        prod.setSupplier(new SupplierDAO().find(1L));
//        productDAO.save(prod);
//        System.out.println("Product added");
//
//        // Cancel Invoice Detail (assume detail ID = 1)
//        invoiceDAO.cancelInvoiceDetail(1L);
//        System.out.println("Invoice detail canceled");
    }
}
