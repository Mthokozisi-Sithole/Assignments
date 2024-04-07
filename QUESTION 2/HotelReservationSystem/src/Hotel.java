import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String name;
    private List<Room> rooms;

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable(checkIn, checkOut)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Room getRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null; // Room not found
    }

    public Reservation makeReservation(Customer customer, Room room, LocalDate checkIn, LocalDate checkOut) {
        Reservation reservation = new Reservation(customer, room, checkIn, checkOut);
        // Update room availability
        room.book(checkIn, checkOut);
        return reservation;
    }

    public void cancelReservation(Reservation reservation) {
        // Update room availability
        reservation.getRoom().cancel(reservation.getCheckIn(), reservation.getCheckOut());
    }
}
