import java.util.HashMap;

public class TaskRepository {
    private final HashMap<Integer, Task> repository;

    public TaskRepository(){
        repository = new HashMap<Integer, Task>();
    }
    public void saveTask(Task t){
        if(t==null){
            throw new IllegalArgumentException("Null tasks cannot be saved.");
        }
        if(repository.containsKey(t.getId())){
            throw new IllegalStateException("The task with ID " + t.getId() + " already exists in the repository.");
        }
        repository.put(t.getId(), t);
    }
    public Task findTaskById(int id){
        Task t = repository.get(id);
        if(t==null){
            throw new IllegalStateException("A task with the ID " + id + " does not exist in the repository.");
        }
        return t;
    }
    public void deleteTaskById(int id){
        findTaskById(id);
        repository.remove(id);
    }
    public HashMap<Integer, Task> getAllTasks(){
        return new HashMap<Integer, Task>(repository);
    }
}
