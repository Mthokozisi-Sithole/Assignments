import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.text.DecimalFormat;


public class HotelReservationSystem {
    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("###,###.00");

    public static void main(String[] args) {
        List<Hotel> hotels = generateHotels(); // Generate random hotels

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hotel Reservation System!");

        while (true) {
            System.out.println("\nAvailable hotels:");
            for (int i = 0; i < hotels.size(); i++) {
                System.out.println((i + 1) + ". " + hotels.get(i).getName());
            }

            System.out.print("Enter the number of the hotel you want to book (or 0 to exit): ");
            int hotelChoice = scanner.nextInt();
            if (hotelChoice == 0) {
                System.out.println("Thank you for using Hotel Reservation System. Goodbye!");
                break;
            } else if (hotelChoice < 1 || hotelChoice > hotels.size()) {
                System.out.println("Invalid choice. Please enter a number between 1 and " + hotels.size());
                continue;
            }

            Hotel selectedHotel = hotels.get(hotelChoice - 1);
            System.out.println("Selected hotel: " + selectedHotel.getName());

            System.out.print("Enter the check-in date (yyyy-mm-dd): ");
            LocalDate checkInDate = LocalDate.parse(scanner.next());

            System.out.print("Enter the check-out date (yyyy-mm-dd): ");
            LocalDate checkOutDate = LocalDate.parse(scanner.next());

            if (checkOutDate.isBefore(checkInDate)) {
                System.out.println("Invalid dates. Check-out date must be after check-in date.");
                continue;
            }

            List<Room> availableRooms = selectedHotel.getAvailableRooms(checkInDate, checkOutDate);
            if (availableRooms.isEmpty()) {
                System.out.println("No available rooms for the selected dates.");
                continue;
            }

            System.out.println("Available rooms:");
            for (Room room : availableRooms) {
                // Format the price using South African Rand (ZAR)
                String formattedPrice = formatPrice(room.getPrice());
                System.out.println("Room Number: " + room.getRoomNumber() +
                        ", Capacity: " + room.getCapacity() +
                        ", Price per night: R" + formattedPrice);
            }

            System.out.print("Enter the number of the room you want to book: ");
            int roomNumber = scanner.nextInt();

            Room selectedRoom = selectedHotel.getRoomByNumber(roomNumber);
            if (selectedRoom == null || !selectedRoom.isAvailable(checkInDate, checkOutDate)) {
                System.out.println("Invalid room choice or room not available for selected dates.");
                continue;
            }

            System.out.print("Enter your name: ");
            String customerName = scanner.next();
            System.out.print("Enter your email: ");
            String customerEmail = scanner.next();

            Customer customer = new Customer(customerName, customerEmail);
            Reservation reservation = selectedHotel.makeReservation(customer, selectedRoom, checkInDate, checkOutDate);
            System.out.println("Reservation successful! Confirmation number: " + reservation.getConfirmationNumber());
        }

        scanner.close();
    }

    private static List<Hotel> generateHotels() {
        List<Hotel> hotels = new ArrayList<>();
        Random random = new Random();
        String[] hotelNames = {"Prime Luxury Hotel", "Grand Plaza Hotel", "Royal Palace Hotel", "Sunset Resort"};
        for (String name : hotelNames) {
            int roomCount = random.nextInt(5) + 5; // Generate random room count (between 5 and 10)
            Hotel hotel = new Hotel(name);
            for (int i = 1; i <= roomCount; i++) {
                int capacity = random.nextInt(4) + 1; // Generate random capacity (between 1 and 4)
                double price = (random.nextDouble() * 200) + 50; // Generate random price (between 50 and 250)
                hotel.addRoom(new Room(i, capacity, price));
            }
            hotels.add(hotel);
        }
        return hotels;
    }

    private static String formatPrice(double price) {
        return PRICE_FORMAT.format(price);
    }
}
