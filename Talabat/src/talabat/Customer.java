package talabat;

public class Customer extends User {

    public int ordersCount;
    public static int numberOfCustomers;
    private final int maxOrders = 100;
    private String mobileNumber, address;
    private Cart cart = new Cart();
    private Order[] orders = new Order[maxOrders];

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
        super(pass, user);
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.setPassword(pass);
        numberOfCustomers++;

        loadCart();
        loadOrders();

    }

    public void orderCart() {

        Talabat.database.orderCart(this.getUsername());
        loadOrders();
        
        System.out.println("talabat.Customer.orderCart():: "+orders[ordersCount-1].getOrdererdMeals()[0].getName());
        
        cart.resetCartAfterOrder();
        
    }

    public Cart loadCart() {
        Cart c = Talabat.database.returnCartOfCustomer(this.getUsername());
        this.cart = c;
        return c;
    }

    public Order[] loadOrders() {
        Order[] orders = Talabat.database.returnOrderOfcustomer(super.getUsername());
        this.orders = orders;
        ordersCount=this.orders.length;
        return orders;
    }

}
