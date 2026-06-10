package user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {

    @BeforeEach
    void setUp() {
        new File(UserManager.getUserFile()).delete();
    }

    @AfterEach
    void tearDown() {
        new File(UserManager.getUserFile()).delete();
    }

    @Test
    void registerCreatesNewUser() {
        UserManager manager = new UserManager();
        assertTrue(manager.register("alice", "secret"));
    }

    @Test
    void registerDuplicateUsernameFails() {
        UserManager manager = new UserManager();
        manager.register("bob", "pass1");
        assertFalse(manager.register("bob", "pass2"));
    }

    @Test
    void loginSucceedsWithCorrectCredentials() {
        UserManager manager = new UserManager();
        manager.register("carol", "mypassword");
        assertTrue(manager.login("carol", "mypassword"));
    }

    @Test
    void loginFailsWithWrongPassword() {
        UserManager manager = new UserManager();
        manager.register("dave", "rightpass");
        assertFalse(manager.login("dave", "wrongpass"));
    }

    @Test
    void loginFailsForUnknownUser() {
        UserManager manager = new UserManager();
        assertFalse(manager.login("ghost", "whatever"));
    }

    @Test
    void changePasswordUpdatesCredentials() {
        UserManager manager = new UserManager();
        manager.register("erin", "oldpass");
        manager.changePassword("erin", "oldpass", "newpass");

        assertFalse(manager.login("erin", "oldpass"));
        assertTrue(manager.login("erin", "newpass"));
    }

    @Test
    void deleteAccountRemovesUser() {
        UserManager manager = new UserManager();
        manager.register("frank", "pass");
        manager.deleteAccount("frank", "pass");

        assertFalse(manager.login("frank", "pass"));
    }

    @Test
    void deleteAccountWithWrongPasswordThrows() {
        UserManager manager = new UserManager();
        manager.register("grace", "pass");
        assertThrows(IllegalArgumentException.class,
                () -> manager.deleteAccount("grace", "wrong"));
    }
}
