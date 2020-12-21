package talabat;

public class Customer extends User {

    public String mobileNumber;
    public String address;
    Order[] orders = new Order[100];
    public int ordersCount;
    Cart cart = new Cart();

    public Customer(String username,String pass) {
        this.accountType=0;
        this.username=username;
        this.password=pass;
        
    }

    public void orderCart() {
        Order o = new Order();
        o.addOrder(cart);

        orders[ordersCount++] = o;
        cart.emptyCart();
    }

    public void browseRestaurants() {

    }

    public void browseMealOfRestaurant() {

    }

    public void viewOrders() {

        for (int i = 0; i < ordersCount; i++) {
            System.out.println("Order number: " + (i + 1) + "#");
            orders[i].displayOrder();
        }
    }

}
