/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talabat;

/**
 *
 * @author inour
 */
public class Customer {

    public int mobileNumber;
    public String address;
    Cart cart = new Cart();

    public Customer(int mobileNumber, String address) {
        this.mobileNumber = mobileNumber;
        this.address = address;

    }

    public void orderMeal(Meal m,int quantity) {
            
    }

    public void browseRestaurants() {

    }

    public void browseMealOfRestaurant() {

    }

    public void viewOrders() {

    }

}
