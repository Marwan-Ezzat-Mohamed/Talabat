package talabat;

public class Cart {
    private final int maxOrders = 100;    
    Order [] orders = new Order[maxOrders];
    private float totalPrice = 0;
    private int ordersQuantity; //number of orders currently in the cart.
    
    public Cart() {
        ordersQuantity = 0; 
    }
    public void addOrder(Order order)
    {
        orders[ordersQuantity++] = order;
        float Price = order.getorderedMeal().getmealPrice();
        totalPrice += Price * order.getquantity();
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
                float Price = order.getorderedMeal().getmealPrice();
                totalPrice -= Price * order.getquantity();
                ordersQuantity--;
                return;
            }
        }
    }
}
