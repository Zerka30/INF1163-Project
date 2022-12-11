package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RetourFilmbis extends JFrame {
    private JPanel Retourfilmpanel;
    private JTextField texttest;
    private JLabel Labeltest;
    private JButton buttontest;
    private JLabel labeltest2;

    public int valeurprix;

    public  RetourFilmbis(String title) {

        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Retourfilmpanel);
        this.pack();

        buttontest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //grab the text
                int textrecup=(int)Double.parseDouble(texttest.getText());

                valeurprix=textrecup;

                System.out.println(valeurprix);

                labeltest2.setText("La valeur est changé par "+textrecup);




            }
        });
    }


    public int getValeur() {
        return valeurprix;
    }




}
