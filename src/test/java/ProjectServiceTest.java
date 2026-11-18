import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
public class ProjectServiceTest {
    @Test
    public void ConstructorThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new ProjectService(null);
        });
    }
    @Test
    public void CreateProjectWithNullNameThrowsError(){
        ProjectRepository repo = new ProjectRepository();
        ProjectService projServ = new ProjectService(repo);
        int initialSize = repo.getAllProjects().size();
        assertThrows(IllegalArgumentException.class, () -> {
            projServ.createProject(null, "desc");
        });
        assertEquals(initialSize, repo.getAllProjects().size());
    }
    @Test
    public void CreateProjectWithBlankNameThrowsError(){
        ProjectRepository repo = new ProjectRepository();
        ProjectService projServ = new ProjectService(repo);
        int initialSize = repo.getAllProjects().size();
        assertThrows(IllegalArgumentException.class, () -> {
            projServ.createProject("", "desc");
        });
        assertEquals(initialSize, repo.getAllProjects().size());
    }
    @Test
    public void CreateProjectWithWhitespaceNameThrowsError(){
        ProjectRepository repo = new ProjectRepository();
        ProjectService projServ = new ProjectService(repo);
        int initialSize = repo.getAllProjects().size();
        assertThrows(IllegalArgumentException.class, () -> {
            projServ.createProject(" ", "desc");
        });
        assertEquals(initialSize, repo.getAllProjects().size());
    }
    @Test
    public void CreateProjectSuccessfully(){
        ProjectRepository repo = new ProjectRepository();
        ProjectService projServ = new ProjectService(repo);
        Project p = projServ.createProject("create project", "");
        assertSame(p, repo.getAllProjects().get(p.getId()));
    }
    @Test
    public void retrieveNonexistentProjectByIdFails(){
        ProjectRepository repo = new ProjectRepository();
        ProjectService projServ = new ProjectService(repo);
        Project p = new Project("create project", "");
        assertThrows(IllegalStateException.class, () -> {
            projServ.retrieveProjectById(p.getId());
        });
    }
    @Test
    public void retrieveProjectSuccessfully(){
        ProjectRepository repo = new ProjectRepository();
        ProjectService projServ = new ProjectService(repo);
        Project p = projServ.createProject("create project", "");
        assertSame(p, projServ.retrieveProjectById(p.getId()));
    }
    @Test
    public void deletingNonexistentProjectFails(){
        ProjectRepository repo = new ProjectRepository();
        ProjectService projServ = new ProjectService(repo);
        Project p = new Project("create project", "");
        assertThrows(IllegalStateException.class, () -> {
            projServ.deleteProjectById(p.getId());
        });
    }
    @Test
    public void deleteProjectByIdSuccessfully(){
        ProjectRepository repo = new ProjectRepository();
        ProjectService projServ = new ProjectService(repo);
        Project p = projServ.createProject("create project", "");
        Project p1 = projServ.createProject("second project", null);
        projServ.deleteProjectById(p1.getId());
        assertSame(p, projServ.retrieveProjectById(p.getId()));
        assertThrows(IllegalStateException.class, () -> {
            projServ.retrieveProjectById(p1.getId());
        });
    }
    @Test
    public void getAllProjectsReturnsEmptyCollection(){
        ProjectRepository repo = new ProjectRepository();
        ProjectService projServ = new ProjectService(repo);
        HashMap<Integer, Project> collection = projServ.getAllProjects();
        assertEquals(0, collection.size());
    }
    @Test
    public void modificationsToReturnedCollectionDontAffectInternalCollection(){
        ProjectRepository repo = new ProjectRepository();
        ProjectService projServ = new ProjectService(repo);
        Project p = projServ.createProject("create project", "");
        HashMap<Integer, Project> collection = projServ.getAllProjects();
        collection.remove(p.getId());
        assertSame(p, projServ.retrieveProjectById(p.getId()));
    }
    @Test
    public void getAllProjectsReturnsAllProjectsSuccessfully(){
        ProjectRepository repo = new ProjectRepository();
        ProjectService projServ = new ProjectService(repo);
        Project p = projServ.createProject("create project", "");
        Project p1 = projServ.createProject("second project", null);
        HashMap<Integer, Project> coll = projServ.getAllProjects();
        assertSame(p, coll.get(p.getId()));
        assertSame(p1, coll.get(p1.getId()));
    }
}
