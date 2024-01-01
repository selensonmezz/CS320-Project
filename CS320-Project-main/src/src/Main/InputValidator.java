package Main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class InputValidator {

    public static boolean isValidDate(String input) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            Date date = dateFormat.parse(input);

            return true;
        } catch (ParseException e) {

            return false;
        }
    }

    public static boolean isValidTime(String input) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setLenient(false); // Disable leniency

        try {
            Date time = timeFormat.parse(input);

            return true;
        } catch (ParseException e) {

            return false;
        }
    }

    public static boolean isNonEmptyString(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidChoice(String input, int min, int max) {
        int choice = Integer.parseInt(input);
        return choice >= min && choice <= max;
    }

}