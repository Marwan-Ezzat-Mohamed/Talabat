package OwnerPackage;

import talabat.*;

public class Restaurant {

    private static int numberOfRestaurants;
    private final int maxSize = 100;
    private int mealCount, numberOfOrders;
    private Meal[] meals = new Meal[maxSize];
    private Order[] orders = new Order[maxSize];
    private String name, description;
    private byte[] Image;

    
    
    public Restaurant() {
        for (int i = 0; i < maxSize; i++) {
            orders[i] = new Order();
            meals[i] = new Meal();
        }
        numberOfRestaurants++;
    }

    public Restaurant(String name, Meal m) {
        //call the main constructor
        this();

        this.name = name;
        this.meals[mealCount++] = m;
        numberOfRestaurants++;
    }

    public Restaurant(String name, byte[] Image, String description) {
        //call the main constructor
        this();

        this.name = name;
        this.description = description;
        this.Image = Image;
        numberOfRestaurants++;
    }
    
    
    
     public Order displayOrders() {
        Order order = Talabat.database.returnOrderOfOwner(this.name);
        return order;
    }

    
    //getters and setters
    public void setImage(byte[] Image) {
        this.Image = Image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMealCount(int mealCount) {
        this.mealCount = mealCount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  
    public static int getNumberOfRestaurants() {
        return numberOfRestaurants;
    }

    public int getMealCount() {
        return mealCount;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public Meal[] getMeals() {
        return meals;
    }

    public Order[] getOrders() {
        return orders;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getImage() {
        return Image;
    }

}
