import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by ankhbayarazzaya on 10/11/15.
 */
public class Snake extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new Snake();
        MyPanel myPanel = new MyPanel();

        frame.add(myPanel);
        frame.setSize(950, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}
