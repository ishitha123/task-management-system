import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test 
    public void nullTitleThrowsError() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "desc", new Project("name", "description"), new User("u", "u@gmail.com"));
        });
    }
    @Test 
    public void blankTitleThrowsError() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("", "desc", new Project("name", "description"), new User("u", "u@gmail.com"));
        });
    }
    @Test 
    public void nullProjectThrowsError() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("name", "desc", null, new User("u", "u@gmail.com"));
        });
    }
    @Test 
    public void assignToNullUser() {
        Task t = new Task("title", "desc", new Project("name", "description"), new User("u", "u@gmail.com"));
        assertThrows(IllegalArgumentException.class, () -> {
            t.assignToUser(null);
        });
    }
    @Test 
    public void changeToNullStatus() {
        Task t = new Task("title", "desc", new Project("name", "description"), new User("u", "u@gmail.com"));
        assertThrows(IllegalArgumentException.class, () -> {
            t.changeStatus(null);
        });
    }
    @Test 
    public void successfullyCreatedTask() {
        Project p = new Project("name", "description");
        User u = new User("u", "u@gmail.com");
        Task t = new Task("title", "desc", p, u);
        assertEquals("title", t.getTitle());
        assertEquals("desc", t.getDescription());
        assertEquals(p, t.getProject());
        assertEquals(u, t.getUser());
        assertEquals(TaskStatus.TODO, t.getStatus());
    }
    @Test
    public void successfulTaskWithNullUser() {
        Project p = new Project("name", "description");
        Task t = new Task("title", "desc", p, null);
        assertEquals("title", t.getTitle());
        assertEquals("desc", t.getDescription());
        assertEquals(p, t.getProject());
        assertNull(t.getUser());
        assertEquals(TaskStatus.TODO, t.getStatus());
    }
    @Test 
    public void reassignUserSuccessfully() {
        Project p = new Project("name", "description");
        User u = new User("u", "u@gmail.com");
        Task t = new Task("title", "desc", p, u);
        User u2 = new User("u2", "u2@gmail.com");
        t.assignToUser(u2);
        assertEquals(u2, t.getUser());
    }
    @Test 
    public void changeStatusSuccessfully(){
        Project p = new Project("name", "description");
        User u = new User("u", "u@gmail.com");
        Task t = new Task("title", "desc", p, u);
        t.changeStatus(TaskStatus.DONE);
        assertEquals(TaskStatus.DONE, t.getStatus());
    }
    @Test 
    public void unassignNullUser() {
        Project p = new Project("name", "description");
        Task t = new Task("title", "desc", p, null);
        assertThrows(IllegalStateException.class, () -> {
            t.unassignUser();
        });
    }
    @Test
    public void unassignUserSuccessful() {
        Project p = new Project("name", "description");
        User u = new User("u", "u@gmail.com");
        Task t = new Task("title", "desc", p, u);
        t.unassignUser();
        assertNull(t.getUser());
    }
    // Add test for creating task with null description
    @Test
    public void createTaskWithNullDescription() {
        Project p = new Project("name", "description");
        User u = new User("u", "u@gmail.com");
        Task t = new Task("title", null, p, u);
        assertNull(t.getDescription());
    }
    @Test 
    public void changeDescriptionToNull(){
        Project p = new Project("name", "description");
        User u = new User("u", "u@gmail.com");
        Task t = new Task("title", "desc", p, u);
        t.editDescription(null);
        assertNull(t.getDescription());
    }
    @Test 
    public void changeDescriptionToNotNull() {
        Project p = new Project("name", "description");
        User u = new User("u", "u@gmail.com");
        Task t = new Task("title", null, p, u);
        t.editDescription("desc");
        assertEquals("desc", t.getDescription());
    }
}
