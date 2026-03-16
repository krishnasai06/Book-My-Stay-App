import java.util.*;

class Reservation {

    private String reservationId;
    private String guestName;
    private String roomType;
    private String roomId;

    public Reservation(String reservationId, String guestName, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayReservation() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room Type: " + roomType +
                " | Room ID: " + roomId);
    }
}

class BookingHistory {

    private List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    public List<Reservation> getAllReservations() {
        return history;
    }
}

class BookingReportService {

    public void displayAllBookings(List<Reservation> reservations) {

        System.out.println("\n----- Booking History -----");

        for (Reservation r : reservations) {
            r.displayReservation();
        }

        System.out.println("---------------------------");
    }

    public void generateSummary(List<Reservation> reservations) {

        Map<String, Integer> summary = new HashMap<>();

        for (Reservation r : reservations) {

            String type = r.getRoomType();

            summary.put(type, summary.getOrDefault(type, 0) + 1);
        }

        System.out.println("\n----- Booking Summary -----");

        for (String roomType : summary.keySet()) {
            System.out.println(roomType + " bookings : " + summary.get(roomType));
        }

        System.out.println("---------------------------");
    }
}

public class UC8BookingHistory {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("RES-1", "Alice", "SingleRoom", "SingleRoom-101"));
        history.addReservation(new Reservation("RES-2", "Bob", "DoubleRoom", "DoubleRoom-201"));
        history.addReservation(new Reservation("RES-3", "Charlie", "SuiteRoom", "SuiteRoom-301"));
        history.addReservation(new Reservation("RES-4", "David", "SingleRoom", "SingleRoom-102"));

        BookingReportService reportService = new BookingReportService();

        reportService.displayAllBookings(history.getAllReservations());

        reportService.generateSummary(history.getAllReservations());
    }
}