import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

public class TaskRepositoryTest {
    @Test
    public void saveNullTask() {
        TaskRepository t = new TaskRepository();
        assertThrows(IllegalArgumentException.class, () -> {
            t.saveTask(null);
        });
    }
    @Test
    public void saveAlreadyAddedTask(){
        TaskRepository r = new TaskRepository();
        Project p = new Project("app", "application");
        User u = new User("Sally", "s@gmail.com");
        Task t = new Task("design", "design", p, u);
        r.saveTask(t);
        assertThrows(IllegalStateException.class, () -> {
            r.saveTask(t);
        });
    }
    @Test
    public void saveTaskSuccessfully(){
        TaskRepository r = new TaskRepository();
        Project p = new Project("app", "application");
        User u = new User("Sally", "s@gmail.com");
        Task t = new Task("design", "design", p, u);
        r.saveTask(t);
        assertEquals(t, r.findTaskById(t.getId()));
        assertEquals(1, r.getAllTasks().size());
    }
    @Test
    public void findNonExistingTaskById() {
        TaskRepository r = new TaskRepository();
        Project p = new Project("app", "application");
        User u = new User("Sally", "s@gmail.com");
        Task t = new Task("design", "design", p, u);
        assertThrows(IllegalStateException.class, () -> {
            r.findTaskById(t.getId());
        });
    }
    @Test
    public void findTaskByIdSuccessfully() {
        TaskRepository r = new TaskRepository();
        Project p = new Project("app", "application");
        User u = new User("Sally", "s@gmail.com");
        Task t = new Task("design", "design", p, u);
        r.saveTask(t);
        assertEquals(t, r.findTaskById(t.getId()));
    }
    @Test
    public void deleteNonExistingTaskById() {
        TaskRepository r = new TaskRepository();
        Project p = new Project("app", "application");
        User u = new User("Sally", "s@gmail.com");
        Task t = new Task("design", "design", p, u);
        assertThrows(IllegalStateException.class, () -> {
            r.deleteTaskById(t.getId());
        });
    }
    @Test
    public void deleteTaskByIdSuccessfully() {
        TaskRepository r = new TaskRepository();
        Project p = new Project("app", "application");
        User u = new User("Sally", "s@gmail.com");
        Task t = new Task("design", "design", p, u);
        Task t1 = new Task("second", "desc", p, u);
        r.saveTask(t);
        r.saveTask(t1);
        r.deleteTaskById(t.getId());
        assertThrows(IllegalStateException.class, () -> {
            r.findTaskById(t.getId());
        });
        assertEquals(t1, r.findTaskById(t1.getId()));
        assertEquals(1, r.getAllTasks().size());
    }
    @Test
    public void getAllTasksReturnsDefensiveCopy() {
        TaskRepository r = new TaskRepository();
        Project p = new Project("app", "application");
        User u = new User("Sally", "s@gmail.com");
        Task t = new Task("design", "design", p, u);
        Task t1 = new Task("second", "desc", p, u);
        r.saveTask(t);
        r.saveTask(t1);
        HashMap<Integer, Task> r2 = r.getAllTasks();
        r2.clear();
        assertTrue(r2.isEmpty());
        assertEquals(2, r.getAllTasks().size());
    }
    @Test
    public void getAllTasksReturnsCorrectContents() {
        TaskRepository r = new TaskRepository();
        Project p = new Project("app", "application");
        User u = new User("Sally", "s@gmail.com");
        Task t = new Task("design", "design", p, u);
        Task t1 = new Task("second", "desc", p, u);
        r.saveTask(t);
        r.saveTask(t1);
        HashMap<Integer, Task> r1Map = new HashMap<>(Map.of(
            t.getId(), t,
            t1.getId(), t1
        ));
        assertEquals(r1Map, r.getAllTasks());
    }
    @Test
    public void constructorTest() {
        TaskRepository r = new TaskRepository();
        assertTrue(r.getAllTasks().isEmpty());
    }
}
