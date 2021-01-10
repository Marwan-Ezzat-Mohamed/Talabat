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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Marwan Ezzat
 */
public class Database {

    public Connection databaseConnection;

    public Database() {
        try {
            this.databaseConnection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertIntoBasketTable(String mealId) {

        // meal id //userId
        String username = Talabat.currentUser;

        Statement myStmt = null;
        ResultSet myRs, myRs1 = null;
        try {

            // Create a statement
            myStmt = databaseConnection.createStatement();

            // Execute SQL query
            myRs1 = myStmt.executeQuery("select cart_id from customers where username ='" + username + "';");
            String basketId = null;
            while (myRs1.next()) {
                basketId = myRs1.getString("cart_id");
            }

            myStmt.executeUpdate("insert into contains values(" + basketId + "," + mealId + ");");

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Restaurant> returnAllRestaurants() {

        ArrayList<Restaurant> list = new ArrayList<Restaurant>();

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
        String user = s;
        ArrayList<Order> orderList = new ArrayList<Order>();

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

    public void updateRestaurantImage(InputStream s) {

        Statement myStmt = null;
        try {

            PreparedStatement ps = databaseConnection.prepareStatement("UPDATE restaurants SET image = ? WHERE name = 'mac';");

            ps.setBlob(1, s);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Inserted");

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addMealToRestaurant(Meal m, String restaurantName, InputStream s) {

        try {

            //myStmt.executeUpdate("UPDATE restaurants SET image = " + s + " WHERE name = 'mac';");
            PreparedStatement ps = databaseConnection.prepareStatement("insert into meals values(?,?,?,?,?,?);");

            ps.setString(1, null);
            ps.setString(2, m.name);
            ps.setString(3, m.description);
            ps.setBlob(4, s);
            ps.setString(5, restaurantName);
            ps.setFloat(6, m.mealPrice);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "meal added");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error please try again");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Meal> getRestaurantMeals(String restaurantName) {

        ArrayList<Meal> mealList = new ArrayList<Meal>();

        Statement myStmt = null;
        ResultSet myRs = null;
        try {

            myStmt = databaseConnection.createStatement();

            myRs = myStmt.executeQuery("select * from meals where restaurantName ='" + restaurantName + "';");

            while (myRs.next()) {

                String name = myRs.getString("name");
                String description = myRs.getString("description");
                float price = myRs.getFloat("price");
                byte[] image = myRs.getBytes("image");

                Meal m = new Meal(name, description, price, image);
                mealList.add(m);

                System.out.println(name + "\n" + description);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mealList;
    }

    public int getMealId(String mealName, String restaurantName) {

        int id = -1;

        Statement myStmt;
        ResultSet myRs;
        try {

            myStmt = databaseConnection.createStatement();

            myRs = myStmt.executeQuery("select id from meals where name ='" + mealName + "'and restaurantName='" + restaurantName + "';");

            while (myRs.next()) {
                id = myRs.getInt("id");

            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public void updateMealWithImage(Meal m, InputStream s, int id) {

        try {

            PreparedStatement ps = databaseConnection.prepareStatement("update meals set name = ?,description=?,image=?,price=? where id = ? ;");

            ps.setString(1, m.name);
            ps.setString(2, m.description);
            ps.setBlob(3, s);
            ps.setFloat(4, m.mealPrice);
            ps.setInt(5, id);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Meal updated");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error please try again");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateMealWithoutImage(Meal m, int id) {

        try {

            PreparedStatement ps = databaseConnection.prepareStatement("update meals set name = ?,description=?,price=? where id = ? ;");

            ps.setString(1, m.name);
            ps.setString(2, m.description);
            ps.setFloat(3, m.mealPrice);
            ps.setInt(4, id);
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Meal updated");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error please try again");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertMealIntoCart(int mealId, int quantity, String username) {

        try {

            PreparedStatement ps = databaseConnection.prepareStatement("insert ignore into cart (cartOwner,mealId) values(?,?);");
            ps.setString(1, username);
            ps.setInt(2, mealId);

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

    public Cart returnCart(String username) {

        Cart cart = new Cart();

        Statement myStmt = null;
        ResultSet myRs = null;
        try {

            myStmt = databaseConnection.createStatement();

            myRs = myStmt.executeQuery("select * from cart where cartOwner = '" + username + "' and orderNumber=0; ");

            while (myRs.next()) {

                cart.addMeal((returnMealFromId(myRs.getInt("mealId"))), myRs.getInt("quantity"));

            }
        } catch (Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cart;
    }

    public Meal returnMealFromId(int id) {

        Statement myStmt = null;
        ResultSet myRs = null;
        Meal m = null;
        try {

            myStmt = databaseConnection.createStatement();

            myRs = myStmt.executeQuery("select * from meals where id =" + id + ";");

            if (myRs.next()) {
                m = new Meal(myRs.getString("name"), myRs.getString("description"), myRs.getFloat("price"), myRs.getBytes("image"), id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    public void removeMeal(int mealid, String username) {

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

    public Order[] returnOrderOfcustomers(String username) {

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

            System.out.println("max order number = " + maxOrderNumber);
            while (myRs.next()) {

                int mealId = myRs.getInt("mealID");
                int mealOrderNumber = myRs.getInt("orderNumber");
                int quantity = myRs.getInt("quantity");
                Date d = myRs.getTimestamp("orderDate");
                orders[mealOrderNumber].Date = d;
                orders[mealOrderNumber].addMeal(returnMealFromId(mealId), quantity, mealOrderNumber);

            }
        } catch (Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
    
    
    
    public Order returnOrderOfOwner(String restaurantName) {

        Statement myStmt = null;
        ResultSet myRs = null;
        Order order = null;
        try {
            

            myStmt = databaseConnection.createStatement();

            //myRs = myStmt.executeQuery("select max(orderNumber) from cart where cartOwner='" + username + "';");
            int maxOrderNumber = 0;
            
            
            ///select * from meals , cart where restaurantName='mac' and mealId=id order by orderDate


            myRs = myStmt.executeQuery("select * from meals , cart where restaurantName='" + restaurantName + "' and orderNumber<>0 and mealId=id order by orderNumber; ");

            
            order = new Order();
            
            while (myRs.next()) {
                
                int mealId = myRs.getInt("mealID");
                int quantity = myRs.getInt("quantity");
                int numberInOrder = myRs.getInt("orderNumber");
                Date d = myRs.getTimestamp("orderDate");
                Meal m =  returnMealFromId(mealId);
                order .addMeal(m, quantity, d,numberInOrder);
            }
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return order;
    }
}
