package talabat;

import java.util.Date;

public class Order {

    private Meal[] ordererdMeals = new Meal[100];
    private int quantity, numberOfOrders;
    private String notes, restaurantName;
    private Date Date;
    private int numberOfMealsInCart;

    public Meal[] getOrdererdMeals() {
        return ordererdMeals;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public String getNotes() {
        return notes;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public Date getDate() {
        return Date;
    }

    public int getNumberOfMealsInCart() {
        return numberOfMealsInCart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addCart(Cart cart) {
        numberOfOrders++;
        this.numberOfMealsInCart = cart.getNumberOfMeals();
        for (int i = 0; i < numberOfMealsInCart; i++) {
            ordererdMeals[i] = cart.getMeals()[i];

        }

    }

    public void setOrdererdMeals(Meal[] ordererdMeals) {
        this.ordererdMeals = ordererdMeals;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public void setNumberOfMealsInCart(int numberOfMealsInCart) {
        this.numberOfMealsInCart = numberOfMealsInCart;
    }

    public void addMeal(Meal m, int q, int numberInOrder) {
      
        ordererdMeals[numberOfMealsInCart] = m;
        ordererdMeals[numberOfMealsInCart].setMealsQuantityInCart(q);
        ordererdMeals[numberOfMealsInCart].setNumberInOrder(numberInOrder);
        numberOfMealsInCart++;
        

    }

    public void addMeal(Meal m, int q, Date d, int numberInOrder) {

        System.out.println("numberOfMealsInCart::" + numberOfMealsInCart);
        ordererdMeals[numberOfMealsInCart] = m;
        ordererdMeals[numberOfMealsInCart].setMealsQuantityInCart(q);
        ordererdMeals[numberOfMealsInCart].setOrderDate(d);
        ordererdMeals[numberOfMealsInCart].setNumberInOrder(numberInOrder);
        numberOfMealsInCart++;

    }
}
