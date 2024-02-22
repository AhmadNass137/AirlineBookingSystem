import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class User extends JFrame {
    private JLabel welcomeLabel;
    private JLabel balanceLabel;
    public JPanel mainPanel;
    private JButton requestFlightStatementButton;
    private JButton showVisaFlightsButton;
    private JButton showPassportFlightsButton;
    private JButton bookPassportFlightButton;
    private JButton bookVisaFlightButton;
    private JTextField flightIDField;
    private JLabel FlightIDLabel;
    private JRadioButton discountButton;
    public static String fullName;
    public static double balance;
    public static String name;
    public static String passportID;
    private static BufferedReader in;
    private static PrintWriter out;
    public static User userGUI;
    public User(BufferedReader in, PrintWriter out, String passportID, String name, String balance) {
        User.passportID = passportID;
        User.name = name;
        User.balance = Double.parseDouble(balance);
        User.in = in;
        User.out = out;
        userGUI = this;
        welcomeLabel.setText("Welcome, " + User.name);
        balanceLabel.setText("Your current balance is " + User.balance);
        showVisaFlightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("4S5zzZ8iU7tH6g1oA2Pq0v3KlEmNjD9wRrBzXsG");
                String flights = "";
                String reader;
                try {
                    while (!((reader = in.readLine()).equals("0K1zzR4zM3zZ2z7zS8Hz6z9CzWzFzV5zJzTzPzY"))) {
                        flights += reader + "\n";
                        if (reader.contains("Approximate Time:"))
                            flights += "\n";
                    }
                    JOptionPane.showMessageDialog(mainPanel, flights);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        showPassportFlightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("5T6zzA9jV8uI7h2pB3Qr1w4LmFnOkE0xSsCzYtH");
                String flights = "";
                String reader;
                try {
                    while (!((reader = in.readLine()).equals("0K1zzR4zM3zZ2z7zS8Hz6z9CzWzFzV5zJzTzPzY"))) {
                        flights += reader + "\n";
                        if (reader.contains("Approximate Time:"))
                            flights += "\n";
                    }
                    JOptionPane.showMessageDialog(mainPanel, flights);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        bookPassportFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flightIDField.getText().equals(""))
                    JOptionPane.showMessageDialog(mainPanel, "Please enter the flight ID first.");
                else {
                    out.println("8M9wtT2cO1nB0a5iU6Jk4p7EfYgHdX3qLlVyRmA");
                    out.println(flightIDField.getText());
                    try {
                        if (!in.readLine().equals("FlightFound"))
                            JOptionPane.showMessageDialog(mainPanel, "No such flight exists!");
                        else {
                            out.println("6U7zzB0kW9vJ8i3qC4Rs2x5MnGoPlF1yTtDzZuI");
                            out.println(flightIDField.getText());
                            if (in.readLine().equals("VisaRequired"))
                                JOptionPane.showMessageDialog(mainPanel, "Sorry, this flight requires a Visa.");
                            else {
                                out.println("7V8zzC1lX0wK9j4rD5St3y6NoHpQmG2zUuEzAvJ");
                                out.println(passportID);
                                out.println(flightIDField.getText());
                                if (discountButton.isSelected())
                                    out.println("discount");
                                else
                                    out.println("cost");
                                JOptionPane.showMessageDialog(mainPanel, "Flight booked successfully!");
                            }
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        bookVisaFlightButton.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                if (flightIDField.getText().equals(""))
                    JOptionPane.showMessageDialog(mainPanel, "Please enter the flight ID first.");
                else {
                    out.println("8M9wtT2cO1nB0a5iU6Jk4p7EfYgHdX3qLlVyRmA");
                    out.println(flightIDField.getText());
                    try {
                        if (!in.readLine().equals("FlightFound"))
                            JOptionPane.showMessageDialog(mainPanel, "No such flight exists!");
                        else {
                            out.println("6U7zzB0kW9vJ8i3qC4Rs2x5MnGoPlF1yTtDzZuI");
                            out.println(flightIDField.getText());
                            if (!in.readLine().equals("VisaRequired"))
                                JOptionPane.showMessageDialog(mainPanel, "This flight doesn't require a Visa.");
                            else {
                                out.println("7V8zzC1lX0wK9j4rD5St3y6NoHpQmG2zUuEzAvJ");
                                out.println(passportID);
                                out.println(flightIDField.getText());
                                if (discountButton.isSelected())
                                    out.println("discount");
                                else
                                    out.println("cost");
                                JOptionPane.showMessageDialog(mainPanel, "Flight booked successfully!");
                            }
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        requestFlightStatementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String history = "";
                out.println("0Y1zzF4oA3zN2m7uG8Vw6z9QrKsTpJ5zXxHzDyM");
                out.println(passportID);
                String reader;
                try {
                    while (!((reader = in.readLine()).equals("7P8zzW1zR0zE9z4zX5Mz3z6HzBzKzA2zOzYzUzD"))) {
                        history += reader + "\n";
                        if (reader.contains("Approximate Time:"))
                            history += "\n";
                    }
                    JOptionPane.showMessageDialog(mainPanel, history);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
