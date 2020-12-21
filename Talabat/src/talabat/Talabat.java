package talabat;

public class Talabat {

    static LoginAndSignUpFrame loginFrame;
    static Customer[] customers = new Customer[2];

    static Owner[] owners = new Owner[2];

    public static void login() {
        int numberOfCustomers = Customer.numberOfCustomers;
        int numberOfOwners = Owner.numberOfOwners;

        String inputUsername = loginFrame.usernameTextField.getText().toString();
        String inputPassword = loginFrame.passwordField.getText().toString();
 
        boolean validLogin = false;
        for (int i = 0; i < numberOfCustomers; i++) {
            if (customers[i].username.equals(inputUsername) && customers[i].password.equals(inputPassword)) {
                validLogin = true;
                //do some thing;
            }
        }

        for (int i = 0; i < numberOfOwners; i++) {
            if (owners[i].username.equals(inputUsername) && owners[i].password.equals(inputPassword)) {
                validLogin = true;
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

    public static void main(String[] args) {
        //Testing t = new Testing();
        // t.testingCart();
        loginFrame = new LoginAndSignUpFrame();
        loginFrame.show();

        customers[0] = new Customer("marwan", "123");
        customers[1] = new Customer("joe", "123");
        owners[0] = new Owner("marwan", "12345", "mac");
        owners[1] = new Owner("jow", "12345", "mac2");

        loginFrame.loginButton.addActionListener((e) -> login());

    }

}
