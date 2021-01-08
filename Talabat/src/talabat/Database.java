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

    public void insertIntoBasketTable(String mealId) {

        // meal id //userId
        String username = Talabat.currentUser;

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs, myRs1 = null;
        try {
            // get connection 
            myConn = DriverManager.getConnection("jdbc:mysql://sql2.freesqldatabase.com:3306/sql2383521", "sql2383521", "bL5%tX9!");
            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            myRs1 = myStmt.executeQuery("select cart_id from customers where username ='" + username + "';");
            String basketId = null;
            while (myRs1.next()) {
                basketId = myRs1.getString("cart_id");
            }

            myStmt.executeUpdate("insert into contains values(" + basketId + "," + mealId + ");");
            System.out.println("insert done");

        } catch (Exception ex) {
            System.out.println("error 404");
        }

    }

    public ArrayList<Restaurant> returnAllRestaurants() {

        ArrayList<Restaurant> list = new ArrayList<Restaurant>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            // get connection 
            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");
            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            myRs = myStmt.executeQuery("select * from restaurants");

            // 4. Process the result set
            Restaurant r;
            while (myRs.next()) {
                r = new Restaurant(myRs.getString("name"), myRs.getBytes("image"), myRs.getString("description"));

                list.add(r);
            }
        } catch (Exception ex) {
            System.out.println("error 404");
        }
        return list;
    }

    public ArrayList<Order> returnMyOrders(String s) {
        String user = s;
        ArrayList<Order> orderList = new ArrayList<Order>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            // get connection 
            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");
            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            System.out.println(user);
            myRs = myStmt.executeQuery("select * from orders where username='" + user + "';");

            //meals rest_name orderDate total_price
            //meals agebo mn basket_id bta3 el user //kol meal leha id ageb mno el rest_name
            //
            // 4. Process the result set
            Order o;
            Cart c = new Cart();
            c.addMeal(new Meal("kofta", 20.0f), 2);

            while (myRs.next()) {
                o = new Order();

                orderList.add(o);
            }
        } catch (Exception ex) {
            System.out.println("error 404");
        }
        return orderList;
    }

    public void updateRestaurantImage(InputStream s) {
        Connection myConn = null;
        Statement myStmt = null;
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");
            myStmt = myConn.createStatement();

            //myStmt.executeUpdate("UPDATE restaurants SET image = " + s + " WHERE name = 'mac';");
            PreparedStatement ps = myConn.prepareStatement("UPDATE restaurants SET image = ? WHERE name = 'mac';");

            ps.setBlob(1, s);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Inserted");

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addMealToRestaurant(Meal m, String restaurantName, InputStream s) {
        Connection myConn = null;
        Statement myStmt = null;
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");
            myStmt = myConn.createStatement();

            //myStmt.executeUpdate("UPDATE restaurants SET image = " + s + " WHERE name = 'mac';");
            PreparedStatement ps = myConn.prepareStatement("insert into meals values(?,?,?,?,?,?);");

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

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {

            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");

            myStmt = myConn.createStatement();

            System.out.println(restaurantName);
            myRs = myStmt.executeQuery("select * from meals where restaurantName ='" + restaurantName + "';");

            while (myRs.next()) {
                String name = myRs.getString("name");
                String description = myRs.getString("description");
                float price = myRs.getFloat("price");
                byte[] image = myRs.getBytes("image");
                System.out.println(name + "\n" + description);
                Meal m = new Meal(name, description, price, image);

                mealList.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mealList;
    }

    public int getMealId(String mealName, String restaurantName) {

        int id = -1;
        Connection myConn;
        Statement myStmt;
        ResultSet myRs;
        try {

            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");

            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery("select id from meals where name ='" + mealName + "'and restaurantName='" + restaurantName + "';");

            while (myRs.next()) {
                id = myRs.getInt("id");

            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public void updateMeal(Meal m, InputStream s, int id) {

        Connection myConn;
        Statement myStmt;
        ResultSet myRs;
        try {

            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");

            PreparedStatement ps = myConn.prepareStatement("update meals set name = ?,description=?,image=? where id = ? ;");

            ps.setString(1, m.name);
            ps.setString(2, m.description);

            ps.setBlob(3, s);
            ps.setInt(4, id);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Meal updated");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error please try again");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertMealIntoCart(int mealId, int quantity, String username) {

        Connection myConn;
        Statement myStmt;
        ResultSet myRs;
        try {

            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");

            PreparedStatement ps = myConn.prepareStatement("insert ignore into cart (cartOwner,mealId) values(?,?);");
            ps.setString(1, username);
            ps.setInt(2, mealId);

            ps.executeUpdate();

            PreparedStatement ps1 = myConn.prepareStatement("update cart set quantity=quantity + ? where mealId=? and cartOwner=? and orderNumber=0;");
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

        Connection myConn;
        Statement myStmt = null;
        ResultSet myRs;
        try {

            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");

            myStmt = myConn.createStatement();
            //get max number of order for a specific customer
            myRs = myStmt.executeQuery("select max(orderNumber) from cart where cartOwner='" + username + "';");
            int maxOrderNumber = 0;
            if (myRs.next()) {
                maxOrderNumber = myRs.getInt(1);
            }
            maxOrderNumber++;

            PreparedStatement ps1 = myConn.prepareStatement("update cart set orderNumber=? , orderDate=? where cartOwner=? and orderNumber=0;");
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

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");

            myStmt = myConn.createStatement();

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

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        Meal m = null;
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");

            myStmt = myConn.createStatement();

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

        Connection myConn;
        Statement myStmt = null;
        ResultSet myRs;
        try {

            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");

            myStmt = myConn.createStatement();
            //get max number of order for a specific customer
            myStmt.executeUpdate("delete from cart where cartOwner='" + username + "' and mealId=" + mealid + ";");

            JOptionPane.showMessageDialog(null, "Meal deleted");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error please try again");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Order[] returnOrderOfcustomers(String username) {

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        Order[] orders = null;
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");

            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery("select max(orderNumber) from cart where cartOwner='" + username + "';");
            int maxOrderNumber = 0;
            if (myRs.next()) {
                maxOrderNumber = myRs.getInt(1);
            }
            myRs = null;
            myRs = myStmt.executeQuery("select * from cart where cartOwner = '" + username + "' and orderNumber<>0; ");

            orders = new Order[maxOrderNumber+1];
            
            for(int i=0;i<maxOrderNumber+1;i++)
            {
                orders[i]= new Order();
            }

            System.out.println("max order number = "+maxOrderNumber );
            while (myRs.next()) {

                int mealId=myRs.getInt("mealID");
                int mealOrderNumber=myRs.getInt("orderNumber");
                int quantity=myRs.getInt("quantity");
                Date d= myRs.getTimestamp("orderDate");
                orders[mealOrderNumber].Date=d;
                orders[mealOrderNumber].addMeal(returnMealFromId(mealId),quantity ,mealOrderNumber );

            }
        } catch (Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
}
