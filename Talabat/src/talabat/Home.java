
package talabat;

public class Home extends javax.swing.JFrame {

    public Home() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Up_panel = new javax.swing.JPanel();
        Talabat_logo = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        Search = new javax.swing.JTextField();
        side_plate1 = new javax.swing.JLabel();
        side_plate2 = new javax.swing.JLabel();
        about = new javax.swing.JLabel();
        my_orders = new javax.swing.JLabel();
        Search_button = new javax.swing.JLabel();
        all_restaurants = new javax.swing.JLabel();
        basket = new javax.swing.JLabel();
        dwn_panel = new javax.swing.JPanel();
        meals_pan = new javax.swing.JPanel();
        Hot_deals = new javax.swing.JLabel();
        meal1pic = new javax.swing.JLabel();
        meal2pic = new javax.swing.JLabel();
        meal3pic = new javax.swing.JLabel();
        meal4pic = new javax.swing.JLabel();
        restau_pan = new javax.swing.JPanel();
        retaurants = new javax.swing.JLabel();
        retaurants1 = new javax.swing.JLabel();
        retaurants2 = new javax.swing.JLabel();
        retaurants3 = new javax.swing.JLabel();
        retaurants4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home");
        setBackground(new java.awt.Color(255, 51, 51));
        setPreferredSize(new java.awt.Dimension(1170, 860));
        setResizable(false);
        setSize(new java.awt.Dimension(1170, 860));

        Up_panel.setBackground(new java.awt.Color(255, 153, 97));
        Up_panel.setName("mina"); // NOI18N

        Talabat_logo.setForeground(new java.awt.Color(51, 255, 51));
        Talabat_logo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        Talabat_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (4).png"))); // NOI18N

        label1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setText("Everyday, right away");

        Search.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Search.setToolTipText("");

        side_plate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (5).png"))); // NOI18N

        side_plate2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (6).png"))); // NOI18N

        about.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        about.setForeground(new java.awt.Color(255, 255, 255));
        about.setText("ABOUT");

        my_orders.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        my_orders.setForeground(new java.awt.Color(255, 255, 255));
        my_orders.setText("MY ORDERS");

        Search_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (1).png"))); // NOI18N
        Search_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        all_restaurants.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        all_restaurants.setForeground(new java.awt.Color(255, 255, 255));
        all_restaurants.setText("ALL RESTURANS");

        basket.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        basket.setForeground(new java.awt.Color(255, 255, 255));
        basket.setText("BASKET");

