package view;

import entity.Member;
import model.Service;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateMember {

    private JPanel sheet;
    private JButton createMember;
    private JTextField addressInput;
    private JTextField creditcardInput;
    private JTextField expirationInput;
    private JTextField secretInput;
    private JTextField phoneNumberInput;

    public CreateMember(SessionFactory sessionFactory) {
        // Create function when we press button to create a new member in database
        createMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Create a new object member
                var member = new Member(phoneNumberInput.getText(), addressInput.getText(), creditcardInput.getText(), expirationInput.getText(), secretInput.getText());

                // Create a pop up to with 2 button to confirm or cancel the creation of the member, translate in french
                var confirm = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment créer ce membre ? \n" + member.toString(), "Confirmation", JOptionPane.YES_NO_OPTION);

                // If we press yes, we create the member in database
                if (confirm == JOptionPane.YES_OPTION) {
                    // Clear all input to be able to create a new member
                    phoneNumberInput.setText("");
                    addressInput.setText("");
                    creditcardInput.setText("");
                    expirationInput.setText("année/mois");
                    secretInput.setText("");

                    // Create a new member in database using MemberService
                    var service = new Service(sessionFactory);
                    service.save(member);

                    // Create a pop up to display member secret passsword, translate in french
                    JOptionPane.showMessageDialog(null, "Le membre à bien été créé ! \n" + "Voici le mot de passe du membre : " + member.getSecretCode());

                }

            }
        });
    }

    public JPanel getPanelWindow() {
        return sheet;
    }
}