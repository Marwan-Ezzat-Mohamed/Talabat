package talabat;

import java.util.Date;
import javax.swing.ImageIcon;

public class Meal {

    private String name, description, notesForOrder;
    private float mealPrice;
    private int mealId, numberInOrder, mealsQuantityInCart;
    private byte[] databaseImage;
    private Date orderDate;

    public Meal(String name, String description, float mealPrice) {
        this.name = name;
        this.description = description;
        this.mealPrice = mealPrice;
    }

    public Meal(String name, float mealPrice) {
        this.name = name;
        this.mealPrice = mealPrice;

    }
    
    public Meal(String name, String description, float price, byte[] image) {
        this.name = name;
        this.description = description;
        this.mealPrice = price;
        this.databaseImage = image;

    }

    public Meal(String name, String description, float price, byte[] image, int id) {
        this.name = name;
        this.description = description;
        this.mealPrice = price;
        this.databaseImage = image;
        this.mealId = id;

    }

    public Meal() {
    }

    public void setNumberInOrder(int numberInOrder) {
        this.numberInOrder = numberInOrder;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getNotesForOrder() {
        return notesForOrder;
    }

    public float getMealPrice() {
        return mealPrice;
    }

    public int getMealId() {
        return mealId;
    }

    public int getNumberInOrder() {
        return numberInOrder;
    }

    public int getMealsQuantityInCart() {
        return mealsQuantityInCart;
    }

    public void setMealsQuantityInCart(int mealsQuantityInCart) {
        this.mealsQuantityInCart = mealsQuantityInCart;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNotesForOrder(String notesForOrder) {
        this.notesForOrder = notesForOrder;
    }

    public void setMealPrice(float mealPrice) {
        this.mealPrice = mealPrice;
    }

    public byte[] getDatabaseImage() {
        return databaseImage;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    
    public void displayInfo() {
        System.out.println("Meal name: " + this.name + "\nMeal price: " + this.mealPrice + "\nDescription: " + this.description);
    }

    public float getPrice() {
        return mealPrice;
    }

}
