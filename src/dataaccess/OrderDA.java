package dataaccess;

import java.time.LocalDate;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.OrderPOJO;

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
}
