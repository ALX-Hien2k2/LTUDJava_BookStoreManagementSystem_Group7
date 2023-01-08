package dataaccess;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.PublisherPOJO;

public class PublisherDA {

    public PublisherPOJO getPublisher(int id) {
        PublisherPOJO ans = null;
        Connection connection = null;
        try {
            connection = MyConnection.create();
            String query = "SELECT * FROM publisher WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String country = rs.getString("country");
                boolean status = rs.getBoolean("status");
                ans = new PublisherPOJO(id, name, country, status);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PublisherDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PublisherDA.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return ans;
    }

    public List<PublisherPOJO> getAll() {
        List<PublisherPOJO> ans = null;
        try {
            ans = new ArrayList<>();
            Connection connection = MyConnection.create();
            Statement statement;
            statement = connection.createStatement();
            String query = "SELECT * FROM publisher";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String country = rs.getString("country");
                boolean status = rs.getBoolean("status");
                PublisherPOJO st = new PublisherPOJO(id, name, country, status);
                ans.add(st);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PublisherDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        }
        return ans;
    }

    public List<PublisherPOJO> searchPublisher(String publisherName) {
        List<PublisherPOJO> ans = null;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT * FROM publisher WHERE name like ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, String.format("%%%s%%", publisherName));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String country = rs.getString("country");
                Boolean status = rs.getBoolean("status");
                PublisherPOJO publisher = new PublisherPOJO(id, name, country, status);
                ans.add(publisher);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PublisherDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PublisherDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }

    public int insertPublisher(PublisherPOJO newPublisher) {
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
            query = "SELECT * FROM publisher WHERE name = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, newPublisher.getName());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // The category's name already exists
                insertedId = -2;
            } else {
                query = "INSERT INTO publisher (name, country) VALUES (?, ?);";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, newPublisher.getName());
                pstmt.setString(2, newPublisher.getCountry());

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
            Logger.getLogger(PublisherDA.class.getName()).log(Level.SEVERE, null, ex);
            // Error: SQLException
            insertedId = -1;
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PublisherDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return insertedId;
    }

    public int updatePublisher(PublisherPOJO publisher) {
        int status = 1;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            query = "SELECT * FROM publisher WHERE id = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, publisher.getId());
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                // Category not found
                status = -2;
            } else {
                query = "UPDATE publisher SET name = ? WHERE id = ?;";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, publisher.getName());
                pstmt.setInt(2, publisher.getId());

                // Execute the statement
                int rowsUpdated = pstmt.executeUpdate();
                System.out.println(rowsUpdated + " rows affected");
                if (rowsUpdated == 0) {
                    // Update account's info failed! OR The category's new info is the same as the old one
                    status = -3;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PublisherDA.class.getName()).log(Level.SEVERE, null, ex);
            // Error: SQLException
            status = -1;
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PublisherDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }
}
