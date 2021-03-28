package talabat;

import java.util.Date;

public class Order {

    private Meal[] ordererdMeals = new Meal[100];
    private int quantity, numberOfOrders;
    private String notes, restaurantName;
    private Date Date;
    private int numberOfMeals;

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

    public int getNumberOfMeals() {
        return numberOfMeals;
    }

    public int getQuantity() {
        return quantity;
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
        this.numberOfMeals = numberOfMealsInCart;
    }

    
    public void addMeal(Meal m, int q, int numberInOrder) {
      
        ordererdMeals[numberOfMeals] = m;
        ordererdMeals[numberOfMeals].setMealsQuantityInCart(q);
        ordererdMeals[numberOfMeals].setNumberInOrder(numberInOrder);
        numberOfMeals++;
        

    }

    public void addMeal(Meal m, int q, int numberInOrder, Date d) {
        
        ordererdMeals[numberOfMeals] = m;
        ordererdMeals[numberOfMeals].setMealsQuantityInCart(q);
        ordererdMeals[numberOfMeals].setOrderDate(d);
        ordererdMeals[numberOfMeals].setNumberInOrder(numberInOrder);
        numberOfMeals++;

    }
    
   
}
