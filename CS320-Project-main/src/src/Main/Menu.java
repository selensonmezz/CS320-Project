package Main;

import Appointment.Appointment;
import Appointment.AppointmentDataAccess;
import Clinic.ClinicDataAccess;
import Patient.Patient;
import Patient.PatientDataAccess;
import Main.InputValidator;

import javax.management.InvalidApplicationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private AppointmentDataAccess appointmentDataAccess;
    private PatientDataAccess patientDataAccess;

    public Menu(AppointmentDataAccess appointmentDataAccess, PatientDataAccess patientDataAccess) {
        this.appointmentDataAccess = appointmentDataAccess;
        this.patientDataAccess = patientDataAccess;
    }

    public void displayMainMenu() {
        System.out.println("Clinic Booking System (CBS)");
        System.out.println("Type 1 to Enter Patient information");
        System.out.println("Type 2 to List all Patients");
        System.out.println("Type 3 to Update Patient information");
        System.out.println("Type 4 to Delete a Patient");
        System.out.println("Type 5 to View available appointments");
        System.out.println("Type 6 View scheduled appointments");
        System.out.println("Type 7 Create an appointment");
        System.out.println("Type 8 to Update an appointment");
        System.out.println("Type 9 to Delete an appointment");
        System.out.println("Type 10 to View report schedule appointments of the month");
        System.out.println("Type 11 to View details of Clinic");
        System.out.println("Type 0 to Exit");
        System.out.print("Select an option: ");
    }

    public void initiateMenu() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        while (!exit) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter patient ID, first name, last name, phone number:");
                    int patientId = scanner.nextInt();
                    scanner.nextLine();
                    String firstName = scanner.nextLine();
                    String lastName = scanner.nextLine();
                    String phoneNum = scanner.nextLine();
                    Patient newPatient = new Patient(patientId, firstName, lastName, phoneNum);
                    patientDataAccess.insertPatient(newPatient);
                    break;

                case 2:
                    PatientDataAccess.listAllPatients();
                    break;

                case 3:
                    System.out.println("Enter patient ID to update:");
                    int updatePatientId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline after numeric input
                    System.out.println("Enter new first name, last name, and phone number:");
                    String newFirstName = scanner.nextLine();
                    String newLastName = scanner.nextLine();
                    String newPhoneNum = scanner.nextLine();
                    Patient updatedPatient = new Patient(updatePatientId, newFirstName, newLastName, newPhoneNum);
                    patientDataAccess.updatePatient(updatedPatient);
                    break;

                case 4:
                    System.out.println("Enter patient ID to delete:");
                    int deletePatientId = scanner.nextInt();
                    scanner.nextLine();
                    patientDataAccess.deletePatient(deletePatientId);
                    break;

                case 5:
                    List<Appointment> availableAppointments = appointmentDataAccess.getAvailableAppointments();
                    for (Appointment appointment : availableAppointments) {
                        String formattedDate = dateFormat.format(appointment.getDate());
                        System.out.println("Appointment ID: " + appointment.getId() +
                                ", Date: " + formattedDate +
                                ", Time: " + appointment.getTime() +
                                ", Patient Name: " + appointment.getPatientName() +
                                ", Notes: " + appointment.getNotes());
                    }
                    break;

                case 6:
                    List<Appointment> scheduledAppointments = appointmentDataAccess.getScheduledAppointments();
                    for (Appointment appointment : scheduledAppointments) {
                        String formattedDate = dateFormat.format(appointment.getDate());
                        System.out.println("Appointment ID: " + appointment.getId() +
                                ", Date: " + formattedDate +
                                ", Time: " + appointment.getTime() +
                                ", Patient Name: " + appointment.getPatientName() +
                                ", Notes: " + appointment.getNotes());
                    }
                    break;

                case 7:
                    System.out.println("Enter appointment details (date in yyyy-MM-dd format, time, patient name, notes):");
                    String dateString = scanner.nextLine();
                    String time = scanner.nextLine();
                    String patientName = scanner.nextLine();
                    String notes = scanner.nextLine();
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString); // Convert string to Date
                    Appointment newAppointment = new Appointment(0, date, time, patientName, notes); // Appointment id is set 0, but generated by the Database later
                    appointmentDataAccess.createAppointment(newAppointment);
                    break;

                case 8:
                    System.out.println("Enter appointment ID to update:");
                    int updateAppointmentId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter new appointment details (date in yyyy-MM-dd format, time, patient name, notes):");
                    String newDateString = scanner.nextLine();
                    String newTime = scanner.nextLine();
                    String newPatientName = scanner.nextLine();
                    String newNotes = scanner.nextLine();
                    Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(newDateString);
                    Appointment updatedAppointment = new Appointment(updateAppointmentId, newDate, newTime, newPatientName, newNotes);
                    appointmentDataAccess.updateAppointment(updatedAppointment);
                    break;

                case 9:
                    System.out.println("Enter appointment ID to delete:");
                    int deleteAppointmentId = scanner.nextInt();
                    scanner.nextLine();
                    appointmentDataAccess.deleteAppointment(deleteAppointmentId);
                    break;

                case 10:
                    System.out.println("Enter the month and year:");
                    int month = scanner.nextInt();
                    int year = scanner.nextInt();
                    List<Appointment> monthlyAppointments = appointmentDataAccess.getAppointmentsByMonthYear(month, year);
                    for (Appointment appointment : monthlyAppointments) {
                        String formattedDate = dateFormat.format(appointment.getDate());
                        System.out.println("Appointment ID: " + appointment.getId() +
                                ", Date: " + formattedDate +
                                ", Time: " + appointment.getTime() +
                                ", Patient Name: " + appointment.getPatientName() +
                                ", Notes: " + appointment.getNotes());
                    }
                    break;

                case 11:
                    ClinicDataAccess.clinicInfo();
                    break;

                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }


}
