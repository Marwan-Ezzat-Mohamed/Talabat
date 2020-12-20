package talabat;

public class Owner {

    public String username, password, restaurantName;
    private Restaurant restaurant = new Restaurant();

    public Owner(String username, String password, String restaurantName) {

        this.username = username;
        this.password = password;
        this.restaurantName = restaurantName;
        restaurant.name = this.restaurantName;
    }

    public void addMeal(Meal m) {
        restaurant.meals[restaurant.mealCount++] = m;
    }

    public void removeMeal(int index) {
        restaurant.mealCount--;
        index--;
        Meal[] newMeals = new Meal[100];
        int j = 0;
        for (int i = 0; i < 100; i++) {
            if (i == index) {
                continue;
            }
            newMeals[j++] = restaurant.meals[i];
        }
        restaurant.meals = newMeals;
    }

    //waiting for gui
    public void editMeal(int i) {
        //restaurant.meals[i].name /// = textbox text
        //restaurant.meals[i].description /// = textbox text
        //restaurant.meals[i].price /// = textbox text
    }

    //waiting for orders class
    public void viewOrders() {
//        for (int i = 0; i < restaurant.mealCount; i++) {
//
//            restaurant.orders[i].displayInfo();
//        }
    }

    public void viewMeals() {
        for (int i = 0; i < restaurant.mealCount; i++) {

            restaurant.meals[i].displayInfo();
        }
    }

}
