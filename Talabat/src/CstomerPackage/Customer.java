package CstomerPackage;

import talabat.*;


public class Customer extends User {

    public int ordersCount;
    public static int numberOfCustomers;
    private final int maxOrders = 100;
    private String mobileNumber, address;
    private Cart cart = new Cart();
    private Order[] orders = new Order[maxOrders];
    
    
    public Customer(String mobileNumber, String address, String user, String pass) {
        super(pass, user);
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.setPassword(pass);
        numberOfCustomers++;

        loadCart();
        loadOrders();

    }

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
    
    
    
    public void orderCart() {

        Talabat.database.orderCart(this.getUsername());
        loadOrders();
        cart.resetCartAfterOrder();
        
    }

    public Cart loadCart() {
        Cart c = Talabat.database.returnCartOfCustomer(this.getUsername());
        this.cart = c;
        return c;
    }

    public Order[] loadOrders() {
        Order[] o = Talabat.database.returnOrderOfcustomer(super.getUsername());
        this.orders = o;
        ordersCount=this.orders.length;
        return o;
    }

}
