package dataaccess;

import java.time.LocalDate;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.ImportSheetPOJO;
import pojo.DetailImportSheetPOJO;

public class ImportSheetDA {
    public List<ImportSheetPOJO> getAllImportSheet() {
        List<ImportSheetPOJO> ans = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT book_import.*, users.fullname AS staff FROM book_import, users WHERE book_import.staff_id = users.id;";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate date = rs.getDate("date").toLocalDate();
                String staff = rs.getString("staff");
                double total_cost = rs.getDouble("total_cost");
                ImportSheetPOJO ImportSheet = new ImportSheetPOJO(id, date, staff, total_cost);
                ans.add(ImportSheet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportSheetDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImportSheetDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }

    public List<DetailImportSheetPOJO> getDetailImportSheet(int id) {
        List<DetailImportSheetPOJO> ans = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT book_import_detail.*, book.name AS book_title FROM book_import_detail, book WHERE book_import_detail.book_id = book.id AND book_import_detail.import_sheet_id = " + id + ";";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                int import_sheet_id = rs.getInt("import_sheet_id");
                String book = rs.getString("book_title");
                int quantity = rs.getInt("quantity");
                double import_price = rs.getDouble("import_price");
                double total_cost = rs.getDouble("total_cost");
                DetailImportSheetPOJO detailImportSheet = new DetailImportSheetPOJO(import_sheet_id, book, quantity, import_price, total_cost);
                ans.add(detailImportSheet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportSheetDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImportSheetDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }
}
