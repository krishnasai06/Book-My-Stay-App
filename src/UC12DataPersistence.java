import java.io.*;
import java.util.*;

class Booking implements Serializable {
    String guestName;
    int rooms;

    Booking(String guestName, int rooms) {
        this.guestName = guestName;
        this.rooms = rooms;
    }

    public String toString() {
        return guestName + " booked " + rooms + " room(s)";
    }
}

class HotelSystem implements Serializable {
    int availableRooms;
    List<Booking> bookings;

    HotelSystem(int rooms) {
        availableRooms = rooms;
        bookings = new ArrayList<>();
    }

    void bookRoom(String guest, int rooms) {
        if (rooms <= availableRooms) {
            bookings.add(new Booking(guest, rooms));
            availableRooms -= rooms;
            System.out.println("Booking successful for " + guest);
        } else {
            System.out.println("Booking failed for " + guest);
        }
    }

    void showStatus() {
        System.out.println("Available Rooms: " + availableRooms);
        for (Booking b : bookings) {
            System.out.println(b);
        }
    }
}

class PersistenceService {
    static void save(HotelSystem system, String file) {
        try {
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(file));
            o.writeObject(system);
            o.close();
        } catch (Exception e) {
            System.out.println("Error saving state");
        }
    }

    static HotelSystem load(String file) {
        try {
            ObjectInputStream i = new ObjectInputStream(new FileInputStream(file));
            HotelSystem system = (HotelSystem) i.readObject();
            i.close();
            return system;
        } catch (Exception e) {
            System.out.println("No saved state found. Starting fresh.");
            return new HotelSystem(10);
        }
    }
}

public class UC12DataPersistence {
    public static void main(String[] args) {
        String file = "hotel_state.ser";

        HotelSystem system = PersistenceService.load(file);

        system.bookRoom("Guest1", 2);
        system.bookRoom("Guest2", 3);

        system.showStatus();

        PersistenceService.save(system, file);
    }
}