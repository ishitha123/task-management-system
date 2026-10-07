import java.util.Objects;

public class Task {
    private int id;
    private static int nextId = 1;
    private String title;
    private String description;
    private TaskStatus status;
    private Project project;
    private User user;

    public Task(String title, String description, Project project, User user){
        if(title==null || title.isBlank()){
            throw new IllegalArgumentException("Title cannot be null or blank.");
        }
        if(project==null){
            throw new IllegalArgumentException("Project cannot be null.");
        }
        id = nextId++;
        this.title = title;
        this.description = description;
        this.user = user;
        this.project = project;
        status = TaskStatus.TODO;
    }
    public void assignToUser(User u){
        if(u==null){
            throw new IllegalArgumentException("A task cannot be assigned to a null user.");
        }
        user = u;
    }
    public void editDescription(String d){
        description = d;
    }
    public void changeStatus(TaskStatus status){
        if(status==null){
            throw new IllegalArgumentException("A task's status cannot be null.");
        }
        this.status = status;
    }
    public void unassignUser(){
        if(user==null){
            throw new IllegalStateException("This task has not been assigned to any user.");
        }
        user = null;
    }
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public Project getProject(){
        return project;
    }
    public User getUser(){
        return user;
    }
    public TaskStatus getStatus(){
        return status;
    }
    @Override 
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        if(!(o instanceof Task)){
            return false;
        }
        Task t = (Task)o;
        return t.getId()==this.id;
    }
    @Override 
    public int hashCode(){
        return Objects.hash(id);
    }
}
