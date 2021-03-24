/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talabat;

import javax.swing.JOptionPane;

public class passwordIsWeakException extends Exception {

    public passwordIsWeakException() {

        super("password must be at least 2 chars");
        JOptionPane.showMessageDialog(null, "password must be at least 2 chars");
    }

}
