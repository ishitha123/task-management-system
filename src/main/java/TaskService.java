public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repo) {
        if(repo==null){
            throw new IllegalArgumentException("The repository cannot be null.");
        }
        repository = repo;
    }
    public Task createTask(String title, String description, Project p, User u){
        Task t = new Task(title, description, p, u);
        repository.saveTask(t);
        return t;
    }
   public Task retrieveTask(int id){
        return repository.findTaskById(id);
    }
   public void assignTask(User u, int id){
        Task t = repository.findTaskById(id);
        t.assignToUser(u);
    }
   public void removeAssignment(int id){
        Task t = repository.findTaskById(id);
        t.unassignUser();
    }
   public void updateStatus(int id, TaskStatus s){
        Task t = repository.findTaskById(id);
        t.changeStatus(s);
    }
   public void deleteTask(int id){
        repository.deleteTaskById(id);
    }
}
