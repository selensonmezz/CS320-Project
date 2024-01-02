package Main;

import Appointment.AppointmentDataAccess;
import Clinic.ClinicDataAccess;
import Patient.PatientDataAccess;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // Establish connection
        DatabaseConnector.establishConnection();
        ;

        // Create data access objects for appointments and patients
        AppointmentDataAccess appointmentDataAccess = new AppointmentDataAccess(DatabaseConnector.getConnection());
        PatientDataAccess patientDataAccess = new PatientDataAccess(DatabaseConnector.getConnection());
        ClinicDataAccess clinicDataAccess = new ClinicDataAccess(DatabaseConnector.getConnection());

        // Initialize and start the menu
        Menu menu = new Menu(appointmentDataAccess, patientDataAccess, clinicDataAccess);
        if (performLogin()) {
        try {
            menu.initiateMenu();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while running the application.");
        } finally {
            // Close the connection after menu operations are done
            DatabaseConnector.closeConnection();
        }
        } else {
            System.out.println("Login failed. Exiting the application.");
            // Close the connection without running the menu
            DatabaseConnector.closeConnection();
        }
    }

    private static boolean performLogin() {
        try {
            // Call the login method from the Login class
            return User.Login.login();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred during login.");
            return false;
        }
    }
}
