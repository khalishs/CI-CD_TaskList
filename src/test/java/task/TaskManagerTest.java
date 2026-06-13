package task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    private static final String TEST_USER = "junit_test_user";
    private String futureDate;

    @BeforeEach
    void setUp() {
        new File(TEST_USER + ".dat").delete();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        // a date well in the future so it always passes the "not in the past" check
        futureDate = sdf.format(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365));
    }

    @AfterEach
    void tearDown() {
        new File(TEST_USER + ".dat").delete();
    }

    @Test
    void addPersonalTaskStoresTask() {
        TaskManager manager = new TaskManager(TEST_USER);
        manager.addTask("Belajar", "Belajar CI/CD", futureDate, "Personal");

        assertEquals(1, manager.getTasks().size());
        assertEquals("Personal", manager.getTasks().get(0).getTaskType());
    }

    @Test
    void addWorkTaskStoresTask() {
        TaskManager manager = new TaskManager(TEST_USER);
        manager.addTask("Rapat", "Rapat tim", futureDate, "Work");

        assertEquals(1, manager.getTasks().size());
        assertEquals("Work", manager.getTasks().get(0).getTaskType());
    }

    @Test
    void addTaskWithInvalidTypeIsRejected() {
        TaskManager manager = new TaskManager(TEST_USER);
        manager.addTask("Salah", "Tipe salah", futureDate, "Unknown");

        assertTrue(manager.getTasks().isEmpty());
    }

    @Test
    void addTaskWithInvalidDateIsRejected() {
        TaskManager manager = new TaskManager(TEST_USER);
        manager.addTask("Salah", "Tanggal salah", "not-a-date", "Personal");

        assertTrue(manager.getTasks().isEmpty());
    }

    @Test
    void editTaskUpdatesFields() {
        TaskManager manager = new TaskManager(TEST_USER);
        manager.addTask("Lama", "Deskripsi lama", futureDate, "Personal");
        manager.editTask(0, "Baru", "Deskripsi baru", futureDate);

        Task edited = manager.getTasks().get(0);
        assertEquals("Baru", edited.getTaskName());
        assertEquals("Deskripsi baru", edited.getDescription());
    }

    @Test
    void editTaskWithInvalidIdDoesNotThrow() {
        TaskManager manager = new TaskManager(TEST_USER);
        assertDoesNotThrow(() -> manager.editTask23(99, "X", "Y", futureDate));
    }

    @Test
    void deleteTaskRemovesTask() {
        TaskManager manager = new TaskManager(TEST_USER);
        manager.addTask("Hapus", "Akan dihapus", futureDate, "Personal");
        manager.deleteTask(0);

        assertTrue(manager.getTasks().isEmpty());
    }

    @Test
    void tasksPersistAcrossManagerInstances() {
        TaskManager manager = new TaskManager(TEST_USER);
        manager.addTask("Persisten", "Tersimpan di file", futureDate, "Work");

        TaskManager reloaded = new TaskManager(TEST_USER);
        assertEquals(1, reloaded.getTasks().size());
        assertEquals("Persisten", reloaded.getTasks().get(0).getTaskName());
    }
}
