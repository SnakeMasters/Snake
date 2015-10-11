import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ankhbayarazzaya on 10/11/15.
 */
public class MyPanel extends JPanel {
    public Timer timer;
    int dx = 10;

    public MyPanel() {
        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillOval(100, 100, 2 * dx, 2 * dx);
        dx += 10;
        if (dx > 200) dx = 10;
    }
}
