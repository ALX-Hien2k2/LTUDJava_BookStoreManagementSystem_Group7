/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentation;

import business.book_business;
import java.awt.Font;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import pojo.book;

/**
 *
 * @author TIN
 */
public class BooksFrame extends javax.swing.JFrame {

    book_business book_bsn = new book_business();
    Book_OutOfStock b_oos = new Book_OutOfStock();
    Book_Disable b_disable = new Book_Disable();

    /**
     * Creates new form BooksFrame
     */
    public BooksFrame() {
        initComponents();
    }

    public void ShowBook() {
        List<book> books = book_bsn.getAll();
        Object obj[][] = new Object[books.size()][8];
        String[] columnNames = {"Id", "Book", "Price", "Quantity", "Author", "Publisher", "Category", "isActive"};
        int count = 0;
        for (int i = 0; i < books.size(); i++) {
            boolean isActive = books.get(i).isIsActive();
            if (!isActive) {
                count++;
                continue;
            }
            int id = books.get(i).getId();
            String name = books.get(i).getName();
            Double price = books.get(i).getPrice();
            int quantity = books.get(i).getQuantity();
            String author = books.get(i).getAuthor();
            String publisher = books.get(i).getPublisher();
            String category = books.get(i).getCategory();

            obj[i][0] = id;
            obj[i][1] = name;
            obj[i][2] = price;
            obj[i][3] = quantity;
            obj[i][4] = author;
            obj[i][5] = publisher;
            obj[i][6] = category;
            obj[i][7] = isActive;
        }
        int size = books.size() - count;
        Object newObj[][] = new Object[size][8];
        int j = 0;
        for (int i = 0; i < books.size(); i++) {
            if (obj[i][0] != null) {
                System.out.println(obj[i][0]);
                newObj[j] = obj[i];
                j++;
            }
        }
        DefaultTableModel tableModel = new DefaultTableModel(newObj, columnNames) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Integer.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Double.class;
                    case 3:
                        return Integer.class;
                    case 4:
                        return String.class;
                    case 5:
                        return String.class;
                    case 6:
                        return String.class;
                    case 7:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        table.setAutoCreateRowSorter(true);
        table.setModel(tableModel);
        jScrollPane1.setViewportView(table);
        table.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                TableModel model = (TableModel) e.getSource();
                String columnName = model.getColumnName(column);
                if (column == 7) {
                    int data = Integer.parseInt(model.getValueAt(row, 0).toString());
                    book_bsn.Disable_book(data);

                    return;
                } else if (columnName.equals("Book")) {
                    String data = model.getValueAt(row, column).toString();
                    if (book_bsn.check_name_exists(data)) {
                        Notice_book.setText("Book is exists");

                        return;
                    } else {
                        int id = Integer.parseInt(model.getValueAt(row, 0).toString());
                        book_bsn.update_string(data, id);
                        Notice_book.setText("Complete");
                        return;
                    }
                } else if (columnName.equals("Price")) {
                    Double data = Double.parseDouble(model.getValueAt(row, column).toString());

                    int id = Integer.parseInt(model.getValueAt(row, 0).toString());
                    book_bsn.update_double(data, id);
                    Notice_book.setText("Complete");
                    return;
                } else if (columnName.equals("Quantity")) {
                    int data = Integer.parseInt(model.getValueAt(row, column).toString());

                    int id = Integer.parseInt(model.getValueAt(row, 0).toString());
                    book_bsn.update_int(data, id, columnName);
                    Notice_book.setText("Complete");
                    return;
                } else if (!columnName.equals("Id")) {
                    String data = model.getValueAt(row, column).toString();
                    int value = book_bsn.check_id_by_name(data, columnName);
                    if (value == -1) {
                        Notice_book.setText("Unknown input");

                        return;
                    } else {
                        int id = Integer.parseInt(model.getValueAt(row, 0).toString());
                        book_bsn.update_int(value, id, columnName);
                        Notice_book.setText("Complete");
                        return;
                    }
                }

            }

        });
        add_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_buttonActionPerformed(evt);
            }
        });

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        jButton1.setText("View Out Of Stock Book");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("View Disabled Book");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Add book to order");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Notice_add_book = new javax.swing.JLabel();
        Notice_book = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        add_button = new javax.swing.JButton();
        cate_tf = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        publisher_tf = new javax.swing.JTextField();
        author_tf = new javax.swing.JTextField();
        quant_tf = new javax.swing.JTextField();
        price_tf = new javax.swing.JTextField();
        name_tf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        add_new_book = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        List<book> books = book_bsn.getAll();
        books = book_bsn.getAll();
        Object obj[][] = new Object[books.size()][8];
        String[] columnNames = {"Id", "Book", "Price", "Quantity", "Author", "Publisher", "Category", "isActive"};
        int count = 0;
        for (int i = 0; i < books.size(); i++) {
            boolean isActive = books.get(i).isIsActive();
            if (!isActive) {
                count++;
                continue;
            }
            int id = books.get(i).getId();
            String name = books.get(i).getName();
            Double price = books.get(i).getPrice();
            int quantity = books.get(i).getQuantity();
            String author = books.get(i).getAuthor();
            String publisher = books.get(i).getPublisher();
            String category = books.get(i).getCategory();

            obj[i][0] = id;
            obj[i][1] = name;
            obj[i][2] = price;
            obj[i][3] = quantity;
            obj[i][4] = author;
            obj[i][5] = publisher;
            obj[i][6] = category;
            obj[i][7] = isActive;
        }
        int size = books.size()-count;
        Object newObj[][] = new Object[size][8];
        int j = 0;
        for(int i = 0;i<books.size();i++){
            if(obj[i][0]!=null){

                newObj[j]=obj[i];
                j++;
            }

        }
        table = new javax.swing.JTable();
        table_name = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Book");
        setBackground(new java.awt.Color(204, 204, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(657, 33, -1, -1));
        getContentPane().add(Notice_add_book, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 295, 251, -1));
        getContentPane().add(Notice_book, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 301, 292, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));

        add_button.setText("Add");
        add_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_buttonActionPerformed(evt);
            }
        });

        jLabel7.setText("Category");

        jLabel4.setText("Price");

        jLabel2.setText("Name");

        jLabel3.setText("Quantity");

        jLabel6.setText("Publisher");

        jLabel5.setText("Author");

        add_new_book.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        add_new_book.setText("Add new book");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cate_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(price_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quant_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(author_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(publisher_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(add_button))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(add_new_book)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(add_new_book)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(name_tf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(price_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quant_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(author_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(publisher_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cate_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(69, 69, 69)
                .addComponent(add_button)
                .addGap(96, 96, 96))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 340, 450));

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        jPanel1.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 420, -1, -1));

        jButton2.setText("View Disabled Book");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, -1, -1));

        jButton1.setText("View Out Of Stock Book");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, -1));

        jButton3.setText("Add book to order");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 330, -1, -1));

        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        jPanel1.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 330, -1, -1));

        DefaultTableModel tableModel = new DefaultTableModel(newObj, columnNames){
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                    return Integer.class;
                    case 1:
                    return String.class;
                    case 2:
                    return Double.class;
                    case 3:
                    return Integer.class;
                    case 4:
                    return String.class;
                    case 5:
                    return String.class;
                    case 6:
                    return String.class;
                    case 7:
                    return Boolean.class;
                    default:
                    return String.class;
                }
            }
        };
        table.setAutoCreateRowSorter(true);
        table.setModel(tableModel);
        table.setGridColor(new java.awt.Color(0, 0, 0));
        table.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(table);
        table.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                TableModel model = (TableModel)e.getSource();
                String columnName = model.getColumnName(column);
                if(column == 7){
                    int data =Integer.parseInt( model.getValueAt(row, 0).toString());
                    book_bsn.Disable_book(data);

                    return;
                }
                else if(columnName.equals("Book")) {
                    String data = model.getValueAt(row, column).toString();
                    if (book_bsn.check_name_exists(data)) {
                        Notice_book.setText("Book is exists");

                        return;
                    }
                    else {
                        int id =Integer.parseInt( model.getValueAt(row, 0).toString());
                        book_bsn.update_string(data, id);
                        Notice_book.setText("Complete");
                        return;
                    }
                }
                else if(columnName.equals("Price")) {
                    Double data = Double.parseDouble(model.getValueAt(row, column).toString());

                    int id =Integer.parseInt( model.getValueAt(row, 0).toString());
                    book_bsn.update_double(data, id);

                    return;
                }
                else if(columnName.equals("Quantity")) {
                    int data = Integer.parseInt(model.getValueAt(row, column).toString());

                    int id =Integer.parseInt( model.getValueAt(row, 0).toString());
                    book_bsn.update_int(data, id, columnName);

                    return;
                }
                else if(!columnName.equals("Id")) {
                    String data = model.getValueAt(row, column).toString();
                    int value = book_bsn.check_id_by_name(data, columnName);
                    if (value == -1) {
                        Notice_book.setText("Unknown input");

                        return;
                    }
                    else {
                        int id =Integer.parseInt( model.getValueAt(row, 0).toString());
                        book_bsn.update_int(value, id, columnName);
                        Notice_book.setText("Complete");

                        return;
                    }
                }

            }

        });

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 633, 280));

        table_name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        table_name.setText("Book");
        jPanel1.add(table_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        ShowBook();
    }//GEN-LAST:event_refreshActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        b_oos.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        b_disable.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_backActionPerformed

    private void add_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_buttonActionPerformed
        // TODO add your handling code here:
        String name = name_tf.getText();
        String price = price_tf.getText();
        String quantity = quant_tf.getText();
        String author = author_tf.getText();
        String publisher = publisher_tf.getText();
        String category = cate_tf.getText();
        if (name.equals("") || price.equals("") || quantity.equals("") || author.equals("") || publisher.equals("") || category.equals("")) {
            Notice_add_book.setText("Please enter full of informations");
            return;
        }
        if (book_bsn.check_name_exists(name)) {
            Notice_add_book.setText("Book is exists");
            return;
        }
        if (!isNumeric(price)) {
            Notice_add_book.setText("Please enter the correct price");
            return;
        }
        if (!isNumeric(quantity)) {
            Notice_add_book.setText("Please enter the correct quantity");
            return;
        }
        int author_id = book_bsn.check_id_by_name(author, "Author");
        if (author_id == -1) {
            Notice_add_book.setText("Unknown author");
            return;
        }

        int pub_id = book_bsn.check_id_by_name(publisher, "Publisher");
        if (pub_id == -1) {
            Notice_add_book.setText("Unknown publisher");
            return;
        }
        int cate_id = book_bsn.check_id_by_name(category, "Category");
        if (cate_id == -1) {
            Notice_add_book.setText("Unknown category");
            return;
        }

        book_bsn.add_book(name, Double.parseDouble(price), Integer.parseInt(quantity), author_id, pub_id, cate_id);
        Notice_add_book.setText("Success");
    }//GEN-LAST:event_add_buttonActionPerformed
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BooksFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BooksFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BooksFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BooksFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BooksFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Notice_add_book;
    private javax.swing.JLabel Notice_book;
    private javax.swing.JButton add_button;
    private javax.swing.JLabel add_new_book;
    private javax.swing.JTextField author_tf;
    private javax.swing.JButton back;
    private javax.swing.JTextField cate_tf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField name_tf;
    private javax.swing.JTextField price_tf;
    private javax.swing.JTextField publisher_tf;
    private javax.swing.JTextField quant_tf;
    private javax.swing.JButton refresh;
    private javax.swing.JTable table;
    private javax.swing.JLabel table_name;
    // End of variables declaration//GEN-END:variables
}
