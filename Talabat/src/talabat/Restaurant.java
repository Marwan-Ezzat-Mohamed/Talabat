package talabat;

import com.sun.imageio.plugins.common.I18N;


public class Restaurant {

    private static int numberOfRestaurants;

    public void setName(String name) {
        this.name = name;
    }
    protected int mealCount = 0,numberOfOrders;
    protected Meal[] meals = new Meal[100];
    protected Order[] orders = new Order[100];
    private String name;
    private String description;
    private byte[] Image;

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Restaurant() {
        for(int i=0;i<100;i++)
        {
            orders[i]=new Order();
        }
        
        for(int i=0;i<100;i++)
        {
            meals[i]=new Meal();
        }
        numberOfRestaurants++;
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
    public Restaurant(String name, Meal m) {
        //call the main constructor
        this();
        
        this.name=name;
        this.meals[mealCount++]=m;
        numberOfRestaurants++;
    }

    public Restaurant(String name, byte[] Image,String description) {
        //call the main constructor
        this();
        
        this.name = name;
        this.description = description;
        this.Image = Image;
        numberOfRestaurants++;
    }
    
    public void displayOrders()
    {
        for(int i=0;i<numberOfOrders+1;i++)
        {
            orders[i].displayOrder();
        }
    }
    

    
    
    

}
