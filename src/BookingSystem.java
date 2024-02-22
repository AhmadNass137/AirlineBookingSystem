import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class BookingSystem extends Thread {
    String[] loginInfo = new String[2];
    protected Socket clientSocket;
    static PrintWriter out;
    public static boolean authenticateAdmin(String username, String password) throws IOException {
        boolean valid = false;
        BufferedReader reader = new BufferedReader(new FileReader("C:\\FlightSystem\\Admins.txt"));
        String line;
        while ((line = reader.readLine()) != null && !valid) {
            String[] parts = line.split(": ");
            if (parts[0].equals("Username") && parts[1].equals(username)) {
                line = reader.readLine();
                parts = line.split(": ");
                if (parts[0].equals("Password") && parts[1].equals(password)) {
                    valid = true;
                }
            }
        }
        reader.close();
        return valid;
    }
    public static boolean authenticateUser(String passportID, String password) throws IOException {
        boolean valid = false;
        BufferedReader reader = new BufferedReader(new FileReader("C:\\FlightSystem\\Passengers.txt"));
        String line;
        while ((line = reader.readLine()) != null && !valid) {
            String[] parts = line.split(": ");
            if (parts[0].equals("Passport ID") && parts[1].equals(passportID)) {
                line = reader.readLine();
                parts = line.split(": ");
                if (parts[0].equals("Password") && parts[1].equals(password)) {
                    valid = true;
                }
            }
        }
        reader.close();
        return valid;
    }

    public static void updateCounters(String passportID, String flightID) throws IOException {
        File flights = new File("C:\\FlightSystem\\Flights.txt");
        BufferedReader reader = new BufferedReader(new FileReader(flights));
        String line;
        int numA = -1, numB = -1;
        String classType;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Flight ID")&&parts[1].equals(flightID) ) {
                reader.readLine(); reader.readLine(); reader.readLine(); reader.readLine();
                classType = reader.readLine();
                reader.readLine(); reader.readLine(); reader.readLine();
                if (classType.toLowerCase().contains("high")) {
                    File fileToBeModifiedHigh = new File("C:\\FlightSystem\\Passengers.txt");
                    BufferedReader readerHigh = new BufferedReader(new FileReader(fileToBeModifiedHigh));
                    String lineHigh;
                    while ((lineHigh = readerHigh.readLine()) != null) {
                        String[] partsHigh = lineHigh.split(": ");
                        if (partsHigh[0].equals("Passport ID") && partsHigh[1].equals(passportID)) {
                            lineHigh = readerHigh.readLine();
                            partsHigh = lineHigh.split(": ");
                            break;
                        }
                    }
                    while ((lineHigh = readerHigh.readLine()) != null) {
                        String[] partsHigh = lineHigh.split(": ");
                        if (partsHigh[0].equals("High Class Flights")) {
                            numA = Integer.parseInt(partsHigh[1]);
                            numA++;
                            break;
                        }
                    }
                    while ((lineHigh = readerHigh.readLine()) != null) {
                        String[] partsHigh = lineHigh.split(": ");
                        if (partsHigh[0].equals("Business Class Flights")) {
                            numB = Integer.parseInt(partsHigh[1]);
                            break;
                        }
                    }
                    System.out.println((("High Class Flights: " + numA + "\nBusiness Class Flights: " + numB)));
                    break;
                }
                else if (classType.toLowerCase().contains("business")) {
                    File passengers = new File("C:\\FlightSystem\\Passengers.txt");
                    BufferedReader readerHigh = new BufferedReader(new FileReader(passengers));
                    String lineHigh;
                    while ((lineHigh = readerHigh.readLine()) != null) {
                        String[] partsHigh = lineHigh.split(": ");
                        if (partsHigh[0].equals("Passport ID") && partsHigh[1].equals(passportID)) {
                            lineHigh = readerHigh.readLine();
                            partsHigh = lineHigh.split(": ");
                            break;
                        }
                    }
                    while ((lineHigh = readerHigh.readLine()) != null) {
                        String[] partsHigh = lineHigh.split(": ");
                        if (partsHigh[0].equals("High Class Flights")) {
                            numA = Integer.parseInt(partsHigh[1]);
                            break;
                        }
                    }
                    while ((lineHigh = readerHigh.readLine()) != null) {
                        String[] partsHigh = lineHigh.split(": ");
                        if (partsHigh[0].equals("Business Class Flights")) {
                            numB = Integer.parseInt(partsHigh[1]);
                            numB++;
                            break;
                        }
                    }
                    break;
                }
            }
        }

        BufferedReader userR = new BufferedReader(new FileReader(new File("C:\\FlightSystem\\Passengers.txt")));
        BufferedReader tempR = new BufferedReader(new FileReader(new File ("C:\\FlightSystem\\Temp.txt")));
        FileWriter tempW = new FileWriter("C:\\FlightSystem\\Temp.txt",false);
        String curr;
        while ((curr = userR.readLine()) != null)
            tempW.write(curr + "\n");
        tempW.flush();
        FileWriter userW = new FileWriter("C:\\FlightSystem\\Passengers.txt",false);
        while ((curr = tempR.readLine()) != null) {
            String[] parts = curr.split(": ");
            if (parts.length > 1 && parts[0].equals("Passport ID") && parts[1].equals(passportID)) {
                userW.write(curr + "\n");
                userW.write(tempR.readLine() + "\n");
                userW.write(tempR.readLine() + "\n");
                userW.write(tempR.readLine() + "\n");
                tempR.readLine();
                tempR.readLine();
                System.out.println(numA);
                userW.write("High Class Flights: " + numA + "\n");
                userW.write("Business Class Flights: " + numB + "\n");
            }
            else {
                userW.write(curr + "\n");
            }
            userW.flush();
        }
    }
    public static void recordFlight(String userID, String flightID) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("C:\\FlightSystem\\PreviousFlights.txt", true));
        File flights = new File("C:\\FlightSystem\\Flights.txt");
        BufferedReader reader = new BufferedReader(new FileReader(flights));
        boolean areWeThereYet = false;
        String line;
        out.write("Passport ID: " + userID + "\n");
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Flight ID") && parts[1].equals(flightID))
                areWeThereYet = true;
            else if (parts[0].equals("Flight ID"))
                areWeThereYet = false;
            if (areWeThereYet)
                out.write(line + "\n");
        }
        reader.close();
        out.flush();
        out.close();
    }

    public static void fetchFlight(String ID) throws IOException {
        File fileToBeModified = new File("C:\\FlightSystem\\Flights.txt");
        BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
        boolean areWeThereYet = false;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Flight ID") && parts[1].equals(ID))
                areWeThereYet = true;
            else if (parts[0].equals("Flight ID"))
                areWeThereYet = false;
            if (parts.length > 1 && areWeThereYet)
                out.println(parts[1]);
        }
        reader.close();
    }

    public static void fetchUser(String ID) throws IOException {
        File fileToBeModified = new File("C:\\FlightSystem\\Passengers.txt");
        BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
        boolean areWeThereYet = false;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Passport ID") && parts[1].equals(ID))
                areWeThereYet = true;
            else if (parts[0].equals("Passport ID") || parts[0].equals("High Class Flights"))
                areWeThereYet = false;
            if (parts.length > 1 && areWeThereYet)
                out.println(parts[1]);
        }
        reader.close();
    }
    public static void flightStatement(String userID) throws IOException {
        File history = new File("C:\\FlightSystem\\PreviousFlights.txt");
        BufferedReader reader = new BufferedReader(new FileReader(history));
        boolean areWeThereYet = false;
        int count = 0;
        int TTL = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Passport ID") && parts[1].equals(userID)) {
                TTL = 8;
                count++;
            }
            if (count == 4) break;
            while (TTL > 0) {
                out.println(reader.readLine());
                TTL--;
            }
        }
        out.println("7P8zzW1zR0zE9z4zX5Mz3z6HzBzKzA2zOzYzUzD");
        reader.close();
    }
    public static void addFlight(String id, String deptPlace, String arrvlPlace, String type, ArrayList<String> docs, String classType, String dateAndTime, double cost, String approxTime) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("C:\\FlightSystem\\Flights.txt", true));
        out.write("Flight ID: " + id.toLowerCase() + "\nDeparture Place: " + deptPlace + "\nArrival Place: " + arrvlPlace + "\nType: " + type + "\nRequired Documents: " + docs.toString().substring(1, docs.toString().length() - 1) + "\nClass Type: " + classType + "\nDate and Time: " + dateAndTime + "\nCost: " + cost + "\nApproximate Time: " + approxTime + "\n\n");
        out.flush();
        out.close();
    }

    public static void addUser(String fullName, String passportID, String balance, String password) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("C:\\FlightSystem\\Passengers.txt", true));
        out.write("Passport ID: " + passportID.toLowerCase() + "\nPassword: " + password + "\nBalance: " + balance + "\nFull Name: " + fullName + "\nHigh Class Flights: 0\nBusiness Class Flights: 0");
        out.flush();
        out.close();
    }

    public static void findVisaFlights() throws IOException {
        File fileToBeModified = new File("C:\\FlightSystem\\Flights.txt");
        BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
        String line;
        String flightID, deptPlace, arrvlPlace, type, docs, classType, DAT, cost, approxTime;
        while ((line = reader.readLine()) != null) {
            flightID = "";
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Flight ID")) {
                flightID += line;
                deptPlace = reader.readLine();
                arrvlPlace = reader.readLine();
                type = reader.readLine();
                docs = reader.readLine();
                classType = reader.readLine();
                DAT = reader.readLine();
                cost = reader.readLine();
                approxTime = reader.readLine();
                if (docs.toLowerCase().contains("visa")) {
                    out.println(flightID);
                    out.println(deptPlace);
                    out.println(arrvlPlace);
                    out.println(type);
                    out.println(docs);
                    out.println(classType);
                    out.println(DAT);
                    out.println(cost);
                    out.println(approxTime);
                }
            }
        }
        out.println("0K1zzR4zM3zZ2z7zS8Hz6z9CzWzFzV5zJzTzPzY");
        reader.close();
    }

    public static void findPassportFlights() throws IOException {
        File fileToBeModified = new File("C:\\FlightSystem\\Flights.txt");
        BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
        String line;
        String flightID, deptPlace, arrvlPlace, type, docs, classType, DAT, cost, approxTime;
        while ((line = reader.readLine()) != null) {
            flightID = "";
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Flight ID")) {
                flightID += line;
                deptPlace = reader.readLine();
                arrvlPlace = reader.readLine();
                type = reader.readLine();
                docs = reader.readLine();
                classType = reader.readLine();
                DAT = reader.readLine();
                cost = reader.readLine();
                approxTime = reader.readLine();
                if (!docs.toLowerCase().contains("visa")) {
                    out.println(flightID);
                    out.println(deptPlace);
                    out.println(arrvlPlace);
                    out.println(type);
                    out.println(docs);
                    out.println(classType);
                    out.println(DAT);
                    out.println(cost);
                    out.println(approxTime);
                }
            }
        }
        out.println("0K1zzR4zM3zZ2z7zS8Hz6z9CzWzFzV5zJzTzPzY");
        reader.close();
    }

    public static boolean requiresVisa(String flightID) throws IOException {
        File fileToBeModified = new File("C:\\FlightSystem\\Flights.txt");
        BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Flight ID") && parts[1].equals(flightID)) {
                while ((line = reader.readLine()) != null) {
                    parts = line.split(": ");
                    if (parts.length > 1 && parts[0].equals("Required Documents"))
                        return parts[1].toLowerCase().contains("visa");
                }
            }
        }
        return false;
    }
    public static void updateBalance(String passportID, double newCost, double discount) throws IOException{

        double balance = 0;
        String line;
        BufferedReader usersReader = new BufferedReader(new FileReader(new File("C:\\FlightSystem\\Passengers.txt")));
        while ((line = usersReader.readLine()) != null) {
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Passport ID") && parts[1].equals(passportID)) {
                while ((line = usersReader.readLine()) != null) {
                    parts = line.split(": ");
                    if (parts.length > 1 && parts[0].equals("Balance"))
                        balance = Double.parseDouble(parts[1]);
                }
            }
        }
        System.out.println("balance " + balance + "\tcost " + newCost);
        balance = balance - newCost;
        BufferedReader userR = new BufferedReader(new FileReader(new File("C:\\FlightSystem\\Passengers.txt")));
        BufferedReader tempR = new BufferedReader(new FileReader(new File ("C:\\FlightSystem\\Temp.txt")));
        FileWriter tempW = new FileWriter("C:\\FlightSystem\\Temp.txt",false);
        String curr;
        while ((curr = userR.readLine()) != null)
            tempW.write(curr + "\n");
        tempW.flush();
        FileWriter userW = new FileWriter("C:\\FlightSystem\\Passengers.txt",false);
        while ((curr = tempR.readLine()) != null) {
            String[] parts = curr.split(": ");
            if (parts.length > 1 && parts[0].equals("Passport ID") && parts[1].equals(passportID)) {
                userW.write(curr + "\n");
                userW.write(tempR.readLine() + "\n");
                userW.write(tempR.readLine() + "\n");
                tempR.readLine();
                userW.write("Balance: " + balance + "\n");
                userW.write(tempR.readLine() + "\n");
                userW.write(tempR.readLine() + "\n");
            }
            else {
                userW.write(curr + "\n");
            }
            userW.flush();
        }
    }
    public static boolean flightExists(String id) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\FlightSystem\\Flights.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Flight ID") && parts[1].equals(id))
                return true;
        }
        reader.close();
        return false;
    }

    public static boolean userExists(String id) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\FlightSystem\\Passengers.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Passport ID") && parts[1].equals(id))
                return true;
        }
        reader.close();
        return false;
    }

    static void removeFlight(String flightID) throws IOException {
        File fileToBeModified = new File("C:\\FlightSystem\\Flights.txt");
        String newContent = "";
        BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
        boolean removing = false;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Flight ID") && parts[1].equals(flightID))
                removing = true;
            else if (parts[0].equals("Flight ID"))
                removing = false;
            if (!removing)
                newContent += line + System.lineSeparator();
        }
        reader.close();
        FileWriter writer = new FileWriter(fileToBeModified);
        writer.write(newContent);
        writer.close();
    }
    public static double costAfterDiscount(String passportID, String flightID, BufferedReader in) throws IOException {
        File passengers = new File("C:\\FlightSystem\\Passengers.txt");
        BufferedReader reader = new BufferedReader(new FileReader(passengers));
        String line;
        double discount = 1.0;
        double H, B, cost = 1;
        File fileToBeModifiedHigh = new File("C:\\FlightSystem\\Passengers.txt");
        BufferedReader readerHigh = new BufferedReader(new FileReader(fileToBeModifiedHigh));
        while ((line = readerHigh.readLine()) != null) {
            String[] partsHigh = line.split(": ");
            if (partsHigh.length > 1 && partsHigh[0].equals("Passport ID") && partsHigh[1].equals(passportID)) {
                while ((line = readerHigh.readLine()) != null) {
                    partsHigh = line.split(": ");
                    if (line.equals(""))
                        break;
                    if (partsHigh[0].equals("High Class Flights")) {
                        H = Double.parseDouble(partsHigh[1]);
                        if (H % 3 == 0) {
                            System.out.println(H);
                            System.out.println("50%");
                            discount = 0.5;
                            break;
                        }
                    } else {
                        if (partsHigh[0].equals("Business Class Flights")) {
                            B = Double.parseDouble(partsHigh[1]);
                            System.out.println(B);
                            if (B % 3 == 0) {
                                System.out.println("30%");
                                discount = 0.7;
                            }
                        }
                    }
                }
            }
        }
        File flights = new File("C:\\FlightSystem\\Flights.txt");
        BufferedReader flightsReader = new BufferedReader(new FileReader(flights));
        String linecost;
        while ((linecost = flightsReader.readLine()) != null) {
            String[] partscost = linecost.split(": ");
            if (partscost.length > 1 && partscost[0].equals("Flight ID") && partscost[1].equals(flightID)) {
                while ((line = flightsReader.readLine()) != null) {
                    partscost = line.split(": ");
                    if (line.equals(""))
                        break;
                    if (partscost.length > 1 && partscost[0].equals("Cost"))
                        cost = Double.parseDouble(partscost[1]);
                }
                break;
            }
        }
        if (in.readLine().equals("discount"))
            return cost * discount;
        return cost;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(7000);
            System.out.println("Connection Socket Created on server Socket on port 7000");
            try {
                while (true) {
                    System.out.println("Waiting for Connection");
                    new BookingSystem(serverSocket.accept());
                }
            } catch (IOException e) {
                System.err.println("Accept failed.");
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: 7000.");
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("Could not close port: 7000.");
            }
        }
    }

    private BookingSystem(Socket clientSoc) {
        this.clientSocket = clientSoc;
        start();
    }

    public void run() {
        System.out.println("New Communication Thread Started");
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Server: " + inputLine);
                switch (inputLine) {
                    case "6K7uqR0aM9lZ8y3gS4Hi2n5CdWeFbV1oJjTwPkY" -> {
                        loginInfo[0] = in.readLine();
                        loginInfo[1] = in.readLine();
                        System.out.println(authenticateAdmin(loginInfo[0], loginInfo[1]));
                        if (authenticateAdmin(loginInfo[0], loginInfo[1]))
                            out.println("SuccessfulAdminLogin");
                        else
                            out.println("FailureAdminLogin");
                    }
                    case "5J6tpQ9zL8kY7x2fR3Gh1m4BcVdEaU0nIiSvOjX" -> {
                        loginInfo[0] = in.readLine();
                        loginInfo[1] = in.readLine();
                        System.out.println(authenticateUser(loginInfo[0], loginInfo[1]));
                        if (authenticateUser(loginInfo[0], loginInfo[1]))
                            out.println("SuccessfulUserLogin");
                        else
                            out.println("FailureUserLogin");
                    }
                    case "7L8vrS1bN0mA9z4hT5Ij3o6DeXfGcW2pKkUxQlZ" -> {
                        removeFlight(in.readLine());
                    }
                    case "8M9wtT2cO1nB0a5iU6Jk4p7EfYgHdX3qLlVyRmA" -> {
                        if (flightExists(in.readLine()))
                            out.println("FlightFound");
                        else
                            out.println("FlightNotFound");
                    }
                    case "9N0xuU3dP2oC1b6jV7Kl5q8FgZhIeY4rMmWzSnB" -> {
                        addFlight(in.readLine(), in.readLine(), in.readLine(), in.readLine(), new ArrayList<>(Arrays.asList(in.readLine().split(" - "))), in.readLine(), in.readLine(), Double.parseDouble(in.readLine()), in.readLine());
                    }
                    case "2Q3zxX6gS5rF4e9mY0No8t1IjCkLhB7uPpZzVqE" -> {
                        fetchFlight(in.readLine());
                    }
                    case "0O1yvV4eQ3pD2c7kW8Lm6r9GhAiJfZ5sNnXzToC" -> {
                        if (userExists(in.readLine()))
                            out.println("UserFound");
                        else
                            out.println("UserNotFound");
                    }
                    case "1P2zwW5fR4qE3d8lX9Mn7s0HiBjKgA6tOoYzUpD" -> {
                        addUser(in.readLine(), in.readLine(), in.readLine(), in.readLine());
                    }
                    case "3R4zyY7hT6sG5f0nZ1Op9u2JkDlMiC8vQqAzWrF" -> {
                        System.out.println("Fetching user...");
                        fetchUser(in.readLine());
                    }
                    case "4S5zzZ8iU7tH6g1oA2Pq0v3KlEmNjD9wRrBzXsG" -> {
                        findVisaFlights();
                    }
                    case "5T6zzA9jV8uI7h2pB3Qr1w4LmFnOkE0xSsCzYtH" -> {
                        findPassportFlights();
                    }
                    case "6U7zzB0kW9vJ8i3qC4Rs2x5MnGoPlF1yTtDzZuI" -> {
                        if (requiresVisa(in.readLine()))
                            out.println("VisaRequired");
                        else
                            out.println("PassportRequired");
                    }
                    case "7V8zzC1lX0wK9j4rD5St3y6NoHpQmG2zUuEzAvJ" -> {
                        String userID = in.readLine();
                        String flightID = in.readLine();
                        double newCost = costAfterDiscount(userID, flightID, in);
                        recordFlight(userID, flightID);
                        updateCounters(userID, flightID);
                        updateBalance(userID, newCost, 1.0);
                    }
                    case "0Y1zzF4oA3zN2m7uG8Vw6z9QrKsTpJ5zXxHzDyM" -> {
                        flightStatement(in.readLine());
                    }
                    default -> {
                        System.out.println("Unknown request");
                    }
                }
            }
            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Connection lost");
        }
    }
}