/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talabat;
import CstomerPackage.*;
import OwnerPackage.*;
        

import javax.swing.JLabel;

/**
 *
 * @author Joemo
 */
public class Meal_jframe extends javax.swing.JFrame {

    /**
     * Creates new form Meal_jframe
     */
    public Meal_jframe() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        quantity = new javax.swing.JLabel();
        size = new javax.swing.JLabel();
        orderPrice = new javax.swing.JLabel();
        note = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        quantityValue = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        noteTextarea = new javax.swing.JTextArea();
        sizeCheckbox = new javax.swing.JComboBox<>();
        plus = new javax.swing.JLabel();
        minus = new javax.swing.JLabel();
        addtoBasket = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        mealName = new javax.swing.JLabel();
        mealDescription = new javax.swing.JLabel();
        mealImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Meal Options");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 102, 0));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMinimumSize(new java.awt.Dimension(540, 450));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setResizable(false);
        setSize(new java.awt.Dimension(540, 450));
        setType(java.awt.Window.Type.POPUP);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(921, 921));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        quantity.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        quantity.setText("Quantity");

        size.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        size.setText("Size");

        orderPrice.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        orderPrice.setText("50");

        note.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        note.setText("Add a note");

        total.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        total.setText("Total");

        quantityValue.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        quantityValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quantityValue.setText("1");

        noteTextarea.setColumns(20);
        noteTextarea.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        noteTextarea.setForeground(new java.awt.Color(153, 153, 153));
        noteTextarea.setLineWrap(true);
        noteTextarea.setRows(4);
        noteTextarea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(noteTextarea);

        sizeCheckbox.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        sizeCheckbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Small", "Medium ", "Large" }));
        sizeCheckbox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sizeCheckbox.setOpaque(false);
        sizeCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeCheckboxActionPerformed(evt);
            }
        });

        plus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/plus.png"))); // NOI18N
        plus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        plus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plusMouseClicked(evt);
            }
        });

        minus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/minus.png"))); // NOI18N
        minus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minusMouseClicked(evt);
            }
        });

        addtoBasket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/adtobskt.png"))); // NOI18N
        addtoBasket.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addtoBasket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addtoBasketMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(50, 50, 50)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(size)
                            .add(quantity))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(minus)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(quantityValue, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(plus))
                            .add(sizeCheckbox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 128, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(51, 51, 51))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel3Layout.createSequentialGroup()
                                .add(note)
                                .add(297, 297, 297))
                            .add(jScrollPane1)
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(total)
                                .add(45, 45, 45)
                                .add(orderPrice, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 109, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 40, Short.MAX_VALUE)
                                .add(addtoBasket)))
                        .add(54, 54, 54))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                        .add(quantityValue)
                        .add(minus)
                        .add(plus))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(quantity)
                        .add(29, 29, 29)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(size)
                            .add(sizeCheckbox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 28, Short.MAX_VALUE)
                .add(note)
                .add(18, 18, 18)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 68, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(total)
                    .add(orderPrice)
                    .add(addtoBasket))
                .add(44, 44, 44))
        );

        jPanel2.setBackground(new java.awt.Color(230, 81, 0));

        mealName.setBackground(new java.awt.Color(230, 81, 0));
        mealName.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        mealName.setForeground(new java.awt.Color(255, 255, 255));
        mealName.setText("Meal name");

        mealDescription.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        mealDescription.setForeground(new java.awt.Color(255, 255, 255));
        mealDescription.setText("Description");

        mealImage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        mealImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/no_photo.png"))); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(mealImage)
                .add(26, 26, 26)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(mealName)
                    .add(mealDescription, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 151, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(mealImage, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(9, 9, 9)
                        .add(mealName)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(mealDescription, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 534, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 533, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    protected int quantityOfmeal = 1, mealIndex;
    public float mealPriceFloat;


    private void plusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plusMouseClicked
        // TODO add your handling code here:
        if (quantityOfmeal < 20) {
            quantityOfmeal++;
        }
        quantityValue.setText(String.valueOf(quantityOfmeal));
        String s = orderPrice.getText();
        s.replace("EGP", "");
        String s1 = s.replace("EGP", "");
        float totalPrice = mealPriceFloat * quantityOfmeal;
        orderPrice.setText(String.valueOf(totalPrice) + "EGP");
    }//GEN-LAST:event_plusMouseClicked

    private void minusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minusMouseClicked
        // TODO add your handling code here:
        if (quantityOfmeal > 1) {
            quantityOfmeal--;
        }
        quantityValue.setText(String.valueOf(quantityOfmeal));
        String s = orderPrice.getText();
        String s1 = s.replace("EGP", "");
        float totalPrice = mealPriceFloat * quantityOfmeal;
        orderPrice.setText(String.valueOf(totalPrice) + "EGP");
    }//GEN-LAST:event_minusMouseClicked

    private void sizeCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeCheckboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sizeCheckboxActionPerformed

    private void addtoBasketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addtoBasketMouseClicked
        // TODO add your handling code here:

       
        String notes=noteTextarea.getText();
        String username=Talabat.customer.getUsername();
        
        int mealId=Talabat.database.getMealId(MainFrame.allMealsList.get(mealIndex).getName(), MainFrame.allMealsList.get(mealIndex).getRestaurantName());

        if(mealId==-1)return;
        String restaurantName=MainFrame.allMealsList.get(mealIndex).getRestaurantName();
        Talabat.customer.getCart().addMeal(mealId, quantityOfmeal,mealPriceFloat,notes ,username,restaurantName);
      
        Talabat.mainFrame.updateCurrentUserCartTable();
        

        

        

    }//GEN-LAST:event_addtoBasketMouseClicked

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
            java.util.logging.Logger.getLogger(Meal_jframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Meal_jframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Meal_jframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Meal_jframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Meal_jframe().setVisible(true);
            }
        });
    }

    public JLabel getOrderPrice() {
        return orderPrice;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel addtoBasket;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel mealDescription;
    public javax.swing.JLabel mealImage;
    public javax.swing.JLabel mealName;
    public javax.swing.JLabel minus;
    public javax.swing.JLabel note;
    public javax.swing.JTextArea noteTextarea;
    public javax.swing.JLabel orderPrice;
    public javax.swing.JLabel plus;
    public javax.swing.JLabel quantity;
    public javax.swing.JLabel quantityValue;
    public javax.swing.JLabel size;
    public javax.swing.JComboBox<String> sizeCheckbox;
    public javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables

}
