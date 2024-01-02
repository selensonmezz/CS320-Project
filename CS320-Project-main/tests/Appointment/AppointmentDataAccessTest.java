package Appointment;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppointmentDataAccessTest {

    private Connection connection;
    private AppointmentDataAccess appointmentDataAccess;

    @Before
    public void setUp() {
        // Initialize the connection and AppointmentDataAccess before each test
        connection = establishConnection();
        appointmentDataAccess = new AppointmentDataAccess(connection);
    }

    @After
    public void tearDown() {
        // Close the connection after each test
        closeConnection();
    }

    @Test
    public void testCreateAppointment() {
        // Create an Appointment object for testing
        Appointment testAppointment = new Appointment(0, new java.sql.Date(System.currentTimeMillis()), "10:00 AM", "Test Patient", "Test Prescription");

        // Call the createAppointment method
        appointmentDataAccess.createAppointment(testAppointment);

        // Retrieve the list of all appointments and check if the test appointment is in the list
        List<Appointment> allAppointments = appointmentDataAccess.listAllAppointments();
        assertTrue("Appointment not created successfully", containsAppointment(allAppointments, testAppointment));
    }

    
    // Helper method to check if an appointment is in the list
    private boolean containsAppointment(List<Appointment> appointments, Appointment targetAppointment) {
        for (Appointment appointment : appointments) {
            if (appointment.getPatient_name().equals(targetAppointment.getPatient_name())
                    && appointment.getPrescription().equals(targetAppointment.getPrescription())) {
                return true;
            }
        }
        return false;
    }

    // Establish a connection to the test database
    private Connection establishConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/cbs", "root", "1002");
        } catch (SQLException e) {
            throw new RuntimeException("Error establishing connection", e);
        }
    }

    // Close the connection
    private void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error closing connection", e);
        }
    }
}