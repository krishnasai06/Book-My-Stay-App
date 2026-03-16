
    import java.util.*;

    public class UC10BookingCancellation  {

        static Map<String, String> bookings = new HashMap<>();
        static Map<String, Integer> inventory = new HashMap<>();
        static Stack<String> rollbackStack = new Stack<>();

        public static void confirmBooking(String bookingId, String roomType) {
            if (inventory.get(roomType) > 0) {
                bookings.put(bookingId, roomType);
                inventory.put(roomType, inventory.get(roomType) - 1);
                System.out.println("Booking confirmed: " + bookingId + " for " + roomType);
            } else {
                System.out.println("No rooms available for type: " + roomType);
            }
        }

        public static void cancelBooking(String bookingId) {

            if (!bookings.containsKey(bookingId)) {
                System.out.println("Cancellation failed: Booking does not exist.");
                return;
            }

            String roomType = bookings.get(bookingId);

            rollbackStack.push(bookingId);

            inventory.put(roomType, inventory.get(roomType) + 1);

            bookings.remove(bookingId);

            System.out.println("Booking cancelled successfully: " + bookingId);
        }

        public static void displayInventory() {
            System.out.println("\nCurrent Inventory:");
            for (String type : inventory.keySet()) {
                System.out.println(type + " : " + inventory.get(type));
            }
        }

        public static void displayRollbackStack() {
            System.out.println("\nRollback Stack:");
            System.out.println(rollbackStack);
        }

        public static void main(String[] args) {

            inventory.put("Single", 2);
            inventory.put("Double", 2);

            confirmBooking("B101", "Single");
            confirmBooking("B102", "Double");

            displayInventory();

            cancelBooking("B101");

            displayInventory();

            displayRollbackStack();

            cancelBooking("B101");
        }
    }

