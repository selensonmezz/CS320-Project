package Tests;

import User.Login;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import static org.junit.Assert.*;

    public class LoginTest {

        @Test
        public void testLogin_Successful() {
            // Simulating user input for successful login
            String input = "doctor\n1234\ndoctor\n1234\n";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);

            // Redirecting System.out to capture the output
            Scanner scanner = new Scanner(System.out);
            String expectedOutput = "Welcome, doctor!\n";

            // Redirecting System.out to capture the output
            String actualOutput = captureOutput(() -> Login.login());

            // Reset System.in and System.out
            System.setIn(System.in);
            System.setOut(System.out);

            assertEquals("Login should be successful", expectedOutput, actualOutput);
        }

        @Test
        public void testLogin_Unsuccessful() {
            // Simulating user input for unsuccessful login
            String input = "InvalidName\nInvalidPassword\nInvalidName\nInvalidPassword\nInvalidName\nInvalidPassword\n";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);

            // Redirecting System.out to capture the output
            Scanner scanner = new Scanner(System.out);
            String expectedOutput = "Incorrect name or password. You have 2 attempts left.\n" +
                    "Incorrect name or password. You have 1 attempts left.\n" +
                    "Login failed. You've exceeded the maximum number of attempts.\n";

            // Redirecting System.out to capture the output
            String actualOutput = captureOutput(() -> Login.login());

            // Reset System.in and System.out
            System.setIn(System.in);
            System.setOut(System.out);

            assertEquals("Login should be unsuccessful", expectedOutput, actualOutput);
        }

        // Helper method to capture console output
        private String captureOutput(Runnable code) {
            InputStream originalIn = System.in;
            InputStream originalOut = System.out;

            try {
                // Redirect output to capture the console output
                final StringBuilder consoleOutput = new StringBuilder();
                System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
                    @Override
                    public void write(int b) {
                        consoleOutput.append((char) b);
                    }
                }));

                code.run(); // Execute the code that will generate console output

                return consoleOutput.toString();
            } finally {
                System.setIn(originalIn);
                System.setOut(new java.io.PrintStream(originalOut));
            }
        }
    }
}

