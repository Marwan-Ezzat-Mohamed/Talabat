package talabat;

import static talabat.MainFrame.ownerIndex;

public class Customer extends User {

    private static int numberOfCustomers;

    private String mobileNumber;
    private String address;
    private Order[] orders = new Order[100];
    private int ordersCount;
    private Cart cart = new Cart();

    public static int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public Order[] getOrders() {
        return orders;
    }

    public int getOrdersCount() {
        return ordersCount;
    }

    public Cart getCart() {
        return cart;
    }

    public Customer(String mobileNumber, String address, String user, String pass) {
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.setUsername(user);
        this.setPassword(pass);
        numberOfCustomers++;
    }

    public void orderCart() {
        Talabat.database.orderCart(this.getUsername());
       
        Order o = new Order();
        o.addCart(cart);

        orders[ordersCount++] = o;
        cart.emptyCart();
        
    }
    
    public Cart returnCart()
    {
        Cart c =Talabat.database.returnCartOfCustomer(this.getUsername());
        return c;
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
