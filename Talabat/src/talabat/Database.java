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
import java.util.ArrayList;
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

    public int getMealId(String mealName) {

        int id = -1;
        Connection myConn;
        Statement myStmt;
        ResultSet myRs;
        try {

            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");

            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery("select id from meals where name ='" + mealName + "';");

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

    

}
