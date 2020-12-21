package talabat;

public class Talabat {

    static LoginAndSignUpFrame loginFrame;
    static Customer[] customers = new Customer[4];

    static Owner[] owners = new Owner[3];

    public static void login() {
        int numberOfCustomers = Customer.numberOfCustomers;
        int numberOfOwners = Owner.numberOfOwners;

        ////System.out.println(customers[3].username);

        String inputUsername = loginFrame.usernameTextField.getText().toString();
        String inputPassword = loginFrame.passwordField.getText().toString();

        boolean validLogin = false;
        for (int i = 0; i < numberOfCustomers; i++) {
            if (customers[i].username.equals(inputUsername) && customers[i].password.equals(inputPassword)) {
                validLogin = true;
                //System.out.println("Login successful");
                //do some thing;
            }
        }

        for (int i = 0; i < numberOfOwners; i++) {
            if (owners[i].username.equals(inputUsername) && owners[i].password.equals(inputPassword)) {
                validLogin = true;
                //System.out.println("Login successful");
                //do some thing;
            }
        }

        if (!validLogin) {
            loginFrame.invalidLoginLabel.setText("Invalid username or password");
            return;
        } else {
            loginFrame.invalidLoginLabel.setText("");
            return;
        }

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
        //Testing t = new Testing();
        // t.testingCart();
        loginFrame = new LoginAndSignUpFrame();
        loginFrame.show();
        //Home min = new Home();
        //min.show();

        customers[0] = new Customer("1", "s1", "noran", "123");
        customers[1] = new Customer("2", "s2", "nour", "123");
        customers[2] = new Customer("3", "s3", "habiba", "34214324324325432bgrfberg34526");
        owners[0] = new Owner("marwan", "12345", "mac");
        owners[1] = new Owner("joe", "12345", "mac2");
        owners[2] = new Owner("mina", "12345", "mac3");

        loginFrame.loginButton.addActionListener((e) -> login());
        loginFrame.SignUpButton.addActionListener((e) -> signUpForCustomer());

    }

}
