import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test 
    public void nullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User(null, "user@gmail.com");
        });
    }
    @Test 
    public void blankNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User("", "user@gmail.com");
        });
    }
    @Test 
    public void nullEmailThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User("Dave", null);
        });
    }
    @Test 
    public void blankEmailThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User("Dave", "");
        });
    }
    @Test 
    public void validUserCreated() {
        User u = new User("Jane Doe", "jdoe@gmail.com");
        assertEquals("Jane Doe", u.getName());
        assertEquals("jdoe@gmail.com", u.getEmail());
    }
    @Test 
    public void differentIdsReceived() {
        User u = new User("Jane Doe", "jdoe@gmail.com");
        User u1 = new User("Billy Bob", "bbob@yahoo.com");
        assertNotEquals(u.getId(), u1.getId());
    }
    @Test 
    public void equalsMethod() {
        User u = new User("Jane Doe", "jdoe@gmail.com");
        User u1 = new User("Billy Bob", "bbob@yahoo.com");
        assertFalse(u.equals(u1));
    }
}
