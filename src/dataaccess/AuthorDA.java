package dataaccess;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.AuthorPOJO;

public class AuthorDA {
    public AuthorPOJO getAuthor(int id) {
        AuthorPOJO ans = null;
        Connection connection = null;
        try {
            connection = MyConnection.create();
            String query = "SELECT * FROM author WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                String homeTown = rs.getString("homeTown");
                boolean isActive = rs.getBoolean("isActive");
                ans = new AuthorPOJO(id, name, dob, homeTown, isActive);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(AuthorDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        }
        finally{
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AuthorDA.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return ans;
    }

    public List<AuthorPOJO> getAll() {
        List<AuthorPOJO> ans = null;
        try {
            ans = new ArrayList<>();
            Connection connection = MyConnection.create();
            Statement statement;
            statement = connection.createStatement();
            String query = "SELECT * FROM author";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                String homeTown = rs.getString("homeTown");
                boolean isActive = rs.getBoolean("isActive");
                AuthorPOJO st = new AuthorPOJO(id, name, dob, homeTown, isActive);
                ans.add(st);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(AuthorDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        }
        return ans;
    }

    public boolean addAuthor(AuthorPOJO author) {
        boolean ans = true;
        Connection connection = null;
        try {
            connection = MyConnection.create();
            String query = "INSERT INTO author(name, dob, homeTown) VALUES(?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, author.getName());
            statement.setString(2, author.getDob());
            statement.setString(3, author.getHomeTown());
            int row = statement.executeUpdate();
            if(row < 1){
                ans = false;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(AuthorDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = false;
        }
        finally{
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AuthorDA.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return ans;
    }

    public boolean updateAuthor(AuthorPOJO author) {
        boolean ans = true;
        Connection connection = null;
        try {
            connection = MyConnection.create();
            String query = "UPDATE author SET name = ?, dob = ?, homeTown = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, author.getName());
            statement.setString(2, author.getDob());
            statement.setString(3, author.getHomeTown());
            statement.setInt(4, author.getId());
            int row = statement.executeUpdate();
            if(row < 1){
                ans = false;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(AuthorDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = false;
        }
        finally{
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AuthorDA.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return ans;
    }

    public boolean disableAuthor(int id) {
        boolean ans = true;
        Connection connection = null;
        try {
            connection = MyConnection.create();
            String query = "UPDATE author SET isActive = false WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int row = statement.executeUpdate();
            if(row < 1){
                ans = false;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(AuthorDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = false;
        }
        finally{
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AuthorDA.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return ans;
    }

    public boolean enableAuthor(int id) {
        boolean ans = true;
        Connection connection = null;
        try {
            connection = MyConnection.create();
            String query = "UPDATE author SET isActive = true WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int row = statement.executeUpdate();
            if(row < 1){
                ans = false;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(AuthorDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = false;
        }
        finally{
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AuthorDA.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return ans;
    }
}
