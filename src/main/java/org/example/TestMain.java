package org.example;


// TestMain.java

import org.example.dao.*;
import org.example.model.*;
import org.example.rmi.SupermarketService;
import org.example.rmi.SupermarketServiceImpl;

import java.rmi.RemoteException;
import java.util.Date;

public class TestMain {
    public static void main(String[] args) throws RemoteException {
        AccountDAO accountDAO = new AccountDAO();
        UserDAO userDAO = new UserDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        ProductDAO productDAO = new ProductDAO();
        InvoiceDAO invoiceDAO = new InvoiceDAO();
        SaleAgentDAO saleAgentDAO = new SaleAgentDAO();

        CustomerDAO customerDAO = new CustomerDAO();
        SupermarketServiceImpl service = new SupermarketServiceImpl();
        Long id = 1L;
//        System.out.println(productDAO.findByProductId(id));

        System.out.println(service.getAllInvoices());
    }
}
