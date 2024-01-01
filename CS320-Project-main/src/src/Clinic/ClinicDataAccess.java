package Clinic;
import Main.DatabaseConnector;
import java.sql.*;

public class ClinicDataAccess {
    private Connection databaseConnection;

    public ClinicDataAccess(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }




    public Clinic getClinicInfo(int clinicId) {
        String sql = "SELECT * FROM clinic WHERE clinic_id = ?";
        Clinic clinic = null;

        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, clinicId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("clinic_id");
                String name = resultSet.getString("clinic_name");
                String location = resultSet.getString("location");
                String contactDetails = resultSet.getString("contact_details");

                clinic = new Clinic(id, name, location, contactDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clinic;
    }

}
