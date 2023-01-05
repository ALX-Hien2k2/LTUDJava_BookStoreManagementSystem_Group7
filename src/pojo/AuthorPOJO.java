package pojo;

public class AuthorPOJO {
    int id;
    String name;
    String dob;
    String homeTown;
    boolean isActive;

    public AuthorPOJO() {

    }

    public AuthorPOJO(String name, String dob, String homeTown) {
        this.name = name;
        this.dob = dob;
        this.homeTown = homeTown;
        this.isActive = true;
    }

    public AuthorPOJO(int id, String name, String dob, String homeTown) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.homeTown = homeTown;
    }

    public AuthorPOJO(int id, String name, String dob, String homeTown, boolean isActive) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.homeTown = homeTown;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String toString(){
        return id + " ** " + name + " ** " + dob + " ** " + homeTown;
    }
}
