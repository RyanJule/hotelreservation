package application;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.*;

public class AdminMenu {
    static String menuTitle = "Admin Menu\n\n";
    static String line = "-------------------------------------------";
    static String option1 = "1. See all Customers";
    static String option2 = "2. See all Rooms";
    static String option3 = "3. See all Reservations";
    static String option4 = "4. Add a Room";
    static String option5 = "5. Back to Main Menu";
    public static void initializeAdminMenu() {
        System.out.println(menuTitle);
        System.out.println(line);
        System.out.println(option1);
        System.out.println(option2);
        System.out.println(option3);
        System.out.println(option4);
        System.out.println(option5);
        System.out.println(line);
        System.out.println("Please select a number for your menu option");

        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        if (option == 1) {
            Collection<Customer> customers = api.AdminResource.getAllCustomers();
            for (Customer c : customers) {
                System.out.println(c);
            }
            initializeAdminMenu();
        }
        else if (option == 2) {
            api.AdminResource.getAllRooms();
            initializeAdminMenu();
        }
        else if (option == 3) {
            api.AdminResource.displayAllReservations();
            initializeAdminMenu();
        }
        else if (option == 4) {
            try {
                System.out.println("Please enter a room number");
                String roomNumber = sc.next();
                System.out.println("Please enter a Price");
                double price = sc.nextDouble();
                System.out.println("Please enter a RoomType of either SINGLE or DOUBLE");
                RoomType enumeration = RoomType.valueOf(sc.next());
                Room room = new Room(roomNumber, price, enumeration);
                List<IRoom> rooms = new ArrayList<>();
                rooms.add(room);
                api.AdminResource.addRoom(rooms);
                initializeAdminMenu();
            }
            catch (IllegalArgumentException ex) {
                System.out.println("Please try again with correct parameters");
                initializeAdminMenu();
            }
            catch (InputMismatchException ex1 ) {
                System.out.println("Please try again with correct parameters");
                initializeAdminMenu();
            }
        }

        else if (option == 5) {
            MainMenu.initializeMainMenu();
        }
    }
}
