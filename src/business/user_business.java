/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import pojo.UserPOJO;
import java.util.*;
import dataaccess.*;

/**
 *
 * @author TIN
 */
public class user_business {

    public List<UserPOJO> getAllAccount() {
        UserDA da = new UserDA();
        return da.getAllAccount();
    }

    public UserPOJO sign_in(String email, String password) {
        UserDA da = new UserDA();
        return da.get_acc(email, password);
    }

    public void update_name(String name, int id) {
        UserDA da = new UserDA();
        da.update_name(name, id);
    }

    public void update_dob(String dob, int id) {
        UserDA da = new UserDA();
        da.update_dob(dob, id);
    }

    public void change_pass(String pass, int id) {
        UserDA da = new UserDA();
        da.change_pass(pass, id);
    }
}
