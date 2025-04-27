
// SupermarketService.java
package org.example.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import org.example.model.*;

public interface SupermarketService extends Remote {
    // Sales Agent
    Account login(String userId, String password) throws RemoteException;
    User viewUserInfo(String userId) throws RemoteException;
    void changePassword(String userId, String newPassword) throws RemoteException;
    // Manager
    void addCategory(Category category) throws RemoteException;
    void deleteCategory(Long categoryId) throws RemoteException;
    void addProduct(Product product) throws RemoteException;
    void deleteProduct(Long productId) throws RemoteException;
    void cancelInvoiceDetail(Long detailId) throws RemoteException;
    List<Employee> listEmployees() throws RemoteException;
    void addEmployee(Employee employee) throws RemoteException;
    void deleteEmployee(String userId) throws RemoteException;
    List<Product> getAllProducts() throws RemoteException;
    List<Supplier> listSuppliers() throws RemoteException;
    List<Category> listCategories() throws RemoteException;
    List<InvoiceDetail> listInvoiceDetails(Long invoiceId) throws RemoteException;
    boolean addUser(User user, String password) throws RemoteException;

    boolean createInvoice(Invoice invoice, List<InvoiceDetail> details) throws RemoteException;

    List<Invoice> getAllInvoices() throws RemoteException;
    List<Customer> getAllCustomers() throws RemoteException;

//    boolean deleteInvoiceDetail(String invoiceId, String productId) throws RemoteException;

    List<InvoiceDetail> getInvoiceDetails(Long invoiceId) throws RemoteException;
}