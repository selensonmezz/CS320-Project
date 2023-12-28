package Clinic;
import Main.DatabaseConnector;
import java.sql.*;

public class ClinicDataAccess {
    private Connection databaseConnection;

    public ClinicDataAccess(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

}
