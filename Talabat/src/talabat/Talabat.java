package talabat;

public class Talabat {

    static LoginAndSignUpFrame loginFrame;
    static Customer[] customers = new Customer[3];

    static Owner[] owners = new Owner[3];

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

        customers[0] = new Customer("noran", "123");
        customers[1] = new Customer("nour", "123");
        customers[2] = new Customer("habiba", "143243242jhgrfherfs45y53462345223");
        owners[0] = new Owner("marwan", "12345", "mac");
        owners[1] = new Owner("joe", "12345", "mac2");
        owners[2] = new Owner("mina", "12345", "mac3");

        loginFrame.loginButton.addActionListener((e) -> login());

    }

}
