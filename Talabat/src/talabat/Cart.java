package talabat;

import java.util.Date;

public class Cart {

    private final int maxMeals = 100;
    public Meal[] meals = new Meal[maxMeals];
    public float totalPrice = 0;
    public int mealsQuantity; 
    public Date orderDate;
    public String notes,restaurnatName;
//number of orders currently in the cart.

    public void addMeal(Meal meal, int quantity) {
        meals[mealsQuantity] = meal;
        totalPrice+=meal.getPrice()*quantity;
        mealsQuantity+=quantity;
    }

    public void removeMeal(int index) {
        mealsQuantity--;
        index--;
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
        for (int i = 0; i < mealsQuantity; i++) {

            meals[i].displayInfo();;
        }
    }

    public void emptyCart() {
        for (int i = 0; i < maxMeals; i++) {
            meals[i] = null;
        }
        mealsQuantity = 0;
    }

}
