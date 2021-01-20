package talabat;

import java.time.LocalDate;

public class Cart {

    private final int maxMeals = 100;
    private Meal[] meals = new Meal[maxMeals];
    private float totalPrice = 0;
    private int numberOfMeals;
    private LocalDate orderDate;
    private String notes, restaurnatName;

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

    public String getNotes() {
        return notes;
    }

    public String getRestaurnatName() {
        return restaurnatName;
    }

    public void addMeal(Meal meal, int quantity) {
        int index=isMealFound(meal);
        if (index!=-1) {
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

    public int isMealFound(Meal m) {
        for (int i = 0; i < numberOfMeals; i++) {
            if (m.getName().equals(meals[i].getName())) {
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
}
