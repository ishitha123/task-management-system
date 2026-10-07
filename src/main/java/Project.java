import java.util.Objects;
public class Project {
    private int id;
    private static int nextId = 1;
    private String name;
    private String description;

    public Project(String name, String description) throws IllegalArgumentException{
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }
        this.name = name;
        this.description = description;
        id = nextId++;
    }
    public void editDescription(String d){
        description = d;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public int getId(){
        return id;
    }
    @Override
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        if(!(o instanceof Project)){
            return false;
        }
        Project p = (Project)o;
        return p.getId()==this.id;
    }
    @Override 
    public int hashCode(){
        return Objects.hash(id);
    }
}
