package talabat;

import javax.print.attribute.standard.JobName;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Talabat {

    public static MainFrame loginFrame;
    static Customer[] customers = new Customer[100];
    public static Owner[] owners = new Owner[100];
    public static String currentUser;
    public static int currentUserIndex;
    public static String currentOwner;
    public static int currentOwnerIndex;
   
    public static int login() {
        int numberOfCustomers = Customer.numberOfCustomers;
        int numberOfOwners = Owner.numberOfOwners;

        String inputUsername = loginFrame.usernameTextField.getText();
        String inputPassword = loginFrame.passwordField.getText();

        for (int i = 0; i < numberOfCustomers; i++) {
            if (customers[i].username.equals(inputUsername) && customers[i].password.equals(inputPassword)) {
                System.out.println("Login successful");
                loginFrame.invalidLoginLabel.setText("");
                loginFrame.username.setText(customers[i].username);
                currentUser=customers[i].username;
                currentUserIndex=i;
                //do some thing;
                return 1;

            }
        }

        for (int i = 0; i < numberOfOwners; i++) {
            if (owners[i].username.equals(inputUsername) && owners[i].password.equals(inputPassword)) {
                System.out.println("Login successful");
                loginFrame.invalidLoginLabel.setText("");
                currentOwner=owners[i].username;
                currentOwnerIndex=i;
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
        int numberOfCustomers = Customer.numberOfCustomers;
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
            if (customers[i].username.equals(inputUsername)) {
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
                myConn1 = DriverManager.getConnection("jdbc:mysql://sql2.freesqldatabase.com:3306/sql2383521", "sql2383521", "bL5%tX9!");
                myStmt1 = myConn1.createStatement();
                String st = "INSERT INTO customers Values( '" + inputUsername + "','" + inputPassword + "','" + mobile + "','" + address + "');";
                myStmt1.executeUpdate(st);
                customers[Customer.numberOfCustomers] = new Customer(mobile, address, inputUsername, inputPassword);
            } catch (SQLException ex) {
                Logger.getLogger(Talabat.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }

    }

    public static boolean signUpForOwner() {
        int numberOfOwners = Owner.numberOfOwners;
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
            if (owners[i].username.equals(inputUsername)) {
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
                myConn1 = DriverManager.getConnection("jdbc:mysql://sql2.freesqldatabase.com:3306/sql2383521", "sql2383521", "bL5%tX9!");
                myStmt1 = myConn1.createStatement();
                String st = "INSERT INTO owners Values( '" + inputUsername + "','" + inputPassword + "','" + restaurantName + "');";
                String st2="insert into Restaurant values ('"+restaurantName+"',"+null+","+null+");";
                myStmt1.executeUpdate(st2);
                myStmt1.executeUpdate(st);
                owners[Owner.numberOfOwners] = new Owner(inputUsername, inputPassword, restaurantName);
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
            myConn = DriverManager.getConnection("jdbc:mysql://sql2.freesqldatabase.com:3306/sql2383521", "sql2383521", "bL5%tX9!");

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
        Statement myStmt = null;
        ResultSet myRs = null;
        
        try {
            // get connection 
            myConn = DriverManager.getConnection("jdbc:mysql://sql2.freesqldatabase.com:3306/sql2383521", "sql2383521", "bL5%tX9!");
            // 2. Create a statement
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("select * from owners");

            // 4. Process the result set
            int i = 0;
            while (myRs.next()) {
                newOwners[i] = new Owner(myRs.getString("username"), myRs.getString("password"), myRs.getString("restaurantName"));
                i++;
            }
        } catch (Exception ex) {
            System.out.println("Error 404");
        }
        
        return newOwners;
    }

    public static void main(String[] args) {

        customers = updateCustomers();
        owners = updateOwner();
                
        loginFrame = new MainFrame();
        loginFrame.show();
        
        ImageIcon i = new ImageIcon(ImageIcon.class.getResource("/pics/burger.jpg"));
        
        Meal burger= new Meal("beef burger","burger gamed", 90F,i);
        Meal chickenBurger= new Meal("chicken burger", 70F);
        Meal chickenzft= new Meal("chicken zft", 1F);

        owners[0].addMeal(burger);
        owners[0].addMeal(chickenBurger);
        owners[0].addMeal(chickenzft);
        
            
        
    }

}
