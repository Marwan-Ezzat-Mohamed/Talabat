package talabat;

public class Customer extends User {

    public static int numberOfCustomers;

    public String mobileNumber;
    public String address;
    Order[] orders = new Order[100];
    public int ordersCount;
    Cart cart = new Cart();

    public Customer(String mobileNumber, String address, String user, String pass) {
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.username = user;
        this.password = pass;
        numberOfCustomers++;
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
