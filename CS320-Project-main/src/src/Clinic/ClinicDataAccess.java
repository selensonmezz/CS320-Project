package Clinic;
import Main.DatabaseConnector;
import java.sql.*;

public class ClinicDataAccess {
    private Connection databaseConnection;

    public ClinicDataAccess(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }




    public void getClinicInfo() {
        try {
            String sql = "SELECT * FROM clinic";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Clinic Info:");
            while (resultSet.next()) {
                int clinicId = resultSet.getInt("clinic_id");
                String clinicName = resultSet.getString("clinic_name");
                String clinicLocation = resultSet.getString("clinic_location");
                String contactDetails = resultSet.getString("contact_details");
                System.out.println("Clinic ID: " + clinicId + ", Clinic Name: " + clinicName + ", Clinic Location: " + clinicLocation + ", Contact Details: " + contactDetails);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}




