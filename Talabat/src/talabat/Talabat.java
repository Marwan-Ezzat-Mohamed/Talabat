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
        
        mainFrame = new MainFrame();
        mainFrame.show();
   
    }

}
