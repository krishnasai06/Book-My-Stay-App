
    import java.util.HashMap;
import java.util.Map;

    abstract class Room {

        protected String roomType;
        protected double price;

        public Room(String roomType, double price) {
            this.roomType = roomType;
            this.price = price;
        }

        public String getRoomType() {
            return roomType;
        }

        public void displayDetails() {
            System.out.println("Room Type: " + roomType);
            System.out.println("Price per night: ₹" + price);
        }
    }

    class SingleRoom extends Room {
        public SingleRoom() {
            super("SingleRoom", 2500);
        }
    }

    class DoubleRoom extends Room {
        public DoubleRoom() {
            super("DoubleRoom", 4000);
        }
    }

    class SuiteRoom extends Room {
        public SuiteRoom() {
            super("SuiteRoom", 8000);
        }
    }

    class RoomInventory {

        private HashMap<String, Integer> availability;

        public RoomInventory() {
            availability = new HashMap<>();

            availability.put("SingleRoom", 10);
            availability.put("DoubleRoom", 0);
            availability.put("SuiteRoom", 3);
        }

        public int getAvailability(String roomType) {
            return availability.getOrDefault(roomType, 0);
        }

        public Map<String, Integer> getAllAvailability() {
            return availability;
        }
    }

    class SearchService {

        private RoomInventory inventory;
        private HashMap<String, Room> rooms;

        public SearchService(RoomInventory inventory) {

            this.inventory = inventory;

            rooms = new HashMap<>();
            rooms.put("SingleRoom", new SingleRoom());
            rooms.put("DoubleRoom", new DoubleRoom());
            rooms.put("SuiteRoom", new SuiteRoom());
        }

        public void searchAvailableRooms() {

            System.out.println("----- Available Rooms -----");

            for (String type : inventory.getAllAvailability().keySet()) {

                int count = inventory.getAvailability(type);

                if (count > 0) {

                    Room room = rooms.get(type);

                    room.displayDetails();
                    System.out.println("Available Rooms: " + count);
                    System.out.println();
                }
            }
        }
    }

    public class RoomSearchandAvailability {

        public static void main(String[] args) {

            System.out.println("Guest searching for available rooms...\n");

            RoomInventory inventory = new RoomInventory();

            SearchService searchService = new SearchService(inventory);

            searchService.searchAvailableRooms();
        }
    }

