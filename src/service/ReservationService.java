package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

public class ReservationService {

    static ReservationService singleInstance = new ReservationService();

    public static Map<String, IRoom> allRooms;

    static Map<Reservation, Customer> allReservations;

    private ReservationService() {

    }

    public static ReservationService getInstance() {
        return singleInstance;
    }

    public static void addRoom(IRoom room) {
        allRooms.put(room.getRoomNumber(), room);
    }

    public static IRoom getARoom(String roomId) {
        return allRooms.get(roomId);
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        Map<String, IRoom> availRooms = findRooms(checkInDate, checkOutDate);
        if (!availRooms.containsKey(room.getRoomNumber())) {
            System.out.println("Room is already reserved for those dates");
            return null;
        }
        else {
            allReservations.put(reservation, reservation.getCustomer());
            return reservation;
        }
    }

    //takes checkIn and checkOut and returns a set of rooms that have not yet been reserved for those dates//
    public static Map<String, IRoom> findRooms(Date checkInDateA, Date checkOutDateA) {
        Map<String, IRoom> availableRooms = new HashMap<String, IRoom>();
        availableRooms.putAll(allRooms);

        for (Reservation r : allReservations.keySet()) {
            Date checkInDateB = r.getCheckInDate();
            Date checkOutDateB = r.getCheckOutDate();
            boolean datesOverlap = (!checkInDateA.after(checkOutDateB) && !checkOutDateA.before(checkInDateB));
            if (datesOverlap) {
                availableRooms.remove(r.getRoom().getRoomNumber());
            }
        }
        return availableRooms;
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer) {
        Set<Reservation> customerReservations = new HashSet<Reservation>();
        for (Reservation r : allReservations.keySet()) {
            if (r.getCustomer().equals(customer)) {
                customerReservations.add(r);
                System.out.println(r);
            }
        }
        return customerReservations;
    }

    public static void printAllReservations() {
        for (Reservation r : allReservations.keySet()) {
            System.out.println(r.toString());
        }
    }
}
