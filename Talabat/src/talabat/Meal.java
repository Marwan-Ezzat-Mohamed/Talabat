package talabat;

public class Meal {

    public String name, description;

    
    public float mealPrice;
    public int orderCount;
    
    public byte[] Image;
    public Meal(String name, float mealPrice) {
        this.name = name;
        this.mealPrice = mealPrice;
    }

    public Meal(String name, String description, float price,byte []image) {
        this.name = name;
        this.description = description;
        this.mealPrice = price;
        this.Image=image;
    }

    public void displayInfo() {
        System.out.println("Meal name: " + this.name + "\nMeal price: " + this.mealPrice + "\nDescription: " + this.description);
    }

    public float getPrice() {
        return mealPrice;
    }

}
