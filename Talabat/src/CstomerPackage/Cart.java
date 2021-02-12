package CstomerPackage;



import java.time.LocalDate;
import talabat.*;

public class Cart {

    private final int maxMeals = 100;
    private Meal[] meals = new Meal[maxMeals];
    private float totalPrice = 0;
    private int numberOfMeals;
    private LocalDate orderDate;
    

    public int getMaxMeals() {
        return maxMeals;
    }

    public Meal[] getMeals() {
        return meals;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public int getNumberOfMeals() {
        return numberOfMeals;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    
    public void addMealIntoMealsArray (Meal meal, int quantity)
    {
        int index = mealFoundInMealsArray(meal);
        if (index != -1) {
            totalPrice += meal.getPrice() * quantity;
            meals[index] = meal;
            meals[index].setMealsQuantityInCart(quantity);
            return;
        }
        meals[numberOfMeals] = meal;
        meals[numberOfMeals].setMealsQuantityInCart(quantity);
        totalPrice += meal.getPrice() * quantity;
        numberOfMeals++;
    }
    // insert fl array bs 34an lma a3ml intialize fl awl bs
    public void addMeal(Meal meal, int quantity) {
        addMealIntoMealsArray(meal, quantity);
     

    }

    // insert fl array wl database
    public void addMeal(int id, int quantity, float price, String notes, String username, String restaurantName) {

        Meal meal = Talabat.database.returnMealFromId(id);
        
        Talabat.database.insertMealIntoCart(id, quantity, price, notes,username, restaurantName);

        addMealIntoMealsArray(meal, quantity);

    }

    public int mealFoundInMealsArray(Meal m) {
        for (int i = 0; i < numberOfMeals; i++) {
            if (m.getName().equals(meals[i].getName())&&m.getRestaurantName().equals(meals[i].getRestaurantName())) {
                return i;
            }
        }
        return -1;
    }

    public void removeMeal(int index) {

        // lw m3ml4 select le 7aga yms7 awl meal
        if (index == -1) {
            index = 0;
        }
        
        String mealRestaurantName = meals[index].getRestaurantName();
        int mealId = Talabat.database.getMealId(meals[index].getName(), mealRestaurantName);
        
        //insert fl database
        Talabat.database.removeMeal(mealId, Talabat.customer.getUsername());

        // remove localy mn el array
        numberOfMeals--;
        Meal[] newMeals = new Meal[100];
        int j = 0;
        for (int i = 0; i < 100; i++) {
            if (i == index) {
                continue;
            }
            newMeals[j++] = meals[i];
        }
        meals = newMeals;
    }

    public void resetCartAfterOrder() {
        meals = new Meal[maxMeals];
        totalPrice = 0;
        numberOfMeals = 0;
        orderDate = null;
        
    }
}
