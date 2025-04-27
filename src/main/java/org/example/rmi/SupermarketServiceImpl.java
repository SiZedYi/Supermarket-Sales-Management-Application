package org.example.rmi;

// SupermarketServiceImpl.java

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.List;

import jakarta.persistence.EntityTransaction;
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
    private SupplierDAO supplierDAO = new SupplierDAO();
    private CustomerDAO customerDAO = new CustomerDAO();

    public SupermarketServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public Account login(String userId, String password) throws RemoteException {
        try {
            return accountDAO.login(userId, password);
        } catch (Exception e) {
            throw new RemoteException("Login failed: " + e.getMessage());
        }
    }

    @Override
    public User viewUserInfo(String userId) throws RemoteException {
        try {
            return userDAO.findById(userId);
        } catch (Exception e) {
            throw new RemoteException("View user info failed: " + e.getMessage());
        }
    }

    @Override
    public void changePassword(String userId, String newPassword) throws RemoteException {
        try {
            accountDAO.updatePassword(userId, newPassword);
        } catch (Exception e) {
            throw new RemoteException("Change password failed: " + e.getMessage());
        }
    }

    @Override
    public void addCategory(Category category) throws RemoteException {
        try {
            categoryDAO.save(category);
        } catch (Exception e) {
            throw new RemoteException("Add category failed: " + e.getMessage());
        }
    }

    @Override
    public void deleteCategory(Long categoryId) throws RemoteException {
        try {
            categoryDAO.delete(categoryId);
        } catch (Exception e) {
            throw new RemoteException("Delete category failed: " + e.getMessage());
        }
    }

    @Override
    public void addProduct(Product product) throws RemoteException {
        try {
            productDAO.save(product);
        } catch (Exception e) {
            throw new RemoteException("Add product failed: " + e.getMessage());
        }
    }

    @Override
    public void deleteProduct(Long productId) throws RemoteException {
        try {
            productDAO.delete(productId);
        } catch (Exception e) {
            throw new RemoteException("Delete product failed: " + e.getMessage());
        }
    }

    @Override
    public void cancelInvoiceDetail(Long detailId) throws RemoteException {
        try {
            invoiceDAO.cancelInvoiceDetail(detailId);
        } catch (Exception e) {
            throw new RemoteException("Cancel invoice detail failed: " + e.getMessage());
        }
    }

    @Override
    public List<User> listEmployees() throws RemoteException {
        try {
            return employeeDAO.findAll();
        } catch (Exception e) {
            throw new RemoteException("List employees failed: " + e.getMessage());
        }
    }

    @Override
    public void addEmployee(Employee employee) throws RemoteException {
        try {
            userDAO.update(employee.getUser());
            entityManagerPersist(employee);
        } catch (Exception e) {
            throw new RemoteException("Add employee failed: " + e.getMessage());
        }
    }

    @Override
    public void deleteEmployee(String userId) throws RemoteException {
        try {
            employeeDAO.delete(userId);
        } catch (Exception e) {
            throw new RemoteException("Delete employee failed: " + e.getMessage());
        }
    }

    @Override
    public List<Product> getAllProducts() throws RemoteException {
        try {
            return productDAO.findAll();
        } catch (Exception e) {
            throw new RemoteException("List products failed: " + e.getMessage());
        }
    }

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

    @Override
    public List<Category> listCategories() throws RemoteException {
        try {
            return categoryDAO.findAll();
        } catch (Exception e) {
            throw new RemoteException("List categories failed: " + e.getMessage());
        }
    }

    @Override
    public boolean addUser(User user, String password) throws RemoteException {
        try {
            return userDAO.addUser(user, password);
        } catch (Exception e) {
            throw new RemoteException("Add user failed: " + e.getMessage());
        }
    }

    @Override
    public List<Supplier> listSuppliers() throws RemoteException {
        try {
            return supplierDAO.findAll();
        } catch (Exception e) {
            throw new RemoteException("List suppliers failed: " + e.getMessage());
        }
    }


    @Override
    public boolean createInvoice(Invoice invoice, List<InvoiceDetail> details) throws RemoteException {
        try {
            invoiceDAO.saveInvoice(invoice, details);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Failed to create invoice", e);
        }
    }

    @Override
    public List<Invoice> getAllInvoices() throws RemoteException {
        try {
            return invoiceDAO.findAll();
        } catch (Exception e) {
            throw new RemoteException("List invoices failed: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Customer> getAllCustomers() throws RemoteException {
        try {
            return customerDAO.findAll();
        } catch (Exception e) {
            throw new RemoteException("List invoices failed: " + e.getMessage(), e);
        }
    }

//    @Override
//    public Invoice getInvoiceById(Long invoiceId) throws RemoteException {
//        try {
//            return invoiceDAO.findInvoiceById(invoiceId);
//        } catch (Exception e) {
//            throw new RemoteException("List invoices failed: " + e.getMessage(), e);
//        }
//    }

    @Override
    public Product getProductById(Long productId) throws RemoteException {
        try {
            return productDAO.findByProductId(productId);
        } catch (Exception e) {
            throw new RemoteException("List invoices failed: " + e.getMessage(), e);
        }
    }

    @Override
    public List<InvoiceDetail> getInvoiceDetails(Long invoiceId) throws RemoteException {
        try {
            return invoiceDAO.findInvoiceDetails(invoiceId);
        } catch (Exception e) {
            throw new RemoteException("Get invoice details failed: " + e.getMessage(), e);
        }
    }

//    @Override
//    public boolean deleteInvoiceDetail(String invoiceId, String productId) throws RemoteException {
//        try {
//            EntityTransaction tx = em.getTransaction();
//            tx.begin();
//            boolean result = invoiceDao.deleteDetail(invoiceId, productId);
//            tx.commit();
//            return result;
//        } catch (Exception e) {
//            throw new RemoteException("Delete invoice detail failed: " + e.getMessage(), e);
//        }
//    }
}