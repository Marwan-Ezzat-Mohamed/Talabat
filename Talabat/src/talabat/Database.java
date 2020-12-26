/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talabat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Marwan Ezzat
 */
public class Database {
    
    public ArrayList<Restaurant> returnAllRestaurants() {

        ArrayList<Restaurant> list = new ArrayList<Restaurant>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            // get connection 
            myConn = DriverManager.getConnection("jdbc:mysql://sql2.freesqldatabase.com:3306/sql2383521", "sql2383521", "bL5%tX9!");
            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            myRs = myStmt.executeQuery("select * from restaurants_images");

            // 4. Process the result set
            Restaurant r;
            while (myRs.next()) {
                r = new Restaurant(myRs.getString("restaurant_name"), myRs.getBytes("image"),myRs.getString("r_description"));
                list.add(r);
            }
        } catch (Exception ex) {
            System.out.println("error 404");
        }
        return list;
    }
    
     public ArrayList<Order> returnMyOrders(String s) {
        String user=s;
        ArrayList<Order> orderList = new ArrayList<Order>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            // get connection 
            myConn = DriverManager.getConnection("jdbc:mysql://sql2.freesqldatabase.com:3306/sql2383521", "sql2383521", "bL5%tX9!");
            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            System.out.println(user);
            myRs = myStmt.executeQuery("select * from orders where username='"+user+"';");
            
            // 4. Process the result set
            Order o;
            Cart c=new Cart();
            c.addMeal(new Meal("kofta",20.0f),2);
           
            while (myRs.next()) {
                o = new Order();
                o.addCart(c);
                orderList.add(o);
            }
        } catch (Exception ex) {
            System.out.println("error 404");
        }
        return orderList;
    }
}
