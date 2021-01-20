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
    private String restaurantName;
    private Restaurant restaurant = new Restaurant();

    

    public String getRestaurantName() {
        return restaurantName;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Owner(String username, String password, String restaurantName) {

        super(password, username);

        this.setPassword(password);
        this.restaurantName = restaurantName;
        restaurant.setName(this.restaurantName);

        numberOfOwners++;
        for (int i = 0; i < 100; i++) {
            this.restaurant.getOrders()[i] = new Order();
        }
    }

    public void addMeal(Meal m, InputStream s) {

        Connection myConn = null;
        Statement myStmt = null;
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");
            myStmt = myConn.createStatement();

            PreparedStatement ps = myConn.prepareStatement("insert into meals values(?,?,?,?,?,?);");

            ps.setString(1, null);
            ps.setString(2, m.getName());
            ps.setString(3, m.getDescription());
            ps.setBlob(4, s);
            ps.setString(5, getRestaurantName());
            ps.setFloat(6, m.getMealPrice());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "meal added");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error please try again");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeMeal(String mealName) {

        restaurant.setMealCount(restaurant.getMealCount()-1);
     
        //remove from database
        int id = Talabat.database.getMealId(mealName, restaurantName);
        Talabat.database.removeMealFromOwner(id);

    }

    //waiting for gui
    public void editMeal(int i) {
        //restaurant.meals[i].name /// = textbox text
        //restaurant.meals[i].description /// = textbox text
        //restaurant.meals[i].price /// = textbox text
    }

    public void editRestaurantDescription(String description) {
        this.restaurant.setDescription(description);
        Talabat.database.editRestaurantDescription(this.restaurantName, description);
    }

    
    public void viewMeals() {
        for (int i = 0; i < restaurant.getMealCount(); i++) {

            restaurant.getMeals()[i].displayInfo();
        }
    }

}
