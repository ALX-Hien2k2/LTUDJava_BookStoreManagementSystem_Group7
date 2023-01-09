package pojo;

public class PublisherPOJO {
    private int id;
    private String name;
    private String country;
    private Boolean status;

    public PublisherPOJO() {
    }

    public PublisherPOJO(String name, String country) {
        this.name = name;
        this.country = country;
        this.status = true;
    }

    public PublisherPOJO(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public PublisherPOJO(int id, String name, String country, Boolean status) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return id + " ** " + name + " ** " + country + " ** " + status;
    }
    
}
