import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionSortDemo extends JPanel {
    private int[] array;
    private int currentIndex = -1;
    private int selectedMinIndex = -1;
    private JButton startbutton;

    public SelectionSortDemo(int[] array) {
        this.array = array;
	this.setLayout(null);    
        startbutton = new JButton("short");
	startbutton.setBounds(50,20,150,20);
        startbutton.setFont(new Font("Arial", Font.BOLD, 15));
        startbutton.setFocusPainted(false);    
        startbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSelectionSort(); 
            }
        });
        add(startbutton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < array.length; i++) {
            if (i == selectedMinIndex) {
                g.setColor(Color.RED);
            } else if (i == currentIndex) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.BLUE);
            }
            g.fillRect(i * 60 + 10, getHeight() - array[i] - 30, 40, array[i]);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(array[i]), i * 60 + 20, getHeight() - 10);
        }
    }

    public void showSelectionSort() {
        new Thread(() -> {
            for (int i = 0; i < array.length - 1; i++) {
                currentIndex = i;
                selectedMinIndex = i;
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j] < array[selectedMinIndex]) {
                        selectedMinIndex = j;
                    }
                    repaint();
                    sleep();
                }
                swap(i, selectedMinIndex);
                repaint();
                sleep();
            }
            currentIndex = -1;
            selectedMinIndex = -1;
            repaint();
        }).start();
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void sleep() {
        try {
            Thread.sleep(500);  
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Selection Sort Demo");
        int[] array = {100, 350, 200, 150, 500, 50};
        SelectionSortDemo sort = new SelectionSortDemo(array);
        frame.add(sort);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
