/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccess;

import pojo.BookImportPOJO;
import pojo.ImportDetails;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIN
 */
public class ImportBookDA {

    public List<BookImportPOJO> getAll() {
        List<BookImportPOJO> ans = null;
        try {
            ans = new ArrayList<>();
            Connection connection = MyConnection.create();
            Statement statement;
            statement = connection.createStatement();
            String query = "SELECT i.*,u.fullname FROM book_import i, users u where i.staff_id = u.id;";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate day = rs.getDate("date").toLocalDate();
                int staff_id = rs.getInt("staff_id");                
                double total = rs.getDouble("total_cost");
                String staff_name = rs.getString("fullname");
                BookImportPOJO b = new BookImportPOJO(id,day,staff_id,total,staff_name);
                ans.add(b);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(book_da.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        }
        return ans;
    }
     public List<ImportDetails> getDetails() {
        List<ImportDetails> ans = null;
        try {
            ans = new ArrayList<>();
            Connection connection = MyConnection.create();
            Statement statement;
            statement = connection.createStatement();
            String query = "SELECT dt.*,b.name FROM book_import_detail dt, book b where dt.book_id = b.id;";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int id_imp = rs.getInt("import_sheet_id");
                int id_book = rs.getInt("book_id");
                int quant = rs.getInt("quantity");
                double price = rs.getDouble("import_price");
                double total = rs.getDouble("total_cost");
                String name = rs.getString("name");
                ImportDetails b = new ImportDetails(id_imp,id_book,name,quant,price,total);
                ans.add(b);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(book_da.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        }
        return ans;
    }
    public boolean add_import(int id, String day, int staff_id, double total) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pr_statement = null;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "INSERT INTO book_import(id,date,staff_id,total_cost) VALUES(?, ?, ?, ?)";
            pr_statement = connection.prepareStatement(query);
            pr_statement.setInt(1, id);
            pr_statement.setString(2, day);
            pr_statement.setInt(3, staff_id);
            pr_statement.setDouble(4, total);

            int r = pr_statement.executeUpdate();
            return r > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.close();
                statement.close();
                pr_statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean add_import_details(int id, int book_id, int quant, double price, double total) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pr_statement = null;
        try {
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "INSERT INTO book_import_detail(import_sheet_id,book_id,quantity,import_price,total_cost) VALUES(?, ?, ?, ?, ?)";
            pr_statement = connection.prepareStatement(query);
            pr_statement.setInt(1, id);
            pr_statement.setInt(2, book_id);
            pr_statement.setInt(3, quant);
            pr_statement.setDouble(4, price);
            pr_statement.setDouble(5, total);

            int r = pr_statement.executeUpdate();
            return r > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.close();
                statement.close();
                pr_statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
