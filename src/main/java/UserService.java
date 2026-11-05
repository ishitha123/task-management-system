public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository r){
        if(r==null){
            throw new IllegalArgumentException("User repository cannot be null.");
        }
        repository = r;
    }
    public User registerUser(String name, String email) {
        User u = new User(name, email);
        repository.saveUser(u);
        return u;
    }
    public User retrieveUser(int id){
        return repository.findUserById(id);
    }
    public void deleteUser(int id){
        repository.deleteUserById(id);
    }
}
