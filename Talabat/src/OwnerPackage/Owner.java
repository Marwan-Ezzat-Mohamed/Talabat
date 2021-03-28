package OwnerPackage;

import java.io.InputStream;
import java.util.ArrayList;
import talabat.*;



public class Owner extends User {

    public static int numberOfOwners;
    
    private Restaurant restaurant = new Restaurant();

    public Owner(String username, String password, String restaurantName) throws Exception {

        super(password, username);


        restaurant.setName(restaurantName);

        numberOfOwners++;
        for (int i = 0; i < 100; i++) {
            this.restaurant.getOrders()[i] = new Order();
        }
    }

   

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void addMeal(Meal m, InputStream s) {

        Talabat.database.addMealToRestaurant(m, restaurant.getName(), s);
        restaurant.setMealCount(restaurant.getMealCount() + 1);

    }

    public void removeMeal(String mealName) {

        restaurant.setMealCount(restaurant.getMealCount() - 1);

        //remove from database
        int id = Talabat.database.getMealId(mealName, restaurant.getName());
        Talabat.database.removeMealFromOwner(id);

    }

    //overlaoding 
    //update with image
    public void editMeal(int index, Meal m) {
        int id = Talabat.database.getMealId(Talabat.mainFrame.mealList.get(index).getName(), this.restaurant.getName());
        Talabat.database.updateMeal(m, id);
    }

    //update without image
    public void editMeal(int index, InputStream is, Meal m) {
        int id = Talabat.database.getMealId(Talabat.mainFrame.mealList.get(index).getName(), this.restaurant.getName());
        Talabat.database.updateMeal(m, is, id);
    }

    public void editRestaurantDescription(String description) {
        this.restaurant.setDescription(description);
        Talabat.database.editRestaurantDescription(this.restaurant.getName(), description);
    }

    public ArrayList<Meal> dispalyMeals() {
        return Talabat.database.getRestaurantMeals(this.restaurant.getName());
    }

}
