import java.time.LocalDate;

public class Room {
    private int roomNumber;
    private int capacity;
    private double price;
    private boolean[] availability; // Array representing availability for each day

    public Room(int roomNumber, int capacity, double price) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.price = price;
        this.availability = new boolean[365]; // Assuming availability for one year
    }

    public boolean isAvailable(LocalDate checkIn, LocalDate checkOut) {
        // Check availability for each day in the date range
        for (LocalDate date = checkIn; !date.isAfter(checkOut); date = date.plusDays(1)) {
            if (availability[date.getDayOfYear() - 1]) {
                return false; // Room not available for at least one day
            }
        }
        return true; // Room available for the entire date range
    }

    public void book(LocalDate checkIn, LocalDate checkOut) {
        // Mark room as booked for each day in the date range
        for (LocalDate date = checkIn; !date.isAfter(checkOut); date = date.plusDays(1)) {
            availability[date.getDayOfYear() - 1] = true;
        }
    }

    public void cancel(LocalDate checkIn, LocalDate checkOut) {
        // Mark room as available for each day in the date range
        for (LocalDate date = checkIn; !date.isAfter(checkOut); date = date.plusDays(1)) {
            availability[date.getDayOfYear() - 1] = false;
        }
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getPrice() {
        return price;
    }
}
