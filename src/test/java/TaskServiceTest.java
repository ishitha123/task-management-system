import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {
    @Test
    public void createTaskNullTitle(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        User u = new User("Jimmy", "jimmy@gmail.com");
        int initialSize = r.getAllTasks().size();
        assertThrows(IllegalArgumentException.class, () -> {
            t.createTask(null, null, p, u);
        });
        assertEquals(initialSize, r.getAllTasks().size());
    }
    @Test
    public void createTaskNullProject(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        User u = new User("Jimmy", "jimmy@gmail.com");
        int initialSize = r.getAllTasks().size();
        assertThrows(IllegalArgumentException.class, () -> {
            t.createTask("Finish coding", "desc", null, u);
        });
        assertEquals(initialSize, r.getAllTasks().size());
    }
    @Test
    public void createTaskBlankTitle(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        User u = new User("Jimmy", "jimmy@gmail.com");
        int initialSize = r.getAllTasks().size();
        assertThrows(IllegalArgumentException.class, () -> {
            t.createTask("", null, p, u);
        });
        assertEquals(initialSize, r.getAllTasks().size());
    }
    @Test
    public void createTaskWhitespaceTitle(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        User u = new User("Jimmy", "jimmy@gmail.com");
        int initialSize = r.getAllTasks().size();
        assertThrows(IllegalArgumentException.class, () -> {
            t.createTask(" ", null, p, u);
        });
        assertEquals(initialSize, r.getAllTasks().size());
    }
    @Test
    public void createTaskSuccessfullyNonNullUser(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        User u = new User("Jimmy", "jimmy@gmail.com");
        int initialSize = r.getAllTasks().size();
        Task newTask = t.createTask("Set up project", null, p, u);
        assertEquals((initialSize+1), r.getAllTasks().size());
        assertTrue(r.getAllTasks().containsKey(newTask.getId()));
        assertEquals("Set up project", newTask.getTitle());
        assertNull(newTask.getDescription());
        assertEquals(p, newTask.getProject());
        assertEquals(u, newTask.getUser());
        assertSame(newTask, r.getAllTasks().get(newTask.getId()));
    }
    @Test
    public void createTaskSuccessfullyWithNullUser(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        User u = null;
        int initialSize = r.getAllTasks().size();
        Task newTask = t.createTask("Set up project", null, p, u);
        assertEquals((initialSize+1), r.getAllTasks().size());
        assertTrue(r.getAllTasks().containsKey(newTask.getId()));
        assertEquals("Set up project", newTask.getTitle());
        assertNull(newTask.getDescription());
        assertEquals(p, newTask.getProject());
        assertNull(newTask.getUser());
        assertSame(newTask, r.getAllTasks().get(newTask.getId()));
    }
    @Test
    public void successfulTaskRetrieval(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        Task newTask = t.createTask("Set up project", null, p, null);
        Task retrievedTask = t.retrieveTask(newTask.getId());
        assertSame(newTask, retrievedTask);
    }
    @Test
    public void retrieveNonexistentTask(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        assertThrows(IllegalStateException.class, () -> {
            t.retrieveTask(0);
        });
    }
    @Test
    public void assigningNonexistentTask(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        User u = new User("Jimmy", "jimmy@gmail.com");
        Task task = new Task("Develop app", "desc", p, u);
        assertThrows(IllegalStateException.class, () -> {
            t.assignTask(u, task.getId());
        });
    }
    @Test
    public void assignToSameUser(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        User u = new User("Jimmy", "jimmy@gmail.com");
        Task task = t.createTask("task", "desc", p, u);
        t.assignTask(u, task.getId());
        assertSame(u, task.getUser());
    }
    @Test
    public void assignToDifferentUser(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        User u = new User("Jimmy", "jimmy@gmail.com");
        User u1 = new User("Abby", "abby@yahoo.com");
        Task task = t.createTask("task", "desc", p, u);
        t.assignTask(u1, task.getId());
        assertSame(u1, task.getUser());
    }
    @Test
    public void assignToUserFirstTime(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        User u = new User("Jimmy", "jimmy@gmail.com");
        Task task = t.createTask("task", "desc", p, null);
        t.assignTask(u, task.getId());
        assertSame(u, task.getUser());
    }
    @Test
    public void removeNonExistentAssignment(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        Task task = t.createTask("Develop app", "desc", p, null);
        assertThrows(IllegalStateException.class, () -> {
            t.removeAssignment(task.getId());
        });
    }
    @Test
    public void removeAssignmentForNonexistentTask(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        User u = new User("Jimmy", "jimmy@gmail.com");
        Task task = new Task("Develop app", "desc", p, u);
        assertThrows(IllegalStateException.class, () -> {
            t.removeAssignment(task.getId());
        });
    }
    @Test
    public void removeAssignmentSuccessfully(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        User u = new User("Jimmy", "jimmy@gmail.com");
        Task task = t.createTask("task", "desc", p, u);
        assertNotNull(task.getUser());
        t.removeAssignment(task.getId());
        assertNull(task.getUser());
    }
    @Test
    public void assignTaskToNullUser(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        User u = new User("Jimmy", "jimmy@gmail.com");
        Task task = t.createTask("task", "desc", p, u);
        assertThrows(IllegalArgumentException.class, () -> {
            t.assignTask(null, task.getId());
        });
        assertSame(u, task.getUser());
    }
    @Test
    public void changeTaskStatusSuccessfully(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        User u = new User("Jimmy", "jimmy@gmail.com");
        Task task = t.createTask("task", "desc", p, u);
        TaskStatus previousStatus = task.getStatus();
        assertEquals(TaskStatus.TODO, previousStatus);
        t.updateStatus(task.getId(), TaskStatus.IN_PROGRESS);
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }
    @Test
    public void changeToSameTaskStatusSuccessfully(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        User u = new User("Jimmy", "jimmy@gmail.com");
        Task task = t.createTask("task", "desc", p, u);
        TaskStatus previousStatus = task.getStatus();
        t.updateStatus(task.getId(), previousStatus);
        assertEquals(previousStatus, task.getStatus());
    }
    @Test
    public void updateStatusOfNonexistentTaskThrowsError(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        User u = new User("Jimmy", "jimmy@gmail.com");
        Task task = new Task("Develop app", "desc", p, u);
        TaskStatus previousStatus = task.getStatus();
        assertThrows(IllegalStateException.class, () ->{
            t.updateStatus(task.getId(), TaskStatus.DONE);
        });
        assertEquals(previousStatus, task.getStatus());
    }
    @Test
    public void updateStatusToNullThrowsError(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        User u = new User("Jimmy", "jimmy@gmail.com");
        Task task = t.createTask("task", "desc", p, u);
        TaskStatus previousStatus = task.getStatus();
        assertThrows(IllegalArgumentException.class, () -> {
            t.updateStatus(task.getId(), null);
        });
        assertEquals(previousStatus, task.getStatus());
    }
    @Test
    public void deleteTaskSuccessfully(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        User u = new User("Jimmy", "jimmy@gmail.com");
        Task task = t.createTask("task", "desc", p, u);
        assertSame(task, t.retrieveTask(task.getId()));
        int initialSize = r.getAllTasks().size();
        t.deleteTask(task.getId());
        assertThrows(IllegalStateException.class, () -> {
            t.retrieveTask(task.getId());
        });
        assertEquals((initialSize-1), r.getAllTasks().size());
    }
    @Test
    public void deleteNonexistentTaskFails(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p = new Project("Coding Project", "coding practice");
        User u = new User("Jimmy", "jimmy@gmail.com");
        Task task = new Task("Develop app", "desc", p, u);
        assertThrows(IllegalStateException.class, () -> {
            t.deleteTask(task.getId());
        });
    }
    @Test
    public void deletingOneTaskDoesNotAffectOtherTasks(){
        TaskRepository r = new TaskRepository();
        TaskService t = new TaskService(r);
        Project p1 = new Project("Coding Project", "coding practice");
        User u1 = new User("Jimmy", "jimmy@gmail.com");
        Task task1 = t.createTask("Jimmy's task", "jimmyyyy", p1, u1);
        Project p2 = new Project("Homework", "homework");
        User u2 = new User("Abby", "abby@gmail.com");
        Task task2 = t.createTask("Abby's Task", "abbyyyy", p2, u2);
        t.deleteTask(task2.getId());
        assertThrows(IllegalStateException.class, () -> {
            t.retrieveTask(task2.getId());
        });
        assertSame(task1, t.retrieveTask(task1.getId()));
    }
}
