package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {
    private JPanel myPanel;
    private JButton myButton;
    private JTextField element;
    private JPanel result;
    private JScrollPane scroll;
    private JScrollBar scrollBar1;
    int row = 0;

    public Test() {
        System.out.println(myPanel);

        myPanel.setSize(new Dimension(500, 500));
       // scroll.add(result);
        myButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var label = new JLabel("ttoo");
                label.setPreferredSize(new Dimension(10, 10));
                label.setOpaque(true);
                label.setBackground(Color.RED);
                row++;
                var grid = new GridLayout(row, 2, 20, 25);
                scroll = new JScrollPane(result, 20, 30);


                result.setPreferredSize(new Dimension(10, 10));
                result.setLayout(grid);
                //var scrollPane = new JScrollPane(result);


                //scroll.add(result);
               // result.add(scrollBar);
                result.add(label);
                result.revalidate();
            }
        });
    }
    public static void main(String[] args) {
        var test = new Test();
        var home = new JFrame("videotron");
        home.getContentPane().add(test.myPanel);
        home.setPreferredSize(new Dimension(500, 500));
        home.setLocationRelativeTo(null);
        home.pack();
        home.setLocationRelativeTo(null);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setVisible(true);
    }
}
