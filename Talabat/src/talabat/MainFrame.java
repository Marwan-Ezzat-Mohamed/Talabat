/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talabat;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Marwan Ezzat
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form LoginFrame
     */
    
    
    
    public class Gradient extends JPanel {

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            int w = getWidth();
            int h = getHeight();
            Color color1 = new Color(254, 89, 2);  //t2el
            Color color2 = new Color(255, 122, 49); //5afef

            GradientPaint gp = new GradientPaint(1300, 0, color1, 0, h, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
    }

    private TableRowSorter orderSorter;
    private TableRowSorter mealSortter;
    static ArrayList<Order> orderList;
    static ArrayList<Meal> mealList;
    
    
    
    public void restMealsTable(String s) {
        Database mq = new Database();
        mealList=mq.returnRestMeals(s);
        String[] columnName = {"photo","Name","price", "description"};
        System.out.println("size :"+ mealList.size());
        Object[][] rows = new Object[mealList.size()][columnName.length];
        for (int i = 0; i < mealList.size(); i++) {
            
            if (mealList.get(i).Image != null) {
                ImageIcon image = new ImageIcon(new ImageIcon(mealList.get(i).Image).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
                rows[i][0] = image;
            } else {
                rows[i][0] = null;
            }
            
            rows[i][1] = mealList.get(i).name;
            rows[i][2] = String.valueOf(mealList.get(i).mealPrice);
            rows[i][3] = mealList.get(i).description;
        }
        TableModelForRestaurantsTable mealModel = new TableModelForRestaurantsTable(rows, columnName);
        mealSortter = new TableRowSorter<>(mealModel);
        mealsTable.setModel(mealModel);
        mealsTable.setRowHeight(120);

        mealsTable.getColumnModel().getColumn(0).setMaxWidth(80);
        mealsTable.getColumnModel().getColumn(0).setMinWidth(80);

        mealsTable.getColumnModel().getColumn(1).setMaxWidth(150);
        mealsTable.getColumnModel().getColumn(1).setMinWidth(150);
        
        mealsTable.getColumnModel().getColumn(2).setMaxWidth(150);
        mealsTable.getColumnModel().getColumn(2).setMinWidth(150);
        
        mealSortter.setSortable(0, false);
        mealsTable.setRowSorter(mealSortter);
        searchMealsTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchMealsTextField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                search(searchMealsTextField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                search(searchMealsTextField.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    mealSortter.setRowFilter(null);
                } else {
                    mealSortter.setRowFilter(RowFilter.regexFilter(str, 1));
                }
            }

        });
        


    }
    
    
    
    
      public void myOrdersTable(String s) {
        Database mq = new Database();
        String[] columnName = {"gdf","Meal", "Restaurant"};
        orderList=mq.returnMyOrders(s);
        Object[][] rows = new Object[list.size()][columnName.length];
        for (int i = 0; i < orderList.size(); i++) {
            
            rows[i][1] = orderList.get(i).ordererdMeals[0].name;
            rows[i][2] = orderList.get(i).restaurantName;
        }
        DefaultTableModel orderModel = new DefaultTableModel(rows, columnName);
        orderSorter = new TableRowSorter<>(orderModel);
        myOrdersTable.setModel(orderModel);
        myOrdersTable.setRowHeight(120);

        myOrdersTable.getColumnModel().getColumn(0).setMaxWidth(80);
        myOrdersTable.getColumnModel().getColumn(0).setMinWidth(80);

        myOrdersTable.getColumnModel().getColumn(1).setMaxWidth(150);
        myOrdersTable.getColumnModel().getColumn(1).setMinWidth(150);


    }

    
    
    
    private TableRowSorter sorter;

    static ArrayList<Restaurant> list;

    public void populateAllRestaurantsTable() {
        Database mq = new Database();
        list = mq.returnAllRestaurants();
        String[] columnName = {"Image", "Name", "Description"};
        Object[][] rows = new Object[list.size()][columnName.length];
        for (int i = 0; i < list.size(); i++) {
            rows[i][1] = list.get(i).name;
            rows[i][2] = list.get(i).description;
            if (list.get(i).Image != null) {
                ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).Image).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
                rows[i][0] = image;
            } else {
                rows[i][0] = null;
            }
        }
        TableModelForRestaurantsTable model = new TableModelForRestaurantsTable(rows, columnName);
        sorter = new TableRowSorter<>(model);
        jTable1.setModel(model);
        jTable1.setRowHeight(120);

        jTable1.getColumnModel().getColumn(0).setMaxWidth(80);
        jTable1.getColumnModel().getColumn(0).setMinWidth(80);

        jTable1.getColumnModel().getColumn(1).setMaxWidth(150);
        jTable1.getColumnModel().getColumn(1).setMinWidth(150);

        jTable1.getColumnModel().getColumn(2).setMaxWidth(150);
        jTable1.getColumnModel().getColumn(2).setMinWidth(150);

    }

    public MainFrame() {
        initComponents();
        

        new java.util.Timer().schedule(new java.util.TimerTask() {
            public void run() {
                endSplashScreenAnimation();
            }
        }, 3000);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainFramePanel = new javax.swing.JPanel();
        splashscreen = new javax.swing.JPanel();
        jLabel112 = new javax.swing.JLabel();
        loginPanel = new Gradient();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        invalidLoginLabel = new javax.swing.JLabel();
        showPasswordCheckBox = new javax.swing.JCheckBox();
        passwordField = new javax.swing.JPasswordField();
        talabatLogo = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        usernameTextField = new javax.swing.JTextField();
        dontHaveAccountLabel = new javax.swing.JLabel();
        signUpLinkButton = new javax.swing.JLabel();
        signUpForCustomerPanel = new Gradient();
        signUpUsernameTextField = new javax.swing.JTextField();
        SignUpButton = new javax.swing.JButton();
        talabatLogoForSignUp = new javax.swing.JLabel();
        passwordFieldForSignUp = new javax.swing.JPasswordField();
        showPasswordCheckBoxForSignUp = new javax.swing.JCheckBox();
        invalidLoginLabelForSignUp = new javax.swing.JLabel();
        passwordLabel1 = new javax.swing.JLabel();
        usernameLabelForSignUp = new javax.swing.JLabel();
        confirmPasswordLabelForSignUp = new javax.swing.JLabel();
        confirmPasswordFieldForSignUp = new javax.swing.JPasswordField();
        mobileLabelForSignUp = new javax.swing.JLabel();
        mobileTextFieldForSignUp = new javax.swing.JTextField();
        addressLabelForSignUp = new javax.swing.JLabel();
        addressTextFieldForSignUp = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        loginLinkButton = new javax.swing.JLabel();
        signUpAsOwnerLink = new javax.swing.JLabel();
        signUpAsOwnerLink1 = new javax.swing.JLabel();
        signUpForOwnerPanel = new Gradient();
        signUpUsernameTextField1 = new javax.swing.JTextField();
        SignUpButtonForOwner = new javax.swing.JButton();
        talabatLogoForSignUp1 = new javax.swing.JLabel();
        passwordFieldForSignUp1 = new javax.swing.JPasswordField();
        showPasswordCheckBoxForSignUp1 = new javax.swing.JCheckBox();
        passwordLabel2 = new javax.swing.JLabel();
        usernameLabelForSignUp1 = new javax.swing.JLabel();
        confirmPasswordLabelForSignUp1 = new javax.swing.JLabel();
        confirmPasswordFieldForSignUp1 = new javax.swing.JPasswordField();
        restaurantNameLabelForSignUp1 = new javax.swing.JLabel();
        restaurantNameTextFieldForSignUp1 = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        loginLinkButton1 = new javax.swing.JLabel();
        signUpAsCustomerLink = new javax.swing.JLabel();
        invalidLoginLabelForSignUp1 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        homePanel = new javax.swing.JPanel();
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
        jPanel1 = new javax.swing.JPanel();
        meal1pic = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        meal1pic1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        meal1pic2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        restau_pan = new javax.swing.JPanel();
        retaurants = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        retaurants2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        retaurants3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        retaurants4 = new javax.swing.JLabel();
        MyOrder = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        homeLogo = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel33 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        username = new javax.swing.JLabel();
        userPhoto = new javax.swing.JLabel();
        meals_pan8 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        myOrdersTable = new javax.swing.JTable();
        myOrdersSearchTextField = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        Basket = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        homeLogo1 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        meals_pan9 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        meal1pic25 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        meal1pic26 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        meal1pic27 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        meal1pic28 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        meal1pic29 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        meal1pic30 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        resturantPanel = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel23 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        resturantNameLabel = new javax.swing.JLabel();
        resturantDescriptionLabel = new javax.swing.JLabel();
        resturantIcon = new javax.swing.JLabel();
        meals_pan5 = new javax.swing.JPanel();
        Hot_deals5 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        mealsTable = new javax.swing.JTable();
        searchMealsTextField = new javax.swing.JTextField();
        meals_pan7 = new javax.swing.JPanel();
        Hot_deals6 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        meal1pic11 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        meal1pic13 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        meal1pic14 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        allRestuarntsPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel113 = new javax.swing.JLabel();
        SearchTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Talabat App");
        setResizable(false);

        mainFramePanel.setBackground(new java.awt.Color(255, 51, 51));
        mainFramePanel.setToolTipText("");
        mainFramePanel.setName(""); // NOI18N
        mainFramePanel.setLayout(new java.awt.CardLayout());

        splashscreen.setBackground(new java.awt.Color(254, 89, 2));

        jLabel112.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/talabatanim.gif"))); // NOI18N

        javax.swing.GroupLayout splashscreenLayout = new javax.swing.GroupLayout(splashscreen);
        splashscreen.setLayout(splashscreenLayout);
        splashscreenLayout.setHorizontalGroup(
            splashscreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, splashscreenLayout.createSequentialGroup()
                .addContainerGap(310, Short.MAX_VALUE)
                .addComponent(jLabel112)
                .addGap(291, 291, 291))
        );
        splashscreenLayout.setVerticalGroup(
            splashscreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(splashscreenLayout.createSequentialGroup()
                .addGap(326, 326, 326)
                .addComponent(jLabel112)
                .addContainerGap(349, Short.MAX_VALUE))
        );

        mainFramePanel.add(splashscreen, "card2");

        loginPanel.setBackground(new java.awt.Color(254, 89, 2));

        usernameLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setText("Username");

        passwordLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(255, 255, 255));
        passwordLabel.setText("Password");

        invalidLoginLabel.setForeground(new java.awt.Color(255, 255, 102));

        showPasswordCheckBox.setBackground(new java.awt.Color(255, 102, 0));
        showPasswordCheckBox.setForeground(new java.awt.Color(255, 255, 255));
        showPasswordCheckBox.setText("Show Password");
        showPasswordCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordCheckBoxActionPerformed(evt);
            }
        });

        passwordField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        passwordField.setBorder(null);
        passwordField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordFieldMouseClicked(evt);
            }
        });
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });
        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldKeyPressed(evt);
            }
        });

        talabatLogo.setFont(new java.awt.Font("DialogInput", 1, 54)); // NOI18N
        talabatLogo.setForeground(new java.awt.Color(255, 255, 255));
        talabatLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/talabat2x.png"))); // NOI18N

        loginButton.setBackground(new java.awt.Color(255, 198, 44));
        loginButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        loginButton.setForeground(new java.awt.Color(255, 102, 0));
        loginButton.setText("Login");
        loginButton.setBorder(null);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        usernameTextField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        usernameTextField.setBorder(null);
        usernameTextField.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        usernameTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usernameTextFieldMouseClicked(evt);
            }
        });
        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });

        dontHaveAccountLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        dontHaveAccountLabel.setForeground(new java.awt.Color(255, 255, 255));
        dontHaveAccountLabel.setText("Don't have an account?");

        signUpLinkButton.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        signUpLinkButton.setForeground(new java.awt.Color(255, 255, 255));
        signUpLinkButton.setText("Sign up");
        signUpLinkButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signUpLinkButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signUpLinkButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signUpLinkButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(430, 430, 430)
                        .addComponent(talabatLogo))
                    .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(loginPanelLayout.createSequentialGroup()
                            .addGap(464, 464, 464)
                            .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dontHaveAccountLabel))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(signUpLinkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                            .addGap(471, 471, 471)
                            .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(showPasswordCheckBox)
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(invalidLoginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordLabel)
                                    .addComponent(usernameLabel)
                                    .addComponent(usernameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                    .addComponent(passwordField))))))
                .addContainerGap(432, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(talabatLogo)
                .addGap(137, 137, 137)
                .addComponent(usernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showPasswordCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(invalidLoginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dontHaveAccountLabel)
                    .addComponent(signUpLinkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(227, Short.MAX_VALUE))
        );

        mainFramePanel.add(loginPanel, "card2");

        signUpForCustomerPanel.setBackground(new java.awt.Color(254, 89, 2));

        signUpUsernameTextField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        signUpUsernameTextField.setBorder(null);
        signUpUsernameTextField.setCaretColor(new java.awt.Color(255, 153, 0));
        signUpUsernameTextField.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        signUpUsernameTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signUpUsernameTextFieldMouseClicked(evt);
            }
        });
        signUpUsernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpUsernameTextFieldActionPerformed(evt);
            }
        });

        SignUpButton.setBackground(new java.awt.Color(255, 198, 44));
        SignUpButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        SignUpButton.setForeground(new java.awt.Color(255, 102, 0));
        SignUpButton.setText("Sign Up");
        SignUpButton.setBorder(null);
        SignUpButton.setBorderPainted(false);
        SignUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpButtonActionPerformed(evt);
            }
        });

        talabatLogoForSignUp.setFont(new java.awt.Font("DialogInput", 1, 54)); // NOI18N
        talabatLogoForSignUp.setForeground(new java.awt.Color(255, 255, 255));
        talabatLogoForSignUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/talabat2x.png"))); // NOI18N

        passwordFieldForSignUp.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        passwordFieldForSignUp.setBorder(null);
        passwordFieldForSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordFieldForSignUpMouseClicked(evt);
            }
        });
        passwordFieldForSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldForSignUpActionPerformed(evt);
            }
        });

        showPasswordCheckBoxForSignUp.setBackground(new java.awt.Color(255, 102, 0));
        showPasswordCheckBoxForSignUp.setForeground(new java.awt.Color(255, 255, 255));
        showPasswordCheckBoxForSignUp.setText("Show");
        showPasswordCheckBoxForSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordCheckBoxForSignUpActionPerformed(evt);
            }
        });

        invalidLoginLabelForSignUp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        invalidLoginLabelForSignUp.setForeground(new java.awt.Color(255, 255, 102));

        passwordLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        passwordLabel1.setForeground(new java.awt.Color(255, 255, 255));
        passwordLabel1.setText("Password");

        usernameLabelForSignUp.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        usernameLabelForSignUp.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabelForSignUp.setText("Username");

        confirmPasswordLabelForSignUp.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        confirmPasswordLabelForSignUp.setForeground(new java.awt.Color(255, 255, 255));
        confirmPasswordLabelForSignUp.setText("Confirm password");

        confirmPasswordFieldForSignUp.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        confirmPasswordFieldForSignUp.setBorder(null);
        confirmPasswordFieldForSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmPasswordFieldForSignUpMouseClicked(evt);
            }
        });
        confirmPasswordFieldForSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPasswordFieldForSignUpActionPerformed(evt);
            }
        });

        mobileLabelForSignUp.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        mobileLabelForSignUp.setForeground(new java.awt.Color(255, 255, 255));
        mobileLabelForSignUp.setText("Mobile number");

        mobileTextFieldForSignUp.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        mobileTextFieldForSignUp.setBorder(null);
        mobileTextFieldForSignUp.setCaretColor(new java.awt.Color(255, 153, 0));
        mobileTextFieldForSignUp.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        mobileTextFieldForSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mobileTextFieldForSignUpMouseClicked(evt);
            }
        });
        mobileTextFieldForSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobileTextFieldForSignUpActionPerformed(evt);
            }
        });

        addressLabelForSignUp.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        addressLabelForSignUp.setForeground(new java.awt.Color(255, 255, 255));
        addressLabelForSignUp.setText("Address");

        addressTextFieldForSignUp.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        addressTextFieldForSignUp.setBorder(null);
        addressTextFieldForSignUp.setCaretColor(new java.awt.Color(255, 153, 0));
        addressTextFieldForSignUp.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        addressTextFieldForSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addressTextFieldForSignUpMouseClicked(evt);
            }
        });
        addressTextFieldForSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressTextFieldForSignUpActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Already have an account?");

        loginLinkButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        loginLinkButton.setForeground(new java.awt.Color(255, 255, 255));
        loginLinkButton.setText("Login");
        loginLinkButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginLinkButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginLinkButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginLinkButtonMouseExited(evt);
            }
        });

        signUpAsOwnerLink.setForeground(new java.awt.Color(255, 255, 255));
        signUpAsOwnerLink.setText("owner ");
        signUpAsOwnerLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signUpAsOwnerLinkMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signUpAsOwnerLinkMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                signUpAsOwnerLinkMousePressed(evt);
            }
        });

        signUpAsOwnerLink1.setForeground(new java.awt.Color(255, 255, 255));
        signUpAsOwnerLink1.setText("Sign up as an ");
        signUpAsOwnerLink1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signUpAsOwnerLink1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout signUpForCustomerPanelLayout = new javax.swing.GroupLayout(signUpForCustomerPanel);
        signUpForCustomerPanel.setLayout(signUpForCustomerPanelLayout);
        signUpForCustomerPanelLayout.setHorizontalGroup(
            signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signUpForCustomerPanelLayout.createSequentialGroup()
                .addGroup(signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signUpForCustomerPanelLayout.createSequentialGroup()
                        .addGap(381, 381, 381)
                        .addGroup(signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordLabel1)
                            .addComponent(usernameLabelForSignUp)
                            .addGroup(signUpForCustomerPanelLayout.createSequentialGroup()
                                .addGroup(signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addressLabelForSignUp)
                                    .addComponent(mobileLabelForSignUp)
                                    .addComponent(confirmPasswordLabelForSignUp))
                                .addGap(39, 39, 39)
                                .addGroup(signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mobileTextFieldForSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(confirmPasswordFieldForSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addressTextFieldForSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(signUpForCustomerPanelLayout.createSequentialGroup()
                                .addGap(199, 199, 199)
                                .addGroup(signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(signUpUsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(signUpForCustomerPanelLayout.createSequentialGroup()
                                        .addComponent(passwordFieldForSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(showPasswordCheckBoxForSignUp))))
                            .addComponent(invalidLoginLabelForSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(signUpForCustomerPanelLayout.createSequentialGroup()
                        .addGap(430, 430, 430)
                        .addComponent(talabatLogoForSignUp))
                    .addGroup(signUpForCustomerPanelLayout.createSequentialGroup()
                        .addGap(435, 435, 435)
                        .addGroup(signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SignUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(9, 9, 9)
                        .addComponent(loginLinkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(signUpForCustomerPanelLayout.createSequentialGroup()
                        .addGap(505, 505, 505)
                        .addComponent(signUpAsOwnerLink1)
                        .addGap(0, 0, 0)
                        .addComponent(signUpAsOwnerLink)))
                .addContainerGap(321, Short.MAX_VALUE))
        );
        signUpForCustomerPanelLayout.setVerticalGroup(
            signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signUpForCustomerPanelLayout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(talabatLogoForSignUp)
                .addGap(118, 118, 118)
                .addGroup(signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signUpForCustomerPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(usernameLabelForSignUp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(signUpForCustomerPanelLayout.createSequentialGroup()
                        .addComponent(signUpUsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(signUpForCustomerPanelLayout.createSequentialGroup()
                                .addGroup(signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(passwordFieldForSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(showPasswordCheckBoxForSignUp))
                                .addGap(22, 22, 22)
                                .addGroup(signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(confirmPasswordLabelForSignUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(confirmPasswordFieldForSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(mobileLabelForSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mobileTextFieldForSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(addressLabelForSignUp)
                                    .addComponent(addressTextFieldForSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(passwordLabel1))))
                .addGap(10, 10, 10)
                .addComponent(invalidLoginLabelForSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SignUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signUpForCustomerPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1))
                    .addGroup(signUpForCustomerPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(loginLinkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signUpAsOwnerLink1)
                    .addComponent(signUpAsOwnerLink))
                .addGap(114, 114, 114))
        );

        mobileTextFieldForSignUp.getAccessibleContext().setAccessibleName("");

        mainFramePanel.add(signUpForCustomerPanel, "card2");

        signUpForOwnerPanel.setBackground(new java.awt.Color(254, 89, 2));

        signUpUsernameTextField1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        signUpUsernameTextField1.setBorder(null);
        signUpUsernameTextField1.setCaretColor(new java.awt.Color(255, 153, 0));
        signUpUsernameTextField1.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        signUpUsernameTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signUpUsernameTextField1MouseClicked(evt);
            }
        });
        signUpUsernameTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpUsernameTextField1ActionPerformed(evt);
            }
        });

        SignUpButtonForOwner.setBackground(new java.awt.Color(255, 198, 44));
        SignUpButtonForOwner.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        SignUpButtonForOwner.setForeground(new java.awt.Color(255, 102, 0));
        SignUpButtonForOwner.setText("Sign Up");
        SignUpButtonForOwner.setBorder(null);
        SignUpButtonForOwner.setBorderPainted(false);
        SignUpButtonForOwner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpButtonForOwnerActionPerformed(evt);
            }
        });

        talabatLogoForSignUp1.setFont(new java.awt.Font("DialogInput", 1, 54)); // NOI18N
        talabatLogoForSignUp1.setForeground(new java.awt.Color(255, 255, 255));
        talabatLogoForSignUp1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/talabat2x.png"))); // NOI18N

        passwordFieldForSignUp1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        passwordFieldForSignUp1.setBorder(null);
        passwordFieldForSignUp1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordFieldForSignUp1MouseClicked(evt);
            }
        });
        passwordFieldForSignUp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldForSignUp1ActionPerformed(evt);
            }
        });

        showPasswordCheckBoxForSignUp1.setBackground(new java.awt.Color(255, 102, 0));
        showPasswordCheckBoxForSignUp1.setForeground(new java.awt.Color(255, 255, 255));
        showPasswordCheckBoxForSignUp1.setText("Show");
        showPasswordCheckBoxForSignUp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordCheckBoxForSignUp1ActionPerformed(evt);
            }
        });

        passwordLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        passwordLabel2.setForeground(new java.awt.Color(255, 255, 255));
        passwordLabel2.setText("Password");

        usernameLabelForSignUp1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        usernameLabelForSignUp1.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabelForSignUp1.setText("Username");

        confirmPasswordLabelForSignUp1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        confirmPasswordLabelForSignUp1.setForeground(new java.awt.Color(255, 255, 255));
        confirmPasswordLabelForSignUp1.setText("Confirm password");

        confirmPasswordFieldForSignUp1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        confirmPasswordFieldForSignUp1.setBorder(null);
        confirmPasswordFieldForSignUp1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmPasswordFieldForSignUp1MouseClicked(evt);
            }
        });
        confirmPasswordFieldForSignUp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPasswordFieldForSignUp1ActionPerformed(evt);
            }
        });

        restaurantNameLabelForSignUp1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        restaurantNameLabelForSignUp1.setForeground(new java.awt.Color(255, 255, 255));
        restaurantNameLabelForSignUp1.setText("Restaurant name ");

        restaurantNameTextFieldForSignUp1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        restaurantNameTextFieldForSignUp1.setBorder(null);
        restaurantNameTextFieldForSignUp1.setCaretColor(new java.awt.Color(255, 153, 0));
        restaurantNameTextFieldForSignUp1.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        restaurantNameTextFieldForSignUp1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                restaurantNameTextFieldForSignUp1MouseClicked(evt);
            }
        });
        restaurantNameTextFieldForSignUp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restaurantNameTextFieldForSignUp1ActionPerformed(evt);
            }
        });

        jLabel81.setBackground(new java.awt.Color(255, 255, 255));
        jLabel81.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setText("Already have an account?");

        loginLinkButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        loginLinkButton1.setForeground(new java.awt.Color(255, 255, 255));
        loginLinkButton1.setText("Login");
        loginLinkButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginLinkButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginLinkButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginLinkButton1MouseExited(evt);
            }
        });

        signUpAsCustomerLink.setForeground(new java.awt.Color(255, 255, 255));
        signUpAsCustomerLink.setText("customer");
        signUpAsCustomerLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signUpAsCustomerLinkMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signUpAsCustomerLinkMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                signUpAsCustomerLinkMousePressed(evt);
            }
        });

        invalidLoginLabelForSignUp1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        invalidLoginLabelForSignUp1.setForeground(new java.awt.Color(255, 255, 102));

        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setText("Sign up as a");

        javax.swing.GroupLayout signUpForOwnerPanelLayout = new javax.swing.GroupLayout(signUpForOwnerPanel);
        signUpForOwnerPanel.setLayout(signUpForOwnerPanelLayout);
        signUpForOwnerPanelLayout.setHorizontalGroup(
            signUpForOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signUpForOwnerPanelLayout.createSequentialGroup()
                .addGroup(signUpForOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signUpForOwnerPanelLayout.createSequentialGroup()
                        .addGap(381, 381, 381)
                        .addGroup(signUpForOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordLabel2)
                            .addComponent(usernameLabelForSignUp1)
                            .addGroup(signUpForOwnerPanelLayout.createSequentialGroup()
                                .addGroup(signUpForOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(restaurantNameLabelForSignUp1)
                                    .addComponent(confirmPasswordLabelForSignUp1))
                                .addGap(39, 39, 39)
                                .addGroup(signUpForOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(restaurantNameTextFieldForSignUp1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(confirmPasswordFieldForSignUp1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(signUpForOwnerPanelLayout.createSequentialGroup()
                                .addGap(199, 199, 199)
                                .addGroup(signUpForOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(signUpUsernameTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(signUpForOwnerPanelLayout.createSequentialGroup()
                                        .addComponent(passwordFieldForSignUp1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(showPasswordCheckBoxForSignUp1))))
                            .addComponent(invalidLoginLabelForSignUp1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(signUpForOwnerPanelLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(signUpForOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(SignUpButtonForOwner, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel81))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(loginLinkButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(signUpForOwnerPanelLayout.createSequentialGroup()
                                .addGap(119, 119, 119)
                                .addComponent(jLabel82)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(signUpAsCustomerLink))))
                    .addGroup(signUpForOwnerPanelLayout.createSequentialGroup()
                        .addGap(430, 430, 430)
                        .addComponent(talabatLogoForSignUp1)))
                .addContainerGap(321, Short.MAX_VALUE))
        );
        signUpForOwnerPanelLayout.setVerticalGroup(
            signUpForOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signUpForOwnerPanelLayout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(talabatLogoForSignUp1)
                .addGap(118, 118, 118)
                .addGroup(signUpForOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signUpForOwnerPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(usernameLabelForSignUp1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(signUpForOwnerPanelLayout.createSequentialGroup()
                        .addComponent(signUpUsernameTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(signUpForOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(signUpForOwnerPanelLayout.createSequentialGroup()
                                .addGroup(signUpForOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(passwordFieldForSignUp1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(showPasswordCheckBoxForSignUp1))
                                .addGap(22, 22, 22)
                                .addGroup(signUpForOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(confirmPasswordLabelForSignUp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(confirmPasswordFieldForSignUp1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(signUpForOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(restaurantNameLabelForSignUp1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(restaurantNameTextFieldForSignUp1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(signUpForOwnerPanelLayout.createSequentialGroup()
                                .addComponent(passwordLabel2)
                                .addGap(118, 118, 118)))))
                .addComponent(invalidLoginLabelForSignUp1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(SignUpButtonForOwner, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(signUpForOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(loginLinkButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(signUpForOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signUpAsCustomerLink)
                    .addComponent(jLabel82))
                .addGap(230, 230, 230))
        );

        mainFramePanel.add(signUpForOwnerPanel, "card2");

        homePanel.setBackground(new java.awt.Color(255, 255, 255));
        homePanel.setPreferredSize(new java.awt.Dimension(1170, 860));

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
        about.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/aboutclr.png"))); // NOI18N
        about.setText("ABOUT");
        about.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        my_orders.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        my_orders.setForeground(new java.awt.Color(255, 255, 255));
        my_orders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/ordrclr.png"))); // NOI18N
        my_orders.setText("MY ORDERS");
        my_orders.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        my_orders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                my_ordersMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                my_ordersMousePressed(evt);
            }
        });

        Search_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (1).png"))); // NOI18N
        Search_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        all_restaurants.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        all_restaurants.setForeground(new java.awt.Color(255, 255, 255));
        all_restaurants.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/restclr.png"))); // NOI18N
        all_restaurants.setText("ALL RESTURANS");
        all_restaurants.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        all_restaurants.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                all_restaurantsMouseClicked(evt);
            }
        });

        basket.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        basket.setForeground(new java.awt.Color(255, 255, 255));
        basket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/bsktclr.png"))); // NOI18N
        basket.setText("BASKET");
        basket.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        basket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                basketMousePressed(evt);
            }
        });

        javax.swing.GroupLayout Up_panelLayout = new javax.swing.GroupLayout(Up_panel);
        Up_panel.setLayout(Up_panelLayout);
        Up_panelLayout.setHorizontalGroup(
            Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Up_panelLayout.createSequentialGroup()
                .addComponent(side_plate1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addGroup(Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Up_panelLayout.createSequentialGroup()
                        .addComponent(basket)
                        .addGap(49, 49, 49)
                        .addComponent(my_orders)
                        .addGap(42, 42, 42)
                        .addComponent(all_restaurants)
                        .addGap(35, 35, 35)
                        .addComponent(about)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Up_panelLayout.createSequentialGroup()
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Search_button)
                        .addGap(115, 115, 115))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Up_panelLayout.createSequentialGroup()
                        .addComponent(label1)
                        .addGap(239, 239, 239))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Up_panelLayout.createSequentialGroup()
                        .addComponent(Talabat_logo)
                        .addGap(258, 258, 258)))
                .addComponent(side_plate2))
        );
        Up_panelLayout.setVerticalGroup(
            Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Up_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Talabat_logo)
                .addGap(49, 49, 49)
                .addComponent(label1)
                .addGap(26, 26, 26)
                .addGroup(Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Search_button))
                .addGap(49, 49, 49)
                .addGroup(Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(about)
                    .addComponent(all_restaurants)
                    .addComponent(my_orders)
                    .addComponent(basket))
                .addGap(33, 33, 33))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Up_panelLayout.createSequentialGroup()
                .addGroup(Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(side_plate1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(side_plate2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        dwn_panel.setBackground(new java.awt.Color(255, 255, 255));

        meals_pan.setBackground(new java.awt.Color(255, 255, 255));

        Hot_deals.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Hot_deals.setText("HOT DEALS");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        meal1pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (2).png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setText("Meal Name");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Description");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("25 EGP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(meal1pic)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(meal1pic)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        meal1pic1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (2).png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel5.setText("Meal Name");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Description");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("25 EGP");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(meal1pic1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(meal1pic1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        meal1pic2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (2).png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel8.setText("Meal Name");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Description");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("25 EGP");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(meal1pic2)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(meal1pic2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout meals_panLayout = new javax.swing.GroupLayout(meals_pan);
        meals_pan.setLayout(meals_panLayout);
        meals_panLayout.setHorizontalGroup(
            meals_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meals_panLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(meals_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Hot_deals)
                    .addGroup(meals_panLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        meals_panLayout.setVerticalGroup(
            meals_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, meals_panLayout.createSequentialGroup()
                .addComponent(Hot_deals)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(meals_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        restau_pan.setBackground(new java.awt.Color(255, 255, 255));

        retaurants.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        retaurants.setText("RESTAURANTS");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel12.setText("Meal Name");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Description");

        retaurants2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (3).png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(retaurants2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13))
            .addComponent(retaurants2)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel14.setText("Meal Name");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Description");

        retaurants3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (3).png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(retaurants3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15))
            .addComponent(retaurants3)
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel16.setText("Meal Name");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Description");

        retaurants4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (3).png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(retaurants4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17))
            .addComponent(retaurants4)
        );

        javax.swing.GroupLayout restau_panLayout = new javax.swing.GroupLayout(restau_pan);
        restau_pan.setLayout(restau_panLayout);
        restau_panLayout.setHorizontalGroup(
            restau_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(restau_panLayout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addGroup(restau_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(restau_panLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(retaurants))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        restau_panLayout.setVerticalGroup(
            restau_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(restau_panLayout.createSequentialGroup()
                .addComponent(retaurants)
                .addGap(18, 18, 18)
                .addGroup(restau_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dwn_panelLayout = new javax.swing.GroupLayout(dwn_panel);
        dwn_panel.setLayout(dwn_panelLayout);
        dwn_panelLayout.setHorizontalGroup(
            dwn_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(restau_pan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dwn_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(meals_pan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        dwn_panelLayout.setVerticalGroup(
            dwn_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dwn_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(meals_pan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(restau_pan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Up_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dwn_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addComponent(Up_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dwn_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainFramePanel.add(homePanel, "card4");

        MyOrder.setBackground(new java.awt.Color(255, 255, 255));

        jPanel32.setBackground(new java.awt.Color(255, 90, 0));

        homeLogo.setBackground(new java.awt.Color(255, 226, 192));
        homeLogo.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        homeLogo.setForeground(new java.awt.Color(255, 102, 51));
        homeLogo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        homeLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (4).png"))); // NOI18N
        homeLogo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        homeLogo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        homeLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                homeLogoMousePressed(evt);
            }
        });

        jLabel84.setBackground(new java.awt.Color(255, 201, 147));
        jLabel84.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel84.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/bsktwite.png"))); // NOI18N
        jLabel84.setText("Basket");
        jLabel84.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel84.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel84MousePressed(evt);
            }
        });

        jLabel85.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(255, 255, 255));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/aboutwhite.png"))); // NOI18N
        jLabel85.setText("About");
        jLabel85.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel86.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel86.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/ordrwite.png"))); // NOI18N
        jLabel86.setText("My Orders");
        jLabel86.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel87.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/restwite.png"))); // NOI18N
        jLabel87.setText("All Restraunts");
        jLabel87.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel87.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel87MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(homeLogo)
                .addGap(49, 49, 49)
                .addComponent(jLabel84)
                .addGap(34, 34, 34)
                .addComponent(jLabel86)
                .addGap(33, 33, 33)
                .addComponent(jLabel87)
                .addGap(39, 39, 39)
                .addComponent(jLabel85)
                .addContainerGap(229, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addComponent(homeLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2750, 2750, 2750))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel84)
                            .addComponent(jLabel86)
                            .addComponent(jLabel87)
                            .addComponent(jLabel85))
                        .addGap(2760, 2760, 2760))))
        );

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(null);
        jScrollPane3.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setHorizontalScrollBar(null);

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setPreferredSize(new java.awt.Dimension(700, 1209));

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));

        username.setFont(new java.awt.Font("Verdana", 1, 48)); // NOI18N
        username.setForeground(new java.awt.Color(230, 81, 0));
        username.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        username.setText("Username");

        userPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/user5x.png"))); // NOI18N

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(userPhoto)
                .addGap(27, 27, 27)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(userPhoto))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(username)))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        meals_pan8.setBackground(new java.awt.Color(255, 255, 255));
        meals_pan8.setPreferredSize(new java.awt.Dimension(700, 221));

        jLabel89.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(230, 81, 0));
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel89.setText("My Orders");

        myOrdersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(myOrdersTable);

        myOrdersSearchTextField.setText("jTextField1");

        javax.swing.GroupLayout meals_pan8Layout = new javax.swing.GroupLayout(meals_pan8);
        meals_pan8.setLayout(meals_pan8Layout);
        meals_pan8Layout.setHorizontalGroup(
            meals_pan8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meals_pan8Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(meals_pan8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(meals_pan8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(myOrdersSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        meals_pan8Layout.setVerticalGroup(
            meals_pan8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, meals_pan8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel89)
                .addGap(10, 10, 10)
                .addComponent(myOrdersSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(meals_pan8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1117, Short.MAX_VALUE)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel33Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 98, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(meals_pan8, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(398, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel33);

        javax.swing.GroupLayout MyOrderLayout = new javax.swing.GroupLayout(MyOrder);
        MyOrder.setLayout(MyOrderLayout);
        MyOrderLayout.setHorizontalGroup(
            MyOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3)
        );
        MyOrderLayout.setVerticalGroup(
            MyOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MyOrderLayout.createSequentialGroup()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE))
        );

        mainFramePanel.add(MyOrder, "card6");

        Basket.setBackground(new java.awt.Color(255, 255, 255));

        jPanel41.setBackground(new java.awt.Color(255, 90, 0));

        homeLogo1.setBackground(new java.awt.Color(255, 226, 192));
        homeLogo1.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        homeLogo1.setForeground(new java.awt.Color(255, 102, 51));
        homeLogo1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        homeLogo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (4).png"))); // NOI18N
        homeLogo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        homeLogo1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        homeLogo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                homeLogo1MousePressed(evt);
            }
        });

        jLabel88.setBackground(new java.awt.Color(255, 201, 147));
        jLabel88.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/bsktwite.png"))); // NOI18N
        jLabel88.setText("Basket");
        jLabel88.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel90.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/aboutwhite.png"))); // NOI18N
        jLabel90.setText("About");
        jLabel90.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel91.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel91.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/ordrwite.png"))); // NOI18N
        jLabel91.setText("My Orders");
        jLabel91.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel92.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(255, 255, 255));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/restwite.png"))); // NOI18N
        jLabel92.setText("All Restraunts");
        jLabel92.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(homeLogo1)
                .addGap(49, 49, 49)
                .addComponent(jLabel88)
                .addGap(34, 34, 34)
                .addComponent(jLabel91)
                .addGap(33, 33, 33)
                .addComponent(jLabel92)
                .addGap(39, 39, 39)
                .addComponent(jLabel90)
                .addContainerGap(229, Short.MAX_VALUE))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                        .addComponent(homeLogo1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2750, 2750, 2750))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel88)
                            .addComponent(jLabel91)
                            .addComponent(jLabel92)
                            .addComponent(jLabel90))
                        .addGap(2760, 2760, 2760))))
        );

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/additems.png"))); // NOI18N
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/Chkout.png"))); // NOI18N
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jScrollPane2.setBorder(null);

        meals_pan9.setBackground(new java.awt.Color(255, 255, 255));
        meals_pan9.setPreferredSize(new java.awt.Dimension(600, 800));

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));
        jPanel44.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        meal1pic25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (2).png"))); // NOI18N
        meal1pic25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meal1pic25MouseClicked(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel74.setText("Meal Name");

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel75.setText("Quantity");

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel76.setText("25 EGP");

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addComponent(meal1pic25)
                .addGap(18, 18, 18)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel75)
                    .addComponent(jLabel74)
                    .addComponent(jLabel76))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel74)
                .addGap(18, 18, 18)
                .addComponent(jLabel75)
                .addGap(31, 31, 31)
                .addComponent(jLabel76)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addComponent(meal1pic25)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel45.setBackground(new java.awt.Color(255, 255, 255));
        jPanel45.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        meal1pic26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (2).png"))); // NOI18N

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel78.setText("Meal Name");

        jLabel79.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel79.setText("Quantity");

        jLabel83.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel83.setText("25 EGP");

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addComponent(meal1pic26)
                .addGap(18, 18, 18)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel79)
                    .addComponent(jLabel78)
                    .addComponent(jLabel83))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel78)
                .addGap(18, 18, 18)
                .addComponent(jLabel79)
                .addGap(31, 31, 31)
                .addComponent(jLabel83)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createSequentialGroup()
                .addComponent(meal1pic26)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel46.setBackground(new java.awt.Color(255, 255, 255));
        jPanel46.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        meal1pic27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (2).png"))); // NOI18N
        meal1pic27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meal1pic27MouseClicked(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel77.setText("Meal Name");

        jLabel94.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel94.setText("Quantity");

        jLabel95.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel95.setText("25 EGP");

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addComponent(meal1pic27)
                .addGap(18, 18, 18)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel94)
                    .addComponent(jLabel77)
                    .addComponent(jLabel95))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel77)
                .addGap(18, 18, 18)
                .addComponent(jLabel94)
                .addGap(31, 31, 31)
                .addComponent(jLabel95)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addComponent(meal1pic27)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));
        jPanel47.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        meal1pic28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (2).png"))); // NOI18N

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel96.setText("Meal Name");

        jLabel97.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel97.setText("Quantity");

        jLabel98.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel98.setText("25 EGP");

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addComponent(meal1pic28)
                .addGap(18, 18, 18)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel97)
                    .addComponent(jLabel96)
                    .addComponent(jLabel98))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel96)
                .addGap(18, 18, 18)
                .addComponent(jLabel97)
                .addGap(31, 31, 31)
                .addComponent(jLabel98)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                .addComponent(meal1pic28)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel48.setBackground(new java.awt.Color(255, 255, 255));
        jPanel48.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        meal1pic29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (2).png"))); // NOI18N

        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel99.setText("Meal Name");

        jLabel100.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel100.setText("Quantity");

        jLabel101.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel101.setText("25 EGP");

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addComponent(meal1pic29)
                .addGap(18, 18, 18)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel100)
                    .addComponent(jLabel99)
                    .addComponent(jLabel101))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel99)
                .addGap(18, 18, 18)
                .addComponent(jLabel100)
                .addGap(31, 31, 31)
                .addComponent(jLabel101)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                .addComponent(meal1pic29)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel49.setBackground(new java.awt.Color(255, 255, 255));
        jPanel49.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        meal1pic30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (2).png"))); // NOI18N
        meal1pic30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meal1pic30MouseClicked(evt);
            }
        });

        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel102.setText("Meal Name");

        jLabel103.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel103.setText("Quantity");

        jLabel104.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel104.setText("25 EGP");

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addComponent(meal1pic30)
                .addGap(18, 18, 18)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel103)
                    .addComponent(jLabel102)
                    .addComponent(jLabel104))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel102)
                .addGap(18, 18, 18)
                .addComponent(jLabel103)
                .addGap(31, 31, 31)
                .addComponent(jLabel104)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel49Layout.createSequentialGroup()
                .addComponent(meal1pic30)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout meals_pan9Layout = new javax.swing.GroupLayout(meals_pan9);
        meals_pan9.setLayout(meals_pan9Layout);
        meals_pan9Layout.setHorizontalGroup(
            meals_pan9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meals_pan9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(meals_pan9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(meals_pan9Layout.createSequentialGroup()
                        .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(meals_pan9Layout.createSequentialGroup()
                        .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(meals_pan9Layout.createSequentialGroup()
                        .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        meals_pan9Layout.setVerticalGroup(
            meals_pan9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meals_pan9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(meals_pan9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(meals_pan9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(meals_pan9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(268, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(meals_pan9);

        jLabel93.setFont(new java.awt.Font("Verdana", 1, 40)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(230, 81, 0));
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel93.setText("Basket");

        jLabel105.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel105.setText("55 EGP");

        jLabel106.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel106.setText("VAT");

        jLabel107.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel107.setText("Total");

        jLabel108.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel108.setText("Subtotal");

        jLabel109.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel109.setText("55 EGP");

        jLabel110.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel110.setText("55 EGP");

        jLabel111.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/bskt3x.png"))); // NOI18N

        javax.swing.GroupLayout BasketLayout = new javax.swing.GroupLayout(Basket);
        Basket.setLayout(BasketLayout);
        BasketLayout.setHorizontalGroup(
            BasketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BasketLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(BasketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BasketLayout.createSequentialGroup()
                        .addGap(325, 325, 325)
                        .addComponent(jLabel111)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel93))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(118, 118, 118))
            .addGroup(BasketLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(BasketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel107)
                    .addComponent(jLabel106)
                    .addComponent(jLabel108)
                    .addComponent(jLabel18))
                .addGap(58, 58, 58)
                .addGroup(BasketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel109, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel110, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel105, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BasketLayout.setVerticalGroup(
            BasketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BasketLayout.createSequentialGroup()
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addGroup(BasketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel93)
                    .addComponent(jLabel111))
                .addGroup(BasketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BasketLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BasketLayout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addGroup(BasketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel108)
                            .addComponent(jLabel105))
                        .addGap(18, 18, 18)
                        .addGroup(BasketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel109)
                            .addComponent(jLabel106))
                        .addGap(18, 18, 18)
                        .addGroup(BasketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel107)
                            .addComponent(jLabel110))
                        .addGap(39, 39, 39)
                        .addGroup(BasketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel18))))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        mainFramePanel.add(Basket, "card6");

        resturantPanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel18.setBackground(new java.awt.Color(255, 90, 0));

        jLabel39.setBackground(new java.awt.Color(255, 226, 192));
        jLabel39.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 102, 51));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (4).png"))); // NOI18N
        jLabel39.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel39.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel39MouseClicked(evt);
            }
        });

        jLabel42.setBackground(new java.awt.Color(255, 201, 147));
        jLabel42.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/bsktwite.png"))); // NOI18N
        jLabel42.setText("Basket");
        jLabel42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel43.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/aboutwhite.png"))); // NOI18N
        jLabel43.setText("About");
        jLabel43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel44.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/ordrwite.png"))); // NOI18N
        jLabel44.setText("My Orders");
        jLabel44.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel80.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/restwite.png"))); // NOI18N
        jLabel80.setText("All Restraunts");
        jLabel80.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39)
                .addGap(49, 49, 49)
                .addComponent(jLabel42)
                .addGap(34, 34, 34)
                .addComponent(jLabel44)
                .addGap(33, 33, 33)
                .addComponent(jLabel80)
                .addGap(39, 39, 39)
                .addComponent(jLabel43)
                .addContainerGap(229, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2750, 2750, 2750))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel42)
                            .addComponent(jLabel44)
                            .addComponent(jLabel80)
                            .addComponent(jLabel43))
                        .addGap(2760, 2760, 2760))))
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBar(null);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setPreferredSize(new java.awt.Dimension(700, 1209));

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));

        resturantNameLabel.setFont(new java.awt.Font("Verdana", 1, 48)); // NOI18N
        resturantNameLabel.setForeground(new java.awt.Color(230, 81, 0));
        resturantNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        resturantNameLabel.setText("McDonald's");

        resturantDescriptionLabel.setBackground(new java.awt.Color(230, 81, 0));
        resturantDescriptionLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        resturantDescriptionLabel.setForeground(new java.awt.Color(230, 81, 0));
        resturantDescriptionLabel.setText("Burgers, Fast Food, Breakfast");
        resturantDescriptionLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        resturantIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (3).png"))); // NOI18N

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(resturantIcon)
                .addGap(46, 46, 46)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resturantNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resturantDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(resturantIcon)
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(resturantNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(resturantDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        meals_pan5.setBackground(new java.awt.Color(255, 255, 255));
        meals_pan5.setPreferredSize(new java.awt.Dimension(700, 221));

        Hot_deals5.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        Hot_deals5.setText("Meals");

        mealsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        mealsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mealsTableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(mealsTable);

        searchMealsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMealsTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout meals_pan5Layout = new javax.swing.GroupLayout(meals_pan5);
        meals_pan5.setLayout(meals_pan5Layout);
        meals_pan5Layout.setHorizontalGroup(
            meals_pan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meals_pan5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(meals_pan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Hot_deals5)
                    .addComponent(searchMealsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(454, Short.MAX_VALUE))
        );
        meals_pan5Layout.setVerticalGroup(
            meals_pan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meals_pan5Layout.createSequentialGroup()
                .addComponent(Hot_deals5)
                .addGap(15, 15, 15)
                .addComponent(searchMealsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(204, Short.MAX_VALUE))
        );

        meals_pan7.setBackground(new java.awt.Color(255, 255, 255));
        meals_pan7.setPreferredSize(new java.awt.Dimension(700, 221));

        Hot_deals6.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        Hot_deals6.setText("Top selling");

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        meal1pic11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (2).png"))); // NOI18N
        meal1pic11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meal1pic11MouseClicked(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel45.setText("Meal Name");

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel46.setText("Description");

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel47.setText("25 EGP");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(meal1pic11)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addComponent(jLabel45)
                    .addComponent(jLabel47))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addGap(18, 18, 18)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel47)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addComponent(meal1pic11)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        meal1pic13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (2).png"))); // NOI18N

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel51.setText("Meal Name");

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel52.setText("Description");

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel53.setText("25 EGP");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(meal1pic13)
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52)
                    .addComponent(jLabel51)
                    .addComponent(jLabel53))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51)
                .addGap(18, 18, 18)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel53)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addComponent(meal1pic13)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        meal1pic14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (2).png"))); // NOI18N

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel57.setText("Meal Name");

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel58.setText("Description");

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel59.setText("25 EGP");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(meal1pic14)
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58)
                    .addComponent(jLabel57)
                    .addComponent(jLabel59))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57)
                .addGap(18, 18, 18)
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel59)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addComponent(meal1pic14)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout meals_pan7Layout = new javax.swing.GroupLayout(meals_pan7);
        meals_pan7.setLayout(meals_pan7Layout);
        meals_pan7Layout.setHorizontalGroup(
            meals_pan7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meals_pan7Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(meals_pan7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Hot_deals6)
                    .addGroup(meals_pan7Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        meals_pan7Layout.setVerticalGroup(
            meals_pan7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, meals_pan7Layout.createSequentialGroup()
                .addGap(0, 13, Short.MAX_VALUE)
                .addComponent(Hot_deals6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(meals_pan7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel23Layout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(meals_pan7, javax.swing.GroupLayout.PREFERRED_SIZE, 1046, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(meals_pan5, javax.swing.GroupLayout.PREFERRED_SIZE, 1085, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(meals_pan7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(meals_pan5, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(257, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel23);

        javax.swing.GroupLayout resturantPanelLayout = new javax.swing.GroupLayout(resturantPanel);
        resturantPanel.setLayout(resturantPanelLayout);
        resturantPanelLayout.setHorizontalGroup(
            resturantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        resturantPanelLayout.setVerticalGroup(
            resturantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resturantPanelLayout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE))
        );

        mainFramePanel.add(resturantPanel, "card6");

        jTable1.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 24)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable1);

        jLabel113.setText("Search");

        javax.swing.GroupLayout allRestuarntsPanelLayout = new javax.swing.GroupLayout(allRestuarntsPanel);
        allRestuarntsPanel.setLayout(allRestuarntsPanelLayout);
        allRestuarntsPanelLayout.setHorizontalGroup(
            allRestuarntsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allRestuarntsPanelLayout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addGroup(allRestuarntsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(allRestuarntsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel113)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(541, Short.MAX_VALUE))
        );
        allRestuarntsPanelLayout.setVerticalGroup(
            allRestuarntsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, allRestuarntsPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(allRestuarntsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel113))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        mainFramePanel.add(allRestuarntsPanel, "card9");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainFramePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainFramePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 904, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void endSplashScreenAnimation() {
        mainFramePanel.removeAll();
        mainFramePanel.repaint();
        mainFramePanel.revalidate();
        mainFramePanel.add(loginPanel);
        mainFramePanel.repaint();
        mainFramePanel.revalidate();
    }

    private void usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextFieldActionPerformed

    private void usernameTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameTextFieldMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextFieldMouseClicked

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void passwordFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordFieldMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldMouseClicked

    private void showPasswordCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordCheckBoxActionPerformed
        // TODO add your handling code here:
        if (showPasswordCheckBox.isSelected()) {
            passwordField.setEchoChar((char) 0);
        } else {
            passwordField.setEchoChar('•');
        }
    }//GEN-LAST:event_showPasswordCheckBoxActionPerformed

    private void signUpLinkButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpLinkButtonMouseClicked
        // TODO add your handling code here:

        //remove
        mainFramePanel.removeAll();
        mainFramePanel.repaint();
        mainFramePanel.revalidate();

        // add sign up panel
        mainFramePanel.add(signUpForCustomerPanel);
        loginLinkButton.setForeground(Color.WHITE);
        mainFramePanel.repaint();
        mainFramePanel.revalidate();
    }//GEN-LAST:event_signUpLinkButtonMouseClicked

    private void signUpLinkButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpLinkButtonMouseEntered
        // TODO add your handling code here:
        signUpLinkButton.setForeground(new Color(255, 198, 44));
    }//GEN-LAST:event_signUpLinkButtonMouseEntered

    private void signUpLinkButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpLinkButtonMouseExited
        // TODO add your handling code here:
        signUpLinkButton.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_signUpLinkButtonMouseExited

    private void addressTextFieldForSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressTextFieldForSignUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressTextFieldForSignUpActionPerformed

    private void addressTextFieldForSignUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addressTextFieldForSignUpMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addressTextFieldForSignUpMouseClicked

    private void mobileTextFieldForSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobileTextFieldForSignUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobileTextFieldForSignUpActionPerformed

    private void mobileTextFieldForSignUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobileTextFieldForSignUpMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_mobileTextFieldForSignUpMouseClicked

    private void confirmPasswordFieldForSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPasswordFieldForSignUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmPasswordFieldForSignUpActionPerformed

    private void confirmPasswordFieldForSignUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmPasswordFieldForSignUpMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmPasswordFieldForSignUpMouseClicked

    private void showPasswordCheckBoxForSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordCheckBoxForSignUpActionPerformed
        // TODO add your handling code here:
        if (showPasswordCheckBoxForSignUp.isSelected()) {
            passwordFieldForSignUp.setEchoChar((char) 0);
        } else {
            passwordFieldForSignUp.setEchoChar('•');
        }
    }//GEN-LAST:event_showPasswordCheckBoxForSignUpActionPerformed

    private void passwordFieldForSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldForSignUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldForSignUpActionPerformed

    private void passwordFieldForSignUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordFieldForSignUpMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldForSignUpMouseClicked

    private void signUpUsernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpUsernameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_signUpUsernameTextFieldActionPerformed

    private void signUpUsernameTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpUsernameTextFieldMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_signUpUsernameTextFieldMouseClicked

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        if (Talabat.login()) {
            System.out.println("gertg");
            mainFramePanel.removeAll();
            mainFramePanel.repaint();
            mainFramePanel.revalidate();

            // add sign up panel
            mainFramePanel.add(homePanel);
            mainFramePanel.repaint();
            mainFramePanel.revalidate();
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void SignUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpButtonActionPerformed
        // TODO add your handling code here:
        if (Talabat.signUpForCustomer()) {
            mainFramePanel.removeAll();
            mainFramePanel.repaint();
            mainFramePanel.revalidate();

            // add sign up panel
            mainFramePanel.add(loginPanel);
            mainFramePanel.repaint();
            mainFramePanel.revalidate();
        }

    }//GEN-LAST:event_SignUpButtonActionPerformed

    private void all_restaurantsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_all_restaurantsMouseClicked
        // TODO add your handling code here:
        mainFramePanel.removeAll();
        mainFramePanel.repaint();
        mainFramePanel.revalidate();

        mainFramePanel.add(allRestuarntsPanel);
        mainFramePanel.repaint();
        mainFramePanel.revalidate();
        
        populateAllRestaurantsTable();
        


        sorter.setSortable(0, false);
        jTable1.setRowSorter(sorter);
        SearchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(SearchTextField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                search(SearchTextField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                search(SearchTextField.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(str, 1));
                }
            }

        });
        
        
        
    }//GEN-LAST:event_all_restaurantsMouseClicked
    Meal_jframe meal = new Meal_jframe();
    private void meal1pic11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meal1pic11MouseClicked
        // TODO add your handling code here:
        meal.show();
    }//GEN-LAST:event_meal1pic11MouseClicked

    private void my_ordersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_my_ordersMouseClicked
        // TODO add your handling code here:
        mainFramePanel.removeAll();
        mainFramePanel.repaint();
        mainFramePanel.revalidate();

        // add sign up panel
        mainFramePanel.add(MyOrder);
        mainFramePanel.repaint();
        mainFramePanel.revalidate();
    }//GEN-LAST:event_my_ordersMouseClicked

    private void homeLogoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeLogoMousePressed
        // TODO add your handling code here:
        mainFramePanel.removeAll();
        mainFramePanel.repaint();
        mainFramePanel.revalidate();

        // add sign up panel
        mainFramePanel.add(homePanel);
        mainFramePanel.repaint();
        mainFramePanel.revalidate();
    }//GEN-LAST:event_homeLogoMousePressed

    private void passwordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Talabat.login()) {
                mainFramePanel.removeAll();
                mainFramePanel.repaint();
                mainFramePanel.revalidate();

                mainFramePanel.add(homePanel);
                mainFramePanel.repaint();
                mainFramePanel.revalidate();
            }
        }
    }//GEN-LAST:event_passwordFieldKeyPressed

    private void homeLogo1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeLogo1MousePressed
        // TODO add your handling code here:
        mainFramePanel.removeAll();
        mainFramePanel.repaint();
        mainFramePanel.revalidate();

        // add sign up panel
        mainFramePanel.add(homePanel);
        mainFramePanel.repaint();
        mainFramePanel.revalidate();
    }//GEN-LAST:event_homeLogo1MousePressed

    private void meal1pic25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meal1pic25MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_meal1pic25MouseClicked

    private void meal1pic27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meal1pic27MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_meal1pic27MouseClicked

    private void meal1pic30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meal1pic30MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_meal1pic30MouseClicked

    private void basketMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_basketMousePressed
        // TODO add your handling code here:
        mainFramePanel.removeAll();
        mainFramePanel.repaint();
        mainFramePanel.revalidate();

        // add sign up panel
        mainFramePanel.add(Basket);
        mainFramePanel.repaint();
        mainFramePanel.revalidate();


    }//GEN-LAST:event_basketMousePressed

    private void jLabel84MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel84MousePressed

        mainFramePanel.removeAll();
        mainFramePanel.repaint();
        mainFramePanel.revalidate();
        mainFramePanel.add(Basket);
        mainFramePanel.repaint();
        mainFramePanel.revalidate();
    }//GEN-LAST:event_jLabel84MousePressed

    private void loginLinkButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLinkButtonMouseClicked
        // TODO add your handling code here:
        mainFramePanel.removeAll();
        mainFramePanel.repaint();
        mainFramePanel.revalidate();

        // add sign up panel
        mainFramePanel.add(loginPanel);
        signUpLinkButton.setForeground(Color.WHITE);
        mainFramePanel.repaint();
        mainFramePanel.revalidate();
    }//GEN-LAST:event_loginLinkButtonMouseClicked

    private void loginLinkButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLinkButtonMouseEntered
        // TODO add your handling code here:
        loginLinkButton.setForeground(new Color(255, 198, 44));
    }//GEN-LAST:event_loginLinkButtonMouseEntered

    private void loginLinkButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLinkButtonMouseExited
        // TODO add your handling code here:
        loginLinkButton.setForeground(Color.WHITE);
    }//GEN-LAST:event_loginLinkButtonMouseExited

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            int oldRow = jTable1.getSelectedRow();
            int newRow = sorter.convertRowIndexToModel(oldRow);

            resturantNameLabel.setText(list.get(newRow).name);
            resturantDescriptionLabel.setText(list.get(newRow).description);
            if (list.get(newRow).Image != null) {
                ImageIcon image = new ImageIcon(new ImageIcon(list.get(newRow).Image).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
                resturantIcon.setIcon(image);
            } else {
                resturantIcon.setIcon(null);
            }
            //ImageIcon image = new ImageIcon(new ImageIcon(list.get(newRow).Image).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
            

            mainFramePanel.removeAll();
            mainFramePanel.repaint();
            mainFramePanel.revalidate();

            // add sign up panel
            mainFramePanel.add(resturantPanel);
            mainFramePanel.repaint();
            mainFramePanel.revalidate();
            
            restMealsTable(list.get(newRow).name);
        }


    }//GEN-LAST:event_jTable1MouseClicked

    private void jLabel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseClicked
        // TODO add your handling code here:
        mainFramePanel.removeAll();
        mainFramePanel.repaint();
        mainFramePanel.revalidate();

        // add sign up panel
        mainFramePanel.add(homePanel);
        mainFramePanel.repaint();
        mainFramePanel.revalidate();
    }//GEN-LAST:event_jLabel39MouseClicked

    private void signUpUsernameTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpUsernameTextField1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_signUpUsernameTextField1MouseClicked

    private void signUpUsernameTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpUsernameTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_signUpUsernameTextField1ActionPerformed

    private void SignUpButtonForOwnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpButtonForOwnerActionPerformed
        // TODO add your handling code here:

        if (Talabat.signUpForOwner()) {
            mainFramePanel.removeAll();
            mainFramePanel.repaint();
            mainFramePanel.revalidate();

            // add sign up panel
            mainFramePanel.add(loginPanel);
            mainFramePanel.repaint();
            mainFramePanel.revalidate();
        }

    }//GEN-LAST:event_SignUpButtonForOwnerActionPerformed

    private void passwordFieldForSignUp1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordFieldForSignUp1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldForSignUp1MouseClicked

    private void passwordFieldForSignUp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldForSignUp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldForSignUp1ActionPerformed

    private void confirmPasswordFieldForSignUp1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmPasswordFieldForSignUp1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmPasswordFieldForSignUp1MouseClicked

    private void confirmPasswordFieldForSignUp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPasswordFieldForSignUp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmPasswordFieldForSignUp1ActionPerformed

    private void restaurantNameTextFieldForSignUp1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_restaurantNameTextFieldForSignUp1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_restaurantNameTextFieldForSignUp1MouseClicked

    private void restaurantNameTextFieldForSignUp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restaurantNameTextFieldForSignUp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_restaurantNameTextFieldForSignUp1ActionPerformed

    private void loginLinkButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLinkButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_loginLinkButton1MouseClicked

    private void loginLinkButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLinkButton1MouseEntered
        // TODO add your handling code here:
        loginLinkButton1.setForeground(new Color(255, 198, 44));
    }//GEN-LAST:event_loginLinkButton1MouseEntered

    private void loginLinkButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLinkButton1MouseExited
        // TODO add your handling code here:
        loginLinkButton1.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_loginLinkButton1MouseExited

    private void signUpAsOwnerLink1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpAsOwnerLink1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_signUpAsOwnerLink1MouseClicked

    private void signUpAsOwnerLinkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpAsOwnerLinkMouseEntered
        // TODO add your handling code here:
        signUpAsOwnerLink.setForeground(new Color(255, 198, 44));
    }//GEN-LAST:event_signUpAsOwnerLinkMouseEntered

    private void signUpAsCustomerLinkMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpAsCustomerLinkMouseExited
        // TODO add your handling code here:
        signUpAsCustomerLink.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_signUpAsCustomerLinkMouseExited

    private void signUpAsCustomerLinkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpAsCustomerLinkMouseEntered
        // TODO add your handling code here:
        signUpAsCustomerLink.setForeground(new Color(255, 198, 44));
    }//GEN-LAST:event_signUpAsCustomerLinkMouseEntered

    private void signUpAsOwnerLinkMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpAsOwnerLinkMouseExited
        // TODO add your handling code here:
        signUpAsOwnerLink.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_signUpAsOwnerLinkMouseExited

    private void signUpAsOwnerLinkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpAsOwnerLinkMousePressed
        // TODO add your handling code here:
        mainFramePanel.removeAll();
        mainFramePanel.repaint();
        mainFramePanel.revalidate();

        // add sign up panel
        mainFramePanel.add(signUpForOwnerPanel);
        mainFramePanel.repaint();
        mainFramePanel.revalidate();
    }//GEN-LAST:event_signUpAsOwnerLinkMousePressed

    private void signUpAsCustomerLinkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpAsCustomerLinkMousePressed
        // TODO add your handling code here:
        mainFramePanel.removeAll();
        mainFramePanel.repaint();
        mainFramePanel.revalidate();

        // add sign up panel
        mainFramePanel.add(signUpForCustomerPanel);
        mainFramePanel.repaint();
        mainFramePanel.revalidate();
    }//GEN-LAST:event_signUpAsCustomerLinkMousePressed

    private void showPasswordCheckBoxForSignUp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordCheckBoxForSignUp1ActionPerformed
        // TODO add your handling code here:
         if (showPasswordCheckBoxForSignUp1.isSelected()) {
            passwordFieldForSignUp1.setEchoChar((char) 0);
        } else {
            passwordFieldForSignUp1.setEchoChar('•');
        }
    }//GEN-LAST:event_showPasswordCheckBoxForSignUp1ActionPerformed

    private void jLabel87MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel87MousePressed
        // TODO add your handling code here:
        mainFramePanel.removeAll();
        mainFramePanel.repaint();
        mainFramePanel.revalidate();

        mainFramePanel.add(allRestuarntsPanel);
        mainFramePanel.repaint();
        mainFramePanel.revalidate();
    }//GEN-LAST:event_jLabel87MousePressed

    private void my_ordersMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_my_ordersMousePressed
        // TODO add your handling code here:
        myOrdersTable(Talabat.currentUser);
        
        
       
    }//GEN-LAST:event_my_ordersMousePressed

    private void mealsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mealsTableMouseClicked
        // TODO add your handling code here:
        
        
        
        
        
    }//GEN-LAST:event_mealsTableMouseClicked

    private void searchMealsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMealsTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchMealsTextFieldActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel Basket;
    public javax.swing.JLabel Hot_deals;
    public javax.swing.JLabel Hot_deals5;
    public javax.swing.JLabel Hot_deals6;
    public javax.swing.JPanel MyOrder;
    public javax.swing.JTextField Search;
    public javax.swing.JTextField SearchTextField;
    public javax.swing.JLabel Search_button;
    public javax.swing.JButton SignUpButton;
    public javax.swing.JButton SignUpButtonForOwner;
    public javax.swing.JLabel Talabat_logo;
    public javax.swing.JPanel Up_panel;
    public javax.swing.JLabel about;
    public javax.swing.JLabel addressLabelForSignUp;
    public javax.swing.JTextField addressTextFieldForSignUp;
    public javax.swing.JPanel allRestuarntsPanel;
    public javax.swing.JLabel all_restaurants;
    public javax.swing.JLabel basket;
    public javax.swing.JPasswordField confirmPasswordFieldForSignUp;
    public javax.swing.JPasswordField confirmPasswordFieldForSignUp1;
    public javax.swing.JLabel confirmPasswordLabelForSignUp;
    public javax.swing.JLabel confirmPasswordLabelForSignUp1;
    public javax.swing.JLabel dontHaveAccountLabel;
    public javax.swing.JPanel dwn_panel;
    public javax.swing.JLabel homeLogo;
    public javax.swing.JLabel homeLogo1;
    public javax.swing.JPanel homePanel;
    public javax.swing.JLabel invalidLoginLabel;
    public javax.swing.JLabel invalidLoginLabelForSignUp;
    public javax.swing.JLabel invalidLoginLabelForSignUp1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel100;
    public javax.swing.JLabel jLabel101;
    public javax.swing.JLabel jLabel102;
    public javax.swing.JLabel jLabel103;
    public javax.swing.JLabel jLabel104;
    public javax.swing.JLabel jLabel105;
    public javax.swing.JLabel jLabel106;
    public javax.swing.JLabel jLabel107;
    public javax.swing.JLabel jLabel108;
    public javax.swing.JLabel jLabel109;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel110;
    public javax.swing.JLabel jLabel111;
    public javax.swing.JLabel jLabel112;
    public javax.swing.JLabel jLabel113;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel39;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel42;
    public javax.swing.JLabel jLabel43;
    public javax.swing.JLabel jLabel44;
    public javax.swing.JLabel jLabel45;
    public javax.swing.JLabel jLabel46;
    public javax.swing.JLabel jLabel47;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel51;
    public javax.swing.JLabel jLabel52;
    public javax.swing.JLabel jLabel53;
    public javax.swing.JLabel jLabel57;
    public javax.swing.JLabel jLabel58;
    public javax.swing.JLabel jLabel59;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel74;
    public javax.swing.JLabel jLabel75;
    public javax.swing.JLabel jLabel76;
    public javax.swing.JLabel jLabel77;
    public javax.swing.JLabel jLabel78;
    public javax.swing.JLabel jLabel79;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel80;
    public javax.swing.JLabel jLabel81;
    public javax.swing.JLabel jLabel82;
    public javax.swing.JLabel jLabel83;
    public javax.swing.JLabel jLabel84;
    public javax.swing.JLabel jLabel85;
    public javax.swing.JLabel jLabel86;
    public javax.swing.JLabel jLabel87;
    public javax.swing.JLabel jLabel88;
    public javax.swing.JLabel jLabel89;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JLabel jLabel90;
    public javax.swing.JLabel jLabel91;
    public javax.swing.JLabel jLabel92;
    public javax.swing.JLabel jLabel93;
    public javax.swing.JLabel jLabel94;
    public javax.swing.JLabel jLabel95;
    public javax.swing.JLabel jLabel96;
    public javax.swing.JLabel jLabel97;
    public javax.swing.JLabel jLabel98;
    public javax.swing.JLabel jLabel99;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel14;
    public javax.swing.JPanel jPanel18;
    public javax.swing.JPanel jPanel19;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel20;
    public javax.swing.JPanel jPanel23;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel30;
    public javax.swing.JPanel jPanel32;
    public javax.swing.JPanel jPanel33;
    public javax.swing.JPanel jPanel34;
    public javax.swing.JPanel jPanel41;
    public javax.swing.JPanel jPanel44;
    public javax.swing.JPanel jPanel45;
    public javax.swing.JPanel jPanel46;
    public javax.swing.JPanel jPanel47;
    public javax.swing.JPanel jPanel48;
    public javax.swing.JPanel jPanel49;
    public javax.swing.JPanel jPanel5;
    public javax.swing.JPanel jPanel6;
    public javax.swing.JPanel jPanel7;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JScrollPane jScrollPane4;
    public javax.swing.JScrollPane jScrollPane5;
    public javax.swing.JScrollPane jScrollPane6;
    public javax.swing.JSeparator jSeparator1;
    public javax.swing.JSeparator jSeparator2;
    public javax.swing.JSeparator jSeparator3;
    public javax.swing.JTable jTable1;
    public javax.swing.JLabel label1;
    public javax.swing.JButton loginButton;
    public javax.swing.JLabel loginLinkButton;
    public javax.swing.JLabel loginLinkButton1;
    public javax.swing.JPanel loginPanel;
    public javax.swing.JPanel mainFramePanel;
    public javax.swing.JLabel meal1pic;
    public javax.swing.JLabel meal1pic1;
    public javax.swing.JLabel meal1pic11;
    public javax.swing.JLabel meal1pic13;
    public javax.swing.JLabel meal1pic14;
    public javax.swing.JLabel meal1pic2;
    public javax.swing.JLabel meal1pic25;
    public javax.swing.JLabel meal1pic26;
    public javax.swing.JLabel meal1pic27;
    public javax.swing.JLabel meal1pic28;
    public javax.swing.JLabel meal1pic29;
    public javax.swing.JLabel meal1pic30;
    public javax.swing.JTable mealsTable;
    public javax.swing.JPanel meals_pan;
    public javax.swing.JPanel meals_pan5;
    public javax.swing.JPanel meals_pan7;
    public javax.swing.JPanel meals_pan8;
    public javax.swing.JPanel meals_pan9;
    public javax.swing.JLabel mobileLabelForSignUp;
    public javax.swing.JTextField mobileTextFieldForSignUp;
    public javax.swing.JTextField myOrdersSearchTextField;
    public javax.swing.JTable myOrdersTable;
    public javax.swing.JLabel my_orders;
    public javax.swing.JPasswordField passwordField;
    public javax.swing.JPasswordField passwordFieldForSignUp;
    public javax.swing.JPasswordField passwordFieldForSignUp1;
    public javax.swing.JLabel passwordLabel;
    public javax.swing.JLabel passwordLabel1;
    public javax.swing.JLabel passwordLabel2;
    public javax.swing.JPanel restau_pan;
    public javax.swing.JLabel restaurantNameLabelForSignUp1;
    public javax.swing.JTextField restaurantNameTextFieldForSignUp1;
    public javax.swing.JLabel resturantDescriptionLabel;
    public javax.swing.JLabel resturantIcon;
    public javax.swing.JLabel resturantNameLabel;
    public javax.swing.JPanel resturantPanel;
    public javax.swing.JLabel retaurants;
    public javax.swing.JLabel retaurants2;
    public javax.swing.JLabel retaurants3;
    public javax.swing.JLabel retaurants4;
    public javax.swing.JTextField searchMealsTextField;
    public javax.swing.JCheckBox showPasswordCheckBox;
    public javax.swing.JCheckBox showPasswordCheckBoxForSignUp;
    public javax.swing.JCheckBox showPasswordCheckBoxForSignUp1;
    public javax.swing.JLabel side_plate1;
    public javax.swing.JLabel side_plate2;
    public javax.swing.JLabel signUpAsCustomerLink;
    public javax.swing.JLabel signUpAsOwnerLink;
    public javax.swing.JLabel signUpAsOwnerLink1;
    public javax.swing.JPanel signUpForCustomerPanel;
    public javax.swing.JPanel signUpForOwnerPanel;
    public javax.swing.JLabel signUpLinkButton;
    public javax.swing.JTextField signUpUsernameTextField;
    public javax.swing.JTextField signUpUsernameTextField1;
    public javax.swing.JPanel splashscreen;
    public javax.swing.JLabel talabatLogo;
    public javax.swing.JLabel talabatLogoForSignUp;
    public javax.swing.JLabel talabatLogoForSignUp1;
    public javax.swing.JLabel userPhoto;
    public javax.swing.JLabel username;
    public javax.swing.JLabel usernameLabel;
    public javax.swing.JLabel usernameLabelForSignUp;
    public javax.swing.JLabel usernameLabelForSignUp1;
    public javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
