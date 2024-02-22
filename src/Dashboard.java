import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Dashboard {

    public static void main(String[] args) throws IOException {
        String serverHostname = "127.0.0.1";
        System.out.println("Attempting to connect to host " + serverHostname + " on port 7000.");
        Socket echoSocket = new Socket(serverHostname, 7000);
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        Login login = new Login(in, out);
        login.setContentPane(login.mainPanel);
        login.setTitle("Login");
        login.setSize(400,300);
        login.setBounds(550,250,500,400);
        login.setVisible(true);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
