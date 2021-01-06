/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talabat;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.List;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import static talabat.Talabat.owners;

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
    private TableRowSorter restMealsTableSorter;
    private TableRowSorter mealSortter;
    static ArrayList<Order> orderList;
    static ArrayList<Meal> mealList;
    static int ownerIndex;

    boolean allResturantsIsSorted = false;

    public void myOrdersTableForOwner1() {

        String[] columnName = {"Order No.", "", "Meal Name", "Price", "Quantity", "Date"};
        Object[][] rows = new Object[Talabat.owners[Talabat.currentOwnerIndex].restaurant.numberOfOrders][columnName.length];
        int row = 0;
        for (int i = 0; i < Talabat.owners[Talabat.currentOwnerIndex].restaurant.numberOfOrders; i++) {
            rows[row][0] = i + 1;
            for (int j = 0; j < Talabat.owners[Talabat.currentOwnerIndex].restaurant.orders[i].numberOfMealsInCart; j++) {

                rows[row][2] = Talabat.owners[Talabat.currentOwnerIndex].restaurant.orders[i].ordererdMeals[j].name;
                rows[row][3] = String.valueOf(Talabat.owners[Talabat.currentOwnerIndex].restaurant.orders[i].ordererdMeals[j].mealPrice);
                rows[row][4] = String.valueOf(Talabat.owners[Talabat.currentOwnerIndex].restaurant.orders[i].ordererdMeals[j].mealsQuantityInCart);
                rows[row][5] = Talabat.customers[Talabat.currentUserIndex].cart.orderDate;
                System.out.println(Talabat.owners[Talabat.currentOwnerIndex].restaurant.orders[i].ordererdMeals[j].name);
                row++;

            }
        }

        TableModelForMyOrders orderModel = new TableModelForMyOrders(rows, columnName);
//        orderSorter = new TableRowSorter<>(orderModel);
        myOrdersTableForOwner.setModel(orderModel);
        myOrdersTableForOwner.setRowHeight(160);

        myOrdersTableForOwner.getColumnModel().getColumn(0).setMaxWidth(60);
        myOrdersTableForOwner.getColumnModel().getColumn(0).setMinWidth(60);

        myOrdersTableForOwner.getColumnModel().getColumn(1).setMaxWidth(160);
        myOrdersTableForOwner.getColumnModel().getColumn(1).setMinWidth(160);

        myOrdersTableForOwner.getColumnModel().getColumn(2).setMaxWidth(200);
        myOrdersTableForOwner.getColumnModel().getColumn(2).setMinWidth(200);

        myOrdersTableForOwner.getColumnModel().getColumn(3).setMaxWidth(100);
        myOrdersTableForOwner.getColumnModel().getColumn(3).setMinWidth(100);

        myOrdersTableForOwner.getColumnModel().getColumn(4).setMaxWidth(100);
        myOrdersTableForOwner.getColumnModel().getColumn(4).setMinWidth(100);

        myOrdersTableForOwner.getColumnModel().getColumn(5).setMaxWidth(160);
        myOrdersTableForOwner.getColumnModel().getColumn(5).setMinWidth(160);

    }

    public void restMealsTableForOwners() {

        Database db = new Database();
        mealList = db.getRestaurantMeals(Talabat.owners[Talabat.currentOwnerIndex].restaurantName);

        String[] columnName = {"", "Meal Name", "Description", "Price"};
        Object[][] rows = new Object[mealList.size()][columnName.length];
        for (int i = 0; i < mealList.size(); i++) {

            if (mealList.get(i).databaseImage != null) {

                ImageIcon image = new ImageIcon(new ImageIcon(mealList.get(i).databaseImage).getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));

                rows[i][0] = image;

            } else {
                rows[i][0] = null;
            }
            System.out.println(ownerIndex);
            rows[i][1] = mealList.get(i).name;
            rows[i][2] = mealList.get(i).description;
            rows[i][3] = mealList.get(i).mealPrice;

        }
        TableModelForRestaurantsTable mealModel = new TableModelForRestaurantsTable(rows, columnName);
        restMealsTableSorter = new TableRowSorter<>(mealModel);
        mealsOfResturantForOwner.setModel(mealModel);
        mealsOfResturantForOwner.setRowHeight(160);

        mealsOfResturantForOwner.getColumnModel().getColumn(0).setMaxWidth(160);
        mealsOfResturantForOwner.getColumnModel().getColumn(0).setMinWidth(160);

        mealsOfResturantForOwner.getColumnModel().getColumn(1).setMaxWidth(200);
        mealsOfResturantForOwner.getColumnModel().getColumn(1).setMinWidth(200);

        mealsOfResturantForOwner.getColumnModel().getColumn(2).setMaxWidth(290);
        mealsOfResturantForOwner.getColumnModel().getColumn(2).setMinWidth(290);

        mealsOfResturantForOwner.getColumnModel().getColumn(3).setMaxWidth(100);
        mealsOfResturantForOwner.getColumnModel().getColumn(3).setMinWidth(100);

        restMealsTableSorter.setSortable(0, false);
        restMealsTableSorter.setSortable(2, false);
        restMealsTableSorter.setSortable(3, false);
        mealsOfResturantForOwner.setRowSorter(restMealsTableSorter);
        searchMealsTextField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchMealsTextField1.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                search(searchMealsTextField1.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                search(searchMealsTextField1.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    restMealsTableSorter.setRowFilter(null);
                } else {
                    restMealsTableSorter.setRowFilter(RowFilter.regexFilter(str, 1));
                }
            }

        });
    }

    public void cartTable() {

        float totalPrice = 0;
        String[] columnName = {"", "Meal Name", "Price", "Quantity"};
        Object[][] rows = new Object[Talabat.customers[Talabat.currentUserIndex].cart.numberOfMeals][columnName.length];
        for (int i = 0; i < Talabat.customers[Talabat.currentUserIndex].cart.numberOfMeals; i++) {

            for (int j = 0; j < Talabat.owners[ownerIndex].restaurant.mealCount; j++) {
                if (Talabat.customers[Talabat.currentUserIndex].cart.meals[i].name.equals(Talabat.owners[ownerIndex].restaurant.meals[j].name)) {

                    if (Talabat.owners[ownerIndex].restaurant.meals[j].Image != null) {

                        //System.out.println(Talabat.owners[ownerIndex].restaurant.name);
                        ImageIcon image = new ImageIcon((Talabat.owners[ownerIndex].restaurant.meals[j].Image).getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));
                        rows[i][0] = image;

                    } else {
                        rows[i][0] = null;
                    }
                    break;

                }

            }
            rows[i][1] = Talabat.customers[Talabat.currentUserIndex].cart.meals[i].name;
            rows[i][2] = String.valueOf(Talabat.customers[Talabat.currentUserIndex].cart.meals[i].mealPrice);
            rows[i][3] = String.valueOf(Talabat.customers[Talabat.currentUserIndex].cart.meals[i].mealsQuantityInCart);
            totalPrice += Talabat.customers[Talabat.currentUserIndex].cart.meals[i].mealPrice * Talabat.customers[Talabat.currentUserIndex].cart.meals[i].mealsQuantityInCart;

        }
        totalPriceLabel.setText(String.valueOf(totalPrice));
        TableModelForRestaurantsTable mealModel = new TableModelForRestaurantsTable(rows, columnName);

        userCart.setModel(mealModel);
        userCart.setRowHeight(160);

        userCart.getColumnModel().getColumn(0).setMaxWidth(160);
        userCart.getColumnModel().getColumn(0).setMinWidth(160);

        userCart.getColumnModel().getColumn(1).setMaxWidth(200);
        userCart.getColumnModel().getColumn(1).setMinWidth(200);

    }

    public void restaurantMealsTable(String s) {

        Database db = new Database();
        mealList = db.getRestaurantMeals(s);

        String[] columnName = {"", "Meal Name", "Description", "Price"};
        Object[][] rows = new Object[mealList.size()][columnName.length];
        for (int i = 0; i < mealList.size(); i++) {

            if (mealList.get(i).databaseImage != null) {

                ImageIcon image = new ImageIcon(new ImageIcon(mealList.get(i).databaseImage).getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));

                rows[i][0] = image;

            } else {
                rows[i][0] = null;
            }
            System.out.println(ownerIndex);
            rows[i][1] = mealList.get(i).name;
            rows[i][2] = mealList.get(i).description;
            rows[i][3] = mealList.get(i).mealPrice;

        }
        TableModelForRestaurantsTable mealModel = new TableModelForRestaurantsTable(rows, columnName);
        mealSortter = new TableRowSorter<>(mealModel);
        mealsTable.setModel(mealModel);
        mealsTable.setRowHeight(160);

        mealsTable.getColumnModel().getColumn(0).setMaxWidth(160);
        mealsTable.getColumnModel().getColumn(0).setMinWidth(160);

        mealsTable.getColumnModel().getColumn(1).setMaxWidth(200);
        mealsTable.getColumnModel().getColumn(1).setMinWidth(200);

        mealsTable.getColumnModel().getColumn(2).setMaxWidth(200);
        mealsTable.getColumnModel().getColumn(2).setMinWidth(200);

        mealsTable.getColumnModel().getColumn(3).setMaxWidth(150);
        mealsTable.getColumnModel().getColumn(3).setMinWidth(150);

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

        int mx = 0;
        for (int j = 0; j < Talabat.customers[Talabat.currentUserIndex].ordersCount; j++) {
            mx += Talabat.customers[Talabat.currentUserIndex].orders[j].numberOfMealsInCart;
        }

        String[] columnName = {"Order No.", "", "Meal Name", "Price", "Quantity", "Date"};
        Object[][] rows = new Object[mx][columnName.length];
        int row = 0;
        for (int i = 0; i < Talabat.customers[Talabat.currentUserIndex].ordersCount; i++) {

            for (int j = 0; j < Talabat.customers[Talabat.currentUserIndex].orders[i].numberOfMealsInCart; j++) {
                rows[row][0] = (i + 1);

                if (Talabat.customers[Talabat.currentUserIndex].orders[i].ordererdMeals[j].Image != null) {
                    System.out.println("iamge is not null");
                    ImageIcon image = new ImageIcon((Talabat.customers[Talabat.currentUserIndex].orders[i].ordererdMeals[j].Image).getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));
                    rows[row][1] = image;
                } else {
                    rows[row][1] = null;
                }
                rows[row][2] = Talabat.customers[Talabat.currentUserIndex].orders[i].ordererdMeals[j].name;
                rows[row][3] = String.valueOf(Talabat.customers[Talabat.currentUserIndex].orders[i].ordererdMeals[j].mealPrice);
                rows[row][4] = String.valueOf(Talabat.customers[Talabat.currentUserIndex].orders[i].ordererdMeals[j].mealsQuantityInCart);
                rows[row][5] = Talabat.customers[Talabat.currentUserIndex].cart.orderDate;

                row++;

            }
        }

        TableModelForMyOrders orderModel = new TableModelForMyOrders(rows, columnName);
