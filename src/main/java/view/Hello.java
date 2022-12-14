package view;

import javax.swing.*;
import java.awt.*;

public class Hello {
    private  JPanel panel1;
    private JPanel test;

    public Hello() {

        var grid = new GridLayout(20, 1);
        test.setLayout(grid);

        for (var i = 0; i < 50; i++) {
            var button = new JButton("test " + i);
            button.setSize(new Dimension(100, 50));
            button.setOpaque(true);
            test.add(button);
        }
        var jsp = new JScrollPane(test, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel1.add(jsp);
        test.revalidate();
        panel1.revalidate();
    }

    public static void main(String[] args) {
        var test = new Hello();
        var home = new JFrame("videotron");
        home.getContentPane().add(test.panel1);
        home.setPreferredSize(new Dimension(500, 500));
        home.setLocationRelativeTo(null);
        home.pack();
        home.setLocationRelativeTo(null);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setVisible(true);
    }
}
