import java.io.*;

public class TestingSite {
    public static double costAfterDiscount(String passportID, String flightID) throws IOException {
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
        return cost * discount;
    }
    public static void main(String[] args) throws IOException {
        System.out.println(costAfterDiscount("5050","idid"));
    }
}