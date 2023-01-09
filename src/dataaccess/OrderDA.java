package dataaccess;

import java.time.LocalDate;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.OrderPOJO;
import pojo.DetailOrderPOJO;

public class OrderDA {
    public List<OrderPOJO> getAllOrder() {
        List<OrderPOJO> ans = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT orders.*, users.fullname AS staff, member.name AS member FROM orders, users, member WHERE orders.customer_id > 0 AND orders.customer_id = member.id AND orders.staff_id = users.id;";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate date = rs.getDate("date").toLocalDate();
                String member = rs.getString("member");
                String staff = rs.getString("staff");
                OrderPOJO Order = new OrderPOJO(id, date, member, staff);
                ans.add(Order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }

    public List<DetailOrderPOJO> getDetailOrder(int id) {
        List<DetailOrderPOJO> ans = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT order_detail.*, book.name AS book_title, promo_code.name AS promo_name FROM order_detail, book, promo_code WHERE order_detail.book_id = book.id AND order_detail.promo_id = promo_code.id AND order_detail.order_id = " + id + ";";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                String book = rs.getString("book_title");
                int quantity = rs.getInt("quantity");
                double cost = rs.getDouble("amount");
                double final_cost = rs.getDouble("final_cost");
                String promo_name = rs.getString("promo_name");
                DetailOrderPOJO detailOrder = new DetailOrderPOJO(order_id, book, quantity, cost, final_cost, promo_name);
                ans.add(detailOrder);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }
}
