package Main;

import Appointment.Appointment;
import Appointment.AppointmentDataAccess;
import Clinic.ClinicDataAccess;
import Patient.Patient;
import Patient.PatientDataAccess;
import Main.InputValidator;
import ReportGenerator.ReportGenerator;

import java.sql.*;

import javax.management.InvalidApplicationException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private AppointmentDataAccess appointmentDataAccess;
    private PatientDataAccess patientDataAccess;

    private ClinicDataAccess clinicDataAccess;

    public Menu(AppointmentDataAccess appointmentDataAccess, PatientDataAccess patientDataAccess, ClinicDataAccess clinicDataAccess) {
        this.appointmentDataAccess = appointmentDataAccess;
        this.patientDataAccess = patientDataAccess;
        this.clinicDataAccess = clinicDataAccess;
    }

    public void displayMainMenu() {
        System.out.println("Clinic Booking System (CBS)");
        System.out.println("Type 1 to Enter Patient information");
        System.out.println("Type 2 to List all Patients");
        System.out.println("Type 3 to Update Patient information");
        System.out.println("Type 4 to Delete a Patient");
        System.out.println("Type 5 to View available appointments");
        System.out.println("Type 6 Create an appointment");
        System.out.println("Type 7 to Update an appointment");
        System.out.println("Type 8 to Delete an appointment");
        System.out.println("Type 9 to View report schedule appointments of the month");
        System.out.println("Type 10 to View details of Clinic");
        System.out.println("Type 0 to Exit");
        System.out.print("Select an option: ");
    }

    public void initiateMenu() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int choice = 0;
        boolean validInput = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        while (!exit) {
            displayMainMenu();
            while (true) {
                System.out.println("Enter an integer between 0 and 11: ");

                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();

                    // Check if the choice is within the range [0, 11]
                    if (choice >= 0 && choice <= 11) {
                        scanner.nextLine();
                        break; // Input is valid, exit the loop
                    } else {
                        System.out.println("Invalid input. Number must be between 0 and 11.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter an integer.");
                    scanner.next(); // Clear the invalid input
                }
            }
            switch (choice) {
                case 1:
                    validInput = false;
                    int patientId = 0;
                    while (!validInput) {
                        System.out.println("Enter patient ID (integer only), first name, last name, phone number:");
                        String input = scanner.nextLine(); // Read the whole line as a string
                        try {
                            patientId = Integer.parseInt(input); // Try to parse the integer
                            validInput = true; // If successful, set validInput to true to exit the loop
                        } catch (NumberFormatException e) {
                            System.out.println("Patient ID must be an integer. Please try again.");
                        }
                    }
                    String firstName = scanner.nextLine();
                    String lastName = scanner.nextLine();
                    String phoneNum = scanner.nextLine();
                    Patient newPatient = new Patient(patientId, firstName, lastName, phoneNum);
                    patientDataAccess.insertPatient(newPatient);
                    break;

                case 2:
                    patientDataAccess.listAllPatients();
                    break;

                case 3:
                    validInput = false;
                    System.out.println("Enter patient ID to update:");
                    int updatePatientId = 0;
                    while (!validInput) {
                        String input = scanner.nextLine();
                        try {
                            updatePatientId = Integer.parseInt(input);
                            validInput = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Patient ID must be an integer. Please try again.");
                        }
                    }
                    System.out.println("Enter new first name, last name, and phone number:");
                    String newFirstName = scanner.nextLine();
                    String newLastName = scanner.nextLine();
                    String newPhoneNum = scanner.nextLine();
                    Patient updatedPatient = new Patient(updatePatientId, newFirstName, newLastName, newPhoneNum);
                    patientDataAccess.updatePatient(updatedPatient);
                    break;

                case 4:
                    validInput = false;
                    System.out.println("Enter patient ID to delete:");
                    int deletePatientId = 0;
                    while (!validInput) {
                        String input = scanner.nextLine();
                        try {
                            deletePatientId = Integer.parseInt(input);
                            validInput = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Patient ID must be an integer. Please try again.");
                        }
                    }
                    patientDataAccess.deletePatient(deletePatientId);
                    break;

                case 5:
                    List<Appointment> availableAppointments = appointmentDataAccess.listAllAppointments();
                    for (Appointment appointment : availableAppointments) {
                        String formattedDate = dateFormat.format(appointment.getDate());
                        System.out.println("Appointment ID: " + appointment.getAppointment_id() +
                                ", Date: " + formattedDate +
                                ", Time: " + appointment.getTime() +
                                ", Patient Name: " + appointment.getPatient_name() +
                                ", Prescription: " + appointment.getPrescription());
                    }
                    break;

                case 6:
                    System.out.println("Enter appointment date (yyyy-MM-dd):");
                    String dateString = scanner.nextLine();

                    System.out.println("Enter appointment time (HH:mm):");
                    String time = scanner.nextLine();

                    System.out.println("Enter patient name:");
                    String patientName = scanner.nextLine();

                    System.out.println("Enter notes:");
                    String notes = scanner.nextLine();

                    try{
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString); // Convert string to Date
                        Appointment newAppointment = new Appointment(0, date, time, patientName, notes); // Appointment id is set 0, but generated by the Database later
                        appointmentDataAccess.createAppointment(newAppointment);
                        System.out.println("Appointment created successfully.");
                    } catch (ParseException e) {
                        System.out.println("Error: Invalid date format. Please enter the date in the format yyyy-MM-dd.");
                    }
                    break;

                case 7:
                    validInput = false;
                    System.out.println("Enter appointment ID to update:");
                    int updateAppointmentId = 0;
                    while (!validInput) {
                        String input = scanner.nextLine();
                        try {
                            updateAppointmentId = Integer.parseInt(input);
                            validInput = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Appointment ID must be an integer. Please try again.");
                        }
                    }
                    System.out.println("Enter new appointment details (date in yyyy-MM-dd format, time, patient name, notes):");
                    String newDateString = scanner.nextLine();
                    String newTime = scanner.nextLine();
                    String newPatientName = scanner.nextLine();
                    String newNotes = scanner.nextLine();
                    Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(newDateString);
                    Appointment updatedAppointment = new Appointment(updateAppointmentId, newDate, newTime, newPatientName, newNotes);
                    appointmentDataAccess.updateAppointment(updatedAppointment);
                    break;

                case 8:
                    validInput = false;
                    System.out.println("Enter appointment ID to delete:");
                    int deleteAppointmentId = 0;
                    while (!validInput) {
                        String input = scanner.nextLine();
                        try {
                            deleteAppointmentId = Integer.parseInt(input);
                            validInput = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Appointment ID must be an integer. Please try again.");
                        }
                    }
                    appointmentDataAccess.deleteAppointment(deleteAppointmentId);
                    break;

                case 9:
                    ReportGenerator reportGenerator = new ReportGenerator(appointmentDataAccess);
                    reportGenerator.generateSummaryReport(31);

                    break;

                case 10:
                    clinicDataAccess.getClinicInfo();
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
