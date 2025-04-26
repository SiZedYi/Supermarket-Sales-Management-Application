package org.example.rmi;

// SupermarketServiceImpl.java

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import org.example.rmi.SupermarketService;
import org.example.model.*;
import org.example.dao.*;

public class SupermarketServiceImpl extends UnicastRemoteObject implements SupermarketService {
    private AccountDAO accountDAO = new AccountDAO();
    private UserDAO userDAO = new UserDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();
    private ProductDAO productDAO = new ProductDAO();
    private InvoiceDAO invoiceDAO = new InvoiceDAO();
    private SaleAgentDAO saleAgentDAO = new SaleAgentDAO();
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public SupermarketServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public Account login(String userId, String password) throws RemoteException {
        return accountDAO.login(userId, password);
    }

    @Override
    public User viewUserInfo(String userId) throws RemoteException {
        return userDAO.findById(userId);
    }

    @Override
    public void changePassword(String userId, String newPassword) throws RemoteException {
        accountDAO.updatePassword(userId, newPassword);
    }

    @Override
    public void payInvoice(Long customerId, Long employeeId, Date orderDate) throws RemoteException {
        Customer customer = new CustomerDAO().find(customerId);
        Employee employee = employeeDAO.findAll().stream()
                .filter(e -> e.getUserId().equals(employeeId.toString()))
                .findFirst().orElse(null);
        if (customer != null && employee != null) {
            Invoice invoice = new Invoice();
            invoice.setCustomer(customer);
            invoice.setEmployee(employee);
            invoice.setOrderDate(orderDate);
            invoiceDAO.save(invoice);
        }
    }

    @Override
    public void addCategory(Category category) throws RemoteException {
        categoryDAO.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) throws RemoteException {
        categoryDAO.delete(categoryId);
    }

    @Override
    public void addProduct(Product product) throws RemoteException {
        productDAO.save(product);
    }

    @Override
    public void deleteProduct(Long productId) throws RemoteException {
        productDAO.delete(productId);
    }

    @Override
    public void cancelInvoiceDetail(Long detailId) throws RemoteException {
        invoiceDAO.cancelInvoiceDetail(detailId);
    }

    @Override
    public List<Employee> listEmployees() throws RemoteException {
        return employeeDAO.findAll();
    }

    @Override
    public void addEmployee(Employee employee) throws RemoteException {
        userDAO.update(employee.getUser()); // ensure User exists
        // persist employee
        entityManagerPersist(employee);
    }

    @Override
    public void deleteEmployee(String userId) throws RemoteException {
        employeeDAO.findAll().stream()
                .filter(e -> e.getUserId().equals(userId))
                .findFirst()
                .ifPresent(e -> {
                    // delete employee record
                    entityManagerRemove(e);
                });
    }

    // helper methods for JPA entity manager
    private void entityManagerPersist(Object entity) {
        BaseDAO dao = new BaseDAO();
        dao.em.getTransaction().begin();
        dao.em.persist(entity);
        dao.em.getTransaction().commit();
    }

    private void entityManagerRemove(Object entity) {
        BaseDAO dao = new BaseDAO();
        dao.em.getTransaction().begin();
        dao.em.remove(dao.em.contains(entity) ? entity : dao.em.merge(entity));
        dao.em.getTransaction().commit();
    }
}