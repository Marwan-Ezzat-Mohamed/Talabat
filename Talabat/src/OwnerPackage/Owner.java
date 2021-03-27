package OwnerPackage;

import java.io.InputStream;
import java.util.ArrayList;
import talabat.*;



public class Owner extends User {

    public static int numberOfOwners;
    private String restaurantName;
    private Restaurant restaurant = new Restaurant();

    public Owner(String username, String password, String restaurantName) throws Exception {

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
        restaurant.setMealCount(restaurant.getMealCount() + 1);

    }

    public void removeMeal(String mealName) {

        restaurant.setMealCount(restaurant.getMealCount() - 1);

        //remove from database
        int id = Talabat.database.getMealId(mealName, restaurantName);
        Talabat.database.removeMealFromOwner(id);

    }

    //overlaoding 
    //update with image
    public void editMeal(int index, Meal m) {
        int id = Talabat.database.getMealId(Talabat.mainFrame.mealList.get(index).getName(), Talabat.owner.getRestaurantName());
       
       
        System.out.println(Talabat.mainFrame.mealList.get(index).getName()+":: "+Talabat.mainFrame.mealList.get(index).getPrice()+":::id: "+id);
        Talabat.database.updateMeal(m, id);
    }

    //update without image
    public void editMeal(int index, InputStream is, Meal m) {
        int id = Talabat.database.getMealId(Talabat.mainFrame.mealList.get(index).getName(), Talabat.owner.getRestaurantName());
        Talabat.database.updateMeal(m, is, id);
    }

    public void editRestaurantDescription(String description) {
        this.restaurant.setDescription(description);
        Talabat.database.editRestaurantDescription(this.restaurantName, description);
    }

    public ArrayList<Meal> dispalyMeals() {
        return Talabat.database.getRestaurantMeals(this.restaurantName);
    }

}
