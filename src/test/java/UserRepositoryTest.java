import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

public class UserRepositoryTest {
    @Test
    public void saveUserSuccessfully(){
        UserRepository r = new UserRepository();
        User u = new User("Jackie", "jackie@gmail.com");
        assertFalse(r.getAllUsers().containsKey(u.getId()));
        int initialSize = r.getAllUsers().size();
        r.saveUser(u);
        assertTrue(r.getAllUsers().containsKey(u.getId()));
        assertSame(u, r.getAllUsers().get(u.getId()));
        assertEquals((initialSize+1), r.getAllUsers().size());
    }
    @Test
    public void savingNullUserFails(){
        UserRepository r = new UserRepository();
        int initialSize = r.getAllUsers().size();
        assertThrows(IllegalArgumentException.class, () ->{
            r.saveUser(null);
        });
        assertEquals(initialSize, r.getAllUsers().size());
    }
    @Test
    public void savingExistingUserFails(){
        UserRepository r = new UserRepository();
        User u = new User("Jackie", "jackie@gmail.com");
        r.saveUser(u);
        int initialSize = r.getAllUsers().size();
        assertThrows(IllegalStateException.class, () -> {
            r.saveUser(u);
        });
        assertEquals(initialSize, r.getAllUsers().size());
    }
    @Test
    public void successfulUserRetrieval(){
        UserRepository r = new UserRepository();
        User u = new User("Jackie", "jackie@gmail.com");
        r.saveUser(u);
        assertSame(u, r.findUserById(u.getId()));
    }
    @Test
    public void unsuccessfulUserRetrieval(){
        UserRepository r = new UserRepository();
        User u = new User("Jackie", "jackie@gmail.com");
        assertThrows(IllegalStateException.class, () -> {
            r.findUserById(u.getId());
        });
    }
    @Test
    public void deleteUserSuccessfully(){
        UserRepository r = new UserRepository();
        User u = new User("Jackie", "jackie@gmail.com");
        User u1 = new User("Abby", "abby@gmail.com");
        r.saveUser(u1);
        r.saveUser(u);
        r.deleteUserById(u.getId());
        assertThrows(IllegalStateException.class, () ->{
            r.findUserById(u.getId());
        });
        assertSame(u1, r.findUserById(u1.getId()));
    }
    @Test
    public void deletingNonexistingUserFails(){
        UserRepository r = new UserRepository();
        User u = new User("Jackie", "jackie@gmail.com");
        assertThrows(IllegalStateException.class, () ->{
            r.deleteUserById(u.getId());
        });
    }
    @Test
    public void getAllUsersReturnsEmptyCollection(){
        UserRepository r = new UserRepository();
        HashMap<Integer, User> map = r.getAllUsers();
        assertTrue(map.isEmpty());
    }
    @Test
    public void getAllUsersPreservesOriginalCopy(){
        UserRepository r = new UserRepository();
        User u = new User("Jackie", "jackie@gmail.com");
        User u1 = new User("Abby", "abby@gmail.com");
        r.saveUser(u1);
        r.saveUser(u);
        HashMap<Integer, User> map = r.getAllUsers();
        map.remove(u1.getId());
        assertSame(u1, r.findUserById(u1.getId()));
        assertSame(u, r.findUserById(u.getId()));
    }
}
