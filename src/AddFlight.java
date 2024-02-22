import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AddFlight extends JFrame {
    private AddFlight addFlightGUI;
    public JPanel addFlightPanel;
    public JButton addFlightInfoButton;
    private JTextField idField;
    private JLabel idLabel;
    private JTextField deptPlaceField;
    private JLabel deptPlaceLabel;
    private JLabel docsLabel;
    private JLabel costLabel;
    private JTextField arrvlPlaceField;
    private JTextField typeField;
    private JTextField docsField;
    private JTextField classTypefield;
    private JTextField dateAndTimeField;
    private JTextField costField;
    private JTextField approxTimeField;
    private JLabel arrvlPlaceLabel;
    private JLabel typeLabel;
    private JLabel classTypeLabel;
    private JLabel dateAndTimeLabel;
    private JLabel approxTimeLabel;
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public AddFlight(BufferedReader in, PrintWriter out) {
        addFlightGUI = this;
        addFlightInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idField.getText().equals("") || deptPlaceField.getText().equals("") || arrvlPlaceField.getText().equals("") || typeField.getText().equals("") || docsField.getText().equals("") || classTypefield.getText().equals("") || dateAndTimeField.getText().equals("") || approxTimeField.getText().equals(""))
                    JOptionPane.showMessageDialog(addFlightGUI, "Please fill in all the fields before adding this flight.");
                else {
                    if (!isNumeric(costField.getText()))
                        JOptionPane.showMessageDialog(addFlightGUI, "Please enter a valid cost (numbers only).");
                    else {
                        out.println("8M9wtT2cO1nB0a5iU6Jk4p7EfYgHdX3qLlVyRmA");
                        out.println(idField.getText());
                        try {
                            if (in.readLine().equals("FlightFound"))
                                JOptionPane.showMessageDialog(addFlightGUI,"A flight with this Flight ID already exists!");
                            else {
                                out.println("9N0xuU3dP2oC1b6jV7Kl5q8FgZhIeY4rMmWzSnB");
                                out.println(idField.getText());
                                out.println(deptPlaceField.getText());
                                out.println(arrvlPlaceField.getText());
                                out.println(typeField.getText());
                                out.println(docsField.getText());
                                out.println(classTypefield.getText());
                                out.println(dateAndTimeField.getText());
                                out.println(costField.getText());
                                out.println(approxTimeField.getText());
                                JOptionPane.showMessageDialog(addFlightPanel, "Flight added successfully");
                                addFlightGUI.dispose();
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
