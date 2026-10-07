import java.util.Objects;

public class User{
    private int id;
    private static int nextId = 1;
    private String name;
    private String email;

    public User(String name, String email) throws IllegalArgumentException{
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }
        if(email==null || email.isBlank()){
            throw new IllegalArgumentException("Email cannot be null or blank.");
        }
        id = nextId++;
        this.name = name;
        this.email = email;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public int getId(){
        return id;
    }
    @Override 
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        if(!(o instanceof User)){
            return false;
        }
        User u = (User)o;
        return u.getId()==this.id;
    }
    @Override 
    public int hashCode(){
        return Objects.hash(id);
    }
}
