package CustomModels;
import OwnerPackage.Restaurant;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import static talabat.MainFrame.allRestaurantsImageMap;

public class allRestaurantsListRenderModel extends DefaultListCellRenderer {

        Font font = new Font("helvitica", Font.BOLD, 20);

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);

            Restaurant r = allRestaurantsImageMap.get((String) value);

            if (r != null) {
                if (allRestaurantsImageMap.get((String) value).getImage() != null) {
                    ImageIcon image = new ImageIcon(new ImageIcon(allRestaurantsImageMap.get((String) value).getImage()).getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH));
                    label.setIcon(image);

                } else {
                    ImageIcon image = new ImageIcon("src/pics/no_photo.png");
                    label.setIcon(image);
                }
                String labelText = null;
                if (r.getDescription() == null) {
                    labelText = "<html> <div style='text-align: center;'>" + r.getName() + "</div></html>";
                } else {
                    labelText = "<html> <div style='text-align: center;'>" + r.getName() + "<br>" + r.getDescription() + "</div></html>";
                }
                label.setText(labelText);
            }

            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);

            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.BOTTOM);
            label.setFont(font);

            return label;
        }

    }