//        orderSorter = new TableRowSorter<>(orderModel);
        myOrdersTable.setModel(orderModel);
        myOrdersTable.setRowHeight(160);

        myOrdersTable.getColumnModel().getColumn(0).setMaxWidth(60);
        myOrdersTable.getColumnModel().getColumn(0).setMinWidth(60);

        myOrdersTable.getColumnModel().getColumn(1).setMaxWidth(160);
        myOrdersTable.getColumnModel().getColumn(1).setMinWidth(160);

        myOrdersTable.getColumnModel().getColumn(2).setMaxWidth(200);
        myOrdersTable.getColumnModel().getColumn(2).setMinWidth(200);

        myOrdersTable.getColumnModel().getColumn(3).setMaxWidth(100);
        myOrdersTable.getColumnModel().getColumn(3).setMinWidth(100);

        myOrdersTable.getColumnModel().getColumn(4).setMaxWidth(100);
        myOrdersTable.getColumnModel().getColumn(4).setMinWidth(100);

        myOrdersTable.getColumnModel().getColumn(5).setMaxWidth(160);
        myOrdersTable.getColumnModel().getColumn(5).setMinWidth(160);

    }

    private TableRowSorter sorter;

    static ArrayList<Restaurant> allRestaurantsArrayList;
    private Map<String, Restaurant> allRestaurantsImageMap = new HashMap<>();

    String[] nameList = new String[100];
    int sz;

    Map<String, Restaurant> map = new HashMap<>();

    private void createImageMap() {

        Database d = new Database();
        allRestaurantsArrayList = d.returnAllRestaurants();
        sz = allRestaurantsArrayList.size();

        for (int i = 0; i < sz; i++) {

            allRestaurantsImageMap.put(allRestaurantsArrayList.get(i).name, allRestaurantsArrayList.get(i));
            nameList[i] = allRestaurantsArrayList.get(i).name;

        }

        //3mlt array gded 34an el size yb2a mazbot 34an lw 5leto 100 msln el scroll ely fl grid hyb2a byscroll f page fadya
        String[] s = new String[sz];

        //copy name list to s for loop can be used instead 
        System.arraycopy(nameList, 0, s, 0, sz);
        
        allRestaurantsjList.setBorder(new EmptyBorder(10, 10, 10, 10));
        allRestaurantsjList.setFixedCellWidth(220);
        allRestaurantsjList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = s;

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }
        });

    }

    public class allRestaurantsListRenderModel extends DefaultListCellRenderer {

        Font font = new Font("helvitica", Font.BOLD, 20);

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);

            if (allRestaurantsImageMap.get((String) value).Image != null) {
                ImageIcon image = new ImageIcon(new ImageIcon(allRestaurantsImageMap.get((String) value).Image).getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH));
                label.setIcon(image);

            }
            Restaurant r = allRestaurantsImageMap.get((String) value);

            String labelText = "<html> <div style='text-align: center;'>" + r.name + "<br>" + r.description + "</div></html>";
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setText(labelText);

            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.BOTTOM);
            label.setFont(font);

            //setHorizontalAlignment(JLabel.CENTER);
            return label;
        }

    }

    public void populateAllRestaurantsTable() {

        createImageMap();

        allRestaurantsjList.setCellRenderer(new allRestaurantsListRenderModel());
        allRestaurantsjList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        allRestaurantsjList.setVisibleRowCount(0);

        SearchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }

            private void filter() {
                String filter = SearchTextField.getText();
                String[] nList = new String[6];
                int i = 0;
                for (String s : nameList) {
                    if (s != null) {
                        if (s.contains(filter)) {
                            nList[i] = s;
                            i++;
                        }
                    }

                }

                allRestaurantsjList.setModel(new javax.swing.AbstractListModel<String>() {
                    String[] strings = nList;

                    @Override
                    public int getSize() {
                        return strings.length;
                    }

                    @Override
                    public String getElementAt(int i) {
                        return strings[i];
                    }
                });

            }
        });

    }

    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    public void openFileChooserForRestImageEditing() throws FileNotFoundException {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        //filter the files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);

        //if the user click on save in Jfilechooser
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            ImageIcon img = ResizeImage(path);

            InputStream is = new FileInputStream(selectedFile);
            Database db = new Database();
            db.updateRestaurantImage(is);
            resturantIcon1.setIcon(img);
        } //if the user click on save in Jfilechooser
        else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("No File Selected");
        }
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

        mainPanel = new javax.swing.JPanel();
        splashscreen = new javax.swing.JPanel();
        jLabel112 = new javax.swing.JLabel();
        loginPanel = new Gradient();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        invalidLoginLabel = new javax.swing.JLabel();
        showPasswordCheckBox = new javax.swing.JCheckBox();
        passwordField = new javax.swing.JPasswordField();
        talabatLogo = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        dontHaveAccountLabel = new javax.swing.JLabel();
        signUpLinkButton = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        signUpForCustomerPanel = new Gradient();
        signUpUsernameTextField = new javax.swing.JTextField();
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
        jLabel23 = new javax.swing.JLabel();
        signUpForOwnerPanel = new Gradient();
        signUpUsernameTextField1 = new javax.swing.JTextField();
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
        jLabel24 = new javax.swing.JLabel();
        homePanel = new javax.swing.JPanel();
        Up_panel = new javax.swing.JPanel();
        Talabat_logo = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        side_plate1 = new javax.swing.JLabel();
        side_plate2 = new javax.swing.JLabel();
        about = new javax.swing.JLabel();
        my_orders = new javax.swing.JLabel();
        all_restaurants = new javax.swing.JLabel();
        basket = new javax.swing.JLabel();
        signOutLabel = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
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
        jPanel33 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        username = new javax.swing.JLabel();
        userPhoto = new javax.swing.JLabel();
        meals_pan8 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        myOrdersTable = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        Basket = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        homeLogo1 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel107 = new javax.swing.JLabel();
        totalPriceLabel = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userCart = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        resturantPanel = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        resturantNameLabel = new javax.swing.JLabel();
        resturantDescriptionLabel = new javax.swing.JLabel();
        resturantIcon = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Hot_deals5 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        mealsTable = new javax.swing.JTable();
        searchMealsTextField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        allRestuarnts = new javax.swing.JPanel();
        resturantPanel1 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel116 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        SearchTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        allRestaurantsjList = new javax.swing.JList<>();
        jPanel21 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        resturantOwnerPanel = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        ordersForOwner = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        resturantNameLabel1 = new javax.swing.JLabel();
        resturantIcon1 = new javax.swing.JLabel();
        addMealButoon = new javax.swing.JLabel();
        descriptionTextField = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        Hot_deals6 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        mealsOfResturantForOwner = new javax.swing.JTable();
        searchMealsTextField1 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        refreshButton = new javax.swing.JLabel();
        MyOrderForOwner = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        ordersForOwner1 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        signOutFromOwner = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        username1 = new javax.swing.JLabel();
        userPhoto1 = new javax.swing.JLabel();
        meals_pan9 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        myOrdersTableForOwner = new javax.swing.JTable();
        jSeparator6 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Talabat App");
        setResizable(false);

        mainPanel.setBackground(new java.awt.Color(255, 51, 51));
        mainPanel.setToolTipText("");
        mainPanel.setName(""); // NOI18N
        mainPanel.setLayout(new java.awt.CardLayout());

        splashscreen.setBackground(new java.awt.Color(254, 89, 2));

        jLabel112.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/talabatanim.gif"))); // NOI18N

        javax.swing.GroupLayout splashscreenLayout = new javax.swing.GroupLayout(splashscreen);
        splashscreen.setLayout(splashscreenLayout);
        splashscreenLayout.setHorizontalGroup(
            splashscreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(splashscreenLayout.createSequentialGroup()
                .addGap(281, 281, 281)
                .addComponent(jLabel112)
                .addContainerGap(320, Short.MAX_VALUE))
        );
        splashscreenLayout.setVerticalGroup(
            splashscreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(splashscreenLayout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addComponent(jLabel112)
                .addContainerGap(382, Short.MAX_VALUE))
        );

        mainPanel.add(splashscreen, "card2");

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

        signUpLinkButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
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

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/login.png"))); // NOI18N
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel22MousePressed(evt);
            }
        });

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(438, 438, 438)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(showPasswordCheckBox)
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(invalidLoginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordLabel)
                                    .addComponent(usernameLabel)
                                    .addComponent(usernameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                    .addComponent(passwordField))
                                .addGroup(loginPanelLayout.createSequentialGroup()
                                    .addComponent(dontHaveAccountLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(signUpLinkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(talabatLogo, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(541, 541, 541)
                        .addComponent(jLabel22)))
                .addContainerGap(447, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(talabatLogo)
                .addGap(125, 125, 125)
                .addComponent(usernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showPasswordCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(invalidLoginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel22)
                .addGap(59, 59, 59)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dontHaveAccountLabel)
                    .addComponent(signUpLinkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(203, Short.MAX_VALUE))
        );

        mainPanel.add(loginPanel, "card2");

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

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/signup.png"))); // NOI18N
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel23MousePressed(evt);
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
                        .addGap(505, 505, 505)
                        .addComponent(signUpAsOwnerLink1)
                        .addGap(0, 0, 0)
                        .addComponent(signUpAsOwnerLink))
                    .addGroup(signUpForCustomerPanelLayout.createSequentialGroup()
                        .addGroup(signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(signUpForCustomerPanelLayout.createSequentialGroup()
                                .addGap(511, 511, 511)
                                .addComponent(jLabel23)
                                .addGap(9, 9, 9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpForCustomerPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(loginLinkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(330, Short.MAX_VALUE))
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
                .addGroup(signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signUpForCustomerPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23))
                    .addGroup(signUpForCustomerPanelLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loginLinkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(31, 31, 31)
                .addGroup(signUpForCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signUpAsOwnerLink1)
                    .addComponent(signUpAsOwnerLink))
                .addGap(114, 114, 114))
        );

        mobileTextFieldForSignUp.getAccessibleContext().setAccessibleName("");

        mainPanel.add(signUpForCustomerPanel, "card2");

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

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/signup.png"))); // NOI18N
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel24MousePressed(evt);
            }
        });

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
                                .addGap(119, 119, 119)
                                .addComponent(jLabel82)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(signUpAsCustomerLink))
                            .addGroup(signUpForOwnerPanelLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(signUpForOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel81))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(loginLinkButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(signUpForOwnerPanelLayout.createSequentialGroup()
                        .addGap(430, 430, 430)
                        .addComponent(talabatLogoForSignUp1)))
                .addContainerGap(330, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addGap(35, 35, 35)
                .addGroup(signUpForOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(loginLinkButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(signUpForOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signUpAsCustomerLink)
                    .addComponent(jLabel82))
                .addGap(230, 230, 230))
        );

        mainPanel.add(signUpForOwnerPanel, "card2");

        homePanel.setBackground(new java.awt.Color(255, 255, 255));
        homePanel.setPreferredSize(new java.awt.Dimension(1170, 860));

        Up_panel.setBackground(new java.awt.Color(255, 153, 97));
        Up_panel.setName("mina"); // NOI18N

        Talabat_logo.setForeground(new java.awt.Color(51, 255, 51));
        Talabat_logo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        Talabat_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/talabat2x.png"))); // NOI18N

        label1.setFont(new java.awt.Font("Verdana", 1, 22)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setText("username");

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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                basketMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                basketMousePressed(evt);
            }
        });

        signOutLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        signOutLabel.setForeground(new java.awt.Color(51, 51, 51));
        signOutLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/logout.png"))); // NOI18N
        signOutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signOutLabelMouseClicked(evt);
            }
        });

        label2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        label2.setForeground(new java.awt.Color(255, 255, 255));
        label2.setText("Everyday, right away");

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/userclr.png"))); // NOI18N

        javax.swing.GroupLayout Up_panelLayout = new javax.swing.GroupLayout(Up_panel);
        Up_panel.setLayout(Up_panelLayout);
        Up_panelLayout.setHorizontalGroup(
            Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Up_panelLayout.createSequentialGroup()
                .addComponent(side_plate1)
                .addGroup(Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Up_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                        .addComponent(basket)
                        .addGap(49, 49, 49)
                        .addGroup(Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Talabat_logo)
                            .addGroup(Up_panelLayout.createSequentialGroup()
                                .addComponent(my_orders)
                                .addGap(42, 42, 42)
                                .addComponent(all_restaurants)
                                .addGap(35, 35, 35)
                                .addComponent(about)))
                        .addGap(18, 18, Short.MAX_VALUE))
                    .addGroup(Up_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label1)
                        .addGap(18, 18, 18)
                        .addComponent(signOutLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(side_plate2))
            .addGroup(Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Up_panelLayout.createSequentialGroup()
                    .addContainerGap(489, Short.MAX_VALUE)
                    .addComponent(label2)
                    .addGap(465, 465, 465)))
        );
        Up_panelLayout.setVerticalGroup(
            Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Up_panelLayout.createSequentialGroup()
                .addGroup(Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(side_plate1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(side_plate2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(Up_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label1)
                    .addComponent(jLabel25)
                    .addComponent(signOutLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Talabat_logo)
                .addGap(106, 106, 106)
                .addGroup(Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(about)
                    .addComponent(all_restaurants)
                    .addComponent(my_orders)
                    .addComponent(basket))
                .addGap(33, 33, 33))
            .addGroup(Up_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Up_panelLayout.createSequentialGroup()
                    .addContainerGap(201, Short.MAX_VALUE)
                    .addComponent(label2)
                    .addGap(113, 113, 113)))
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
                .addContainerGap(117, Short.MAX_VALUE))
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
        jLabel12.setText("Rest. Name");

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
        jLabel14.setText("Rest. Name");

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
        jLabel16.setText("Rest. Name");

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
                .addContainerGap(30, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dwn_panelLayout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(meals_pan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(restau_pan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
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

        mainPanel.add(homePanel, "card4");

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
                .addContainerGap(238, Short.MAX_VALUE))
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

        myOrdersTable.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
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
        myOrdersTable.setGridColor(new java.awt.Color(255, 255, 255));
        myOrdersTable.setSelectionBackground(new java.awt.Color(255, 255, 255));
        myOrdersTable.setShowHorizontalLines(false);
        myOrdersTable.setShowVerticalLines(false);
        myOrdersTable.getTableHeader().setResizingAllowed(false);
        myOrdersTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(myOrdersTable);

        javax.swing.GroupLayout meals_pan8Layout = new javax.swing.GroupLayout(meals_pan8);
        meals_pan8.setLayout(meals_pan8Layout);
        meals_pan8Layout.setHorizontalGroup(
            meals_pan8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meals_pan8Layout.createSequentialGroup()
                .addGroup(meals_pan8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(meals_pan8Layout.createSequentialGroup()
                        .addGap(435, 435, 435)
                        .addComponent(jLabel89))
                    .addGroup(meals_pan8Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        meals_pan8Layout.setVerticalGroup(
            meals_pan8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, meals_pan8Layout.createSequentialGroup()
                .addComponent(jLabel89)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(meals_pan8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1145, Short.MAX_VALUE)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel33Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MyOrderLayout = new javax.swing.GroupLayout(MyOrder);
        MyOrder.setLayout(MyOrderLayout);
        MyOrderLayout.setHorizontalGroup(
            MyOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, 1197, Short.MAX_VALUE)
        );
        MyOrderLayout.setVerticalGroup(
            MyOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MyOrderLayout.createSequentialGroup()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(MyOrder, "card6");

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
                .addContainerGap(238, Short.MAX_VALUE))
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

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/Chkout.png"))); // NOI18N
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel93.setFont(new java.awt.Font("Verdana", 1, 40)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(230, 81, 0));
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel93.setText("Basket");

        jLabel107.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel107.setText("Total");

        totalPriceLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        totalPriceLabel.setText("55 EGP");

        jLabel111.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/bskt3x.png"))); // NOI18N

        userCart.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        userCart.setModel(new javax.swing.table.DefaultTableModel(
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
        userCart.setGridColor(new java.awt.Color(255, 255, 255));
        userCart.setSelectionBackground(new java.awt.Color(255, 208, 182));
        userCart.setShowVerticalLines(false);
        userCart.getTableHeader().setResizingAllowed(false);
        userCart.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(userCart);

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/remove.png"))); // NOI18N
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel21MouseEntered(evt);
            }
        });

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
                .addGap(61, 61, 61)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addGroup(BasketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BasketLayout.createSequentialGroup()
                        .addComponent(jLabel107)
                        .addGap(137, 137, 137)
                        .addGroup(BasketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalPriceLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jLabel21))
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
                        .addGap(52, 52, 52)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BasketLayout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addGroup(BasketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel107)
                            .addComponent(totalPriceLabel))
                        .addGap(81, 81, 81)
                        .addGroup(BasketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel11))))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        mainPanel.add(Basket, "card6");

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
                .addContainerGap(238, Short.MAX_VALUE))
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

        Hot_deals5.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        Hot_deals5.setText("Meals");

        mealsTable.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
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
        mealsTable.setGridColor(new java.awt.Color(255, 255, 255));
        mealsTable.setSelectionBackground(new java.awt.Color(255, 255, 255));
        mealsTable.setShowHorizontalLines(false);
        mealsTable.setShowVerticalLines(false);
        mealsTable.getTableHeader().setResizingAllowed(false);
        mealsTable.getTableHeader().setReorderingAllowed(false);
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

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/search.png"))); // NOI18N

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel23Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(384, 384, 384)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchMealsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(523, 523, 523)
                        .addComponent(Hot_deals5))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Hot_deals5)
                .addGap(21, 21, 21)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchMealsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout resturantPanelLayout = new javax.swing.GroupLayout(resturantPanel);
        resturantPanel.setLayout(resturantPanelLayout);
        resturantPanelLayout.setHorizontalGroup(
            resturantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 1197, Short.MAX_VALUE)
        );
        resturantPanelLayout.setVerticalGroup(
            resturantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resturantPanelLayout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE))
        );

        mainPanel.add(resturantPanel, "card6");

        allRestuarnts.setBackground(new java.awt.Color(255, 255, 255));
        allRestuarnts.setToolTipText("");
        allRestuarnts.setName(""); // NOI18N

        resturantPanel1.setBackground(new java.awt.Color(255, 255, 255));
        resturantPanel1.setPreferredSize(new java.awt.Dimension(1170, 860));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setPreferredSize(new java.awt.Dimension(700, 1209));

        jLabel116.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(255, 90, 0));
        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel116.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/rest3x.png"))); // NOI18N
        jLabel116.setText("   All Restraunts");
        jLabel116.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 90, 0));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/search.png"))); // NOI18N

        allRestaurantsjList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "mac", "pizza king", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        allRestaurantsjList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allRestaurantsjListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(allRestaurantsjList);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(438, 438, 438))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(379, 379, 379)
                        .addComponent(jLabel116))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))))
                .addGap(88, 88, 88))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel116)
                .addGap(70, 70, 70)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout resturantPanel1Layout = new javax.swing.GroupLayout(resturantPanel1);
        resturantPanel1.setLayout(resturantPanel1Layout);
        resturantPanel1Layout.setHorizontalGroup(
            resturantPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resturantPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, 1121, Short.MAX_VALUE)
                .addContainerGap())
        );
        resturantPanel1Layout.setVerticalGroup(
            resturantPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resturantPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(255, 90, 0));

        jLabel40.setBackground(new java.awt.Color(255, 226, 192));
        jLabel40.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 102, 51));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (4).png"))); // NOI18N
        jLabel40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel40.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel48.setBackground(new java.awt.Color(255, 201, 147));
        jLabel48.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/bsktwite.png"))); // NOI18N
        jLabel48.setText("Basket");
        jLabel48.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel49.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/aboutwhite.png"))); // NOI18N
        jLabel49.setText("About");
        jLabel49.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel50.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/ordrwite.png"))); // NOI18N
        jLabel50.setText("My Orders");
        jLabel50.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel114.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(255, 255, 255));
        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel114.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/restwite.png"))); // NOI18N
        jLabel114.setText("All Restraunts");
        jLabel114.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40)
                .addGap(49, 49, 49)
                .addComponent(jLabel48)
                .addGap(34, 34, 34)
                .addComponent(jLabel50)
                .addGap(33, 33, 33)
                .addComponent(jLabel114)
                .addGap(39, 39, 39)
                .addComponent(jLabel49)
                .addContainerGap(253, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2750, 2750, 2750))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel48)
                            .addComponent(jLabel50)
                            .addComponent(jLabel114)
                            .addComponent(jLabel49))
                        .addGap(2760, 2760, 2760))))
        );

        javax.swing.GroupLayout allRestuarntsLayout = new javax.swing.GroupLayout(allRestuarnts);
        allRestuarnts.setLayout(allRestuarntsLayout);
        allRestuarntsLayout.setHorizontalGroup(
            allRestuarntsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resturantPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        allRestuarntsLayout.setVerticalGroup(
            allRestuarntsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allRestuarntsLayout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resturantPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainPanel.add(allRestuarnts, "card11");

        resturantOwnerPanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel19.setBackground(new java.awt.Color(255, 90, 0));

        jLabel41.setBackground(new java.awt.Color(255, 226, 192));
        jLabel41.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 102, 51));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (4).png"))); // NOI18N
        jLabel41.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel41.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel41MouseClicked(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/aboutwhite.png"))); // NOI18N
        jLabel46.setText("About");
        jLabel46.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ordersForOwner.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        ordersForOwner.setForeground(new java.awt.Color(255, 255, 255));
        ordersForOwner.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ordersForOwner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/ordrwite.png"))); // NOI18N
        ordersForOwner.setText("My Orders");
        ordersForOwner.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ordersForOwner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ordersForOwnerMouseClicked(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/restwite.png"))); // NOI18N
        jLabel83.setText("My Restraunt");
        jLabel83.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel47.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/userwite.png"))); // NOI18N
        jLabel47.setText("username");
        jLabel47.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/logout.png"))); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41)
                .addGap(57, 57, 57)
                .addComponent(jLabel83)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(ordersForOwner)
                .addGap(42, 42, 42)
                .addComponent(jLabel46)
                .addGap(104, 104, 104)
                .addComponent(jLabel47)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addGap(23, 23, 23))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2750, 2750, 2750))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel47)
                            .addComponent(jLabel18)
                            .addComponent(jLabel46)
                            .addComponent(ordersForOwner)
                            .addComponent(jLabel83))
                        .addGap(2759, 2759, 2759))))
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setPreferredSize(new java.awt.Dimension(700, 1209));

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));

        resturantNameLabel1.setFont(new java.awt.Font("Verdana", 1, 48)); // NOI18N
        resturantNameLabel1.setForeground(new java.awt.Color(230, 81, 0));
        resturantNameLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        resturantNameLabel1.setText("McDonald's");

        resturantIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/addphoto.png"))); // NOI18N
        resturantIcon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resturantIcon1MouseClicked(evt);
            }
        });

        addMealButoon.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        addMealButoon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/admeal.png"))); // NOI18N
        addMealButoon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMealButoonMouseClicked(evt);
            }
        });

        descriptionTextField.setBackground(new java.awt.Color(255, 255, 255));
        descriptionTextField.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        descriptionTextField.setForeground(new java.awt.Color(0, 0, 0));
        descriptionTextField.setText("No description");
        descriptionTextField.setBorder(null);
        descriptionTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descriptionTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(resturantIcon1)
                .addGap(46, 46, 46)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addMealButoon))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(resturantNameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(resturantNameLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(addMealButoon))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(resturantIcon1))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        Hot_deals6.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        Hot_deals6.setText("Meals");

        mealsOfResturantForOwner.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        mealsOfResturantForOwner.setModel(new javax.swing.table.DefaultTableModel(
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
        mealsOfResturantForOwner.setSelectionBackground(new java.awt.Color(255, 255, 255));
        mealsOfResturantForOwner.setShowVerticalLines(false);
        mealsOfResturantForOwner.getTableHeader().setResizingAllowed(false);
        mealsOfResturantForOwner.getTableHeader().setReorderingAllowed(false);
        mealsOfResturantForOwner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mealsOfResturantForOwnerMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(mealsOfResturantForOwner);

        searchMealsTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMealsTextField1ActionPerformed(evt);
            }
        });

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/search.png"))); // NOI18N

        refreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/rfrsh.png"))); // NOI18N
        refreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(395, 395, 395)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchMealsTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel25Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(refreshButton))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(578, 578, 578)
                        .addComponent(Hot_deals6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Hot_deals6)
                .addGap(33, 33, 33)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchMealsTextField1))
                .addGap(39, 39, 39)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout resturantOwnerPanelLayout = new javax.swing.GroupLayout(resturantOwnerPanel);
        resturantOwnerPanel.setLayout(resturantOwnerPanelLayout);
        resturantOwnerPanelLayout.setHorizontalGroup(
            resturantOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, 1197, Short.MAX_VALUE)
        );
        resturantOwnerPanelLayout.setVerticalGroup(
            resturantOwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resturantOwnerPanelLayout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE))
        );

        mainPanel.add(resturantOwnerPanel, "card6");

        MyOrderForOwner.setBackground(new java.awt.Color(255, 255, 255));

        jPanel20.setBackground(new java.awt.Color(255, 90, 0));

        jLabel45.setBackground(new java.awt.Color(255, 226, 192));
        jLabel45.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 102, 51));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/asset (4).png"))); // NOI18N
        jLabel45.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel45.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel45MouseClicked(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/aboutwhite.png"))); // NOI18N
        jLabel51.setText("About");
        jLabel51.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ordersForOwner1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        ordersForOwner1.setForeground(new java.awt.Color(255, 255, 255));
        ordersForOwner1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ordersForOwner1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/ordrwite.png"))); // NOI18N
        ordersForOwner1.setText("My Orders");
        ordersForOwner1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ordersForOwner1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ordersForOwner1MouseClicked(evt);
            }
        });

        jLabel99.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/restwite.png"))); // NOI18N
        jLabel99.setText("My Restraunt");
        jLabel99.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel52.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/userwite.png"))); // NOI18N
        jLabel52.setText("username");
        jLabel52.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        signOutFromOwner.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        signOutFromOwner.setForeground(new java.awt.Color(255, 255, 255));
        signOutFromOwner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/logout.png"))); // NOI18N
        signOutFromOwner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signOutFromOwnerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addGap(57, 57, 57)
                .addComponent(jLabel99)
                .addGap(35, 35, 35)
                .addComponent(ordersForOwner1)
                .addGap(42, 42, 42)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jLabel52)
                .addGap(27, 27, 27)
                .addComponent(signOutFromOwner)
                .addGap(55, 55, 55))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2750, 2750, 2750))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel52)
                            .addComponent(jLabel51)
                            .addComponent(ordersForOwner1)
                            .addComponent(jLabel99)
                            .addComponent(signOutFromOwner))
                        .addGap(2760, 2760, 2760))))
        );

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setPreferredSize(new java.awt.Dimension(700, 1209));

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));

        username1.setFont(new java.awt.Font("Verdana", 1, 48)); // NOI18N
        username1.setForeground(new java.awt.Color(230, 81, 0));
        username1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        username1.setText("Username");

        userPhoto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/user5x.png"))); // NOI18N

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(userPhoto1)
                .addGap(27, 27, 27)
                .addComponent(username1, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(userPhoto1))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(username1)))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        meals_pan9.setBackground(new java.awt.Color(255, 255, 255));
        meals_pan9.setPreferredSize(new java.awt.Dimension(700, 221));

        jLabel98.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(230, 81, 0));
        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel98.setText("My Orders");

        myOrdersTableForOwner.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        myOrdersTableForOwner.setModel(new javax.swing.table.DefaultTableModel(
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
        myOrdersTableForOwner.setSelectionBackground(new java.awt.Color(255, 255, 255));
        myOrdersTableForOwner.setShowVerticalLines(false);
        myOrdersTableForOwner.getTableHeader().setResizingAllowed(false);
        myOrdersTableForOwner.getTableHeader().setReorderingAllowed(false);
        jScrollPane9.setViewportView(myOrdersTableForOwner);

        javax.swing.GroupLayout meals_pan9Layout = new javax.swing.GroupLayout(meals_pan9);
        meals_pan9.setLayout(meals_pan9Layout);
        meals_pan9Layout.setHorizontalGroup(
            meals_pan9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meals_pan9Layout.createSequentialGroup()
                .addGroup(meals_pan9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(meals_pan9Layout.createSequentialGroup()
                        .addGap(435, 435, 435)
                        .addComponent(jLabel98))
                    .addGroup(meals_pan9Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        meals_pan9Layout.setVerticalGroup(
            meals_pan9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, meals_pan9Layout.createSequentialGroup()
                .addComponent(jLabel98)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(meals_pan9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1145, Short.MAX_VALUE)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel36Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(meals_pan9, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MyOrderForOwnerLayout = new javax.swing.GroupLayout(MyOrderForOwner);
        MyOrderForOwner.setLayout(MyOrderForOwnerLayout);
        MyOrderForOwnerLayout.setHorizontalGroup(
            MyOrderForOwnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, 1197, Short.MAX_VALUE)
        );
        MyOrderForOwnerLayout.setVerticalGroup(
            MyOrderForOwnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MyOrderForOwnerLayout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(MyOrderForOwner, "card6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 904, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void endSplashScreenAnimation() {
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        mainPanel.add(loginPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
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
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add sign up panel
        mainPanel.add(signUpForCustomerPanel);
        loginLinkButton.setForeground(Color.WHITE);
        mainPanel.repaint();
        mainPanel.revalidate();
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

    private void all_restaurantsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_all_restaurantsMouseClicked
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(allRestuarnts);
        mainPanel.repaint();
        mainPanel.revalidate();


    }//GEN-LAST:event_all_restaurantsMouseClicked
    Meal_jframe meal = new Meal_jframe();
    private void my_ordersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_my_ordersMouseClicked
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add sign up panel
        mainPanel.add(MyOrder);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_my_ordersMouseClicked

    private void homeLogoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeLogoMousePressed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add sign up panel
        mainPanel.add(homePanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_homeLogoMousePressed

    private void passwordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Talabat.login() == 1) {
                mainPanel.removeAll();
                mainPanel.repaint();
                mainPanel.revalidate();

                mainPanel.add(homePanel);
                mainPanel.repaint();
                mainPanel.revalidate();
            } else if (Talabat.login() == 2) {
                mainPanel.removeAll();
                mainPanel.repaint();
                mainPanel.revalidate();

                mainPanel.add(resturantOwnerPanel);
                mainPanel.repaint();
                mainPanel.revalidate();
                restMealsTableForOwners();
            }
        }
    }//GEN-LAST:event_passwordFieldKeyPressed

    private void basketMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_basketMousePressed
        // TODO add your handling code here:


    }//GEN-LAST:event_basketMousePressed

    private void jLabel84MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel84MousePressed

        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        mainPanel.add(Basket);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_jLabel84MousePressed

    private void loginLinkButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLinkButtonMouseClicked
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add sign up panel
        mainPanel.add(loginPanel);
        signUpLinkButton.setForeground(Color.WHITE);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_loginLinkButtonMouseClicked

    private void loginLinkButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLinkButtonMouseEntered
        // TODO add your handling code here:
        loginLinkButton.setForeground(new Color(255, 198, 44));
    }//GEN-LAST:event_loginLinkButtonMouseEntered

    private void loginLinkButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLinkButtonMouseExited
        // TODO add your handling code here:
        loginLinkButton.setForeground(Color.WHITE);
    }//GEN-LAST:event_loginLinkButtonMouseExited

    private void jLabel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseClicked
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add sign up panel
        mainPanel.add(homePanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_jLabel39MouseClicked

    private void signUpUsernameTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpUsernameTextField1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_signUpUsernameTextField1MouseClicked

    private void signUpUsernameTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpUsernameTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_signUpUsernameTextField1ActionPerformed

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
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add sign up panel
        mainPanel.add(loginPanel);
        signUpLinkButton.setForeground(Color.WHITE);
        mainPanel.repaint();
        mainPanel.revalidate();
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
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add sign up panel
        mainPanel.add(signUpForOwnerPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_signUpAsOwnerLinkMousePressed

    private void signUpAsCustomerLinkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpAsCustomerLinkMousePressed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add sign up panel
        mainPanel.add(signUpForCustomerPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
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
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(allRestuarnts);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_jLabel87MousePressed

    private void my_ordersMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_my_ordersMousePressed
        // TODO add your handling code here:
        myOrdersTable(Talabat.currentUser);


    }//GEN-LAST:event_my_ordersMousePressed

    private void mealsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mealsTableMouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 2) {
            Meal_jframe mealFrame = new Meal_jframe();

            int oldRow = mealsTable.getSelectedRow();
            int newRow = mealSortter.convertRowIndexToModel(oldRow);

            if (mealList.get(newRow).databaseImage != null) {

                ImageIcon image = new ImageIcon(new ImageIcon(mealList.get(newRow).databaseImage).getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));

                mealFrame.mealImage.setIcon(image);

            } else {
                mealFrame.mealImage.setIcon(null);
            }

            mealFrame.mealName.setText(mealList.get(newRow).name);

            mealFrame.mealDescription.setText(mealList.get(newRow).description);

            mealFrame.orderPrice.setText(String.valueOf(mealList.get(newRow).mealPrice) + "EGP");
            mealFrame.mealPriceFloat = mealList.get(newRow).mealPrice;

            mealFrame.mealIndex = newRow;
            mealFrame.ownerIndex = ownerIndex;

            mealFrame.show();

        }


    }//GEN-LAST:event_mealsTableMouseClicked

    private void searchMealsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMealsTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchMealsTextFieldActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        LocalDate orderDateTime = LocalDate.now();
        Talabat.customers[Talabat.currentUserIndex].cart.orderDate = orderDateTime;

        Talabat.customers[Talabat.currentUserIndex].cart.displayMeals();
        System.out.println(Talabat.owners[ownerIndex].restaurant.numberOfOrders);
        Talabat.owners[ownerIndex].restaurant.orders[Talabat.owners[ownerIndex].restaurant.numberOfOrders].addCart(Talabat.customers[Talabat.currentUserIndex].cart);
        Talabat.owners[ownerIndex].restaurant.numberOfOrders++;
        Talabat.customers[Talabat.currentUserIndex].orderCart();
        cartTable();


    }//GEN-LAST:event_jLabel11MouseClicked

    private void homeLogo1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeLogo1MousePressed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add sign up panel
        mainPanel.add(homePanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_homeLogo1MousePressed

    private void basketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_basketMouseClicked
        // TODO add your handling code here:
        cartTable();
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add sign up panel
        mainPanel.add(Basket);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_basketMouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        int index = userCart.getSelectedRow();

        Talabat.customers[Talabat.currentUserIndex].cart.removeMeal(index);
        Talabat.customers[Talabat.currentUserIndex].cart.displayMeals();
        cartTable();

    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel21MouseEntered

    EditMeal edit = new EditMeal();
    private void mealsOfResturantForOwnerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mealsOfResturantForOwnerMouseClicked
        // TODO add your handling code here:

        int ii = mealsOfResturantForOwner.getSelectedRow();
        int i = restMealsTableSorter.convertRowIndexToModel(ii);
        edit.mealNameTextField.setText(mealList.get(i).name);
        edit.priceTextField.setText(String.valueOf(mealList.get(i).mealPrice));
        edit.descriptionTextField.setText(mealList.get(i).description);
        edit.mealDescriptionLabel.setText(mealList.get(i).description);
        edit.mealNameLabel.setText(mealList.get(i).name);
        edit.priceLabel.setText(String.valueOf(mealList.get(i).mealPrice));
        edit.mealIndex = i;
        Database db = new Database();
        int id = db.getMealId(mealList.get(i).name);
        edit.mealId = id;
        if (mealList.get(i).databaseImage != null) {
            ImageIcon image = new ImageIcon(new ImageIcon(mealList.get(i).databaseImage).getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));

            edit.mealImage.setIcon(image);

        }

        if (!add.isShowing()) {
            edit.show();
        }


    }//GEN-LAST:event_mealsOfResturantForOwnerMouseClicked

    private void searchMealsTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMealsTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchMealsTextField1ActionPerformed

    private void refreshButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshButtonMouseClicked
        // TODO add your handling code here:
        restMealsTableForOwners();
    }//GEN-LAST:event_refreshButtonMouseClicked
    AddMeal add = new AddMeal();
    private void addMealButoonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMealButoonMouseClicked
        // TODO add your handling code here:

        if (!edit.isShowing()) {
            add.show();
        }
    }//GEN-LAST:event_addMealButoonMouseClicked

    private void signOutLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signOutLabelMouseClicked
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add sign up panel
        mainPanel.add(loginPanel);
        mainPanel.repaint();
        mainPanel.revalidate();


    }//GEN-LAST:event_signOutLabelMouseClicked

    private void signOutFromOwnerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signOutFromOwnerMouseClicked
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add sign up panel
        mainPanel.add(loginPanel);
        mainPanel.repaint();
        mainPanel.revalidate();

    }//GEN-LAST:event_signOutFromOwnerMouseClicked

    private void jLabel22MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MousePressed

        if (Talabat.login() == 1) {
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();

            // add sign up panel
            mainPanel.add(homePanel);
            mainPanel.repaint();
            mainPanel.revalidate();
            populateAllRestaurantsTable();
        } else if (Talabat.login() == 2) {
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();

            // System.out.println(owners[i].restaurantName);
            resturantNameLabel1.setText(owners[Talabat.currentOwnerIndex].restaurant.name);

            if (owners[Talabat.currentOwnerIndex].restaurant.description != null) {

                resturantDescriptionLabel.setText(owners[Talabat.currentOwnerIndex].restaurant.description);
            }
            if (owners[Talabat.currentOwnerIndex].restaurant.Image != null) {
                ImageIcon image = new ImageIcon(new ImageIcon(owners[Talabat.currentOwnerIndex].restaurant.Image).getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));
                resturantIcon1.setIcon(image);
            }

            restaurantMealsTable(Talabat.owners[Talabat.currentOwnerIndex].restaurantName);

            // add sign up panel
            mainPanel.add(resturantOwnerPanel);
            mainPanel.repaint();
            mainPanel.revalidate();
            restMealsTableForOwners();

        }
    }//GEN-LAST:event_jLabel22MousePressed

    private void jLabel23MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MousePressed
        // TODO add your handling code here:
        if (Talabat.signUpForCustomer()) {
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();

            // add sign up panel
            mainPanel.add(loginPanel);
            mainPanel.repaint();
            mainPanel.revalidate();
        }
    }//GEN-LAST:event_jLabel23MousePressed

    private void jLabel24MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MousePressed
        // TODO add your handling code here:
        if (Talabat.signUpForOwner()) {
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();

            // add sign up panel
            mainPanel.add(loginPanel);
            mainPanel.repaint();
            mainPanel.revalidate();
        }
    }//GEN-LAST:event_jLabel24MousePressed

    private void ordersForOwnerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersForOwnerMouseClicked

        myOrdersTableForOwner1();
        mainPanel.removeAll();

        mainPanel.repaint();
        mainPanel.revalidate();

        // add sign up panel
        mainPanel.add(MyOrderForOwner);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_ordersForOwnerMouseClicked

    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel41MouseClicked

    private void jLabel45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel45MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel45MouseClicked

    private void ordersForOwner1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersForOwner1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ordersForOwner1MouseClicked

    private void allRestaurantsjListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allRestaurantsjListMouseClicked

        if (evt.getClickCount() == 2) {
            String selectedRestaurantName = allRestaurantsjList.getSelectedValue();

            Owner[] ownersArray = new Owner[Owner.numberOfOwners];
            ownersArray = Talabat.owners;

            for (int i = 0; i < Owner.numberOfOwners; i++) {
                if (ownersArray[i].restaurantName.equals(selectedRestaurantName)) {
                    //owner index used to get restaurant information
                    ownerIndex = i;
                    break;
                }
            }
            String restaurantDescripion = ownersArray[ownerIndex].restaurant.description;

            resturantNameLabel.setText(selectedRestaurantName);
            resturantDescriptionLabel.setText(restaurantDescripion);

            //get image of resturant directly from the map
            if (allRestaurantsImageMap.get(selectedRestaurantName).Image != null) {
                //get image from map
                ImageIcon img = new ImageIcon(new ImageIcon(allRestaurantsImageMap.get(selectedRestaurantName).Image).getImage());

                //convert image to icon of size 160*160
                ImageIcon image = new ImageIcon(img.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));

                resturantIcon.setIcon(image);
            } else {
                resturantIcon.setIcon(null);
            }

            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();

            mainPanel.add(resturantPanel);
            mainPanel.repaint();
            mainPanel.revalidate();

            System.out.println("selected restaurant is : " + selectedRestaurantName);
            restaurantMealsTable(selectedRestaurantName);
        }
    }//GEN-LAST:event_allRestaurantsjListMouseClicked

    private void resturantIcon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resturantIcon1MouseClicked
        try {
            // TODO add your handling code here:
            openFileChooserForRestImageEditing();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_resturantIcon1MouseClicked

    private void descriptionTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descriptionTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descriptionTextFieldActionPerformed

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
    public javax.swing.JPanel MyOrderForOwner;
    public javax.swing.JTextField SearchTextField;
    public javax.swing.JLabel Talabat_logo;
    public javax.swing.JPanel Up_panel;
    public javax.swing.JLabel about;
    public javax.swing.JLabel addMealButoon;
    public javax.swing.JLabel addressLabelForSignUp;
    public javax.swing.JTextField addressTextFieldForSignUp;
    public javax.swing.JList<String> allRestaurantsjList;
    public javax.swing.JPanel allRestuarnts;
    public javax.swing.JLabel all_restaurants;
    public javax.swing.JLabel basket;
    public javax.swing.JPasswordField confirmPasswordFieldForSignUp;
    public javax.swing.JPasswordField confirmPasswordFieldForSignUp1;
    public javax.swing.JLabel confirmPasswordLabelForSignUp;
    public javax.swing.JLabel confirmPasswordLabelForSignUp1;
    public javax.swing.JTextField descriptionTextField;
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
    public javax.swing.JLabel jLabel107;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel111;
    public javax.swing.JLabel jLabel112;
    public javax.swing.JLabel jLabel114;
    public javax.swing.JLabel jLabel116;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel21;
    public javax.swing.JLabel jLabel22;
    public javax.swing.JLabel jLabel23;
    public javax.swing.JLabel jLabel24;
    public javax.swing.JLabel jLabel25;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel37;
    public javax.swing.JLabel jLabel39;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel40;
    public javax.swing.JLabel jLabel41;
    public javax.swing.JLabel jLabel42;
    public javax.swing.JLabel jLabel43;
    public javax.swing.JLabel jLabel44;
    public javax.swing.JLabel jLabel45;
    public javax.swing.JLabel jLabel46;
    public javax.swing.JLabel jLabel47;
    public javax.swing.JLabel jLabel48;
    public javax.swing.JLabel jLabel49;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel50;
    public javax.swing.JLabel jLabel51;
    public javax.swing.JLabel jLabel52;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
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
    public javax.swing.JLabel jLabel98;
    public javax.swing.JLabel jLabel99;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel18;
    public javax.swing.JPanel jPanel19;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel20;
    public javax.swing.JPanel jPanel21;
    public javax.swing.JPanel jPanel23;
    public javax.swing.JPanel jPanel24;
    public javax.swing.JPanel jPanel25;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel30;
    public javax.swing.JPanel jPanel31;
    public javax.swing.JPanel jPanel32;
    public javax.swing.JPanel jPanel33;
    public javax.swing.JPanel jPanel34;
    public javax.swing.JPanel jPanel36;
    public javax.swing.JPanel jPanel37;
    public javax.swing.JPanel jPanel41;
    public javax.swing.JPanel jPanel5;
    public javax.swing.JPanel jPanel6;
    public javax.swing.JPanel jPanel7;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JScrollPane jScrollPane5;
    public javax.swing.JScrollPane jScrollPane6;
    public javax.swing.JScrollPane jScrollPane7;
    public javax.swing.JScrollPane jScrollPane9;
    public javax.swing.JSeparator jSeparator1;
    public javax.swing.JSeparator jSeparator2;
    public javax.swing.JSeparator jSeparator3;
    public javax.swing.JSeparator jSeparator4;
    public javax.swing.JSeparator jSeparator5;
    public javax.swing.JSeparator jSeparator6;
    public javax.swing.JLabel label1;
    public javax.swing.JLabel label2;
    public javax.swing.JLabel loginLinkButton;
    public javax.swing.JLabel loginLinkButton1;
    public javax.swing.JPanel loginPanel;
    public javax.swing.JPanel mainPanel;
    public javax.swing.JLabel meal1pic;
    public javax.swing.JLabel meal1pic1;
    public javax.swing.JLabel meal1pic2;
    public javax.swing.JTable mealsOfResturantForOwner;
    public javax.swing.JTable mealsTable;
    public javax.swing.JPanel meals_pan;
    public javax.swing.JPanel meals_pan8;
    public javax.swing.JPanel meals_pan9;
    public javax.swing.JLabel mobileLabelForSignUp;
    public javax.swing.JTextField mobileTextFieldForSignUp;
    public javax.swing.JTable myOrdersTable;
    public javax.swing.JTable myOrdersTableForOwner;
    public javax.swing.JLabel my_orders;
    public javax.swing.JLabel ordersForOwner;
    public javax.swing.JLabel ordersForOwner1;
    public javax.swing.JPasswordField passwordField;
    public javax.swing.JPasswordField passwordFieldForSignUp;
    public javax.swing.JPasswordField passwordFieldForSignUp1;
    public javax.swing.JLabel passwordLabel;
    public javax.swing.JLabel passwordLabel1;
    public javax.swing.JLabel passwordLabel2;
    public javax.swing.JLabel refreshButton;
    public javax.swing.JPanel restau_pan;
    public javax.swing.JLabel restaurantNameLabelForSignUp1;
    public javax.swing.JTextField restaurantNameTextFieldForSignUp1;
    public javax.swing.JLabel resturantDescriptionLabel;
    public javax.swing.JLabel resturantIcon;
    public javax.swing.JLabel resturantIcon1;
    public javax.swing.JLabel resturantNameLabel;
    public javax.swing.JLabel resturantNameLabel1;
    public javax.swing.JPanel resturantOwnerPanel;
    public javax.swing.JPanel resturantPanel;
    public javax.swing.JPanel resturantPanel1;
    public javax.swing.JLabel retaurants;
    public javax.swing.JLabel retaurants2;
    public javax.swing.JLabel retaurants3;
    public javax.swing.JLabel retaurants4;
    public javax.swing.JTextField searchMealsTextField;
    public javax.swing.JTextField searchMealsTextField1;
    public javax.swing.JCheckBox showPasswordCheckBox;
    public javax.swing.JCheckBox showPasswordCheckBoxForSignUp;
    public javax.swing.JCheckBox showPasswordCheckBoxForSignUp1;
    public javax.swing.JLabel side_plate1;
    public javax.swing.JLabel side_plate2;
    public javax.swing.JLabel signOutFromOwner;
    public javax.swing.JLabel signOutLabel;
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
    public javax.swing.JLabel totalPriceLabel;
    public javax.swing.JTable userCart;
    public javax.swing.JLabel userPhoto;
    public javax.swing.JLabel userPhoto1;
    public javax.swing.JLabel username;
    public javax.swing.JLabel username1;
    public javax.swing.JLabel usernameLabel;
    public javax.swing.JLabel usernameLabelForSignUp;
    public javax.swing.JLabel usernameLabelForSignUp1;
    public javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
