package view;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConnectToAdmin extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel labelPassword;
    private JTextField passwordField;

    private final String passwordAdmin;

    public ConnectToAdmin() {
        passwordAdmin = initPasswordAdmin();


        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private String initPasswordAdmin() {
        var properties = new Properties();
        try {
            var input = new FileInputStream(getClass().getClassLoader().getResource("program.cfg").getFile());
            properties.load(input);
        } catch (FileNotFoundException e) {
            return "1234";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        return properties.getProperty("password");
    }

    private void onOK() {
        if (passwordAdmin.equals(passwordField.getText())) {
            dispose();
            //otherframe
        }
        else {
            var message = "<html>Mauvais mot de passe <br />" + labelPassword.getText();
            labelPassword.setText(message);
        }
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        ConnectToAdmin dialog = new ConnectToAdmin();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
