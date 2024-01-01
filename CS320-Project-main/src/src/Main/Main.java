package Main;

import Appointment.AppointmentDataAccess;
import Clinic.ClinicDataAccess;
import Patient.PatientDataAccess;
public class Main {
    public static void main(String[] args) {
        DatabaseConnector.establishConnection();

        // Create data access objects for appointments and patients
        AppointmentDataAccess appointmentDataAccess = new AppointmentDataAccess(DatabaseConnector.getConnection());
        PatientDataAccess patientDataAccess = new PatientDataAccess(DatabaseConnector.getConnection());
        ClinicDataAccess clinicDataAccess = new ClinicDataAccess(DatabaseConnector.getConnection());

        // Initialize and start the menu
        Menu menu = new Menu(appointmentDataAccess, patientDataAccess, clinicDataAccess);
        try {
            menu.initiateMenu();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while running the application.");
        } finally {
            DatabaseConnector.closeConnection();
        }
    }
}
