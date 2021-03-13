package talabat;


import OwnerPackage.Owner;
import CstomerPackage.Customer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import javax.swing.UIManager;


public class Talabat {

    public static MainFrame mainFrame;
    public static Owner owner;
    public static Customer customer ;
    public static Database database;

    
    public static void changeLookAndFell()
    {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public static void main(String[] args) {
        
        //fadl el ui :D ya mina
        
        //bt8ayar el design
        changeLookAndFell();
        database=new Database();
        mainFrame = new MainFrame();
        mainFrame.show();
   
    }

}
