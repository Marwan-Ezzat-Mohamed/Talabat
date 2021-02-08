package talabat;

import javax.print.attribute.standard.JobName;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import static talabat.Talabat.customer;

public class Talabat {

    public static MainFrame mainFrame;
    public static Owner owner;
    public static Customer customer ;
    public static Database database= new Database();

   
    public static void main(String[] args) {
        
        //fadl fl database a5ly order y3ml save ll info bta3t el meal kolha fl tabel 34an lw el meal at8yrt mtt8yr4 fl prev orders
        //fadl el ui
        
        //bt8ayar el design
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        mainFrame = new MainFrame();
        mainFrame.show();
   
    }

}
