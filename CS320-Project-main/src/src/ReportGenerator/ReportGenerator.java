package ReportGenerator;

import Appointment.Appointment;
import Appointment.AppointmentDataAccess;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
    public class ReportGenerator {

        private final AppointmentDataAccess appointmentDataAccess;

        public ReportGenerator(AppointmentDataAccess appointmentDataAccess) {
            this.appointmentDataAccess = appointmentDataAccess;
        }

        public void generateSummaryReport(int dayToCheck) {
            if (!isLastDayOfMonth(dayToCheck)) {
                System.out.println("Warning: The specified day is not the last day of the month. Report not generated.");
                return;
            }

            List<Appointment> allAppointments = appointmentDataAccess.listAllAppointments();
            List<Appointment> lastMonthAppointments = filterLastMonthAppointments(allAppointments);
            displaySummaryReport(lastMonthAppointments);

        }

        private boolean isLastDayOfMonth(int dayToCheck) {
            Calendar calendar = Calendar.getInstance();
            int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            return dayToCheck == lastDayOfMonth;
        }

        private List<Appointment> filterLastMonthAppointments(List<Appointment> appointments) {
            Calendar lastMonth = Calendar.getInstance();
            lastMonth.add(Calendar.MONTH, -1);

            java.util.Date lastMonthStart = lastMonth.getTime();
            lastMonth.set(Calendar.DAY_OF_MONTH, lastMonth.getActualMaximum(Calendar.DAY_OF_MONTH));
            java.util.Date lastMonthEnd = lastMonth.getTime();

            return appointments.stream()
                    .filter(appointment -> appointment.getDate().after(lastMonthStart) && appointment.getDate().before(lastMonthEnd))
                    .toList();
        }

        private void displaySummaryReport(List<Appointment> appointments) {
            System.out.println("Last Month's Appointments Summary Report:");
            System.out.println("----------------------------------------");

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            for (Appointment appointment : appointments) {
                System.out.println("ID: " + appointment.getAppointment_id());
                System.out.println("Date: " + dateFormat.format(appointment.getDate()));
                System.out.println("Time: " + appointment.getTime());
                System.out.println("Patient Name: " + appointment.getPatient_name());
                System.out.println("Patient Prescription: " + appointment.getPrescription());
                System.out.println("----------------------------------------");
            }
        }
    }
