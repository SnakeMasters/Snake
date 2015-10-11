import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by ankhbayarazzaya on 10/11/15.
 */
public class MyPanel extends JPanel implements KeyListener {

    int delay = 50;
    Timer timer;
    boolean firstDraw = false;
    boolean isOver = false;

    int[][] board = new int[Consts.height / Consts.nodeHeight][Consts.weight / Consts.nodeWeight];
    int foodx, foody;
    int direction = 1;
    /*
    right - 1
    down - 2
    left - 3
    up - 4
     */
    ArrayList<Point> body = new ArrayList<Point>();


    public MyPanel() {
        addKeyListener(this);
        setFocusable(true);

        timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();

        for (int j = 10; j <= 69; j++) {
            Point point = new Point(30, j);
            board[30][j] = 1;
            body.add(point);
        }
        setNewFood();
    }

    void setNewFood() {
        do {
            foodx = (int) (Math.random() * 60);
            foody = (int) (Math.random() * 80);
        } while (board[foodx][foody] == 1);
        board[foodx][foody] = 3;
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(25, 25, Consts.weight, Consts.height);

        g.setColor(Color.RED);
        g.fillRect(foody * Consts.nodeHeight + 25, foodx * Consts.nodeHeight + 25,
                Consts.nodeWeight, Consts.nodeHeight);

        if (!firstDraw) {
            //g.fillRect(25, 25, Consts.weight, Consts.height);
            g.setColor(Color.YELLOW);
            for (Point point : body) {
                g.fillRect(point.y * Consts.nodeHeight + 25, point.x * Consts.nodeWeight + 25,
                        Consts.nodeWeight, Consts.nodeHeight);
            }


            firstDraw = true;
        } else {
            int n = body.size();
            Point head = body.get(n - 1);
            Point tail = body.get(0);
            Point newHead = new Point(head.x, head.y);

            if (direction == 1) {
                if (newHead.y + 1 < Consts.weight / Consts.nodeWeight)
                    newHead.y++;
                else
                    newHead.y = 0;
            }
            if (direction == 2) {
                if (newHead.x + 1 < Consts.height / Consts.nodeHeight)
                    newHead.x++;
                else
                    newHead.x = 0;
            }
            if (direction == 3) {
                if (newHead.y - 1 >= 0)
                    newHead.y--;
                else
                    newHead.y = Consts.weight / Consts.nodeWeight - 1;
            }
            if (direction == 4) {
                if (newHead.x - 1 >= 0)
                    newHead.x--;
                else
                    newHead.x = Consts.height / Consts.nodeHeight - 1;
            }

            if (board[newHead.x][newHead.y] == 3) {
                board[newHead.x][newHead.y] = 1;
                body.add(newHead);
                setNewFood();
            } else {
                board[tail.x][tail.y] = 0;
                body.remove(tail);

                if (board[newHead.x][newHead.y] == 0) {
                    board[newHead.x][newHead.y] = 1;
                    body.add(newHead);
                } else
                    isOver = true;
            }

            g.setColor(Color.YELLOW);
            for (Point point : body) {
                g.fillRect(point.y * Consts.nodeHeight + 25, point.x * Consts.nodeWeight + 25,
                        Consts.nodeWeight, Consts.nodeHeight);
            }
            if (isOver)
                System.exit(0);
        }

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT: {
                if (direction != 3)
                    direction = 1;
                break;
            }
            case KeyEvent.VK_DOWN: {
                if (direction != 4)
                    direction = 2;
                break;
            }
            case KeyEvent.VK_LEFT: {
                if (direction != 1)
                    direction = 3;
                break;
            }
            case KeyEvent.VK_UP: {
                if (direction != 2)
                    direction = 4;
                break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {

    }
}
