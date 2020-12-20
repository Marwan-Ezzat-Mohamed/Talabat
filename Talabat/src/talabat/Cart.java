package talabat;

public class Cart {

    private final int maxMeals = 100;
    public Meal[] meals = new Meal[maxMeals];
    public float totalPrice = 0;
    public int mealsQuantity;  //number of orders currently in the cart.

    public void addMeal(Meal meal) {
        meals[mealsQuantity++]=meal;
    }
    
    public void removeMeal(int index) {
        mealsQuantity--;
        index--;
        Meal[] newMeals = new Meal[100];
        int j = 0;
        for (int i = 0; i < 100; i++) {
            if (i == index) {
                continue;
            }
            newMeals[j++] = meals[i];
        }
        meals = newMeals;
    }
    
     public void displayMeals() {
       for(int i=0;i<mealsQuantity;i++)
       {
           
           meals[i].displayInfo();;
       }
    }
}
