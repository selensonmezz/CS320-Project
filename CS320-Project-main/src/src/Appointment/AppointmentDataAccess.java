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
            preparedStatement.setString(3, appointment.getPatient_name());
            preparedStatement.setString(4, appointment.getPrescription());
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Appointment created successfully.");
            } else {
                System.out.println("Failed to create appointment.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Appointment> listAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        try {
            String sql = "SELECT * FROM appointment";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int appointment_id = resultSet.getInt("appointment_id");
                Date date = resultSet.getDate("date");
                String time = resultSet.getString("time");
                String prescription = resultSet.getString("notes");
                String patientName = resultSet.getString("patient_name");
                appointments.add(new Appointment(appointment_id, date, time, patientName, prescription));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    public List<Appointment> listAppointmentByAppointmentId(int appointmentId) {
        List<Appointment> appointments = new ArrayList<>();
        try {
            String sql = "SELECT * FROM appointment WHERE appointment_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, appointmentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int appointment_id = resultSet.getInt("appointment_id");
                Date date = resultSet.getDate("date");
                String time = resultSet.getString("time");
                String prescription = resultSet.getString("notes");
                String patientName = resultSet.getString("patient_name");
                appointments.add(new Appointment(appointment_id, (java.sql.Date) date, time, patientName, prescription));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    public void updateAppointment(Appointment appointment) {
        try {
            String sql = "UPDATE appointment SET date = ?, time = ?, patient_name = ?, notes = ? WHERE appointment_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(appointment.getDate().getTime()));
            preparedStatement.setString(2, appointment.getTime());
            preparedStatement.setString(3, appointment.getPatient_name());
            preparedStatement.setString(4, appointment.getPrescription());
            preparedStatement.setInt(5, appointment.getAppointment_id());

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Appointment with ID " + appointment.getAppointment_id() + " has been updated.");
            } else {
                System.out.println("No appointment found with ID " + appointment.getAppointment_id() + ". No records were updated.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAppointment(int appointmentId) {
        try {
            String sql = "DELETE FROM appointment WHERE appointment_id = ?";
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
