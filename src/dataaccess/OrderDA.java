/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccess;

import com.mysql.cj.xdevapi.Statement;
import com.sun.jdi.connect.spi.Connection;
import java.util.List;
import pojo.OrderPOJO;

/**
 *
 * @author TIN
 */
public class OrderDA {
    public List<OrderPOJO> getAllAccount() {
        List<OrderPOJO> ans = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            ans = new ArrayList<>();
            connection = MyConnection.create();
            statement = connection.createStatement();
            String query = "SELECT * FROM users";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                LocalDate dob = rs.getDate("dob").toLocalDate();
                String role = rs.getString("role");
                Boolean isActive = rs.getBoolean("isActive");
                UserPOJO user = new UserPOJO(id, username, password, fullname, dob, role, isActive);
                ans.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            ans = null;
        } finally {
            try {
                connection.close();
                statement.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }
}
