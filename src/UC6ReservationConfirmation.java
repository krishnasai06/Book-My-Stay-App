import java.util.*;

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation r) {
        queue.add(r);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

class InventoryService {

    private HashMap<String, Integer> inventory;

    public InventoryService() {
        inventory = new HashMap<>();

        inventory.put("SingleRoom", 2);
        inventory.put("DoubleRoom", 2);
        inventory.put("SuiteRoom", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrementRoom(String roomType) {
        int count = inventory.get(roomType);
        inventory.put(roomType, count - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " : " + inventory.get(key));
        }
    }
}

class BookingService {

    private InventoryService inventory;

    private HashSet<String> allocatedRoomIds;

    private HashMap<String, Set<String>> roomAllocations;

    private int roomCounter = 1;

    public BookingService(InventoryService inventory) {
        this.inventory = inventory;

        allocatedRoomIds = new HashSet<>();

        roomAllocations = new HashMap<>();
    }

    public void processBookings(BookingRequestQueue queue) {

        while (!queue.isEmpty()) {

            Reservation r = queue.getNextRequest();

            String roomType = r.getRoomType();

            if (inventory.getAvailability(roomType) > 0) {

                String roomId = generateRoomId(roomType);

                allocatedRoomIds.add(roomId);

                roomAllocations
                        .computeIfAbsent(roomType, k -> new HashSet<>())
                        .add(roomId);

                inventory.decrementRoom(roomType);

                System.out.println("Reservation Confirmed for "
                        + r.getGuestName()
                        + " | Room Assigned: "
                        + roomId);
            } else {

                System.out.println("Reservation Failed for "
                        + r.getGuestName()
                        + " | No rooms available for "
                        + roomType);
            }
        }
    }

    private String generateRoomId(String roomType) {

        String roomId;

        do {
            roomId = roomType + "-" + roomCounter++;
        } while (allocatedRoomIds.contains(roomId));

        return roomId;
    }
}

public class UC6ReservationConfirmation {

    public static void main(String[] args) {

        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("Alice", "SingleRoom"));
        queue.addRequest(new Reservation("Bob", "SingleRoom"));
        queue.addRequest(new Reservation("Charlie", "SuiteRoom"));
        queue.addRequest(new Reservation("David", "SuiteRoom"));

        InventoryService inventory = new InventoryService();

        BookingService bookingService = new BookingService(inventory);

        bookingService.processBookings(queue);

        inventory.displayInventory();
    }
}