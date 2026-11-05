import java.util.HashMap;

public class UserRepository {
    private final HashMap<Integer, User> repository;

    public UserRepository(){
        repository = new HashMap<>();
    }
    public void saveUser(User u){
        if(u==null){
            throw new IllegalArgumentException("A null user cannot be saved to the repository.");
        }
        if(repository.containsKey(u.getId())){
            throw new IllegalStateException("A user with the ID " + u.getId() + " already exists in the repository.");
        }
        repository.put(u.getId(), u);
    }
    public User findUserById(int id){
        if(!repository.containsKey(id)){
            throw new IllegalStateException("A user with the ID " + id + " does not exist in the repository.");
        }
        return repository.get(id);
    }
    public HashMap<Integer, User> getAllUsers(){
        return new HashMap<>(repository);
    }
    public void deleteUserById(int id){
        findUserById(id);
        repository.remove(id);
    }
}
