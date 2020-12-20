package talabat;

public class Meal {

    public String name, description;
    public float mealPrice;
    public int orderCount;

    public Meal(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.mealPrice = price;
    }

    public void displayInfo() {
        System.out.println("Meal name: " + this.name + "\nMeal price: " + this.mealPrice + "\nDescription: " + this.description);
    }

    public float getPrice() {
        return mealPrice;
    }

}
