package Tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import Clinic.Clinic;
import Clinic.ClinicDataAccess;
import Main.DatabaseConnector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClinicDataAccessTest {


    private Connection connection;
    private ClinicDataAccess clinicDataAccess;

    @Before
    public void setUp() {
        // Initialize the connection and AppointmentDataAccess before each test
        DatabaseConnector.establishConnection();
        clinicDataAccess = new ClinicDataAccess(DatabaseConnector.getConnection());
    }

    @After
    public void tearDown() {
        // Close the connection after each test
        closeConnection();
    }




    @Test
    public void testGetClinicInfo() {
        this.clinicDataAccess.getClinicInfo();

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