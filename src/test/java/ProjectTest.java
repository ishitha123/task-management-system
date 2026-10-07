import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectTest {
    @Test 
    public void nullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Project(null, "description");
        });
    }
    @Test 
    public void blankNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Project("", "test");
        });
    }
    @Test 
    public void differentIdsReceived() {
        Project p1 = new Project("p1", "test1");
        Project p2 = new Project("p2", "test2");
        assertNotEquals(p1.getId(), p2.getId());
    }
    @Test 
    public void projectCreatedSuccessfully() {
        Project p1 = new Project("p1", "test1");
        assertEquals("p1", p1.getName());
        assertEquals("test1", p1.getDescription());
    }
    @Test 
    public void nullDescriptionSuccessful() {
         Project p1 = new Project("p1", null);
         assertNull(p1.getDescription());
    }
    @Test
    public void editDescriptionNotNull(){
        Project p1 = new Project("p1", null);
        p1.editDescription("desc");
        assertEquals("desc", p1.getDescription());
    }
    @Test 
    public void editDescriptionNull(){
        Project p1 = new Project("p1", "test1");
        p1.editDescription(null);
        assertNull(p1.getDescription());
    }
}
