package user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @BeforeEach
    void setUp() {
        new File(UserManager.getUserFile()).delete();
    }

    @AfterEach
    void tearDown() {
        new File(UserManager.getUserFile()).delete();
    }

    @Test
    void accountCreatedForValidCredentials() {
        UserManager manager = new UserManager();
        manager.register("heidi", "pass123");

        Account account = new Account("heidi", "pass123", manager);
        assertEquals("heidi", account.getUsername());
        assertEquals("pass123", account.getPassword());
    }

    @Test
    void accountCreationFailsForInvalidCredentials() {
        UserManager manager = new UserManager();
        manager.register("ivan", "correct");

        assertThrows(IllegalArgumentException.class,
                () -> new Account("ivan", "wrong", manager));
    }
}
