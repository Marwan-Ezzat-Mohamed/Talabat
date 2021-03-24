package talabat;

import java.lang.Exception;

abstract public class User {

    private final String username;
    private String password;

    public User(String password, String username) throws Exception {
        setPassword(password);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {

        return password;
    }

    
    public final void setPassword(String password) throws Exception {
        if (password.length() <= 1) {
            throw new passwordIsWeakException();
        }
        this.password = password;
    }
    
    
    

}
