package talabat;

public class Talabat {
    
    public void testingMeals(){
        Customer ahmed = new Customer("341312", "34211fsdgsd");
        Meal x= new Meal("burger", "gdfsgsdf", 100F);
        Meal y= new Meal("fsdfsd", "fsdf", 12132134F);
        
        ahmed.cart.addMeal(x);
        ahmed.cart.addMeal(y);
        ahmed.cart.displayMeals();
        System.out.println("..........................");
        ahmed.cart.removeMeal(2);
        ahmed.cart.displayMeals();
    }
    
         
    

    public static void main(String[] args) {
//       Talabat t = new Talabat();
//       t.testingMeals();
        
    }
    
}
