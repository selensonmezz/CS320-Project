package ReportGenerator;

import Appointment.Appointment;
import Appointment.AppointmentDataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    }