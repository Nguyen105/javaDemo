import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrafficLight extends JPanel {
    private static final int DIAMETER = 100;
    private static final int DELAY_3_SECONDS = 3000;
    private static final int DELAY_1_SECOND = 1000;
    private Color[] colors = {Color.GRAY, Color.GRAY, Color.GRAY};
    private int currentIndex = 0;
    private boolean movingDown = true;

    public TrafficLight() {
        setPreferredSize(new Dimension(200, 450));
        Timer timer = new Timer(DELAY_3_SECONDS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColors();
                repaint();
            }
        });
        Timer oneSecondTimer = new Timer(DELAY_1_SECOND, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColorsYellow();
                repaint();
            }
        });
        timer.start();
        oneSecondTimer.start();
    }

    private void changeColors() {
        if (movingDown) {
            if (currentIndex == 0) {
                colors[0] = Color.RED;
                colors[1] = Color.GRAY;
                colors[2] = Color.GRAY;
                currentIndex++;
            } else if (currentIndex == 1) {
                colors[0] = Color.GRAY;
                colors[1] = Color.YELLOW;
                colors[2] = Color.GRAY;
                currentIndex++;
            } else {
                colors[0] = Color.GRAY;
                colors[1] = Color.GRAY;
                colors[2] = Color.GREEN;
                currentIndex--;
                movingDown = false;
            }
        } else {
            if (currentIndex == 1) {
                colors[0] = Color.GRAY;
                colors[1] = Color.YELLOW;
                colors[2] = Color.GRAY;
                currentIndex--;
            } else if (currentIndex == 0) {
                colors[0] = Color.RED;
                colors[1] = Color.GRAY;
                colors[2] = Color.GRAY;
                currentIndex++;
                movingDown = true;
            }
        }
    }

    private void changeColorsYellow() {
        if (!movingDown) {
            if (currentIndex == 2) {
                colors[0] = Color.GRAY;
                colors[1] = Color.GRAY;
                colors[2] = Color.GREEN;
                currentIndex--;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = 50;
        for (int i = 0; i < 3; i++) {
            g.setColor(colors[i]);
            g.fillOval(50, y, DIAMETER, DIAMETER);
            y += 120;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("java Traffic Light");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new TrafficLight());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
