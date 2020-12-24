package talabat;


public class Restaurant {

    protected int mealCount = 0;
    protected Meal[] meals = new Meal[100];
    protected Order[] orders = new Order[100];
    protected String name,description;
    public byte[] Image;
    

    public Restaurant() {
    }

    public Restaurant(String name, byte[] Image,String description) {
        this.name = name;
        this.description = description;
        this.Image = Image;
    }

    
    
    

}
