import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    @Test
    public void constructorThrowsIllegalArgumentException(){
        UserRepository u = null;
        assertThrows(IllegalArgumentException.class, () -> {
            UserService serv = new UserService(u);
        });
    }
    @Test
    public void registerUserSuccessfully(){
        UserRepository repo = new UserRepository();
        UserService serv = new UserService(repo);
        User u = serv.registerUser("Jimmy", "jimmy@gmail.com");
        assertEquals("Jimmy", u.getName());
        assertEquals("jimmy@gmail.com", u.getEmail());
        assertSame(u, repo.getAllUsers().get(u.getId()));
    }
    @Test
    public void registerUserWithNullNameFails(){
        UserRepository repo = new UserRepository();
        UserService serv = new UserService(repo);
        assertThrows(IllegalArgumentException.class, () -> {
            serv.registerUser(null, "jimmy@gmail.com");
        });
    }
    @Test
    public void registerUserWithBlankNameFails(){
        UserRepository repo = new UserRepository();
        UserService serv = new UserService(repo);
        int initialSize = repo.getAllUsers().size();
        assertThrows(IllegalArgumentException.class, () -> {
            serv.registerUser("", "jimmy@gmail.com");
        });
        assertEquals(initialSize, repo.getAllUsers().size());
    }
    @Test
    public void registerUserWithNullEmailFails(){
        UserRepository repo = new UserRepository();
        UserService serv = new UserService(repo);
        int initialSize = repo.getAllUsers().size();
        assertThrows(IllegalArgumentException.class, () -> {
            serv.registerUser("Jimmy", null);
        });
        assertEquals(initialSize, repo.getAllUsers().size());
    }
    @Test
    public void registerUserWithWhitespaceNameFails(){
        UserRepository repo = new UserRepository();
        UserService serv = new UserService(repo);
        int initialSize = repo.getAllUsers().size();
        assertThrows(IllegalArgumentException.class, () -> {
            serv.registerUser(" ", "jimmy@gmail.com");
        });
        assertEquals(initialSize, repo.getAllUsers().size());
    }
    @Test
    public void registerUserWithWhitespaceEmailFails(){
        UserRepository repo = new UserRepository();
        UserService serv = new UserService(repo);
        int initialSize = repo.getAllUsers().size();
        assertThrows(IllegalArgumentException.class, () -> {
            serv.registerUser("Jimmy", " ");
        });
        assertEquals(initialSize, repo.getAllUsers().size());
    }
    @Test
    public void registerUserWithBlankEmailFails(){
        UserRepository repo = new UserRepository();
        UserService serv = new UserService(repo);
        int initialSize = repo.getAllUsers().size();
        assertThrows(IllegalArgumentException.class, () -> {
            serv.registerUser("Jimmy", "");
        });
        assertEquals(initialSize, repo.getAllUsers().size());
    }
    @Test
    public void retrieveNonexistingUserFails(){
        UserRepository repo = new UserRepository();
        UserService serv = new UserService(repo);
        User u = new User("John", "john@gmail.com");
        assertThrows(IllegalStateException.class, () -> {
            serv.retrieveUser(u.getId());
        });
    }
    @Test
    public void retrieveUserSuccessfully(){
        UserRepository repo = new UserRepository();
        UserService serv = new UserService(repo);
        User u = serv.registerUser("Jimmy", "jimmy@gmail.com");
        int initialSize = repo.getAllUsers().size();
        assertSame(u, serv.retrieveUser(u.getId()));
        assertEquals(initialSize, repo.getAllUsers().size());
    }
    @Test
    public void deleteUserSuccessfully(){
        UserRepository repo = new UserRepository();
        UserService serv = new UserService(repo);
        User u = serv.registerUser("Jimmy", "jimmy@gmail.com");
        User u1 = serv.registerUser("Carl", "carl@gmail.com");
        serv.deleteUser(u.getId());
        assertThrows(IllegalStateException.class, () -> {
            serv.retrieveUser(u.getId());
        });
        assertSame(u1, serv.retrieveUser(u1.getId()));
    }
    @Test
    public void deletingNonexistentUserFails(){
        UserRepository repo = new UserRepository();
        UserService serv = new UserService(repo);
        User u = serv.registerUser("Jimmy", "jimmy@gmail.com");
        User u1 = new User("Carl", "carl@gmail.com");
        assertThrows(IllegalStateException.class, () -> {
            serv.deleteUser(u1.getId());
        });
        assertSame(u, serv.retrieveUser(u.getId()));
    }
}
