import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
public class Login extends JFrame{
    private static Login loginGUI;
    private JButton pressHereBroButton;
    public JPanel mainPanel;
    private JLabel welcomeLabel;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JRadioButton adminButton;
    private JButton registerButton;
    private static BufferedReader in = null;
    private static PrintWriter out = null;
    public Login(BufferedReader in, PrintWriter out)  {
        Login.in = in;
        Login.out = out;
        loginGUI = this;
        loginButton.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (adminButton.isSelected()) {
                    out.println("6K7uqR0aM9lZ8y3gS4Hi2n5CdWeFbV1oJjTwPkY");
                    out.println(usernameField.getText());
                    out.println(passwordField.getPassword());
                }
                else {
                    out.println("5J6tpQ9zL8kY7x2fR3Gh1m4BcVdEaU0nIiSvOjX");
                    out.println(usernameField.getText());
                    out.println(passwordField.getPassword());
                }
                try {
                    String lol = in.readLine();
                    System.out.println(lol);
                    switch (lol) {
                        case ("SuccessfulAdminLogin") -> {
                            loginGUI.dispose();
                            Admin admin = new Admin(in, out, usernameField.getText());
                            admin.setContentPane(admin.mainPanel);
                            admin.setTitle("Admin page");
                            admin.setSize(400,300);
                            admin.setBounds(550,250,500,400);
                            admin.setVisible(true);
                            admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        }
                        case ("SuccessfulUserLogin") -> {
                            loginGUI.dispose();
                            out.println("3R4zyY7hT6sG5f0nZ1Op9u2JkDlMiC8vQqAzWrF");
                            out.println(usernameField.getText());
                            in.readLine();
                            in.readLine();
                            User user = new User(in, out, usernameField.getText(), in.readLine(),in.readLine());
                            user.setContentPane(user.mainPanel);
                            user.setTitle("User page");
                            user.setSize(400, 300);
                            user.setBounds(550, 250, 500, 400);
                            user.setVisible(true);
                            user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        }
                        case ("FailureAdminLogin") ->
                                JOptionPane.showMessageDialog(null, "Login as admin unsuccessful :(");
                        case ("FailureUserLogin") ->
                                JOptionPane.showMessageDialog(null, "Login as user unsuccessful :(");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterUser reg = new RegisterUser(in, out);
                reg.setContentPane(reg.registerPane);
                reg.setTitle("Create an account");
                reg.setSize(400,300);
                reg.setBounds(550,250,500,400);
                reg.setVisible(true);
                reg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}