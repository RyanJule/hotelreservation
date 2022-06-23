package application;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

import api.*;
import service.CustomerService;
import service.ReservationService;


public class MainMenu {

    public static String option1 = "1. Find and reserve a room";
    public static String option2 = "2. See my reservations";
    public static String option3 = "3. Create an account";
    public static String option4 = "4. Admin";
    public static String option5 = "5. Exit";
    public static String line = "-------------------------------------------";

    public static void initializeMainMenu() {

        System.out.println("Welcome to the Hotel Reservation Application\n");
        System.out.println(line);
        System.out.println(option1);
        System.out.println(option2);
        System.out.println(option3);
        System.out.println(option4);
        System.out.println(option5);
        System.out.println(line);
        System.out.println("Please select a number for the menu option");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        switch (i)  {


            case 1: System.out.println("Enter CheckIn Date mm/dd/yyyy");
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    String checkIn = sc.next();
                    Date checkInDate = new Date();
                    try { checkInDate = formatter.parse(checkIn); }
                    catch (ParseException ex) {
                        System.out.println("Invalid Date Format, Returning to main menu");
                        initializeMainMenu();
                    }
                    System.out.println("Enter CheckOut Date mm/dd/yyyy");
                    String checkOut = sc.next();
                    Date checkOutDate = new Date();
                    try { checkOutDate = formatter.parse(checkOut); }
                    catch (ParseException ex) {
                        System.out.println("Invalid Date Format, returning to main menu");
                        initializeMainMenu();
                    }

                    if (checkInDate.before(checkOutDate)) {
                        HotelResource.findARoom(checkInDate, checkOutDate);
                    }

                    else {
                        System.out.println("How you gonna check out if you ain't even checked in???");
                        initializeMainMenu();
                    }

                    System.out.println("Would you like to book a room? y/n");

                    String bookAnswer = sc.next();
                    String accountAnswer;
                    String roomAnswer;
                    String customerEmail;

                    if (bookAnswer.equalsIgnoreCase("y")) {
                        System.out.println("Do you have an account? y/n");
                    }
                    else {
                        System.out.println("Please make an account, returning to main menu");
                        MainMenu.initializeMainMenu();
                    }

                    accountAnswer = sc.next();

                    if (accountAnswer.equalsIgnoreCase("y")) {
                        System.out.println("Enter email Address");
                    }
                    else {
                        System.out.println("No Account found, returning to main menu");
                        initializeMainMenu();
                    }

                    customerEmail = sc.next();

                    if (HotelResource.allCustomers.containsKey(customerEmail)) {
                        System.out.println("What Room would you like to reserve?");
                    }

                    else {
                        System.out.println("No Account Found, Returning to main menu");
                        initializeMainMenu();
                    }

                    roomAnswer = sc.next();

                    if (HotelResource.allRooms.containsKey(roomAnswer)) {
                        System.out.println(HotelResource.bookARoom(customerEmail, HotelResource.allRooms.get(roomAnswer), checkInDate, checkOutDate));
                        initializeMainMenu();
                    }

                    else {
                        System.out.println("Room not found, Returning to main menu");
                        initializeMainMenu();
                    }



            case 2: System.out.println("What is your account's email address?");
                    String addressAnswer = sc.next();
                    if (HotelResource.allCustomers.containsKey(addressAnswer)) {
                        HotelResource.getCustomersReservations(addressAnswer);
                        initializeMainMenu();
                    }
                    else {
                        System.out.println("No reservations to Show, returning to main menu");
                        initializeMainMenu();
                    }

            case 3: System.out.println("Enter Email format: name@domain.com");
                    String email = sc.next();
                    System.out.println("First Name");
                    String firstName = sc.next();
                    System.out.println("Last name");
                    String lastName = sc.next();
                    try {
                        HotelResource.createACustomer(email, firstName, lastName);
                    }
                    catch (IllegalArgumentException ex) {
                        System.out.println("Account not created, please try again with a valid email format");
                    }
                    finally {
                        initializeMainMenu();
                    }

            case 4: AdminMenu.initializeAdminMenu();

            case 5: System.exit(0);
        }
    }
}
