package Patient;
import Main.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientDataAccess {
    private Connection databaseConnection;

    public PatientDataAccess(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }



    public void insertPatient(Patient patient) {
        try {
            String sql = "INSERT INTO patient (patient_id, first_name, last_name, phone_num) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, patient.getPatientId());
            //******************************1, 2, 3
            preparedStatement.setString(2, patient.getFirstName());
            preparedStatement.setString(3, patient.getLastName());
            preparedStatement.setString(4, patient.getPhoneNum());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePatient(Patient patient) {
        try {
            String sql = "UPDATE patient SET first_name = ?, last_name = ?, phone_num = ? WHERE patient_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setString(1, patient.getFirstName());
            preparedStatement.setString(2, patient.getLastName());
            preparedStatement.setString(3, patient.getPhoneNum());
            preparedStatement.setInt(4, patient.getPatientId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePatient(int patient_id) {
        try {
            String sql = "DELETE FROM patient WHERE patient_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, patient_id);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Patient with ID " + patient_id + " has been deleted.");
            } else {
                System.out.println("No patient found with ID " + patient_id + ". No records were deleted.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Patient> listAllPatients() {
        List<Patient> patients = new ArrayList<Patient>();
        try {
            String sql = "SELECT * FROM patient;";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            ResultSet r = preparedStatement.executeQuery();
            while(r.next()) {
                int patient_id = r.getInt("patient_id");
                String patient_name = r.getString("first_name");
                String patient_lastName = r.getString("last_name");
                String phone_number = r.getString("phone_num");
                System.out.println(patient_id + " " + patient_name + " " + patient_lastName + " " + phone_number);
                patients.add(new Patient(patient_id, patient_name, patient_lastName, phone_number));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;

    }

    public Patient displayPatient(int patientId) {
        Patient patient = null;
        try {
            String sql = "SELECT * FROM patient WHERE patient_id = ?;";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, patientId);
            ResultSet r = preparedStatement.executeQuery();

            while(r.next()) {
                int patient_id = r.getInt("patient_id");
                String patient_name = r.getString("first_name");
                String patient_lastName = r.getString("last_name");
                String phone_number = r.getString("phone_num");
                System.out.println(patient_id + " " + patient_name + " " + patient_lastName + " " + phone_number);
                patient = new Patient(patient_id, patient_name, patient_lastName, phone_number);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patient;
    }

}
