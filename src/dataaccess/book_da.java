/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccess;
import pojo.book;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIN
 */
public class book_da {
    public List<book> getAll(){
       List<book> ans = null;
       try {
           ans = new ArrayList<>();
           Connection connection = MyConnection.create();
           Statement statement;
           statement = connection.createStatement();
           String query = "SELECT * FROM book";
           ResultSet rs = statement.executeQuery(query);
           while(rs.next()){
               int id = rs.getInt("id");
               String name = rs.getString("name");
               double price = rs.getDouble("price");
               int quantity = rs.getInt("quantity");
               int author_id= rs.getInt("author_id");
               int publisher_id = rs.getInt("publisher_id");
               int category_id = rs.getInt("category_id");
               book st = new book(id, name,price,quantity, author_id,publisher_id,category_id);
               ans.add(st);
           }
           rs.close();
           statement.close();
       } catch (SQLException ex) {
           Logger.getLogger(book_da.class.getName()).log(Level.SEVERE, null, ex);
           ans = null;
       }
       return ans;
    }
    
    public List<book> getBookByPromotion(int promo_id){
        List<book> ans = null;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = 
                "SELECT b.id, b.name, b.price, b.quantity, a.name AS author, p.name AS publisher, c.name AS category, b.promotion_id " +
                "FROM book b, author a, publisher p, categories c " +
                "WHERE b.author_id = a.id AND b.publisher_id = p.id AND b.category_id = c.id AND b.isActive = 1 AND b.promotion_id = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,promo_id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                String category = rs.getString("category");
                int promotion_id = rs.getInt("promotion_id");
                
                book row_book = new book(id, name, price, quantity, author, publisher, category, promotion_id);
                ans.add(row_book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(book_da.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(book_da.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }
    
    public List<book> searchBookByPromotion(String promoName, int promo_id){
        List<book> ans = null;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = 
                "SELECT b.id, b.name, b.price, b.quantity, a.name AS author, p.name AS publisher, c.name AS category, b.promotion_id " +
                "FROM book b, author a, publisher p, categories c " +
                "WHERE b.author_id = a.id AND b.publisher_id = p.id AND b.category_id = c.id AND b.isActive = 1 AND b.promotion_id = ? AND b.name like ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,promo_id);
            pstmt.setString(2, String.format("%%%s%%", promoName));
            rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                String category = rs.getString("category");
                int promotion_id = rs.getInt("promotion_id");

                book row_book = new book(id, name, price, quantity, author, publisher, category, promotion_id);
                ans.add(row_book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(book_da.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(book_da.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }
}
