
import java.util.HashMap;


public class CustomerStorage {

    private HashMap<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new IllegalArgumentException("Wrong format. Correct format:\n" +
                    "add Василий Петров " + "vasily.petrov@gmail.com +79215637722");
        }
        String name = components[0] + " " + components[1];
        Customer customer = new Customer(name, components[3], components[2]);
        try {
            customer.validate();
            storage.put(name, customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void listCustomers() {

        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public int getCount() {
        return storage.size();
    }



}