package talabat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import jdk.nashorn.internal.ir.ContinueNode;

public class Cart {

    private final int maxMeals = 100;
    public Meal[] meals = new Meal[maxMeals];
    public float totalPrice = 0;
    public int numberOfMeals;
    public LocalDate orderDate;
    public String notes, restaurnatName;
//number of orders currently in the cart.

    public void addMeal(Meal meal, int quantity) {
        int index=isMealFound(meal);
        if (index!=-1) {
            totalPrice += meal.getPrice() * quantity;
            meals[index] = meal;
            meals[index].mealsQuantityInCart+=quantity;
            return;
        }
        meals[numberOfMeals] = meal;
        meals[numberOfMeals].mealsQuantityInCart+=quantity;
        totalPrice += meal.getPrice() * quantity;
        numberOfMeals++;
    }

    public int isMealFound(Meal m) {
        for (int i = 0; i < numberOfMeals; i++) {
            if (m.name.equals(meals[i].name)) {
                return i;
            }
        }
        return -1;
    }

    public void removeMeal(int index) {
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

    public void displayMeals() {
        for (int i = 0; i < numberOfMeals; i++) {

            meals[i].displayInfo();
        }
    }

    
    public void emptyCart() {
        for (int i = 0; i < maxMeals; i++) {
            meals[i] = null;
        }
        numberOfMeals = 0;
    }

}
