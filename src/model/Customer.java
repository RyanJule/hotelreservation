package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    String firstName;
    String lastName;
    String email;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

        Pattern pattern = Pattern.compile("^(.+)@(.+).(.+)$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(this.email);
        boolean matchFound = matcher.find();
        if(!matchFound) { throw new IllegalArgumentException(); }


    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer name: " + firstName + " " + lastName + " email: " + email;
    }

    @Override
    public int hashCode() {
        int value;
        value = email.hashCode();
        return value;
    }

    @Override
    public boolean equals(Object o) {
        Customer customer = (Customer) o;
        if (customer.getEmail() == email) { return true; }
        else { return false; }
    }

}


