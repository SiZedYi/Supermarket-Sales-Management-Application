// RmiServer.java
import org.example.rmi.SupermarketServiceImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiServer {
    public static void main(String[] args) {
        try {
            SupermarketServiceImpl service = new SupermarketServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("SupermarketService", service);
            System.out.println("RMI Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
