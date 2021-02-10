/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talabat;

import java.io.InputStream;
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
import static talabat.Talabat.owner;

public class Database {
    
    public Connection databaseConnection;
    private final String databaseUrl = "jdbc:mysql://remotemysql.com:3306/RjFI4gANpY?autoReconnect=true&useUnicode=yes";
    
    public Database() {
        try {
            this.databaseConnection = DriverManager.getConnection(databaseUrl, "RjFI4gANpY", "UIY691h8aY");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    //check lw el connection closed y3mlha reopen
    public void checkConnection() {
        
        if (databaseConnection == null) {
            JOptionPane.showMessageDialog(null, "Please check your internet connection and try again");
        }
        
        try {
            if (databaseConnection.isClosed()) {
                DriverManager.getConnection(databaseUrl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int login(String username, String password) {
        checkConnection();
        
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            
            myStmt = databaseConnection.createStatement();
            myRs = myStmt.executeQuery("select * from customers");
            
            while (myRs.next()) {
                if (myRs.getString("username").equals(username) && myRs.getString("password").equals(password)) {
                    mainFrame.invalidLoginLabel.setText("");
                    mainFrame.username.setText(username);
                    String address = myRs.getString("address"), mobile = myRs.getString("mobile");
                    Talabat.customer = new Customer(mobile, address, username, password);
                    return 1;
                }
                System.out.println("hello");
            }

            //check for owner
            myStmt = databaseConnection.createStatement();
            myRs = myStmt.executeQuery("select * from owners , restaurants where owners.restaurantName=restaurants.name;");
            
            while (myRs.next()) {
                if (myRs.getString("username").equals(username) && myRs.getString("password").equals(password)) {
                    System.out.println("Login successful");
                    mainFrame.invalidLoginLabel.setText("");
                    
                    String restaurantName = myRs.getString("restaurantName");
                    Talabat.owner = new Owner(username, password, restaurantName);
                    Talabat.owner.getRestaurant().setImage(myRs.getBytes("image"));
                    Talabat.owner.getRestaurant().setDescription(myRs.getString("description"));
                    
                    return 2;
                    
                }
                
            }
            
            mainFrame.invalidLoginLabel.setText("Invalid username or password");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            mainFrame.invalidLoginLabel.setText("unexpected error occurred please try again later");
            return 0;
        }
        return 0;
        
    }
    
    public boolean signupForCusotmer(String username, String password, String confirmPassword, String mobile, String address) {
        
        Statement myStmt = null;
        ResultSet myRs = null;
        
        boolean foundUser = false;
        
        try {
            
            myStmt = databaseConnection.createStatement();
            myRs = myStmt.executeQuery("select * from customers");
            
            while (myRs.next()) {
                System.out.println(myRs.getString("username"));
                if (myRs.getString("username").equals(username)) {
                    foundUser = true;
                }
            }
            
            myRs = myStmt.executeQuery("select * from owners");
            
            while (myRs.next()) {
                System.out.println(myRs.getString("username"));
                if (myRs.getString("username").equals(username)) {
                    foundUser = true;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            mainFrame.invalidLoginLabel.setText("unexpected error occurred please try again later");
            return false;
        }
        
        if (address.equals("") || username.equals("") || password.equals("") || confirmPassword.equals("") || mobile.equals("")) {
            mainFrame.invalidLoginLabelForSignUp.setText("you must fill all the fields");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mainFrame.invalidLoginLabelForSignUp.setText("passwords don't match");
            return false;
        } else if (foundUser) {
            mainFrame.invalidLoginLabelForSignUp.setText("username already exits login?");
            return false;
            
        } else {
            
            mainFrame.invalidLoginLabel.setText("");
            try {
                
                myStmt = databaseConnection.createStatement();
                String st = "INSERT INTO customers Values( '" + username + "','" + password + "','" + mobile + "','" + address + "');";
                myStmt.executeUpdate(st);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Talabat.class.getName()).log(Level.SEVERE, null, ex);
                mainFrame.invalidLoginLabel.setText("unexpected error occurred please try again later");
                return false;
            }
        }
        
    }
    
    public boolean signUpForOwner(String username, String password, String confirmPassword, String restaurantName) {
        
        Statement myStmt = null;
        ResultSet myRs = null;
        
        boolean foundUser = false;
        boolean foundResraurant = false;
        
        try {
            
            myStmt = databaseConnection.createStatement();
            myRs = myStmt.executeQuery("select * from customers");
            while (myRs.next()) {
                if (myRs.getString("username").equals(username)) {
                    foundUser = true;
                }
            }
            
            myRs = myStmt.executeQuery("select * from owners");
            while (myRs.next()) {
                if (myRs.getString("username").equals(username)) {
                    foundUser = true;
                }
            }
            
            myRs = myStmt.executeQuery("select * from restaurants");
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
            mainFrame.invalidLoginLabelForSignUp.setText("you must fill all the fields.");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mainFrame.invalidLoginLabelForSignUp.setText("passwords don't match.");
            return false;
        } else if (foundUser) {
            mainFrame.invalidLoginLabelForSignUp.setText("username already exits.");
            return false;
            
        } else if (foundResraurant) {
            mainFrame.invalidLoginLabelForSignUp.setText("restaurant name already.");
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
        
        checkConnection();
        
        ArrayList<Restaurant> list = new ArrayList<>();
        
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            
            myStmt = databaseConnection.createStatement();
            myRs = myStmt.executeQuery("select * from restaurants");

            //Process the result set
            Restaurant r;
            while (myRs.next()) {
                r = new Restaurant(myRs.getString("name"), myRs.getBytes("image"), myRs.getString("description"));
                
                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ArrayList<Order> returnMyOrders(String s) {
        
        checkConnection();
        String user = s;
        ArrayList<Order> orderList = new ArrayList<>();
        
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            
            myStmt = databaseConnection.createStatement();

            //Execute SQL query
            myRs = myStmt.executeQuery("select * from orders where username='" + user + "';");

            //Process the result set
            Order o;
            Cart c = new Cart();
            c.addMeal(new Meal("kofta", 20.0f), 2);
            
            while (myRs.next()) {
                o = new Order();
                
                orderList.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderList;
    }
    
    public void updateRestaurantImage(InputStream s, String name) {
        
        checkConnection();
        
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
    
    public void addMealToRestaurant(Meal m, String restaurantName, InputStream s) {
        
        checkConnection();
        
        try {
            
            PreparedStatement ps = databaseConnection.prepareStatement("insert into meals values(?,?,?,?,?,?);");
            
            ps.setString(1, null);
            ps.setString(2, m.getName());
            ps.setString(3, m.getDescription());
            ps.setBlob(4, s);
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
        
        checkConnection();
        
        ArrayList<Meal> mealList = new ArrayList<>();
        
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            
            myStmt = databaseConnection.createStatement();
            
            PreparedStatement ps = databaseConnection.prepareStatement("select * from meals where restaurantName =?;");
            
            ps.setString(1, restaurantName);
            
            myRs = ps.executeQuery();
            
            while (myRs.next()) {
                
                String name = myRs.getString("name");
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
    
    public ArrayList<Meal> getAllMealsInAllRestaurants() {
        
        checkConnection();
        
        ArrayList<Meal> mealList = new ArrayList<>();
        
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            
            myStmt = databaseConnection.createStatement();
            
            myRs = myStmt.executeQuery("select * from meals where restaurantName<> 'null';");
            
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
        
        checkConnection();
        
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
        
        checkConnection();
        
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
        
        checkConnection();
        
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
    
    public void insertMealIntoCart(int mealId, int quantity, float orderPrice, String notes, String username) {
        
        checkConnection();
        
        try {
            
            PreparedStatement ps = databaseConnection.prepareStatement("insert ignore into cart (cartOwner,mealId,orderprice,notes) values(?,?,?,?);");
            ps.setString(1, username);
            ps.setInt(2, mealId);
            ps.setFloat(3, orderPrice);
            ps.setString(4, notes);
            
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
        
        checkConnection();
        
        Statement myStmt = null;
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
        
        checkConnection();
        
        Cart cart = new Cart();
        
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            
            myStmt = databaseConnection.createStatement();
            
            myRs = myStmt.executeQuery("select * from cart where cartOwner = '" + username + "' and orderNumber=0; ");
            
            while (myRs.next()) {
                
                cart.addMeal((returnMealFromId(myRs.getInt("mealId"))), myRs.getInt("quantity"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cart;
    }
    
    public Meal returnMealFromId(int id) {
        
        checkConnection();
        
        Statement myStmt = null;
        ResultSet myRs = null;
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
        
        checkConnection();
        
        Statement myStmt = null;
        
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
        
        checkConnection();
        
        Statement myStmt = null;
        ResultSet myRs = null;
        Order[] orders = null;
        try {
            
            myStmt = databaseConnection.createStatement();
            
            myRs = myStmt.executeQuery("select max(orderNumber) from cart where cartOwner='" + username + "';");
            int maxOrderNumber = 0;
            if (myRs.next()) {
                maxOrderNumber = myRs.getInt(1);
            }
            myRs = null;
            myRs = myStmt.executeQuery("select * from cart where cartOwner = '" + username + "' and orderNumber<>0; ");
            
            orders = new Order[maxOrderNumber + 1];

            //intialize orders array
            for (int i = 0; i < maxOrderNumber + 1; i++) {
                orders[i] = new Order();
            }
            
            while (myRs.next()) {
                
                int mealId = myRs.getInt("mealID");
                int mealOrderNumber = myRs.getInt("orderNumber");
                int quantity = myRs.getInt("quantity");
                Date d = myRs.getTimestamp("orderDate");
                SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
                DateFor.format(d);
                orders[mealOrderNumber].setDate(d);
                Meal meal = returnMealFromId(mealId);
                orders[mealOrderNumber].addMeal(meal, quantity, mealOrderNumber);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
    
    public Order returnOrderOfOwner(String restaurantName) {
        
        checkConnection();
        
        ResultSet myRs = null;
        Order order = null;
        try {
            
            PreparedStatement ps = databaseConnection.prepareStatement("select * from meals , cart where restaurantName=? and orderNumber<>0 and mealId=id order by orderNumber;");
            
            ps.setString(1, restaurantName);
            
            myRs = ps.executeQuery();
            
            order = new Order();
            
            while (myRs.next()) {
                
                int mealId = myRs.getInt("mealID");
                int quantity = myRs.getInt("quantity");
                int numberInOrder = myRs.getInt("orderNumber");
                float orderPrice = myRs.getFloat("orderPrice");
                Date d = myRs.getTimestamp("orderDate");
                String notes = myRs.getString("notes");
                Meal m = returnMealFromId(mealId);
                m.setMealPrice(orderPrice);
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
        
        checkConnection();
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
        
        checkConnection();
        
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
