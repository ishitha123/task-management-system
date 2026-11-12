import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

public class ProjectRepositoryTest {
    @Test
    public void saveValidProject(){
        Project p = new Project("Feature", "Feature for building an app");
        ProjectRepository repo = new ProjectRepository();
        assertNull(repo.getAllProjects().get(p.getId()));
        repo.saveProject(p);
        assertSame(p, repo.getAllProjects().get(p.getId()));
    }
    @Test
    public void saveNullProjectFails(){
        ProjectRepository repo = new ProjectRepository();
        assertThrows(IllegalArgumentException.class, () -> {
            repo.saveProject(null);
        });
    }
    @Test
    public void saveDuplicateProjectFails(){
        ProjectRepository repo = new ProjectRepository();
        Project p = new Project("Feature", "Feature for building an app");
        assertNull(repo.getAllProjects().get(p.getId()));
        repo.saveProject(p);
        assertSame(p, repo.getAllProjects().get(p.getId()));
        assertThrows(IllegalStateException.class, () -> {
            repo.saveProject(p);
        });
        assertSame(p, repo.getAllProjects().get(p.getId()));
    }
    @Test
    public void retrievingNonexistentProjectFails(){
        ProjectRepository repo = new ProjectRepository();
        Project p = new Project("Feature", "Feature for building an app");
        assertThrows(IllegalStateException.class, () ->{
            repo.retrieveProjectById(p.getId());
        });
    }
    @Test
    public void retrieveProjectByIdSuccessfully(){
        ProjectRepository repo = new ProjectRepository();
        Project p = new Project("Feature", "Feature for building an app");
        repo.saveProject(p);
        assertSame(p, repo.retrieveProjectById(p.getId()));
    }
    @Test
    public void deleteProjectSuccessfully(){
        ProjectRepository repo = new ProjectRepository();
        Project p = new Project("Feature", "Feature for building an app");
        Project p1 = new Project("Feature 2", "Implement feature 2.");
        repo.saveProject(p);
        repo.saveProject(p1);
        repo.deleteProjectById(p.getId());
        assertThrows(IllegalStateException.class, () ->{
            repo.retrieveProjectById(p.getId());
        });
        assertSame(p1, repo.retrieveProjectById(p1.getId()));
    }
    @Test
    public void deletingNonexistentProjectFails(){
        ProjectRepository repo = new ProjectRepository();
        Project p = new Project("Feature", "Feature for building an app");
        Project p1 = new Project("Feature 2", "Implement feature 2.");
        repo.saveProject(p);
        assertThrows(IllegalStateException.class, () -> {
            repo.deleteProjectById(p1.getId());
        });
        assertSame(p, repo.retrieveProjectById(p.getId()));
    }
    @Test
    public void emptyRepoReturnsEmptyCollection(){
        ProjectRepository repo = new ProjectRepository();
        assertEquals(0, repo.getAllProjects().size());
    }
    @Test
    public void getAllProjectsReturnsAllProjects(){
        ProjectRepository repo = new ProjectRepository();
        Project p = new Project("Feature", "Feature for building an app");
        Project p1 = new Project("Feature 2", "Implement feature 2.");
        repo.saveProject(p);
        repo.saveProject(p1);
        assertEquals(2, repo.getAllProjects().size());
        assertSame(p, repo.getAllProjects().get(p.getId()));
        assertSame(p1, repo.getAllProjects().get(p1.getId()));
    }
    @Test
    public void modifyingReturnedCollectionDoesNotChangeRepo(){
        ProjectRepository repo = new ProjectRepository();
        Project p = new Project("Feature", "Feature for building an app");
        Project p1 = new Project("Feature 2", "Implement feature 2.");
        repo.saveProject(p);
        repo.saveProject(p1);
        HashMap<Integer, Project> collection = repo.getAllProjects();
        collection.remove(p.getId());
        assertSame(p, repo.getAllProjects().get(p.getId()));
        assertSame(p1, repo.getAllProjects().get(p1.getId()));
    }
}
