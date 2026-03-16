
    import java.util.HashMap;
import java.util.Map;

    class RoomInventory {

        private HashMap<String, Integer> roomAvailability;

        public RoomInventory() {
            roomAvailability = new HashMap<>();

            roomAvailability.put("SingleRoom", 10);
            roomAvailability.put("DoubleRoom", 7);
            roomAvailability.put("SuiteRoom", 3);
        }

        public int getAvailability(String roomType) {
            return roomAvailability.getOrDefault(roomType, 0);
        }

        public void updateAvailability(String roomType, int count) {
            if (roomAvailability.containsKey(roomType)) {
                roomAvailability.put(roomType, count);
            } else {
                System.out.println("Room type not found.");
            }
        }

        public void displayInventory() {
            System.out.println("----- Current Room Inventory -----");

            for (Map.Entry<String, Integer> entry : roomAvailability.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }

            System.out.println("----------------------------------");
        }
    }

    public class bookmystayappp {

        public static void main(String[] args) {

            System.out.println("Initializing Room Inventory...\n");

            RoomInventory inventory = new RoomInventory();

            inventory.displayInventory();

            System.out.println("\nChecking availability of DoubleRoom:");
            System.out.println("Available: " + inventory.getAvailability("DoubleRoom"));

            System.out.println("\nUpdating DoubleRoom availability...\n");

            inventory.updateAvailability("DoubleRoom", 5);

            inventory.displayInventory();
        }
    }

