import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterUser extends JFrame  {
    private static RegisterUser registerGUI;
    private JButton createMyAccountButton;
    private JTextField passwordField;
    private JTextField balanceField;
    private JTextField passportIDField;
    private JTextField fullNameField;
    private JLabel fullNameLabel;
    private JLabel PassportID;
    private JLabel passwordLabel;
    public JPanel registerPane;
    private BufferedReader in;
    private PrintWriter out;
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public RegisterUser(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
        registerGUI = this;
        createMyAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fullNameField.getText().equals("") || passportIDField.getText().equals("") || balanceField.getText().equals("") || passwordField.getText().equals(""))
                    JOptionPane.showMessageDialog(registerPane, "Please fill in all the fields before adding this flight.");
                else {
                    if (!isNumeric(balanceField.getText()))
                        JOptionPane.showMessageDialog(registerPane, "Please enter a valid balance (numbers only).");
                    else {
                        out.println("0O1yvV4eQ3pD2c7kW8Lm6r9GhAiJfZ5sNnXzToC");
                        out.println(passportIDField.getText());
                        try {
                            if (in.readLine().equals("UserFound"))
                                JOptionPane.showMessageDialog(registerPane,"This passport ID already exists!");
                            else {
                                out.println("1P2zwW5fR4qE3d8lX9Mn7s0HiBjKgA6tOoYzUpD");
                                out.println(fullNameField.getText());
                                out.println(passportIDField.getText());
                                out.println(balanceField.getText());
                                out.println(passwordField.getText());
                                JOptionPane.showMessageDialog(registerPane, "Account created successfully!");
                                registerGUI.dispose();
                            }
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });
    }
}
