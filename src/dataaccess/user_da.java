/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccess;

import pojo.user;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIN
 */
public class user_da {

    public List<user> getAllAccount() {
        List<user> ans = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT * FROM users";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                LocalDate dob = rs.getDate("dob").toLocalDate();
                String role = rs.getString("role");
                Boolean isActive = rs.getBoolean("isActive");
                user user = new user(id, username, password, fullname, dob, role, isActive);
                ans.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_da.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(user_da.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }

    public user get_acc(String mail, String pass) {
        user ans = null;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pr_statement = null;
        ResultSet rs = null;
        try {

            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT * FROM users where username =  ? and password = ?";
            pr_statement = connection.prepareStatement(query);
            pr_statement.setString(1, mail);
            pr_statement.setString(2, pass);
            rs = pr_statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                LocalDate dob = rs.getDate("dob").toLocalDate();
                String role = rs.getString("role");
                Boolean isActive = rs.getBoolean("isActive");
                user user = new user(id, username, password, fullname, dob, role, isActive);
                ans = user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_da.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(user_da.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }

    public void update_name(String fullname, int id) {

        Connection connection = null;
        Statement statement = null;
    
        PreparedStatement pr_statement = null;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "UPDATE users SET fullname = ? WHERE id = ?";
            pr_statement = connection.prepareStatement(query);
            pr_statement.setString(1, fullname);
            pr_statement.setInt(2, id);
            pr_statement.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(user_da.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.close();
                statement.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(user_da.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
 
    }

    public void update_dob(String dob, int id) {

        Connection connection = null;
        Statement statement = null;
       
        PreparedStatement pr_statement = null;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "UPDATE users set dob = ? where id = ?";
            pr_statement = connection.prepareStatement(query);
            pr_statement.setString(1, dob);
            pr_statement.setString(2, Integer.toString(id));
            pr_statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(user_da.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.close();
                statement.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(user_da.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void change_pass(String pass, int id) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pr_statement = null;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "UPDATE users set password = ? where id = ?";
            pr_statement = connection.prepareStatement(query);
            pr_statement.setString(1, pass);
            pr_statement.setString(2, Integer.toString(id));
            pr_statement.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(user_da.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(user_da.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<user> searchAccount(String name) {
        List<user> ans = null;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE fullname like ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, String.format("%%%s%%", name));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                LocalDate dob = rs.getDate("dob").toLocalDate();
                String role = rs.getString("role");
                Boolean isActive = rs.getBoolean("isActive");
                user user = new user(id, username, password, fullname, dob, role, isActive);
                ans.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_da.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(user_da.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }

}
