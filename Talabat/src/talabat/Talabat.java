package talabat;

import javax.print.attribute.standard.JobName;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Talabat {

    private static MainFrame loginFrame;
    private static Customer[] customers = new Customer[100];
    private static Owner[] owners = new Owner[100];
    private static String currentUser;
    private static int currentUserIndex;
    private static String currentOwner;
    private static String currentOwnerRestaurantName;
    private static int currentOwnerIndex;
    
    private static Customer customer ;

    public static MainFrame getLoginFrame() {
        return loginFrame;
    }

    public static Customer[] getCustomers() {
        return customers;
    }

    public static Owner[] getOwners() {
        return owners;
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static int getCurrentUserIndex() {
        return currentUserIndex;
    }

    public static String getCurrentOwner() {
        return currentOwner;
    }

    public static String getCurrentOwnerRestaurantName() {
        return currentOwnerRestaurantName;
    }

    public static int getCurrentOwnerIndex() {
        return currentOwnerIndex;
    }

    public static Customer getCustomer() {
        return customer;
    }

    public static Owner getOwner() {
        return owner;
    }

    public static Database getDatabase() {
        return database;
    }
    public static Owner owner ;
    
    public static Database database= new Database();

    public static int login() {
        int numberOfCustomers = Customer.getNumberOfCustomers();
        int numberOfOwners = Owner.getNumberOfOwners();

        String inputUsername = loginFrame.usernameTextField.getText();
        String inputPassword = loginFrame.passwordField.getText();

        for (int i = 0; i < numberOfCustomers; i++) {
            if (customers[i].getUsername().equals(inputUsername) && customers[i].getPassword().equals(inputPassword)) {
                System.out.println("Login successful");
                loginFrame.invalidLoginLabel.setText("");
                loginFrame.username.setText(customers[i].getUsername());
                currentUser = customers[i].getUsername();
                currentUserIndex = i;
                customer=customers[i];
                //do some thing;
                return 1;

            }
        }

        for (int i = 0; i < numberOfOwners; i++) {
            if (owners[i].getUsername().equals(inputUsername) && owners[i].getPassword().equals(inputPassword)) {
                System.out.println("Login successful");
                loginFrame.invalidLoginLabel.setText("");
                currentOwner = owners[i].getUsername();
                currentOwnerRestaurantName=owners[i].getRestaurantName();

                owner=owners[i];
                System.out.println("resturantDescriptionLabel.getText() "+owner.getRestaurant().getDescription());
                currentOwnerIndex = i;
                //do some thing;
                return 2;

            }
            //System.out.println("Login successful");
            //do some thing;

        }
        loginFrame.invalidLoginLabel.setText("Invalid username or password");
        return 0;
    }

    public static boolean signUpForCustomer() {
        int numberOfCustomers = Customer.getNumberOfCustomers();
        Connection myConn1 = null;
        Statement myStmt1 = null;

        String inputUsername = loginFrame.signUpUsernameTextField.getText().toString();
        String inputPassword = loginFrame.passwordFieldForSignUp.getText().toString();
        String confirmPassword = loginFrame.confirmPasswordFieldForSignUp.getText().toString();
        String mobile = loginFrame.mobileTextFieldForSignUp.getText().toString();
        String address = loginFrame.addressTextFieldForSignUp.getText().toString();

//        System.out.println(inputUsername);
//        System.out.println(inputPassword);
//        System.out.println(mobile);
//        System.out.println(address);
        boolean foundUser = false;
        for (int i = 0; i < numberOfCustomers; i++) {
            if (customers[i].getUsername().equals(inputUsername)) {
                foundUser = true;
                break;
            }
        }

        if (address.equals("") || inputUsername.equals("") || inputPassword.equals("") || confirmPassword.equals("") || mobile.equals("")) {
            loginFrame.invalidLoginLabelForSignUp.setText("you must fill all the fields");
            return false;
        } else if (!inputPassword.equals(confirmPassword)) {
            loginFrame.invalidLoginLabelForSignUp.setText("passwords don't match");
            return false;
        } else if (foundUser) {
            loginFrame.invalidLoginLabelForSignUp.setText("username already exits login?");
            return false;

        } else {

            try {
                myConn1 = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");
                myStmt1 = myConn1.createStatement();
                String st = "INSERT INTO customers Values( '" + inputUsername + "','" + inputPassword + "','" + mobile + "','" + address + "');";
                myStmt1.executeUpdate(st);
                customers[Customer.getNumberOfCustomers()] = new Customer(mobile, address, inputUsername, inputPassword);
            } catch (SQLException ex) {
                Logger.getLogger(Talabat.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }

    }

    public static boolean signUpForOwner() {
        int numberOfOwners = Owner.getNumberOfOwners();
        Connection myConn1 = null;
        Statement myStmt1 = null;

        String inputUsername = loginFrame.signUpUsernameTextField1.getText().toString();
        String inputPassword = loginFrame.passwordFieldForSignUp1.getText().toString();
        String confirmPassword = loginFrame.confirmPasswordFieldForSignUp1.getText().toString();
        String restaurantName = loginFrame.restaurantNameTextFieldForSignUp1.getText().toString();

//        System.out.println(inputUsername);
//        System.out.println(inputPassword);
//        System.out.println(mobile);
//        System.out.println(address);
        boolean foundUser = false;
        for (int i = 0; i < numberOfOwners; i++) {
            if (owners[i].getUsername().equals(inputUsername)) {
                foundUser = true;
                break;
            }
        }

        if (restaurantName.equals("") || inputUsername.equals("") || inputPassword.equals("") || confirmPassword.equals("")) {
            loginFrame.invalidLoginLabelForSignUp1.setText("you must fill all the fields");
            return false;
        } else if (!inputPassword.equals(confirmPassword)) {
            loginFrame.invalidLoginLabelForSignUp1.setText("passwords don't match");
            return false;
        } else if (foundUser) {
            loginFrame.invalidLoginLabelForSignUp1.setText("username already exists");
            return false;

        } else {

            try {
                myConn1 = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");
                myStmt1 = myConn1.createStatement();
                String st = "INSERT INTO owners Values( '" + inputUsername + "','" + inputPassword + "','" + restaurantName + "');";
                String st2 = "insert into restaurants values ('" + restaurantName + "'," + null + "," + null + ");";
                myStmt1.executeUpdate(st2);
                myStmt1.executeUpdate(st);
                owners[Owner.getNumberOfOwners()] = new Owner(inputUsername, inputPassword, restaurantName);
            } catch (SQLException ex) {
                Logger.getLogger(Talabat.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }

    }

    public static Customer[] updateCustomers() {
        Customer newCustomers[] = new Customer[100];
        Connection myConn = null;

        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            // get connection 
            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");

            System.out.println(myConn);

            // 2. Create a statement
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("select * from customers");

            // 4. Process the result set
            int i = 0;
            while (myRs.next()) {
                newCustomers[i] = new Customer(myRs.getString("mobile").toString(), myRs.getString("address").toString(), myRs.getString("Username").toString(), myRs.getString("Password").toString());
                i++;
            }
        } catch (Exception ex) {
            System.out.println("2313");
        }

        return newCustomers;
    }

    public static Owner[] updateOwner() {
        Owner newOwners[] = new Owner[100];

        Connection myConn = null;
        Statement myStmt, myStmt2 = null;
        ResultSet myRs, myRs2 = null;

        try {
            // get connection 
            myConn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RjFI4gANpY", "RjFI4gANpY", "UIY691h8aY");
            // 2. Create a statement
            myStmt = myConn.createStatement();
            myStmt2= myConn.createStatement();
            myRs = myStmt.executeQuery("select * from owners");

            // 4. Process the result set
            int i = 0;
            while (myRs.next()) {
                myRs2 = myStmt2.executeQuery("select * from restaurants where name='" + myRs.getString("restaurantName") + "';");
                Restaurant r = null;
                newOwners[i] = new Owner(myRs.getString("username"), myRs.getString("pass"), myRs.getString("restaurantName"));

                //get resturant information from database
                while (myRs2.next()) {
                    
                    String rName = myRs2.getString("name");
                    String rDescription = myRs2.getString("description");
                    byte[] Iimage = myRs2.getBytes("image");
                    
                    r = new Restaurant(rName, Iimage, rDescription);
                    
                }
                newOwners[i].setRestaurant(r);
                i++;
            }
        } catch (SQLException ex) { 
            Logger.getLogger(Talabat.class.getName()).log(Level.SEVERE, null, ex);
        }

        return newOwners;
    }

    public static void main(String[] args) {

        customers = updateCustomers();
        owners = updateOwner();
        //customers[0]= new Customer("123", "1233","marwan" ,"123" );

        loginFrame = new MainFrame();
        loginFrame.show();
        
        
        

        //ImageIcon i = new ImageIcon(ImageIcon.class.getResource("/pics/burger.jpg"));

        //Meal burger = new Meal("beef burger", "burger gamed", 90F, i);
        //Meal chickenBurger = new Meal("chicken burger", 70F);
        //Meal chickenzft = new Meal("chicken zft", 1F);

        // owners[0]= new Owner("joe", "123", "macOs");
        // owners[1]= new Owner("joe", "123", "burger king");
        // owners[2]= new Owner("joe", "123", "m4 lazm");
        // owners[3]= new Owner("joe", "123", "fakes");
        //owners[4]= new Owner("joe", "123", "fakesss");
//        owners[0].addMeal(burger);
        // owners[0].addMeal(chickenBurger);
        // owners[0].addMeal(chickenzft);
    }

}
