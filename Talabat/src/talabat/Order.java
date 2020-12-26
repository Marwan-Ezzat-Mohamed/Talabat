package talabat;

import java.util.Date;

public class Order {

    public Meal[] ordererdMeals = new Meal[100];
    public int quantity, numberOfOrders;
    public String notes, restaurantName;
    public Date Date;
    public int numberOfMealsInCart;

    public int getQuantity() {
        return quantity;
    }

    public void addCart(Cart cart) {
        numberOfOrders++;
        this.numberOfMealsInCart = cart.mealsQuantity;
        for (int i = 0; i < numberOfMealsInCart; i++) {
            ordererdMeals[i] = cart.meals[i];
        }

    }

    public void displayOrder() {
        for (int i = 0; i < numberOfMealsInCart; i++) {
            ordererdMeals[i].displayInfo();
        }
    }

}
