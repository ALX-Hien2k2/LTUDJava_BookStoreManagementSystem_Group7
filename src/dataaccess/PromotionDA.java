package dataaccess;

import pojo.PromotionPOJO;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PromotionDA {
    public List<PromotionPOJO> getAllPromotion(){
        List<PromotionPOJO> ans = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT * FROM promo_code WHERE isActive = 1";
            rs = statement.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                LocalDate start_date = rs.getDate("start_date").toLocalDate();
                LocalDate end_date = rs.getDate("end_date").toLocalDate();
                int discount = rs.getInt("discount");
                int max_order = rs.getInt("max_order");
                Boolean can_customer_once = rs.getBoolean("can_customer_once");
                Boolean can_anonymous = rs.getBoolean("can_anonymous");
                Boolean isActive = rs.getBoolean("isActive");
                Boolean isOpen = rs.getBoolean("isOpen");
                PromotionPOJO promo = new PromotionPOJO(id, name, description, start_date, end_date, discount, max_order, can_customer_once, can_anonymous, isActive, isOpen);
                ans.add(promo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }
    
    public List<PromotionPOJO> getPastPromotion(){
        List<PromotionPOJO> ans = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT * FROM promo_code WHERE isActive = 1 AND end_date < CURRENT_DATE";
            rs = statement.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                LocalDate start_date = rs.getDate("start_date").toLocalDate();
                LocalDate end_date = rs.getDate("end_date").toLocalDate();
                int discount = rs.getInt("discount");
                int max_order = rs.getInt("max_order");
                Boolean can_customer_once = rs.getBoolean("can_customer_once");
                Boolean can_anonymous = rs.getBoolean("can_anonymous");
                Boolean isActive = rs.getBoolean("isActive");
                Boolean isOpen = rs.getBoolean("isOpen");
                PromotionPOJO promo = new PromotionPOJO(id, name, description, start_date, end_date, discount, max_order, can_customer_once, can_anonymous, isActive, isOpen);
                ans.add(promo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }
    
    public List<PromotionPOJO> getCurrentPromotion(){
        List<PromotionPOJO> ans = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT * FROM promo_code WHERE isActive = 1 AND CURRENT_DATE BETWEEN start_date AND end_date;";
            rs = statement.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                LocalDate start_date = rs.getDate("start_date").toLocalDate();
                LocalDate end_date = rs.getDate("end_date").toLocalDate();
                int discount = rs.getInt("discount");
                int max_order = rs.getInt("max_order");
                Boolean can_customer_once = rs.getBoolean("can_customer_once");
                Boolean can_anonymous = rs.getBoolean("can_anonymous");
                Boolean isActive = rs.getBoolean("isActive");
                Boolean isOpen = rs.getBoolean("isOpen");
                PromotionPOJO promo = new PromotionPOJO(id, name, description, start_date, end_date, discount, max_order, can_customer_once, can_anonymous, isActive, isOpen);
                ans.add(promo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }
    
    public List<PromotionPOJO> getUpcomingPromotion(){
        List<PromotionPOJO> ans = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT * FROM promo_code WHERE isActive = 1 AND CURRENT_DATE < start_date";
            rs = statement.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                LocalDate start_date = rs.getDate("start_date").toLocalDate();
                LocalDate end_date = rs.getDate("end_date").toLocalDate();
                int discount = rs.getInt("discount");
                int max_order = rs.getInt("max_order");
                Boolean can_customer_once = rs.getBoolean("can_customer_once");
                Boolean can_anonymous = rs.getBoolean("can_anonymous");
                Boolean isActive = rs.getBoolean("isActive");
                Boolean isOpen = rs.getBoolean("isOpen");
                PromotionPOJO promo = new PromotionPOJO(id, name, description, start_date, end_date, discount, max_order, can_customer_once, can_anonymous, isActive, isOpen);
                ans.add(promo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }
    
//    public List<CategoryPOJO> getAllDisabledCategory(){
//        List<CategoryPOJO> ans = null;
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet rs = null;
//        try {
//            ans = new ArrayList<>();
//            connection = MyConnection.create();
//            statement = connection.createStatement();
//            String query = "SELECT * FROM categories WHERE status = 0";
//            rs = statement.executeQuery(query);
//            while(rs.next()){
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                Boolean status = rs.getBoolean("status");
//                CategoryPOJO cate = new CategoryPOJO(id, name, status);
//                ans.add(cate);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
//            ans = null;
//        } finally {
//            try {
//                connection.close();
//                statement.close();
//                rs.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return ans;
//    }
//    
    public List<PromotionPOJO> searchPromotion(String promoName){
        List<PromotionPOJO> ans = null;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT * FROM promo_code WHERE isActive = 1 AND name like ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, String.format("%%%s%%", promoName));
            rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                LocalDate start_date = rs.getDate("start_date").toLocalDate();
                LocalDate end_date = rs.getDate("end_date").toLocalDate();
                int discount = rs.getInt("discount");
                int max_order = rs.getInt("max_order");
                Boolean can_customer_once = rs.getBoolean("can_customer_once");
                Boolean can_anonymous = rs.getBoolean("can_anonymous");
                Boolean isActive = rs.getBoolean("isActive");
                Boolean isOpen = rs.getBoolean("isOpen");
                PromotionPOJO promo = new PromotionPOJO(id, name, description, start_date, end_date, discount, max_order, can_customer_once, can_anonymous, isActive, isOpen);
                ans.add(promo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }
    
//    public List<CategoryPOJO> searchDisabledCategory(String cateName){
//        List<CategoryPOJO> ans = null;
//        Connection connection = null;
//        Statement statement = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            ans = new ArrayList<>();
//            connection = MyConnection.create();
//            statement = connection.createStatement();
//            String query = "SELECT * FROM categories WHERE status = 0 AND name like ?";
//            pstmt = connection.prepareStatement(query);
//            pstmt.setString(1, String.format("%%%s%%", cateName));
//            rs = pstmt.executeQuery();
//            while(rs.next()){
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                Boolean status = rs.getBoolean("status");
//                CategoryPOJO category = new CategoryPOJO(id, name, status);
//                ans.add(category);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
//            ans = null;
//        } finally {
//            try {
//                connection.close();
//                statement.close();
//                pstmt.close();
//                rs.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return ans;
//    }
//    
//    public int insertCategory(CategoryPOJO newCategory){
//        // if insertedId = 0: get LAST_INSERT_ID() fail
//        int insertedId = 0;
//        Connection connection = null;
//        Statement statement = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        String query;
//        try {
//            connection = MyConnection.create();
//            statement = connection.createStatement();
//            query = "SELECT * FROM categories WHERE name = ?;";
//            pstmt = connection.prepareStatement(query);
//            pstmt.setString(1, newCategory.getName());
//            rs = pstmt.executeQuery();
//            if(rs.next()){
//                // The category's name already exists
//                insertedId = -2;
//            } else {
//                query = "INSERT INTO categories (name) VALUES (?);";
//                pstmt = connection.prepareStatement(query);
//                pstmt.setString(1, newCategory.getName());
//
//                // Execute the statement
//                int rowsInserted = pstmt.executeUpdate();
//                if (rowsInserted > 0) {
//                    Statement idStatement = null;
//                    ResultSet idResult = null;
//                    idStatement = connection.createStatement();
//                    idResult = idStatement.executeQuery("SELECT LAST_INSERT_ID()");
//                    if (idResult.next()) {
//                        insertedId = idResult.getInt(1);
//                        System.out.println("insertedId: " + insertedId);
//                    }
//                } else {
//                    // User added failed
//                    insertedId = -3;
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
//            // Error: SQLException
//            insertedId = -1;
//        } finally {
//            try {
//                connection.close();
//                statement.close();
//                pstmt.close();
//                rs.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return insertedId;
//    }
    
    public int updatePromotionInfo(PromotionPOJO updatePromotion){
        int status = 1;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            query = "SELECT * FROM promo_code WHERE id = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, updatePromotion.getId());
            rs = pstmt.executeQuery();
            if(!rs.next()){
                // Promotion not found
                status = -2;
            } else {
                query = "UPDATE promo_code SET name = ?, description = ?, discount = ?, max_order = ?, can_customer_once = ?, can_anonymous = ?  WHERE id = ?;";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, updatePromotion.getName());
                pstmt.setString(2, updatePromotion.getDescription());
                pstmt.setInt(3, updatePromotion.getDiscount());
                pstmt.setInt(4, updatePromotion.getMax_order());
                pstmt.setBoolean(5, updatePromotion.isCan_customer_once());
                pstmt.setBoolean(6, updatePromotion.isCan_anonymous());
                pstmt.setInt(7, updatePromotion.getId());

                // Execute the statement
                int rowsUpdated = pstmt.executeUpdate();
                System.out.println(rowsUpdated + " rows affected");
                if (rowsUpdated == 0){
                    // Update promotion's info failed! OR The promotion's new info is the same as the old one
                    status = -3;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            // Error: SQLException
            status = -1;
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }
    
    public int updatePromotionDate(PromotionPOJO updatePromotionDate){
        int status = 1;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            query = "SELECT * FROM promo_code WHERE id = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, updatePromotionDate.getId());
            rs = pstmt.executeQuery();
            if(!rs.next()){
                // Promotion not found
                status = -2;
            } else {
                query = "UPDATE promo_code SET start_date = ?, end_date = ? WHERE id = ?;";
                pstmt = connection.prepareStatement(query);
                pstmt.setDate(1, Date.valueOf(updatePromotionDate.getStart_date()));
                pstmt.setDate(2, Date.valueOf(updatePromotionDate.getEnd_date()));
                pstmt.setInt(3, updatePromotionDate.getId());
                
                // Execute the statement
                int rowsUpdated = pstmt.executeUpdate();
                System.out.println(rowsUpdated + " rows affected");
                if (rowsUpdated == 0){
                    // Update promotion's info failed! OR The promotion's new info is the same as the old one
                    status = -3;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            // Error: SQLException
            status = -1;
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }
    
    public int disablePromotion(int promotion_id){
        // Disable promotion successfully!
        int status = 1;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            query = "SELECT * FROM promo_code WHERE id = ? AND isActive = 1;";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, promotion_id);
            rs = pstmt.executeQuery();
            if(!rs.next()){
                // Promotion not found or already be disabled
                status = -2;
            } else {
                query = "UPDATE promo_code SET isActive = 0 WHERE id = ?;";
                pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, promotion_id);

                // Execute the statement
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated <= 0) {
                    // Disable promotion fail!
                    status = -3;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            // Error: SQL Exception
            status = -1;
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }
    
    public int enablePromotion(int promotion_id){
        // Enable promotion successfully!
        int status = 1;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            query = "SELECT * FROM promo_code WHERE id = ? AND isActive = 0;";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, promotion_id);
            rs = pstmt.executeQuery();
            if(!rs.next()){
                // Promotion not found or already be enable
                status = -2;
            } else {
                query = "UPDATE promo_code SET isActive = 1 WHERE id = ?;";
                pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, promotion_id);

                // Execute the statement
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated <= 0) {
                    // Enable category fail!
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
    
    public String openPromotion(int promotion_id){
        String status = "";
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            query = "SELECT * FROM promo_code WHERE id = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, promotion_id);
            rs = pstmt.executeQuery();
            if(!rs.next()){
                // Promotion not found
                status = "Promotion not exists!";
            } else {
                Boolean isOpen = rs.getBoolean("isOpen");
                if(isOpen){
                    // Promotion already opened
                    status = "Promotion already opened!";
                } else {
                    query = "UPDATE promo_code SET isOpen = 1 WHERE id = ?;";
                    pstmt = connection.prepareStatement(query);
                    pstmt.setInt(1, promotion_id);

                    // Execute the statement
                    int rowsUpdated = pstmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        status = "Open promotion successfully!";
                    } else {
                        status = "Open promotion fail!";
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            status = "Error: SQLException";
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }
    
    public String closePromotion(int promotion_id){
        String status = "";
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            query = "SELECT * FROM promo_code WHERE id = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, promotion_id);
            rs = pstmt.executeQuery();
            if(!rs.next()){
                // Promotion not found
                status = "Promotion not exists!";
            } else {
                Boolean isOpen = rs.getBoolean("isOpen");
                if(!isOpen){
                    // Promotion already closed
                    status = "Promotion already closed!";
                } else {
                    query = "UPDATE promo_code SET isOpen = 0 WHERE id = ?;";
                    pstmt = connection.prepareStatement(query);
                    pstmt.setInt(1, promotion_id);

                    // Execute the statement
                    int rowsUpdated = pstmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        status = "Close promotion successfully!";
                    } else {
                        status = "Close promotion fail!";
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            status = "Error: SQLException";
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PromotionDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }
}
