package talabat;

import java.util.Date;
import jdk.nashorn.internal.ir.ContinueNode;

public class Cart {

    private final int maxMeals = 100;
    public Meal[] meals = new Meal[maxMeals];
    public float totalPrice = 0;
    public int numberOfMeals;
    public Date orderDate;
    public String notes, restaurnatName;
//number of orders currently in the cart.

    public void addMeal(Meal meal, int quantity) {
        if (isMealFound(meal)) {
            totalPrice += meal.getPrice() * quantity;
            meals[numberOfMeals] = meal;
            System.out.println("FOUND");
            return;
        }
        meals[numberOfMeals] = meal;
        totalPrice += meal.getPrice() * quantity;
        numberOfMeals++;
    }

    public boolean isMealFound(Meal m) {
        for (int i = 0; i < numberOfMeals; i++) {
            if (m.name.equals(meals[i].name)) {
                return true;
            }
        }
        return false;
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

            meals[i].displayInfo();;
        }
    }

    
    public void emptyCart() {
        for (int i = 0; i < maxMeals; i++) {
            meals[i] = null;
        }
        numberOfMeals = 0;
    }

}
