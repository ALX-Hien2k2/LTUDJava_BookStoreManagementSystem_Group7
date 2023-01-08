package pojo;

import java.time.LocalDate;
import java.util.*;
import constant.Constant_var;

public class UserPOJO {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private LocalDate dob;
    private String role;
    private Boolean isActive;
    private Constant_var constant_var;

    public UserPOJO(int id, String username, String password, String fullname, LocalDate dob, String role, Boolean isActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.dob = dob;
        this.role = role;
        this.isActive = isActive;
    }
    public UserPOJO(String fullname, String username, String password, String role) {
        constant_var = new Constant_var();
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.role = role;
        this.dob = constant_var.getDefault_dob();
        this.isActive = true;
    }

    public UserPOJO(int id, String fullname, String username, LocalDate dob, String role) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.dob = dob;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

<<<<<<< Updated upstream
=======
    public boolean changePassword(String newPassword) {
        if (this.password.equals(newPassword)) {
            return false;
        }
        this.password = newPassword;
        return true;
    }

    public UserPOJO sign_up(String username, String password, String password2, int id, String name, String dob, String role, Boolean isActive) {
        LocalDate dateOfBirth = LocalDate.parse(dob);
        return new UserPOJO(id, username, password, name, dateOfBirth, role, isActive);
    }

    public boolean sign_in(String username, String password) {
        return this.username.equals(username) && this.password.equals(password) && this.isActive;
    }

    public void update_info(String name, LocalDate dob) {
        this.fullname = name;
        this.dob = dob;
    }

>>>>>>> Stashed changes
    @Override
    public String toString(){
        return id + " ** " + username + " ** " + password + " ** " + fullname + " ** " + dob + " ** " + role + " ** " + isActive;
    }
}
