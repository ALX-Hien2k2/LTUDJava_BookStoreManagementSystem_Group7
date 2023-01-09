package Main;

import presentation.*;
import javax.swing.*;
import pojo.UserPOJO;

public class Main {

    public static UserPOJO us = null;
    public static pro_presentation profile = null;
    public static login_presentation login = new login_presentation();
    public static Admin_Page ad_page = null;
    public static Employee_Page em_page = null;
    public static BooksFrame books = null;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Admin
                UserListFrame userListFrame = new UserListFrame();
                userListFrame.setVisible(true);
                
                // Book category
                CategoryListFrame categoryListFrame = new CategoryListFrame();
                categoryListFrame.setVisible(true);
                
                // Promotion
                PromotionListFrame promotionListFrame = new PromotionListFrame();
                promotionListFrame.setVisible(true);
                
                // Order
                OrderListFrame orderListFrame = new OrderListFrame();
                orderListFrame.setVisible(true);
                login.setVisible(true);
                // Initialize frame
//                UserListFrame userListFrame = new UserListFrame();
//
//                // Make the frame visible
//                userListFrame.setVisible(true);
            }
        });
    }
}