        javax.swing.GroupLayout Up_panelLayout = new javax.swing.GroupLayout(Up_panel);
        Up_panel.setLayout(Up_panelLayout);
        Up_panelLayout.setHorizontalGroup(
            Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Up_panelLayout.createSequentialGroup()
                .addComponent(side_plate1)
                .addGroup(Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Up_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Up_panelLayout.createSequentialGroup()
                                .addComponent(label1)
                                .addGap(233, 233, 233))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Up_panelLayout.createSequentialGroup()
                                .addComponent(Talabat_logo)
                                .addGap(253, 253, 253)))
                        .addComponent(side_plate2))
                    .addGroup(Up_panelLayout.createSequentialGroup()
                        .addGroup(Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Up_panelLayout.createSequentialGroup()
                                .addGap(226, 226, 226)
                                .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Search_button))
                            .addGroup(Up_panelLayout.createSequentialGroup()
                                .addGap(162, 162, 162)
                                .addComponent(basket)
                                .addGap(18, 18, 18)
                                .addComponent(my_orders)
                                .addGap(18, 18, 18)
                                .addComponent(all_restaurants)
                                .addGap(18, 18, 18)
                                .addComponent(about)))
                        .addContainerGap())))
        );
        Up_panelLayout.setVerticalGroup(
            Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Up_panelLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(Talabat_logo)
                .addGap(56, 56, 56)
                .addComponent(label1)
                .addGroup(Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Up_panelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Search_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Search))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Up_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(basket)
                            .addComponent(my_orders)
                            .addComponent(all_restaurants)
                            .addComponent(about))
                        .addGap(29, 29, 29))))
            .addGroup(Up_panelLayout.createSequentialGroup()
                .addComponent(side_plate1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(Up_panelLayout.createSequentialGroup()
                .addComponent(side_plate2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        dwn_panel.setBackground(new java.awt.Color(255, 255, 255));

        meals_pan.setBackground(new java.awt.Color(255, 255, 255));

        Hot_deals.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Hot_deals.setText("HOT DEALS");

        meal1pic.setIcon(new javax.swing.ImageIcon("C:\\Users\\minab\\Desktop\\1x\\Asset 9.png")); // NOI18N

        meal2pic.setIcon(new javax.swing.ImageIcon("C:\\Users\\minab\\Desktop\\1x\\Asset 9.png")); // NOI18N

        meal3pic.setIcon(new javax.swing.ImageIcon("C:\\Users\\minab\\Desktop\\1x\\Asset 9.png")); // NOI18N

        meal4pic.setIcon(new javax.swing.ImageIcon("C:\\Users\\minab\\Desktop\\1x\\Asset 9.png")); // NOI18N

        javax.swing.GroupLayout meals_panLayout = new javax.swing.GroupLayout(meals_pan);
        meals_pan.setLayout(meals_panLayout);
        meals_panLayout.setHorizontalGroup(
            meals_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meals_panLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(Hot_deals))
            .addGroup(meals_panLayout.createSequentialGroup()
                .addComponent(meal1pic)
                .addGap(45, 45, 45)
                .addComponent(meal2pic)
                .addGap(33, 33, 33)
                .addComponent(meal3pic)
                .addGap(37, 37, 37)
                .addComponent(meal4pic))
        );
        meals_panLayout.setVerticalGroup(
            meals_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meals_panLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Hot_deals)
                .addGap(18, 18, 18)
                .addGroup(meals_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(meal2pic)
                    .addComponent(meal1pic)
                    .addComponent(meal3pic)
                    .addComponent(meal4pic)))
        );

        restau_pan.setBackground(new java.awt.Color(255, 255, 255));

        retaurants.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        retaurants.setText("RESTAURANT");

        retaurants1.setIcon(new javax.swing.ImageIcon("C:\\Users\\minab\\Desktop\\1x\\Asset 10.png")); // NOI18N

        retaurants2.setIcon(new javax.swing.ImageIcon("C:\\Users\\minab\\Desktop\\1x\\Asset 10.png")); // NOI18N

        retaurants3.setIcon(new javax.swing.ImageIcon("C:\\Users\\minab\\Desktop\\1x\\Asset 10.png")); // NOI18N

        retaurants4.setIcon(new javax.swing.ImageIcon("C:\\Users\\minab\\Desktop\\1x\\Asset 10.png")); // NOI18N

        javax.swing.GroupLayout restau_panLayout = new javax.swing.GroupLayout(restau_pan);
        restau_pan.setLayout(restau_panLayout);
        restau_panLayout.setHorizontalGroup(
            restau_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(restau_panLayout.createSequentialGroup()
                .addComponent(retaurants1)
                .addGap(39, 39, 39)
                .addComponent(retaurants2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(retaurants3)
                .addGap(31, 31, 31)
                .addComponent(retaurants4))
            .addGroup(restau_panLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(retaurants)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        restau_panLayout.setVerticalGroup(
            restau_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(restau_panLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(retaurants)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(restau_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(retaurants4)
                    .addComponent(retaurants3)
                    .addComponent(retaurants2)
                    .addComponent(retaurants1))
                .addGap(0, 4, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dwn_panelLayout = new javax.swing.GroupLayout(dwn_panel);
        dwn_panel.setLayout(dwn_panelLayout);
        dwn_panelLayout.setHorizontalGroup(
            dwn_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dwn_panelLayout.createSequentialGroup()
                .addGroup(dwn_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(dwn_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(restau_pan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dwn_panelLayout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(meals_pan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(220, Short.MAX_VALUE))
        );
        dwn_panelLayout.setVerticalGroup(
            dwn_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dwn_panelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(meals_pan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(restau_pan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Up_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dwn_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Up_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dwn_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Up_panel.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents


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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Hot_deals;
    private javax.swing.JTextField Search;
    private javax.swing.JLabel Search_button;
    private javax.swing.JLabel Talabat_logo;
    private javax.swing.JPanel Up_panel;
    private javax.swing.JLabel about;
    private javax.swing.JLabel all_restaurants;
    private javax.swing.JLabel basket;
    private javax.swing.JPanel dwn_panel;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel meal1pic;
    private javax.swing.JLabel meal2pic;
    private javax.swing.JLabel meal3pic;
    private javax.swing.JLabel meal4pic;
    private javax.swing.JPanel meals_pan;
    private javax.swing.JLabel my_orders;
    private javax.swing.JPanel restau_pan;
    private javax.swing.JLabel retaurants;
    private javax.swing.JLabel retaurants1;
    private javax.swing.JLabel retaurants2;
    private javax.swing.JLabel retaurants3;
    private javax.swing.JLabel retaurants4;
    private javax.swing.JLabel side_plate1;
    private javax.swing.JLabel side_plate2;
    // End of variables declaration//GEN-END:variables
}
