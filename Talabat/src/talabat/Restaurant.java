package talabat;

import com.sun.imageio.plugins.common.I18N;

public class Restaurant {

    private final int maxSize = 100;
    private static int numberOfRestaurants;
    private int mealCount, numberOfOrders;
    private Meal[] meals = new Meal[maxSize];
    private Order[] orders = new Order[maxSize];
    private String name, description;

    public void setName(String name) {
        this.name = name;
    }

    public void setMealCount(int mealCount) {
        this.mealCount = mealCount;
    }
    private byte[] Image;

    public void setDescription(String description) {
        this.description = description;
    }

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
