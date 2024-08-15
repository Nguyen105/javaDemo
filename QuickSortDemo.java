import javax.swing.*;
import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 

public class QuickSortDemo extends JPanel {
    private int[] array; 
    private int lowIndex = -1;
    private int highIndex = -1;
    private int pivotIndex = -1;
    private int currentLow = -1;
    private int currentHigh = -1;
    private JButton startbutton;
	
    public QuickSortDemo(int[] array) {
        this.array = array;
	this.setLayout(null);    
        startbutton = new JButton("short");
	startbutton.setBounds(50,20,150,20);
        startbutton.setFont(new Font("Arial", Font.BOLD, 15));
        startbutton.setFocusPainted(false);    
        startbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQuickSort(); 
            }
        });
        add(startbutton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int squareSize = 50;  
        for (int i = 0; i < array.length; i++) {
            if (i == pivotIndex) {
                g.setColor(Color.ORANGE);  
            } else if (i >= lowIndex && i <= highIndex) {
                if (i == currentLow) {
                    g.setColor(Color.GREEN);  
                } else if (i == currentHigh) {
                    g.setColor(Color.RED);  
                } else {
                    g.setColor(Color.CYAN); 
                }
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

    public void showQuickSort() {
        new Thread(() -> {
            quickSort(0, array.length - 1);
            resetIndices();
            repaint();
        }).start();
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        pivotIndex = high;
        int pivot = array[high];
        currentLow = low;
        currentHigh = high - 1;
        while (currentLow <= currentHigh) {
            while (currentLow <= currentHigh && array[currentLow] <= pivot) {
                currentLow++;
            }
            while (currentLow <= currentHigh && array[currentHigh] > pivot) {
                currentHigh--;
            }
            if (currentLow < currentHigh) {
                swap(currentLow, currentHigh);
            }
            repaint();
            sleep();
        }
        swap(currentLow, high);
        repaint();
        sleep();
        return currentLow;
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void resetIndices() {
        lowIndex = -1;
        highIndex = -1;
        pivotIndex = -1;
        currentLow = -1;
        currentHigh = -1;
    }

    private void sleep() {
        try {
            Thread.sleep(1500);  
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Quick Sort Demo");
        int[] array = {100, 350, 200, 250, 500, 150, 50, 80};
        QuickSortDemo sort = new QuickSortDemo(array);
        frame.add(sort);
        frame.setSize(600, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
