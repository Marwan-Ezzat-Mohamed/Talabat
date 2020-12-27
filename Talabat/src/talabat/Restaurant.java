package talabat;

import com.sun.imageio.plugins.common.I18N;


public class Restaurant {

    public static int numberOfRestaurants;
    protected int mealCount = 0,numberOfOrders;
    protected Meal[] meals = new Meal[100];
    protected Order[] orders = new Order[100];
    protected String name,description;
    public byte[] Image;
    
    public Restaurant() {
        numberOfRestaurants++;
    }
    public Restaurant(String name, Meal m) {
        this.name=name;
        this.meals[mealCount++]=m;
        numberOfRestaurants++;
    }

    public Restaurant(String name, byte[] Image,String description) {
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
