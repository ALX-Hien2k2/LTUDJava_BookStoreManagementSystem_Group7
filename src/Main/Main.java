package Main;

import presentation.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//                // Admin: List of users account
//                UserListFrame userListFrame = new UserListFrame();
//                userListFrame.setVisible(true);
                
//                // Book category: List of book category
//                CategoryListFrame categoryListFrame = new CategoryListFrame();
//                categoryListFrame.setVisible(true);

                // Promotion: List of promotion
                PromotionListFrame promotionListFrame = new PromotionListFrame();
                promotionListFrame.setVisible(true);
            }
        });
    }
}