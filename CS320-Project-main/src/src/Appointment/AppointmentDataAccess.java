package Appointment;
import Main.DatabaseConnector;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class AppointmentDataAccess {

    private Connection databaseConnection;

    public AppointmentDataAccess(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void createAppointment(Appointment appointment) {
        try {
            String sql = "INSERT INTO appointment (date, time, patient_name, notes) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(appointment.getDate().getTime()));
            preparedStatement.setString(2, appointment.getTime());
            preparedStatement.setString(3, appointment.getPatientName());
            preparedStatement.setString(4, appointment.getNotes());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        List<Appointment> appointments = new ArrayList<>();
        try {
            String sql = "SELECT * FROM appointment WHERE patient_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date date = resultSet.getDate("date");
                String time = resultSet.getString("time");
                String notes = resultSet.getString("notes");
                String patientName = resultSet.getString("patient_name"); // Assuming you still want to retrieve patient name
                appointments.add(new Appointment(id, date, time, patientName, notes));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    public void deleteAppointment(int appointmentId) {
        try {
            String sql = "DELETE FROM appointment WHERE id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, appointmentId);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Appointment with ID " + appointmentId + " has been deleted.");
            } else {
                System.out.println("No appointment found with ID " + appointmentId + ". No records were deleted.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
