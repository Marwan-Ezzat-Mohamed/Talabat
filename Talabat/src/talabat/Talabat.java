package talabat;

import javax.print.attribute.standard.JobName;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Talabat {

    static MainFrame loginFrame;
    static Customer[] customers = new Customer[100];
    static Owner[] owners = new Owner[100];

    public static boolean login() {
        int numberOfCustomers = Customer.numberOfCustomers;
        int numberOfOwners = Owner.numberOfOwners;

        String inputUsername = loginFrame.usernameTextField.getText();
        String inputPassword = loginFrame.passwordField.getText();

        for (int i = 0; i < numberOfCustomers; i++) {
            if (customers[i].username.equals(inputUsername) && customers[i].password.equals(inputPassword)) {
                System.out.println("Login successful");
                loginFrame.invalidLoginLabel.setText("");
                loginFrame.username.setText(customers[i].username);
                //do some thing;
                return true;

            }
        }

        for (int i = 0; i < numberOfOwners; i++) {
            if (owners[i].username.equals(inputUsername) && owners[i].password.equals(inputPassword)) {
                System.out.println("Login successful");
                loginFrame.invalidLoginLabel.setText("");
                //do some thing;
                return true;

            }
            //System.out.println("Login successful");
            //do some thing;

        }
        loginFrame.invalidLoginLabel.setText("Invalid username or password");
        return false;
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
        if (address.equals("") || inputUsername.equals("") || inputPassword.equals("") || confirmPassword.equals("") || mobile.equals("")) {
            loginFrame.invalidLoginLabelForSignUp.setText("you must fill all the fields");
            return false;
        } else if (!inputPassword.equals(confirmPassword)) {
            loginFrame.invalidLoginLabelForSignUp.setText("passwords don't match");
            return false;
        } else {
           
            try {
                myConn1 = DriverManager.getConnection("jdbc:mysql://sql2.freesqldatabase.com:3306/sql2383521", "sql2383521", "bL5%tX9!");
                myStmt1 = myConn1.createStatement();
                String st="INSERT INTO customers Values( '"+inputUsername+"','"+inputPassword+"','"+mobile+"','"+address+"');";
                System.out.println(st);
                myStmt1.executeUpdate(st);
                customers[Customer.numberOfCustomers] =  new Customer(mobile, address, inputUsername, inputPassword);
            } catch (SQLException ex) {
                Logger.getLogger(Talabat.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }

    }

    public static Customer[] updateCustomers()
    {
        Customer newCustomers[]= new Customer[100];
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            // get connection 
            myConn = DriverManager.getConnection("jdbc:mysql://sql2.freesqldatabase.com:3306/sql2383521", "sql2383521", "bL5%tX9!");
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
            System.out.println("error 404");
        }
        return newCustomers;
    }

    public static void main(String[] args) {
        
        customers =  updateCustomers();
       // System.out.println(customers[0].username);
       // System.out.println(customers[0].password);

        //Testing t = new Testing();
        // t.testingCart();
        Meal y = new Meal("fsdfsd", "fsdf", 14F);
        owners[0] = new Owner("joe", "123", "mac");
        owners[0].addMeal(y);
        
        //jtabelFrame  j = new jtabelFrame();
        //j.show();

         loginFrame = new MainFrame();
        loginFrame.show();
        //Home min = new Home();
        //min.show();

        //Meal_jframe m = new Meal_jframe();
        //m.show();
        //loginFrame.loginButton.addActionListener((e) -> login());
        //loginFrame.SignUpButton.addActionListener((e) -> signUpForCustomer());
    }

}
