import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertionSortDemo extends JPanel {
    private int[] array;
    private int currentIndex = -1;
    private int sortedIndex = -1;
    private JButton startbutton;
	
    public InsertionSortDemo(int[] array) {
        this.array = array;
	this.setLayout(null);    
        startbutton = new JButton("short");
	startbutton.setBounds(50,20,150,20);
        startbutton.setFont(new Font("Arial", Font.BOLD, 15));
        startbutton.setFocusPainted(false);    
        startbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInsertionSort(); 
            }
        });
        add(startbutton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int squareSize = 50;  
        for (int i = 0; i < array.length; i++) {
            if (i == currentIndex) {
                g.setColor(Color.GREEN);
            } else if (i <= sortedIndex) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLUE);
            }
            g.fillRect(i * (squareSize + 10) + 10, getHeight() / 2 - squareSize / 2, squareSize, squareSize);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(String.valueOf(array[i]));
            int textHeight = fm.getAscent();
            g.drawString(String.valueOf(array[i]), i * (squareSize + 10) + 10 + (squareSize - textWidth) / 2, 
                         getHeight() / 2 - squareSize / 2 + (squareSize + textHeight) / 2);
        }
    }

    public void showInsertionSort() {
        new Thread(() -> {
            for (int i = 1; i < array.length; i++) {
                int key = array[i];
                currentIndex = i;
                int j = i - 1;
                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j--;
                    sortedIndex = j + 1;
                    repaint();
                    sleep();
                }
                array[j + 1] = key;
                sortedIndex = i;
                repaint();
                sleep();
            }
            currentIndex = -1;
            sortedIndex = -1;
            repaint();
        }).start();
    }

    private void sleep() {
        try {
            Thread.sleep(1000);  
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Insertion Sort Demo");
        int[] array = {100, 350, 200, 50, 500, 150};
        InsertionSortDemo sort = new InsertionSortDemo(array);
        frame.add(sort);
        frame.setSize(600, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
