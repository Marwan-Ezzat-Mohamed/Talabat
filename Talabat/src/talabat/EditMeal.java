package talabat;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nouran
 */
public class EditMeal extends javax.swing.JFrame {

    /**
     * Creates new form EditMeal
     */
    public EditMeal() {
        initComponents();
    }
    int mealIndex;
    File selectedFile;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        mealName1 = new javax.swing.JLabel();
        mealNameTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        descriptionTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        priceTextField = new javax.swing.JTextField();
        applyChangesbutton = new javax.swing.JLabel();
        removeMealbutton = new javax.swing.JLabel();
        confirmationLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        mealNameLabel = new javax.swing.JLabel();
        mealDescriptionLabel = new javax.swing.JLabel();
        mealImage = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Meal");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(921, 921));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        mealName1.setBackground(new java.awt.Color(255, 255, 255));
        mealName1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        mealName1.setText("Edit Meal name");

        mealNameTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        mealNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mealNameTextFieldActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Edit Description");

        descriptionTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descriptionTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descriptionTextFieldActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Edit Price");

        priceTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        priceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceTextFieldActionPerformed(evt);
            }
        });

        applyChangesbutton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        applyChangesbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/apply.png"))); // NOI18N
        applyChangesbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                applyChangesbuttonMouseClicked(evt);
            }
        });

        removeMealbutton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        removeMealbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/remove.png"))); // NOI18N
        removeMealbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeMealbuttonMouseClicked(evt);
            }
        });

        confirmationLabel.setForeground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mealName1)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(removeMealbutton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mealNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(descriptionTextField)
                    .addComponent(priceTextField)
                    .addComponent(applyChangesbutton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addComponent(confirmationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mealName1)
                    .addComponent(mealNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeMealbutton)
                    .addComponent(applyChangesbutton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(confirmationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(230, 81, 0));

        mealNameLabel.setBackground(new java.awt.Color(230, 81, 0));
        mealNameLabel.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        mealNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        mealNameLabel.setText("Meal name");

        mealDescriptionLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        mealDescriptionLabel.setForeground(new java.awt.Color(255, 255, 255));
        mealDescriptionLabel.setText("Description");

        mealImage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        mealImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/addphoto.png"))); // NOI18N
        mealImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mealImageMouseClicked(evt);
            }
        });

        priceLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        priceLabel.setForeground(new java.awt.Color(255, 255, 255));
        priceLabel.setText("Price");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mealImage)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mealNameLabel)
                    .addComponent(mealDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 176, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mealImage, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(mealNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mealDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mealNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mealNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mealNameTextFieldActionPerformed

    private void priceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceTextFieldActionPerformed

    private void descriptionTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descriptionTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descriptionTextFieldActionPerformed

    private void applyChangesbuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_applyChangesbuttonMouseClicked
        // TODO add your handling code here:
        MainFrame.mealList.get(mealIndex).setName(mealNameTextField.getText());
        MainFrame.mealList.get(mealIndex).setDescription(descriptionTextField.getText());
        MainFrame.mealList.get(mealIndex).setMealPrice(Float.parseFloat(priceTextField.getText()));

        Meal m = new Meal(mealNameTextField.getText(), descriptionTextField.getText(), Float.parseFloat(priceTextField.getText()));

        InputStream is = null;

        if (selectedFile != null) {
            try {
                is = new FileInputStream(selectedFile);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EditMeal.class.getName()).log(Level.SEVERE, null, ex);
            }
            Talabat.owner.editMeal(mealIndex, is, m);
        } else {
            Talabat.owner.editMeal(mealIndex, m);
        }

        mealNameTextField.setText(MainFrame.mealList.get(mealIndex).getName());
        priceTextField.setText(String.valueOf(MainFrame.mealList.get(mealIndex).getMealPrice()));
        descriptionTextField.setText(MainFrame.mealList.get(mealIndex).getDescription());
        mealDescriptionLabel.setText(MainFrame.mealList.get(mealIndex).getDescription());
        mealNameLabel.setText(MainFrame.mealList.get(mealIndex).getName());
        priceLabel.setText(String.valueOf(MainFrame.mealList.get(mealIndex).getMealPrice()));

        this.dispose();


    }//GEN-LAST:event_applyChangesbuttonMouseClicked

    private void removeMealbuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeMealbuttonMouseClicked
        // TODO add your handling code here:
        String mealName = MainFrame.mealList.get(mealIndex).getName();
        Talabat.owner.removeMeal(mealName);

        mealNameTextField.setText(null);
        priceTextField.setText(null);
        descriptionTextField.setText(null);
        mealDescriptionLabel.setText(null);
        mealNameLabel.setText(null);
        priceLabel.setText(null);
        mealImage.setIcon(null);

        mealNameTextField.setEditable(false);
        priceTextField.setEditable(false);
        descriptionTextField.setEditable(false);
        this.dispose();

    }//GEN-LAST:event_removeMealbuttonMouseClicked


    private void mealImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mealImageMouseClicked
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        //if the user click on save in Jfilechooser
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            mealImage.setIcon(ResizeImage(path));
        } //if the user click on save in Jfilechooser
        else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("No File Selected");
        }
    }//GEN-LAST:event_mealImageMouseClicked

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
            java.util.logging.Logger.getLogger(EditMeal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditMeal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditMeal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditMeal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditMeal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel applyChangesbutton;
    public javax.swing.JLabel confirmationLabel;
    public javax.swing.JTextField descriptionTextField;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JLabel mealDescriptionLabel;
    public javax.swing.JLabel mealImage;
    public javax.swing.JLabel mealName1;
    public javax.swing.JLabel mealNameLabel;
    public javax.swing.JTextField mealNameTextField;
    public javax.swing.JLabel priceLabel;
    public javax.swing.JTextField priceTextField;
    public javax.swing.JLabel removeMealbutton;
    // End of variables declaration//GEN-END:variables
}
