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
}
