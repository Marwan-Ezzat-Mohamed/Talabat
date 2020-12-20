package talabat;

public class Cart {
    private final int maxOrders = 100;    
    public Order [] orders = new Order[maxOrders];
    public float totalPrice = 0;
     int ordersQuantity;  //number of orders currently in the cart.
    
    public Cart() {
        ordersQuantity = 0; 
    }
    public void addOrder(Order order)
    {
        orders[ordersQuantity++] = order;
        float price = order.ordererdMeall.mealPrice;
        totalPrice += price * order.getQuantity();
    }
    public void removeOrder(Order order)
    {
        for(int i = 0; i < ordersQuantity; i++)
        {
            if(order == orders[i])
            {
                for (int j = i+1; j < ordersQuantity; j++)
                {
                    orders[j-1] = orders[j]; 
                } 
                float Price = order.ordererdMeall.mealPrice;
                totalPrice -= Price * order.getQuantity();
                ordersQuantity--;
                return;
            }
        }
    }
}
