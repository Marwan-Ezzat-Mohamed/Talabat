package talabat;

public class Customer {

    public String mobileNumber;
    public String address;
    Order[] orders = new Order[100];
    public int ordersCount;
    Cart cart = new Cart();

    public Customer(String mobileNumber, String address) {
        this.mobileNumber = mobileNumber;
        this.address = address;

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
