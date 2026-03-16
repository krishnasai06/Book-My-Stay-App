import java.util.HashMap;
import java.util.Map;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class InventoryService {

    private Map<String, Integer> inventory;

    public InventoryService() {
        inventory = new HashMap<>();

        inventory.put("SingleRoom", 2);
        inventory.put("DoubleRoom", 1);
        inventory.put("SuiteRoom", 0);
    }

    public boolean isValidRoomType(String roomType) {
        return inventory.containsKey(roomType);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void allocateRoom(String roomType) throws InvalidBookingException {

        if (!isValidRoomType(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        int available = inventory.get(roomType);

        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for " + roomType);
        }

        inventory.put(roomType, available - 1);
    }

    public void displayInventory() {

        System.out.println("\nCurrent Inventory State:");

        for (String type : inventory.keySet()) {
            System.out.println(type + " : " + inventory.get(type));
        }
    }
}

class BookingValidator {

    public static void validate(String guestName, String roomType, InventoryService inventory)
            throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!inventory.isValidRoomType(roomType)) {
            throw new InvalidBookingException("Room type does not exist: " + roomType);
        }

        if (inventory.getAvailability(roomType) <= 0) {
            throw new InvalidBookingException("Requested room type is fully booked: " + roomType);
        }
    }
}

public class UC9ErrorHandling {

    public static void main(String[] args) {

        InventoryService inventory = new InventoryService();

        try {

            String guestName = "Alice";
            String roomType = "SingleRoom";

            BookingValidator.validate(guestName, roomType, inventory);

            inventory.allocateRoom(roomType);

            System.out.println("Booking confirmed for " + guestName + " in " + roomType);

        } catch (InvalidBookingException e) {

            System.out.println("Booking failed: " + e.getMessage());
        }

        try {

            String guestName = "Bob";
            String roomType = "SuiteRoom";

            BookingValidator.validate(guestName, roomType, inventory);

            inventory.allocateRoom(roomType);

            System.out.println("Booking confirmed for " + guestName + " in " + roomType);

        } catch (InvalidBookingException e) {

            System.out.println("Booking failed: " + e.getMessage());
        }

        try {

            String guestName = "";
            String roomType = "DoubleRoom";

            BookingValidator.validate(guestName, roomType, inventory);

            inventory.allocateRoom(roomType);

            System.out.println("Booking confirmed for " + guestName + " in " + roomType);

        } catch (InvalidBookingException e) {

            System.out.println("Booking failed: " + e.getMessage());
        }

        inventory.displayInventory();
    }
}