import javax.swing.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class Admin extends JFrame {
    private Admin adminGUI;
    public static String name;
    public JButton addFlightButton;
    public JButton removeFlightButton;
    public JButton modifyFlightButton;
    public JPanel mainPanel;
    private JLabel welcomeLabel;
    private JTextField flightIDField;
    private JLabel enterIDLabel;
    public AddFlight addFlight;
    public ModifyFlight modifyFlight;
    public Admin(BufferedReader in, PrintWriter out, String name) {
        Admin.name = name;
        adminGUI = this;
        welcomeLabel.setText("Welcome, " + name + "!");
        addFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFlight = new AddFlight(in, out);
                addFlight.setContentPane(addFlight.addFlightPanel);
                addFlight.setTitle("Add Flight");
                addFlight.setSize(400,300);
                addFlight.setBounds(550,250,500,400);
                addFlight.setVisible(true);
                addFlight.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        removeFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flightIDField.getText().equals(""))
                    JOptionPane.showMessageDialog(adminGUI,"Please enter a valid flight ID");
                else {
                    out.println("8M9wtT2cO1nB0a5iU6Jk4p7EfYgHdX3qLlVyRmA");
                    out.println(flightIDField.getText());
                    try {
                        if (in.readLine().equals("FlightNotFound"))
                            JOptionPane.showMessageDialog(adminGUI, "Please enter a valid flight ID");
                        else {
                            out.println("7L8vrS1bN0mA9z4hT5Ij3o6DeXfGcW2pKkUxQlZ");
                            out.println(flightIDField.getText());
                        }
                    } catch (IOException ex) { throw new RuntimeException(ex); }
                }
            }
        });
        modifyFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flightIDField.getText().equals(""))
                    JOptionPane.showMessageDialog(adminGUI,"Please enter a valid flight ID");
                else {
                    out.println("8M9wtT2cO1nB0a5iU6Jk4p7EfYgHdX3qLlVyRmA");
                    out.println(flightIDField.getText());
                    try {
                        if (in.readLine().equals("FlightNotFound"))
                            JOptionPane.showMessageDialog(adminGUI,"Please enter a valid flight ID");
                        else {
                            out.println("2Q3zxX6gS5rF4e9mY0No8t1IjCkLhB7uPpZzVqE");
                            out.println(flightIDField.getText());
                            modifyFlight = new ModifyFlight(in, out, in.readLine(), in.readLine(), in.readLine(), in.readLine(), in.readLine(), in.readLine(), in.readLine(), in.readLine(), in.readLine());
                            modifyFlight.setContentPane(modifyFlight.modifyFlightPanel);
                            modifyFlight.setTitle("Modify Flight");
                            modifyFlight.setSize(400,300);
                            modifyFlight.setBounds(550,250,500,400);
                            modifyFlight.setVisible(true);
                            modifyFlight.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
}