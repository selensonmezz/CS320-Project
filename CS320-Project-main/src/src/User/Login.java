package User;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class Login {

        public static boolean login () {
            try {
                File file = new File("C:/Users/deniz/Documents/GitHub/CS320-Project/CS320-Project-main/doctor_info.txt");
                Scanner fileScanner = new Scanner(file);

                String docName = fileScanner.nextLine();
                String docPassword = fileScanner.nextLine();
                fileScanner.close();

                Scanner scanner = new Scanner(System.in);
                int loginAttempts = 0;

                while (loginAttempts < 3) {
                    System.out.print("Enter your name: ");
                    String enteredName = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String enteredPassword = scanner.nextLine();

                    if (enteredName.equals(docName) && enteredPassword.equals(docPassword)) {
                        System.out.println("Welcome, " + docName + "!");
                        return true;
                    } else {
                        loginAttempts++;
                        int attemptsLeft = 3 - loginAttempts;
                        if (attemptsLeft > 0) {
                            System.out.println("Incorrect name or password. You have " + attemptsLeft + " attempts left.");
                        } else {
                            System.out.println("Login failed. You've exceeded the maximum number of attempts.");
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File not found: " + e.getMessage());
            }
            return false;
        }
    }

