package api;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;
import java.util.*;

public class HotelResource {

    static ReservationService reservationService = ReservationService.getInstance();
    static CustomerService customerService = CustomerService.getInstance();

    public static Map<String, Customer> allCustomers = customerService.allCustomers;

    public static Map<String, IRoom> allRooms = reservationService.allRooms;

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public static void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        return reservationService.reserveARoom(customerService.getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    public static Collection<Reservation> getCustomersReservations(String customerEmail) {
        return reservationService.getCustomersReservation(customerService.getCustomer(customerEmail));
    }

    public static int counter = 0;
    public static int token = 1;
    public static Integer findARoom(Date checkIn, Date checkOut) {
        Map<String, IRoom> availRooms = reservationService.findRooms(checkIn, checkOut);

        if (availRooms.isEmpty()) {
            if (token == 1) {
            counter += 1;
            }
            int value = counter * token;
            Calendar cIn = Calendar.getInstance();
            Calendar cOut = Calendar.getInstance();
            cIn.setTime(checkIn);
            cOut.setTime(checkOut);
            cIn.add(Calendar.DATE, value);
            cOut.add(Calendar.DATE, value);
            Date newCheckIn = cIn.getTime();
            Date newCheckOut = cOut.getTime();

            token *= -1;
            System.out.println("No rooms available. Searching closest dates for similar reservations");
            findARoom(newCheckIn, newCheckOut);
        }
        else {
            for (IRoom iRoom : availRooms.values()) {
                System.out.print(iRoom.toString() + "It will be available for these dates: " + checkIn.toString() + " " + checkOut.toString() + "\n");
            }
        }
        return counter;
    }



}
