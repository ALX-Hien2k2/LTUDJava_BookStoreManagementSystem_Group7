package pojo;

public class CategoryPOJO {
    int id;
    String name;
    boolean status;
    
    public CategoryPOJO (){
        
    }

    public CategoryPOJO(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
    
    public CategoryPOJO (String name){
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isStatus() {
        return status;
    }
 
    public String toString(){
        return id + " ** " + name + " ** " + status;
    }
}
