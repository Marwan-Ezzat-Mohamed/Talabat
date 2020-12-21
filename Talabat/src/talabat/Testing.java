/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talabat;

/**
 *
 * @author Marwan Ezzat
 */
public class Testing {
    public void testingMeals() {
        Customer ahmed = new Customer("341312", "34211fsdgsd");
        Meal x = new Meal("burger", "gdfsgsdf", 100F);
        Meal y = new Meal("fsdfsd", "fsdf", 12132134F);

        ahmed.cart.addMeal(x);
        ahmed.cart.addMeal(y);
        ahmed.cart.displayMeals();
        System.out.println("..........................");
        ahmed.cart.removeMeal(2);
        ahmed.cart.displayMeals();
    }

    public void testingCart() {
        Customer ahmed = new Customer("341312", "34211fsdgsd");
        Meal x = new Meal("burger", "gdfsgsdf", 100F);
        Meal y = new Meal("fsdfsd", "fsdf", 12132134F);

        ahmed.cart.addMeal(x);
        ahmed.cart.addMeal(y);
        ahmed.cart.displayMeals();
        System.out.println("..........................");
        ahmed.orderCart();
        System.out.println(ahmed.cart.mealsQuantity);

        ahmed.viewOrders();

    }
}
