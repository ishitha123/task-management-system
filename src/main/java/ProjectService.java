import java.util.HashMap;
public class ProjectService {
    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repo){
        if(repo==null){
            throw new IllegalArgumentException("Project repository cannot be null.");
        }
        repository = repo;
    }
    public Project createProject(String name, String description){
        Project p = new Project(name, description);
        repository.saveProject(p);
        return p;
    }
    public Project retrieveProjectById(int id){
        return repository.retrieveProjectById(id);
    }
    public void deleteProjectById(int id){
        repository.deleteProjectById(id);
    }
    public HashMap<Integer, Project> getAllProjects(){
        return repository.getAllProjects();
    }
}
