import javax.swing.*;

/**
 * Created by ankhbayarazzaya on 10/11/15.
 */
public class Snake extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new Snake();
        frame.add(new MyPanel());
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}

