/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import pojo.user;
import java.util.*;
import dataaccess.*;

/**
 *
 * @author TIN
 */
public class user_business {

    public List<user> getAllAccount() {
        user_da da = new user_da();
        return da.getAllAccount();
    }

    public user sign_in(String email, String password) {
        user_da da = new user_da();
        return da.get_acc(email, password);
    }

    public void update_name(String name, int id) {
        user_da da = new user_da();
        da.update_name(name, id);
    }

    public void update_dob(String dob, int id) {
        user_da da = new user_da();
        da.update_dob(dob, id);
    }

    public void change_pass(String pass, int id) {
        user_da da = new user_da();
        da.change_pass(pass, id);
    }
}
