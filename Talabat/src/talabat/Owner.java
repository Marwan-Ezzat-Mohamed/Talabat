package talabat;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Owner extends User {

    public static int numberOfOwners;
    public String restaurantName;
    public Restaurant restaurant = new Restaurant();

    public Owner(String username, String password, String restaurantName) {

        this.username = username;
        this.password = password;
        this.restaurantName = restaurantName;
        restaurant.name = this.restaurantName;
        this.accountType = 1;
        numberOfOwners++;
        for (int i = 0; i < 100; i++) {
            this.restaurant.orders[i] = new Order();
        }
    }

    public void addMeal(Meal m, InputStream s) {
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

    public void removeMeal(String mealName) {

        restaurant.mealCount--;

        //remove from database
        Database db = new Database();
        int id = db.getMealId(mealName);
        Connection myConn;

        try {

            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");

            PreparedStatement ps = myConn.prepareStatement("delete from meals where id = ? ;");

            ps.setInt(1, id);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Meal deleted");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error please try again");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //waiting for gui
    public void editMeal(int i) {
        //restaurant.meals[i].name /// = textbox text
        //restaurant.meals[i].description /// = textbox text
        //restaurant.meals[i].price /// = textbox text
    }

    public void viewOrders() {
        for (int i = 0; i < restaurant.mealCount; i++) {

            restaurant.orders[i].displayOrder();
        }
    }

    public void viewMeals() {
        for (int i = 0; i < restaurant.mealCount; i++) {

            restaurant.meals[i].displayInfo();
        }
    }

}
