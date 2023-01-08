package Main;

import presentation.*;
import javax.swing.*;

public class Main {
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
            }
        });
    }
}
