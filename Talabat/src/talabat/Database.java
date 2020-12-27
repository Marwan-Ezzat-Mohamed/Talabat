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

    public void insertIntoBasketTable(String mealId) {

        // meal id //userId
        String username = Talabat.currentUser;

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs,myRs1 = null;
        try {
            // get connection 
            myConn = DriverManager.getConnection("jdbc:mysql://sql2.freesqldatabase.com:3306/sql2383521", "sql2383521", "bL5%tX9!");
            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            
            myRs1 = myStmt.executeQuery("select cart_id from customers where username ='"+username+"';");
            String basketId = null;
            while(myRs1.next())
            {
                basketId=myRs1.getString("cart_id");
            }

            myStmt.executeUpdate("insert into contains values("+basketId+","+mealId+");");
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
            myConn = DriverManager.getConnection("jdbc:mysql://sql2.freesqldatabase.com:3306/sql2383521", "sql2383521", "bL5%tX9!");
            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            myRs = myStmt.executeQuery("select * from Restaurant");

            // 4. Process the result set
            Restaurant r;
            while (myRs.next()) {
                r = new Restaurant(myRs.getString("name"), myRs.getBytes("photo"), myRs.getString("description"));

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
            myConn = DriverManager.getConnection("jdbc:mysql://sql2.freesqldatabase.com:3306/sql2383521", "sql2383521", "bL5%tX9!");
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

   
}
