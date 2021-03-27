/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talabat;

import OwnerPackage.Owner;
import OwnerPackage.Restaurant;
import CstomerPackage.Cart;
import CstomerPackage.Customer;
import java.io.IOException;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import static talabat.Talabat.mainFrame;

public class Database {

    public Connection databaseConnection;
    private final String onlineDatabase = "jdbc:mysql://remotemysql.com:3306/RjFI4gANpY?autoReconnect=true";
    private final String onlineDatabaseUsername = "RjFI4gANpY";
    private final String onlineDatabasePassword = "UIY691h8aY";

    // 34an lw mafe4 net youm el mona24a 
    private final String localDatabase = "jdbc:mysql://localhost:3306/rjfi4ganpy";
    private final String localDatabaseUsername = "root";
    private final String localDatabasePassword = "01003494703Mn";

    public Database() {
        
        try {
            this.databaseConnection = DriverManager.getConnection(onlineDatabase, onlineDatabaseUsername, onlineDatabasePassword);
            System.out.println("connected");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public void restoreConnection() {

        // lw el user 48al el app mn 8er net y3ml connection m3 el database
        if (databaseConnection == null) {
            try {
                this.databaseConnection = DriverManager.getConnection(onlineDatabase, "RjFI4gANpY", "UIY691h8aY");
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // b3ml ay select statment 34an yrg3 el connection
        try {
            Statement myStmt = null;
            myStmt = databaseConnection.createStatement();
            myStmt.executeQuery("select 1;");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //bt4of fe net wala la2
    public boolean checkConnection() {

        restoreConnection();

        // commment lw local database
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Please check you internet connection and try again");
            return false;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Please check you internet connection and try again");
            return false;
        }

//        return true;   //uncomment lw local database
    }

    private boolean checkIfUserExists(String username) {

        try {
            PreparedStatement myStmt = databaseConnection.prepareStatement("select * from customers");
            ResultSet myRs = myStmt.executeQuery();
            while (myRs.next()) {
                if (myRs.getString("username").equals(username)) {
                    return true;
                }
            }
            
            myRs = myStmt.executeQuery("select * from owners");
            while (myRs.next()) {
                if (myRs.getString("username").equals(username)) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public int login(String username, String password) {
        if (!checkConnection()) {
            return 0;
        }

        Statement sqlStmt ;
        ResultSet customersInDatabase ;
        ResultSet ownersInDatabase ;
        try {

            sqlStmt = databaseConnection.createStatement();
            customersInDatabase = sqlStmt.executeQuery("select * from customers");

            while (customersInDatabase.next()) {
                if (customersInDatabase.getString("username").equals(username) && customersInDatabase.getString("password").equals(password)) {

                    //reset el label 
                    mainFrame.invalidLoginLabel.setText("");
                    //set el username fl home page lma y3ml login
                    mainFrame.username.setText(username);

                    String address = customersInDatabase.getString("address"), mobile = customersInDatabase.getString("mobile");

                    try {
                        Talabat.customer = new Customer(mobile, address, username, password);
                    } catch (Exception ex) {
                        Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "password must be at least 8 chars");
                    }
                    return 1;
                }
            }

            //check for owner
            ownersInDatabase = sqlStmt.executeQuery("select * from owners , restaurants where owners.restaurantName=restaurants.name;");

            while (ownersInDatabase.next()) {
                if (ownersInDatabase.getString("username").equals(username) && ownersInDatabase.getString("password").equals(password)) {
                    //reset el label 
                    mainFrame.invalidLoginLabel.setText("");

                    String restaurantName = ownersInDatabase.getString("restaurantName");
                    try {
                        Talabat.owner = new Owner(username, password, restaurantName);
                    } catch (Exception ex) {
                        Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Talabat.owner.getRestaurant().setImage(ownersInDatabase.getBytes("image"));
                    Talabat.owner.getRestaurant().setDescription(ownersInDatabase.getString("description"));

                    return 2;
                }
            }
            // lw mla2a4 el user aw owner by3mld de 
            mainFrame.invalidLoginLabel.setText("Invalid username or password");

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            mainFrame.invalidLoginLabel.setText("unexpected error occurred please try again later");
            return 0;
        }
        return 0;

    }

    public boolean signupForCusotmer(String username, String password, String confirmPassword, String mobile, String address) {

        PreparedStatement sqlStmt;
        

        try {
            //34an y4of el password week wala la2
            Customer customer = new Customer(mobile, address, username, password);
        } catch (Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        //bydawr 3l user fl database 34an ytcheck lw el user mwgod abl kda
        boolean foundUser = checkIfUserExists(username);

       
        // htfhmo mn el errors
        if (address.equals("") || username.equals("") || password.equals("") || confirmPassword.equals("") || mobile.equals("")) {
            mainFrame.invalidLoginLabelForSignUp.setText("you must fill all the fields");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mainFrame.invalidLoginLabelForSignUp.setText("passwords don't match");
            return false;
        } else if (foundUser) {
            mainFrame.invalidLoginLabelForSignUp.setText("username already exists login?");
            return false;

        } else {

            //kolo tmam fa n7oto fl database b2a
            mainFrame.invalidLoginLabel.setText("");
            try {

                sqlStmt = databaseConnection.prepareStatement("INSERT INTO customers Values(?,?,?,?);");
                sqlStmt.setString(1, username);
                sqlStmt.setString(2, password);
                sqlStmt.setString(3, mobile);
                sqlStmt.setString(4, address);
                sqlStmt.execute();

                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Talabat.class.getName()).log(Level.SEVERE, null, ex);
                mainFrame.invalidLoginLabel.setText("unexpected error occurred please try again later");
                return false;
            }
        }

    }

    public boolean signUpForOwner(String username, String password, String confirmPassword, String restaurantName) {

        PreparedStatement restaurntsInDatabase;
        ResultSet myRs;

        boolean foundUser = checkIfUserExists(username);
        boolean foundResraurant = false;

        try {
            restaurntsInDatabase = databaseConnection.prepareStatement("select * from restaurants");
            myRs = restaurntsInDatabase.executeQuery();
            while (myRs.next()) {
                if (myRs.getString("name").equals(restaurantName)) {
                    foundResraurant = true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            mainFrame.invalidLoginLabel.setText("unexpected error occurred please try again later");
            return false;
        }
        
        if (restaurantName.equals("") || username.equals("") || password.equals("") || confirmPassword.equals("")) {
            mainFrame.invalidLoginLabelForSignUp1.setText("you must fill all the fields.");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mainFrame.invalidLoginLabelForSignUp1.setText("passwords don't match.");
            return false;
        } else if (foundUser) {
            mainFrame.invalidLoginLabelForSignUp1.setText("username already exists.");
            return false;

        } else if (foundResraurant) {
            mainFrame.invalidLoginLabelForSignUp1.setText("restaurant name already exists.");
            return false;
        } else {

            mainFrame.invalidLoginLabelForSignUp1.setText("");
            try {

                PreparedStatement ps = databaseConnection.prepareStatement("insert into restaurants values (?,?,?);");

                ps.setString(1, restaurantName);
                ps.setString(2, null);
                ps.setString(3, null);
                ps.execute();

                ps = databaseConnection.prepareStatement("INSERT INTO owners Values(?,?,?);");
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, restaurantName);
                ps.execute();

            } catch (SQLException ex) {
                Logger.getLogger(Talabat.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }

    }

    public ArrayList<Restaurant> returnAllRestaurants() {

        if (!checkConnection()) {
            return null;
        }

        ArrayList<Restaurant> list = new ArrayList<>();

        Statement sqlStmt ;
        ResultSet restaurntsInDatabase ;
        
        try {

            sqlStmt = databaseConnection.createStatement();
            restaurntsInDatabase = sqlStmt.executeQuery("select * from restaurants");

            Restaurant r;
            while (restaurntsInDatabase.next()) {
                r = new Restaurant(restaurntsInDatabase.getString("name"), restaurntsInDatabase.getBytes("image"), restaurntsInDatabase.getString("description"));
                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    public ArrayList<Order> returnMyOrders(String user) {

        if (!checkConnection()) {
            return null;
        }
        
        ArrayList<Order> orderList = new ArrayList<>();

        Statement sqlStmt ;
        ResultSet ordersInDatabase ;
        try {

            sqlStmt = databaseConnection.createStatement();
            ordersInDatabase = sqlStmt.executeQuery("select * from orders where username='" + user + "';");

            //Process the result set
            Order o;
            
            while (ordersInDatabase.next()) {
                o = new Order();
                orderList.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderList;
    }

    public void updateRestaurantImage(InputStream s, String name) {

        if (!checkConnection()) {
            return;
        }

        try {

            PreparedStatement ps = databaseConnection.prepareStatement("UPDATE restaurants SET image = ? WHERE name = ?;");

            ps.setBlob(1, s);
            ps.setString(2, name);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Inserted");

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addMealToRestaurant(Meal m, String restaurantName, InputStream image) {

        if (!checkConnection()) {
            return;
        }

        try {

            PreparedStatement ps = databaseConnection.prepareStatement("insert into meals values(?,?,?,?,?,?);");

            ps.setString(1, null);
            ps.setString(2, m.getName());
            ps.setString(3, m.getDescription());
            ps.setBlob(4, image);
            ps.setString(5, restaurantName);
            ps.setFloat(6, m.getMealPrice());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "meal added");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error please try again");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Meal> getRestaurantMeals(String restaurantName) {

        if (!checkConnection()) {
            return null;
        }

        ArrayList<Meal> mealList = new ArrayList<>();

        ResultSet mealsOfRestaurantInDatabase ;
        try {

            PreparedStatement ps = databaseConnection.prepareStatement("select * from meals where restaurantName =?;");

            ps.setString(1, restaurantName);

            mealsOfRestaurantInDatabase = ps.executeQuery();

            while (mealsOfRestaurantInDatabase.next()) {

                String name = mealsOfRestaurantInDatabase.getString("name");
                String description = mealsOfRestaurantInDatabase.getString("description");
                int id = mealsOfRestaurantInDatabase.getInt("id");
                float price = mealsOfRestaurantInDatabase.getFloat("price");
                byte[] image = mealsOfRestaurantInDatabase.getBytes("image");

                Meal m = new Meal(name, description, price, image, id);
                m.setRestaurantName(restaurantName);
                mealList.add(m);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mealList;
    }

    public ArrayList<Meal> getAllMealsInAllRestaurants() {

        if (!checkConnection()) {
            return null;
        }

        ArrayList<Meal> mealList = new ArrayList<>();

        Statement myStmt;
        ResultSet myRs;
        try {

            myStmt = databaseConnection.createStatement();
            myRs = myStmt.executeQuery("select * from meals where restaurantName is not null");

            while (myRs.next()) {

                String name = myRs.getString("name");
                String restaurantName = myRs.getString("restaurantName");
                String description = myRs.getString("description");
                float price = myRs.getFloat("price");
                byte[] image = myRs.getBytes("image");
                
                Meal m = new Meal(name, description, price, image);
                m.setRestaurantName(restaurantName);
                mealList.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mealList;
    }

    public int getMealId(String mealName, String restaurantName) {

        
         System.out.println("namne "+ mealName+"  ::"+restaurantName);
        if (!checkConnection()) {
            System.out.println("checkconnnnnnn");
            return -1;
        }

        int id = -1;

        ResultSet myRs;
        try {

            PreparedStatement ps = databaseConnection.prepareStatement("select id from meals where name =? and restaurantName=?;");
            ps.setString(1, mealName);
            ps.setString(2, restaurantName);
            myRs = ps.executeQuery();

            while (myRs.next()) {
                id = myRs.getInt("id");

            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    //update meal with image
    public void updateMeal(Meal m, InputStream s, int id) {

        if (!checkConnection()) {
            return;
        }

        try {

            PreparedStatement ps = databaseConnection.prepareStatement("update meals set name = ?,description=?,image=?,price=? where id = ? ;");

            ps.setString(1, m.getName());
            ps.setString(2, m.getDescription());
            ps.setBlob(3, s);
            ps.setFloat(4, m.getMealPrice());
            ps.setInt(5, id);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Meal updated");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error please try again");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //update meal without image
    public void updateMeal(Meal m, int id) {

        if (!checkConnection()) {
            return;
        }

        try {

            PreparedStatement ps = databaseConnection.prepareStatement("update meals set name = ?,description=?,price=? where id = ? ;");

            ps.setString(1, m.getName());
            ps.setString(2, m.getDescription());
            ps.setFloat(3, m.getMealPrice());
            ps.setInt(4, id);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Meal updated");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error please try again");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertMealIntoCart(int mealId, int quantity, float orderPrice, String notes, String username, String restaurantName) {

        if (!checkConnection()) {
            return;
        }

        try {

            PreparedStatement ps = databaseConnection.prepareStatement("insert ignore into cart (cartOwner,mealId,orderprice,notes,restaurantName) values(?,?,?,?,?);");
            ps.setString(1, username);
            ps.setInt(2, mealId);
            ps.setFloat(3, orderPrice);
            ps.setString(4, notes);
            ps.setString(5, restaurantName);
            ps.executeUpdate();

            PreparedStatement ps1 = databaseConnection.prepareStatement("update cart set quantity=quantity + ? where mealId=? and cartOwner=? and orderNumber=0;");
            ps1.setInt(1, quantity);
            ps1.setInt(2, mealId);
            ps1.setString(3, username);
            ps1.execute();

            JOptionPane.showMessageDialog(null, "Meal added to cart");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error please try again");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void orderCart(String username) {

        if (!checkConnection()) {
            return;
        }

        Statement myStmt ;
        ResultSet myRs;
        try {

            myStmt = databaseConnection.createStatement();

            //get max number of order for a specific customer
            myRs = myStmt.executeQuery("select max(orderNumber) from cart where cartOwner='" + username + "';");
            int maxOrderNumber = 0;
            if (myRs.next()) {
                maxOrderNumber = myRs.getInt(1);
            }
            maxOrderNumber++;

            myRs = myStmt.executeQuery("select mealId from cart where cartOwner='" + username + "' and orderNumber=0;");
            while (myRs.next()) {
                int id = myRs.getInt("mealId");
                Meal meal = returnMealFromId(id);
                PreparedStatement ps = databaseConnection.prepareStatement("update cart set mealImage=? , mealName=?,orderPrice=? where cartOwner=? and orderNumber=0 and mealId=?;");
                ps.setBytes(1, meal.getDatabaseImage());
                ps.setString(2, meal.getName());
                ps.setFloat(3, meal.getPrice());
                ps.setString(4, username);
                ps.setInt(5, id);

                ps.execute();

            }

            PreparedStatement ps1 = databaseConnection.prepareStatement("update cart set orderNumber=? , orderDate=? where cartOwner=? and orderNumber=0;");
            ps1.setInt(1, maxOrderNumber);
            ps1.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps1.setString(3, username);
            ps1.execute();

            JOptionPane.showMessageDialog(null, "checkout success");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error please try again");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Cart returnCartOfCustomer(String username) {

        if (!checkConnection()) {
            return null;
        }

        Cart cart = new Cart();

        Statement myStmt ;
        ResultSet myRs ;
        try {

            myStmt = databaseConnection.createStatement();

            myRs = myStmt.executeQuery("select * from cart where cartOwner = '" + username + "' and orderNumber=0; ");

            while (myRs.next()) {

                cart.addMealIntoMealsArray((returnMealFromId(myRs.getInt("mealId"))), myRs.getInt("quantity"));
               

            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cart;
    }

    public Meal returnMealFromId(int id) {

        if (!checkConnection()) {
            return null;
        }

        Statement myStmt ;
        ResultSet myRs ;
        Meal m = null;
        try {

            myStmt = databaseConnection.createStatement();

            myRs = myStmt.executeQuery("select * from meals where id =" + id + ";");

            if (myRs.next()) {
                m = new Meal(myRs.getString("name"), myRs.getString("description"), myRs.getFloat("price"), myRs.getBytes("image"), id);
                m.setRestaurantName(myRs.getString("restaurantName"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    public void removeMeal(int mealid, String username) {

        if (!checkConnection()) {
            return;
        }

        Statement myStmt ;

        try {

            myStmt = databaseConnection.createStatement();
            //get max number of order for a specific customer
            myStmt.executeUpdate("delete from cart where cartOwner='" + username + "' and mealId=" + mealid + ";");

            JOptionPane.showMessageDialog(null, "Meal deleted");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error please try again");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Order[] returnOrderOfcustomer(String username) {

        if (!checkConnection()) {
            return null;
        }

        Statement myStmt ;
        ResultSet myRs ;
        Order[] orders = null ;
        try {

            myStmt = databaseConnection.createStatement();

            myRs = myStmt.executeQuery("select max(orderNumber) from cart where cartOwner='" + username + "';");
            int maxOrderNumber = 0;
            if (myRs.next()) {
                maxOrderNumber = myRs.getInt(1);
            }
        
            myRs = myStmt.executeQuery("select * from cart where cartOwner = '" + username + "' and orderNumber<>0; ");

            orders = new Order[maxOrderNumber + 1];

            //intialize orders array
            for (int i = 0; i < maxOrderNumber + 1; i++) {
                orders[i] = new Order();
            }

            while (myRs.next()) {
                int mealId = myRs.getInt("mealId");

                byte[] image = myRs.getBytes("mealImage");

                float mealPrice = myRs.getFloat("orderPrice");
                String mealName = myRs.getString("mealName");
                String restaurantName = myRs.getString("restaurantName");

                Meal meal = new Meal(mealName, null, mealPrice, image, mealId);
                meal.setRestaurantName(restaurantName);
                int mealOrderNumber = myRs.getInt("orderNumber");
                int quantity = myRs.getInt("quantity");

                Date d = myRs.getTimestamp("orderDate");
                SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
                DateFor.format(d);
                orders[mealOrderNumber].setDate(d);

                orders[mealOrderNumber].addMeal(meal, quantity, mealOrderNumber);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public Order returnOrderOfOwner(String restaurantName) {

        if (!checkConnection()) {
            return null;
        }

        ResultSet myRs ;
        Order order = null;
        try {

            PreparedStatement ps = databaseConnection.prepareStatement("select * from cart where restaurantName=? and orderNumber<>0  order by orderNumber;");

            ps.setString(1, restaurantName);

            myRs = ps.executeQuery();

            order = new Order();

            while (myRs.next()) {

                //
                int mealId = myRs.getInt("mealId");

                byte[] image = myRs.getBytes("mealImage");
                float mealPrice = myRs.getFloat("orderPrice");
                String mealName = myRs.getString("mealName");

                Meal m = new Meal(mealName, null, mealPrice, image, mealId);

                Date d = myRs.getTimestamp("orderDate");
                SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
                DateFor.format(d);

                int quantity = myRs.getInt("quantity");
                int numberInOrder = myRs.getInt("orderNumber");

                String notes = myRs.getString("notes");

                if (notes.equals("")) {
                    m.setNotesForOrder("No Notes");
                } else {
                    m.setNotesForOrder(notes);
                }
                order.addMeal(m, quantity, numberInOrder, d);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return order;
    }

    public void removeMealFromOwner(int id) {

        if (!checkConnection()) {
            return;
        }

        try {

            PreparedStatement ps = databaseConnection.prepareStatement("update meals set restaurantName=null where id = ? ;");

            ps.setInt(1, id);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Meal deleted");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error please try again");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editRestaurantDescription(String restaurantName, String description) {

        if (!checkConnection()) {
            return;
        }

        try {
            PreparedStatement ps = databaseConnection.prepareStatement("update restaurants set description=? where name = ? ;");

            ps.setString(1, description);
            ps.setString(2, restaurantName);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "description edited");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error please try again");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
