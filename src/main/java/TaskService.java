public class TaskService {
    private final TaskRepository repository;
    private final UserRepository userRepo;
    public TaskService(TaskRepository repo, UserRepository userRepo) {
        if(repo==null){
            throw new IllegalArgumentException("The task repository cannot be null.");
        }
        if(userRepo==null){
            throw new IllegalArgumentException("The user repository cannot be null.");
        }
        repository = repo;
        this.userRepo = userRepo;
    }
    public Task createTask(String title, String description, Project p, User u){
        throwErrorForNonexistentUser(u);
        Task t = new Task(title, description, p, u);
        repository.saveTask(t);
        return t;
    }
   public Task retrieveTask(int id){
        return repository.findTaskById(id);
    }
   public void assignTask(User u, int id){
        Task t = repository.findTaskById(id);
        throwErrorForNonexistentUser(u);
        t.assignToUser(u);
    }
    private void throwErrorForNonexistentUser(User u){
        if(u!=null){
            if(userRepo.findUserById(u.getId())==null){
                throw new IllegalStateException("Cannot assign a task to a user that's not in the repository.");
            }
        }
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
