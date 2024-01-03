package Tests;

import User.User;
import org.junit.Test;
import static org.junit.Assert.*;
public class UserTest {
        @Test
        public void testGetName() {
            // Create a User object
            String expectedName = "doctor";
            String password = "1234";
            User user = new User(expectedName, password);

            String actualName = user.getName();

            assertEquals("Names should match", expectedName, actualName);
        }

    }
