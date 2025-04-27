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

        CustomerDAO customerDAO = new CustomerDAO();

        System.out.println(customerDAO.findAll());
    }
}
