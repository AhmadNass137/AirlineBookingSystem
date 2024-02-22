import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IncrementFlights {

    public static void main(String[] args) {
        String inputFile = "C:\\FlightSystem\\Passengers.txt";
        String outputFile = "updated_passengers.txt";
        String regex = "High Class Flights: \\d+";
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(outputFile));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.matches(regex)) {
                    String[] parts = line.split(": ");
                    int flights = Integer.parseInt(parts[1]) + 1;
                    String updatedFlights = Integer.toString(flights);
                    writer.write(parts[0] + ": " + updatedFlights + "\n");
                } else
                    writer.write(line + "\n");
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
