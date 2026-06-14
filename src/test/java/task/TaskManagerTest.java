package task;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    @Test
    void test1() {
        new File("junit_test_user.dat").delete();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String d = sdf.format(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365));

        TaskManager m = new TaskManager("junit_test_user");
        m.addTask("Belajar", "Belajar CI/CD", d, "Personal");

        assertEquals(1, m.getTasks().size());
        assertEquals("Personal", m.getTasks().get(0).getTaskType());

        new File("junit_test_user.dat").delete();
    }

    @Test
    void test2() {
        new File("junit_test_user.dat").delete();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String d = sdf.format(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365));

        TaskManager m = new TaskManager("junit_test_user");
        m.addTask("Rapat", "Rapat Tim", d, "Work");

        assertEquals(1, m.getTasks().size());
        assertEquals("Work", m.getTasks().get(0).getTaskType());

        new File("junit_test_user.dat").delete();
    }

    @Test
    void test3() {
        new File("junit_test_user.dat").delete();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String d = sdf.format(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365));

        TaskManager m = new TaskManager("junit_test_user");
        m.addTask("Salah", "Tipe salah", d, "Unknown");

        assertTrue(m.getTasks().isEmpty());

        new File("junit_test_user.dat").delete();
    }

    @Test
    void test4() {
        new File("junit_test_user.dat").delete();

        TaskManager m = new TaskManager("junit_test_user");
        m.addTask("Salah", "Tanggal salah", "not-a-date", "Personal");

        assertTrue(m.getTasks().isEmpty());

        new File("junit_test_user.dat").delete();
    }

    @Test
    void test5() {
        new File("junit_test_user.dat").delete();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String d = sdf.format(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365));

        TaskManager m = new TaskManager("junit_test_user");

        m.addTask("Lama", "Deskripsi lama", d, "Personal");
        m.editTask(0, "Baru", "Deskripsi baru", d);

        assertEquals("Baru", m.getTasks().get(0).getTaskName());
        assertEquals("Deskripsi baru", m.getTasks().get(0).getDescription());

        new File("junit_test_user.dat").delete();
    }

    @Test
    void test6() {
        TaskManager x = new TaskManager("junit_test_user");

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String abc = sdf.format(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365));

        assertDoesNotThrow(() -> x.editTask(99, "X", "Y", abc));
    }

    @Test
    void test7() {
        new File("junit_test_user.dat").delete();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String d = sdf.format(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365));

        TaskManager m = new TaskManager("junit_test_user");

        m.addTask("Hapus", "Akan dihapus", d, "Personal");
        m.deleteTask(0);

        assertTrue(m.getTasks().isEmpty());

        new File("junit_test_user.dat").delete();
    }

    @Test
    void test8() {
        new File("junit_test_user.dat").delete();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String d = sdf.format(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365));

        TaskManager m = new TaskManager("junit_test_user");

        m.addTask("Persisten", "Tersimpan di file", d, "Work");

        TaskManager y = new TaskManager("junit_test_user");

        assertEquals(1, y.getTasks().size());
        assertEquals("Persisten", y.getTasks().get(0).getTaskName());

        new File("junit_test_user.dat").delete();
    }
}
