package talabat;

import javax.print.attribute.standard.JobName;
import java.sql.*;

public class Talabat {

    static LoginAndSignUpFrame loginFrame;
    static Customer[] customers = new Customer[100];
    static Owner[] owners = new Owner[100];

    public static boolean login() {
        int numberOfCustomers = Customer.numberOfCustomers;
        int numberOfOwners = Owner.numberOfOwners;

        String inputUsername = loginFrame.usernameTextField.getText().toString();
        String inputPassword = loginFrame.passwordField.getText().toString();
 
        
        for (int i = 0; i < numberOfCustomers; i++) {
            if (customers[i].username.equals(inputUsername) && customers[i].password.equals(inputPassword)) {
                System.out.println("Login successful");
                loginFrame.invalidLoginLabel.setText("");
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

    public static void signUpForCustomer() {
        int numberOfCustomers = Customer.numberOfCustomers;

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
            return;
        } else if (!inputPassword.equals(confirmPassword)) {
            loginFrame.invalidLoginLabelForSignUp.setText("passwords don't match");
            return;
        } else {
            customers[numberOfCustomers++] = new Customer(mobile, address, inputUsername, inputPassword);
            //System.out.println(inputUsername + numberOfCustomers);
        }

    }

    public static void main(String[] args) {

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            // get connection 
            myConn = DriverManager.getConnection("jdbc:mysql://sql2.freesqldatabase.com:3306/sql2383521", "sql2383521", "bL5%tX9!");
            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            myRs = myStmt.executeQuery("select * from customers");

            // 4. Process the result set
            int i = 0;
            while (myRs.next()) {
                customers[i] = new Customer(myRs.getString("mobile").toString(), myRs.getString("address").toString(), myRs.getString("Username").toString(), myRs.getString("Password").toString());
                i++;
            }
        } catch (Exception ex) {
            System.out.println("error 404");
        }
        System.out.println(customers[0].username);
        System.out.println(customers[0].password);

        //Testing t = new Testing();
        // t.testingCart();
        Meal y = new Meal("fsdfsd", "fsdf", 14F);
        owners[0] = new Owner("joe", "123", "mac");
        owners[0].addMeal(y);

        loginFrame = new LoginAndSignUpFrame();
        loginFrame.show();
        //Home min = new Home();
        //min.show();

        //Meal_jframe m = new Meal_jframe();
        //m.show();
        //loginFrame.loginButton.addActionListener((e) -> login());
        //loginFrame.SignUpButton.addActionListener((e) -> signUpForCustomer());
    }

}
