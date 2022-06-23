package service;

import model.Customer;

import java.util.*;

public class CustomerService {

    private static CustomerService singleInstance1 = new CustomerService();
    public static Map<String, Customer> allCustomers = new HashMap<String, Customer>();

    private CustomerService() {

    }

    public static CustomerService getInstance() {
        return singleInstance1;
    }

    public static void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        allCustomers.put(customer.getEmail(), customer);
        System.out.println(customer);
    }

    public static Customer getCustomer(String customerEmail) {
        return allCustomers.get(customerEmail);
    }

    public Collection getAllCustomers() {
        return allCustomers.values();
    }


}

