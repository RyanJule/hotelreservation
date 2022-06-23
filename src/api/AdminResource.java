package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;


import java.util.Collection;
import java.util.List;
import java.util.Map;

public class AdminResource {

    public static ReservationService reservationService = ReservationService.getInstance();
    public static CustomerService customerService = CustomerService.getInstance();

    public static Map<String, Customer> allCustomers = customerService.allCustomers;

    public static Map<String, IRoom> allRooms = reservationService.allRooms;

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public static void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            if (allRooms.containsKey(room)) { System.out.println("Room " + room.getRoomNumber() +" already exists");}
            else{
                reservationService.addRoom(room);
                System.out.println(room.getRoomNumber() + " Has been created");
            }
        }
    }

    public static Collection<IRoom> getAllRooms() {
        for (IRoom r : allRooms.values()) {
            System.out.println(r);
        }
        return allRooms.values();
    }

    public static Collection<Customer> getAllCustomers() {
        return allCustomers.values();
    }

    public static void displayAllReservations() {
        reservationService.printAllReservations();
    }
}
