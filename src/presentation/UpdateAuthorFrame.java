// package dataaccess;

// import java.sql.*;
// import java.util.*;
// import java.util.logging.Level;
// import java.util.logging.Logger;
// import pojo.AuthorPOJO;

// public class AuthorDA {
//     public List<AuthorPOJO> getAll() {
//         List<AuthorPOJO> ans = null;
//         try {
//             ans = new ArrayList<>();
//             Connection connection = MyConnection.create();
//             Statement statement;
//             statement = connection.createStatement();
//             String query = "SELECT * FROM author";
//             ResultSet rs = statement.executeQuery(query);
//             while(rs.next()){
//                 int id = rs.getInt("id");
//                 String name = rs.getString("name");
//                 String dob = rs.getString("dob");
//                 String homeTown = rs.getString("homeTown");
//                 boolean isActive = rs.getBoolean("isActive");
//                 AuthorPOJO st = new AuthorPOJO(id, name, dob, homeTown, isActive);
//                 ans.add(st);
//             }
//             rs.close();
//             statement.close();
//         } catch (SQLException ex) {
//             Logger.getLogger(AuthorDA.class.getName()).log(Level.SEVERE, null, ex);
//             ans = null;
//         }
//         return ans;
//     }

//     public boolean addAuthor(AuthorPOJO author) {
//         boolean ans = true;
//         Connection connection = null;
//         try {
//             connection = MyConnection.create();
//             String query = "INSERT INTO author(name, dob, homeTown) VALUES(?, ?, ?)";
//             PreparedStatement statement = connection.prepareStatement(query);
//             statement.setString(1, author.getName());
//             statement.setString(2, author.getDob());
//             statement.setString(3, author.getHomeTown());
//             int row = statement.executeUpdate();
//             if(row < 1){
//                 ans = false;
//             }
//             statement.close();
//         } catch (SQLException ex) {
//             Logger.getLogger(AuthorDA.class.getName()).log(Level.SEVERE, null, ex);
//             ans = false;
//         }
//         finally{
//             if(connection != null){
//                 try {
//                     connection.close();
//                 } catch (SQLException ex) {
//                     Logger.getLogger(AuthorDA.class.getName()).log(Level.SEVERE, null, ex);
//                 }
//             }
//         }
//         return ans;
//     }

//     public boolean checkAuthor(int id) {
//         boolean ans = false;
//         Connection connection = null;
//         try {
//             connection = MyConnection.create();
//             String query = "SELECT * FROM author WHERE id = ?";
//             PreparedStatement statement = connection.prepareStatement(query);
//             statement.setInt(1, id);
//             ResultSet rs = statement.executeQuery();
//             if(rs.next()){
//                 ans = true;
//             }
//             rs.close();
//             statement.close();
//         } catch (SQLException ex) {
//             Logger.getLogger(AuthorDA.class.getName()).log(Level.SEVERE, null, ex);
//             ans = false;
//         }
//         finally{
//             if(connection != null){
//                 try {
//                     connection.close();
//                 } catch (SQLException ex) {
//                     Logger.getLogger(AuthorDA.class.getName()).log(Level.SEVERE, null, ex);
//                 }
//             }
//         }
//         return ans;
//     }

//     public boolean updateAuthor(AuthorPOJO author) {
//         boolean ans = true;
//         Connection connection = null;
//         try {
//             connection = MyConnection.create();
//             String query = "UPDATE author SET name = ?, dob = ?, homeTown = ? WHERE id = ?";
//             PreparedStatement statement = connection.prepareStatement(query);
//             statement.setString(1, author.getName());
//             statement.setString(2, author.getDob());
//             statement.setString(3, author.getHomeTown());
//             statement.setInt(4, author.getId());
//             int row = statement.executeUpdate();
//             if(row < 1){
//                 ans = false;
//             }
//             statement.close();
//         } catch (SQLException ex) {
//             Logger.getLogger(AuthorDA.class.getName()).log(Level.SEVERE, null, ex);
//             ans = false;
//         }
//         finally{
//             if(connection != null){
//                 try {
//                     connection.close();
//                 } catch (SQLException ex) {
//                     Logger.getLogger(AuthorDA.class.getName()).
//                             log(Level.SEVERE, null, ex);
//                 }
//             }
//         }
//         return ans;
//     }
// }

package presentation;

import business.AuthorBU;
import pojo.AuthorPOJO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateAuthorFrame extends JFrame implements ActionListener {
    JPanel panel;
    JLabel idLabel, nameLabel, dobLabel, hometownLabel, msgLabel;
    JTextField idField, nameField, dobField, hometownField;
    JButton updateButton;

    public UpdateAuthorFrame() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel msgPanel = new JPanel();
        msgLabel = new JLabel("Update author");
        msgPanel.add(msgLabel);

        JPanel idPanel = new JPanel();
        idLabel = new JLabel("ID:");
        idField = new JTextField(20);
        idPanel.add(idLabel);
        idPanel.add(idField);

        JPanel namePanel = new JPanel();
        nameLabel = new JLabel("New name:");
        nameField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel dobPanel = new JPanel();
        dobLabel = new JLabel("New date of birth:");
        dobField = new JTextField(20);
        dobPanel.add(dobLabel);
        dobPanel.add(dobField);

        JPanel hometownPanel = new JPanel();
        hometownLabel = new JLabel("New hometown:");
        hometownField = new JTextField(20);
        hometownPanel.add(hometownLabel);
        hometownPanel.add(hometownField);

        JPanel updateButtonPanel = new JPanel();
        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        updateButtonPanel.add(updateButton);

        panel.add(msgPanel);
        panel.add(idPanel);
        panel.add(namePanel);
        panel.add(dobPanel);
        panel.add(hometownPanel);
        panel.add(updateButtonPanel);
        add(panel);

        setTitle("Update author");
        setSize(500, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public int getId() {
        return Integer.parseInt(idField.getText());
    }

    public String getName() {
        return nameField.getText();
    }

    public String getDob() {
        return dobField.getText();
    }

    public String getHomeTown() {
        return hometownField.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id = getId();
        AuthorBU authorBU = new AuthorBU();
        AuthorPOJO author = authorBU.getAuthor(id);
        if(author != null){
            author.setName(getName());
            author.setDob(getDob());
            author.setHomeTown(getHomeTown());
            boolean ans = authorBU.updateAuthor(author);
            if(ans){
                JOptionPane.showMessageDialog(this, "Update author successfully");
            }
            else{
                JOptionPane.showMessageDialog(this, "Update author failed");
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Author not found");
        }
    }
}