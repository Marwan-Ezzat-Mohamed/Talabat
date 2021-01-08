package talabat;

import javax.swing.ImageIcon;

public class Meal {

    public String name, description;

    public float mealPrice;
    public int orderCount, mealId,numberInOrder;
    public int mealsQuantityInCart;
    public byte[] databaseImage;

    public Meal(String name, String description, float mealPrice) {
        this.name = name;
        this.description = description;
        this.mealPrice = mealPrice;
    }
    
    public ImageIcon Image = new ImageIcon(ImageIcon.class.getResource("/pics/addphoto.png"));

    public Meal(String name, float mealPrice) {
        this.name = name;
        this.mealPrice = mealPrice;

    }

    public Meal(String name, String description, float price, byte [] image) {
        this.name = name;
        this.description = description;
        this.mealPrice = price;
        this.databaseImage = image;

    }
    
    public Meal(String name, String description, float price, byte [] image,int id) {
        this.name = name;
        this.description = description;
        this.mealPrice = price;
        this.databaseImage = image;
        this.mealId=id;

    }

    public Meal(String name, String description, float mealPrice,ImageIcon i) {
        this.name = name;
        this.description = description;
        this.mealPrice = mealPrice;
        this.Image=i;
    }

    public Meal() {
    }

    

    public void displayInfo() {
        System.out.println("Meal name: " + this.name + "\nMeal price: " + this.mealPrice + "\nDescription: " + this.description);
    }

    public float getPrice() {
        return mealPrice;
    }

}
