
package Tests;

import static org.junit.Assert.*;

import java.sql.*;
import java.util.List;

import Main.DatabaseConnector;
import Patient.Patient;
import Patient.PatientDataAccess;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PatientDataAccessTest {

    private Connection connection;
    private PatientDataAccess patientDataAccess;


    @Before
    public void setUp() {
        DatabaseConnector.establishConnection();
        patientDataAccess = new PatientDataAccess(DatabaseConnector.getConnection());
    }

    @After
    public void tearDown() {
        closeConnection();
    }

    @Test
    public void testInsertPatient() {
        Patient testPatient = new Patient(0, "Test First Name", "Test Last Name", "Test Phone Number");

        patientDataAccess.insertPatient(testPatient);


        Patient patient = patientDataAccess.displayPatient(0);

        assertNotNull(patient);
        assertEquals("Test First Name", patient.getFirstName());
        assertEquals("Test Last Name", patient.getLastName());
        assertEquals("Test Phone Number", patient.getPhoneNum());
    }

    @Test
    public void testDeletePatient() {

        patientDataAccess.deletePatient(0);

        Patient deletedPatient = patientDataAccess.displayPatient(0);

        assertNull(deletedPatient);
    }

    @Test

    public void testUpdatePatient() {
        Patient testPatient = new Patient(0, "Test First Name", "Test Last Name", "Test Phone Number");

        patientDataAccess.insertPatient(testPatient);


        testPatient.setPhoneNum("Updated Phone Number");

        patientDataAccess.updatePatient(testPatient);

        Patient updatedPatient = patientDataAccess.displayPatient(0);

        assertNotNull(updatedPatient);
        assertEquals("Test First Name", updatedPatient.getFirstName());
        assertEquals("Test Last Name", updatedPatient.getLastName());
        assertEquals("Test Phone Number", updatedPatient.getPhoneNum());

    }

    @Test
    public void testListAllPatients() {
        List<Patient> allPatients = patientDataAccess.listAllPatients();

        assertEquals("Number of appointments should match", 3, allPatients.size());
    }

    @Test
    public void testDisplayPatient() {
        Patient testPatient = new Patient(0, "Test First Name", "Test Last Name", "Test Phone Number");

        patientDataAccess.insertPatient(testPatient);

        int testPatientId = 0;
        Patient result = patientDataAccess.displayPatient(testPatientId);

        assertNotNull(result);

        assertEquals(0, result.getPatientId());
        assertEquals("Test First Name", result.getFirstName());
        assertEquals("Test Last Name", result.getLastName());
        assertEquals("Test Phone Number", result.getPhoneNum());
    }



    private boolean containsPatient(List<Patient> patients, Patient targetPatient) {
        for (Patient patient : patients) {
            if (patient.getFirstName().equals(targetPatient.getFirstName())
                    && patient.getLastName().equals(targetPatient.getLastName())
                    && patient.getPhoneNum().equals(targetPatient.getPhoneNum()) ) {
                return true;
            }
        }
        return false;
    }


    private Connection establishConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/cs320", "root", "Bumble1687@");
        } catch (SQLException e) {
            throw new RuntimeException("Error establishing connection", e);
        }
    }

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
