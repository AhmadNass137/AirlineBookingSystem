import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ModifyFlight extends JFrame {
    private ModifyFlight modifyFlightGUI;
    public JPanel modifyFlightPanel;
    public JButton modifyFlightInfoButton;
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
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public ModifyFlight(BufferedReader in, PrintWriter out, String oldID, String oldDeptPlace, String oldArrvlPlace, String oldType, String oldDocs, String oldClassType, String oldDateAndTime, String oldCost, String oldApproxTime) {
        modifyFlightGUI = this;
        idField.setText(oldID);
        deptPlaceField.setText(oldDeptPlace);
        arrvlPlaceField.setText(oldArrvlPlace);
        typeField.setText(oldType);
        docsField.setText(oldDocs.substring(1, oldDocs.length() - 1).replace(","," -"));
        classTypefield.setText(oldClassType);
        dateAndTimeField.setText(oldDateAndTime);
        costField.setText(oldCost);
        approxTimeField.setText(oldApproxTime);
        modifyFlightInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idField.getText().equals("") || deptPlaceField.getText().equals("") || arrvlPlaceField.getText().equals("") || typeField.getText().equals("") || docsField.getText().equals("") || classTypefield.getText().equals("") || dateAndTimeField.getText().equals("") || approxTimeField.getText().equals(""))
                    JOptionPane.showMessageDialog(modifyFlightGUI, "Please fill in all the fields before modifying this flight.");
                else {
                    if (!isNumeric(costField.getText()))
                        JOptionPane.showMessageDialog(modifyFlightGUI, "Please enter a valid cost (numbers only).");
                    else {
                        out.println("8M9wtT2cO1nB0a5iU6Jk4p7EfYgHdX3qLlVyRmA");
                        out.println(idField.getText());
                        try {
                            if (in.readLine().equals("FlightFound"))
                                JOptionPane.showMessageDialog(modifyFlightGUI,"A flight with this Flight ID already exists!");
                            else {
                                out.println("7L8vrS1bN0mA9z4hT5Ij3o6DeXfGcW2pKkUxQlZ");
                                out.println(oldID);
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
                                JOptionPane.showMessageDialog(modifyFlightPanel, "Flight updated successfully");
                                modifyFlightGUI.dispose();
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
