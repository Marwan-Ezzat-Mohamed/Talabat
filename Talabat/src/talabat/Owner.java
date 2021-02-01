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

    public String getRestaurantName() {
        return restaurantName;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    

    public void addMeal(Meal m, InputStream s) {

       Talabat.database.addMealToRestaurant(m, restaurantName, s);
       restaurant.setMealCount(restaurant.getMealCount()+1);
      
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
