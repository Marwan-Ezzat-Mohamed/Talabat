package CstomerPackage;

import talabat.*;

public class Customer extends User {

    public int ordersCount;
    private final int maxOrders = 100;
    private String mobileNumber, address;
    private Cart cart = new Cart();
    private Order[] orders = new Order[maxOrders];

    public Customer(String mobileNumber, String address, String user, String pass) throws Exception {
        super(pass, user);
        this.mobileNumber = mobileNumber;
        this.address = address;
       
        loadCart();
        loadOrders();

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
        this.cart =Talabat.database.returnCartOfCustomer(super.getUsername());
        return this.cart;
    }

    public Order[] loadOrders() {
        
        this.orders = Talabat.database.returnOrderOfcustomer(super.getUsername());
        ordersCount = this.orders.length;
        return this.orders;
    }

}
