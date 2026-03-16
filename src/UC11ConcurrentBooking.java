import java.util.*;

class BookingRequest {
    String guestName;
    int roomsRequested;

    BookingRequest(String guestName, int roomsRequested) {
        this.guestName = guestName;
        this.roomsRequested = roomsRequested;
    }
}

class BookingSystem {
    private Queue<BookingRequest> bookingQueue = new LinkedList<>();
    private int availableRooms;

    BookingSystem(int rooms) {
        availableRooms = rooms;
    }

    public synchronized void addRequest(BookingRequest request) {
        bookingQueue.add(request);
        notifyAll();
    }

    public synchronized BookingRequest getRequest() {
        while (bookingQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return bookingQueue.poll();
    }

    public synchronized void allocateRoom(BookingRequest request) {
        if (availableRooms >= request.roomsRequested) {
            availableRooms -= request.roomsRequested;
            System.out.println(request.guestName + " booked " + request.roomsRequested + " room(s). Remaining rooms: " + availableRooms);
        } else {
            System.out.println("Booking failed for " + request.guestName + ". Not enough rooms.");
        }
    }
}

class BookingProcessor extends Thread {
    private BookingSystem system;

    BookingProcessor(BookingSystem system) {
        this.system = system;
    }

    public void run() {
        while (true) {
            BookingRequest request = system.getRequest();
            system.allocateRoom(request);
        }
    }
}

class Guest extends Thread {
    private BookingSystem system;
    private String name;
    private int rooms;

    Guest(BookingSystem system, String name, int rooms) {
        this.system = system;
        this.name = name;
        this.rooms = rooms;
    }

    public void run() {
        system.addRequest(new BookingRequest(name, rooms));
    }
}

public class UC11ConcurrentBooking {
    public static void main(String[] args) {
        BookingSystem system = new BookingSystem(5);

        BookingProcessor processor1 = new BookingProcessor(system);
        BookingProcessor processor2 = new BookingProcessor(system);

        processor1.start();
        processor2.start();

        new Guest(system, "Guest1", 1).start();
        new Guest(system, "Guest2", 2).start();
        new Guest(system, "Guest3", 1).start();
        new Guest(system, "Guest4", 2).start();
        new Guest(system, "Guest5", 1).start();
    }
}