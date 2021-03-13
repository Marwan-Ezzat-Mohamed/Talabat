package talabat;

import java.lang.Exception;

abstract public class User extends Exception {

    private final String username;
    private String password;

    public User(String password, String username) throws Exception {
        if (!setPassword(password)) {
            throw new passwordIsWeakException();
        } else {
            this.password = password;
            this.username = username;
        }

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {

        return password;
    }

    public final boolean setPassword(String password) {
        if (password.length() <= 1) {
            return false;
        }
        this.password = password;
        return true;
    }

    public class passwordIsWeakException extends Exception {

        public passwordIsWeakException() {
            super("password must be at least 8 chars");
        }

    }

}
