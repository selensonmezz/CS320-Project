package Tests;

import Main.DatabaseConnector;
import ReportGenerator.ReportGenerator;
import Appointment.Appointment;
import Appointment.AppointmentDataAccess;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReportGeneratorTest {

    private ReportGenerator reportGenerator;
    private AppointmentDataAccess appointmentDataAccess;

    @Before
    public void setUp() {
        // Initialize the connection and AppointmentDataAccess before each test
        DatabaseConnector.establishConnection();
        appointmentDataAccess = new AppointmentDataAccess(DatabaseConnector.getConnection());
        reportGenerator = new ReportGenerator(appointmentDataAccess);
    }

    @Test
    public void testIsLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        boolean result = reportGenerator.isLastDayOfMonth(lastDayOfMonth);
        assertTrue(result);

        result = reportGenerator.isLastDayOfMonth(lastDayOfMonth - 1);
        assertFalse(result);
    }

    @Test
    public void testFilterLastMonthAppointments() {
        List<Appointment> appointments = appointmentDataAccess.listAllAppointments();

        List<Appointment> filteredAppointments = reportGenerator.filterLastMonthAppointments(appointments);

        for (Appointment appointment : filteredAppointments) {
            assertTrue(isDateInLastMonth(appointment.getDate()));
        }
    }

    private boolean isDateInLastMonth(Date date) {
        Calendar current = Calendar.getInstance();
        Calendar appointmentDate = Calendar.getInstance();
        appointmentDate.setTime(date);

        // Adjust 'current' to the first day of the current month
        current.set(Calendar.DAY_OF_MONTH, 1);

        // Check if the appointment date is before the current month
        boolean isBeforeCurrentMonth = appointmentDate.before(current);

        // Adjust 'current' to the first day of the last month
        current.add(Calendar.MONTH, -1);

        // Check if the appointment date is after or equal to the first day of the last month
        boolean isAfterOrEqualLastMonthStart = appointmentDate.after(current) || appointmentDate.equals(current);

        return isBeforeCurrentMonth && isAfterOrEqualLastMonthStart;
    }




}
