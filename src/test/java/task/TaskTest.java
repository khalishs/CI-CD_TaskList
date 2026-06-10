package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void personalTaskReturnsCorrectType() {
        Task task = new PersonalTask("Belajar Java", "Polymorphism", "01-01-2025", "12-12-2025");
        assertEquals("Personal", task.getTaskType());
    }

    @Test
    void workTaskReturnsCorrectType() {
        Task task = new WorkTask("Laporan", "Laporan bulanan", "01-01-2025", "12-12-2025");
        assertEquals("Work", task.getTaskType());
    }

    @Test
    void gettersReturnConstructorValues() {
        Task task = new PersonalTask("Nama", "Deskripsi", "01-01-2025", "12-12-2025");
        assertEquals("Nama", task.getTaskName());
        assertEquals("Deskripsi", task.getDescription());
        assertEquals("01-01-2025", task.getCreatedDate());
        assertEquals("12-12-2025", task.getDueDate());
    }

    @Test
    void settersUpdateValues() {
        Task task = new WorkTask("Lama", "Deskripsi lama", "01-01-2025", "12-12-2025");
        task.setTaskName("Baru");
        task.setDescription("Deskripsi baru");
        task.setDueDate("31-12-2025");

        assertEquals("Baru", task.getTaskName());
        assertEquals("Deskripsi baru", task.getDescription());
        assertEquals("31-12-2025", task.getDueDate());
    }

    @Test
    void toStringContainsTaskDetails() {
        Task task = new PersonalTask("Nama", "Deskripsi", "01-01-2025", "12-12-2025");
        String text = task.toString();
        assertTrue(text.contains("Nama"));
        assertTrue(text.contains("Deskripsi"));
        assertTrue(text.contains("12-12-2025"));
    }
}
