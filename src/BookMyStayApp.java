/**
 * BookMyStay - Hotel Booking Management System
 *
 * Use Case 1: Application Entry & Welcome Message
 *
 * This class represents the entry point of the application.
 * It prints the application name and version information.
 *
 * @author Purav
 * @version 1.0
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        // Application name
        String appName = "BookMyStay - Hotel Booking Management System";

        // Version
        String version = "Version 1.0";

        // Display welcome message
        System.out.println("========================================");
        System.out.println(" Welcome to " + appName);
        System.out.println(" " + version);
        System.out.println("========================================");

        // Startup message
        System.out.println("Application started successfully.");

        // Termination message
        System.out.println("Thank you for using BookMyStay!");
    }
}