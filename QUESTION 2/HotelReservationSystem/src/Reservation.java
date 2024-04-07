import java.time.LocalDate;
import java.util.UUID;

public class Reservation {
    private Customer customer;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String confirmationNumber;

    public Reservation(Customer customer, Room room, LocalDate checkIn, LocalDate checkOut) {
        this.customer = customer;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.confirmationNumber = UUID.randomUUID().toString(); // Generate a unique confirmation number
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }
}
