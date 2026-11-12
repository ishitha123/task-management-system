import java.util.HashMap;

public class ProjectRepository {
    private final HashMap<Integer, Project> repository;

    public ProjectRepository(){
        repository = new HashMap<>();
    }
    public void saveProject(Project p){
        if(p==null){
            throw new IllegalArgumentException("A null project cannot be saved.");
        }
        if(repository.containsKey(p.getId())){
            throw new IllegalStateException("A project with the ID " + p.getId() + " already exists in the repository.");
        }
        repository.put(p.getId(), p);
    }
    public Project retrieveProjectById(int id){
        if(!repository.containsKey(id)){
            throw new IllegalStateException("A project with the ID " + id + " does not exist within the repository.");
        }
        return repository.get(id);
    }
    public HashMap<Integer, Project> getAllProjects(){
        return new HashMap<Integer, Project>(repository);
    }
    public void deleteProjectById(int id){
        retrieveProjectById(id);
        repository.remove(id);
    }
}
