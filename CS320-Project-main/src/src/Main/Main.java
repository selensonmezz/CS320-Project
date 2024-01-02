package Main;

import Appointment.AppointmentDataAccess;
import Clinic.ClinicDataAccess;
import Patient.PatientDataAccess;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // Establish connection
        Connection connection = DatabaseConnector.getConnection();

        // Create data access objects for appointments and patients
        AppointmentDataAccess appointmentDataAccess = new AppointmentDataAccess(connection);
        PatientDataAccess patientDataAccess = new PatientDataAccess(connection);
        ClinicDataAccess clinicDataAccess = new ClinicDataAccess(connection);

        // Initialize and start the menu
        Menu menu = new Menu(appointmentDataAccess, patientDataAccess, clinicDataAccess);
        try {
            menu.initiateMenu();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while running the application.");
        } finally {
            // Close the connection after menu operations are done
            DatabaseConnector.closeConnection();
        }
    }
}