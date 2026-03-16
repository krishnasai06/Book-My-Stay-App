
    import java.util.LinkedList;
import java.util.Queue;

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

        public void displayReservation() {
            System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
        }
    }

    class BookingRequestQueue {

        private Queue<Reservation> bookingQueue;

        public BookingRequestQueue() {
            bookingQueue = new LinkedList<>();
        }

        public void addRequest(Reservation reservation) {
            bookingQueue.add(reservation);
            System.out.println("Booking request added for " + reservation.getGuestName());
        }

        public void displayQueue() {

            System.out.println("\n----- Booking Request Queue -----");

            if (bookingQueue.isEmpty()) {
                System.out.println("No booking requests.");
                return;
            }

            for (Reservation r : bookingQueue) {
                r.displayReservation();
            }

            System.out.println("---------------------------------");
        }
    }

    public class UC5BookingRequest {

        public static void main(String[] args) {

            System.out.println("Initializing Booking Request System...\n");

            BookingRequestQueue requestQueue = new BookingRequestQueue();

            Reservation r1 = new Reservation("Alice", "SingleRoom");
            Reservation r2 = new Reservation("Bob", "DoubleRoom");
            Reservation r3 = new Reservation("Charlie", "SuiteRoom");

            requestQueue.addRequest(r1);
            requestQueue.addRequest(r2);
            requestQueue.addRequest(r3);

            requestQueue.displayQueue();
        }
    }

