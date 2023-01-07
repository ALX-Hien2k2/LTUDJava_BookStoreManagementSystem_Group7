package dataaccess;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.CategoryPOJO;

public class CategoryDA {
    public List<CategoryPOJO> getAllCategory(){
        List<CategoryPOJO> ans = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT * FROM categories WHERE status = 1";
            rs = statement.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Boolean status = rs.getBoolean("status");
                CategoryPOJO cate = new CategoryPOJO(id, name, status);
                ans.add(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }
    
    public List<CategoryPOJO> searchCategory(String cateName){
        List<CategoryPOJO> ans = null;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT * FROM categories WHERE status = 1 AND name like ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, String.format("%%%s%%", cateName));
            rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Boolean status = rs.getBoolean("status");
                CategoryPOJO category = new CategoryPOJO(id, name, status);
                ans.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }
    
    public int insertCategory(CategoryPOJO newCategory){
        // if insertedId = 0: get LAST_INSERT_ID() fail
        int insertedId = 0;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            query = "SELECT * FROM categories WHERE name = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, newCategory.getName());
            rs = pstmt.executeQuery();
            if(rs.next()){
                // The category's name already exists
                insertedId = -2;
            } else {
                query = "INSERT INTO categories (name) VALUES (?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, newCategory.getName());

                // Execute the statement
                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    Statement idStatement = null;
                    ResultSet idResult = null;
                    idStatement = connection.createStatement();
                    idResult = idStatement.executeQuery("SELECT LAST_INSERT_ID()");
                    if (idResult.next()) {
                        insertedId = idResult.getInt(1);
                        System.out.println("insertedId: " + insertedId);
                    }
                } else {
                    // User added failed
                    insertedId = -3;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
            // Error: SQLException
            insertedId = -1;
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return insertedId;
    }
    
    public int updateCategoryInfo(CategoryPOJO updateCategory){
        int status = 1;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            query = "SELECT * FROM categories WHERE id = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, updateCategory.getId());
            rs = pstmt.executeQuery();
            if(!rs.next()){
                // Category not found
                status = -2;
            } else {
                query = "UPDATE categories SET name = ? WHERE id = ?;";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, updateCategory.getName());
                pstmt.setInt(2, updateCategory.getId());

                // Execute the statement
                int rowsUpdated = pstmt.executeUpdate();
                System.out.println(rowsUpdated + " rows affected");
                if (rowsUpdated == 0){
                    // Update account's info failed! OR The category's new info is the same as the old one
                    status = -3;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
            // Error: SQLException
            status = -1;
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }
    
    public int disableCategory(int category_id){
        // Disable category successfully!
        int status = 1;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            query = "SELECT * FROM categories WHERE id = ? AND status = 1;";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, category_id);
            rs = pstmt.executeQuery();
            if(!rs.next()){
                // Category not found or already be disabled
                status = -2;
            } else {
                query = "UPDATE categories SET status = 0 WHERE id = ?;";
                pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, category_id);

                // Execute the statement
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated <= 0) {
                    // Disable category fail!
                    status = -3;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
            // Error: SQL Exception
            status = -1;
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }
}
