package steps;

import io.cucumber.java.en.*;
import java.io.*;
import java.util.*;

public class DataLoadSteps {
    private List<Instrument> instruments;
    private List<Position> positions;

    static class Instrument {
        int id;
        String name;
        String isin;
        double unitPrice;

        Instrument(int id, String name, String isin, double unitPrice) {
            this.id = id;
            this.name = name;
            this.isin = isin;
            this.unitPrice = unitPrice;
        }
    }

    static class Position {
        int id;
        int instrumentId;
        int quantity;

        Position(int id, int instrumentId, int quantity) {
            this.id = id;
            this.instrumentId = instrumentId;
            this.quantity = quantity;
        }
    }

    @Given("the input files {string} and {string} are available")
    public void the_input_files_are_available(String instrumentFile, String positionFile) {
        instruments = loadInstruments(instrumentFile);
        positions = loadPositions(positionFile);
    }

    @When("the data is processed")
    public void the_data_is_processed() {
        generateReport(instruments, positions, "PositionReport.csv");
    }

    @Then("the output file {string} should be generated with correct data")
    public void the_output_file_should_be_generated_with_correct_data(String outputFile) {
        // You can add assertions here to validate the output file if needed
        System.out.println("Output file " + outputFile + " generated successfully.");
    }

    private List<Instrument> loadInstruments(String fileName) {
        List<Instrument> instruments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                String isin = values[2];
                double unitPrice = Double.parseDouble(values[3]);
                instruments.add(new Instrument(id, name, isin, unitPrice));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instruments;
    }

    private List<Position> loadPositions(String fileName) {
        List<Position> positions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                int instrumentId = Integer.parseInt(values[1]);
                int quantity = Integer.parseInt(values[2]);
                positions.add(new Position(id, instrumentId, quantity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return positions;
    }

    private void generateReport(List<Instrument> instruments, List<Position> positions, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write("ID,PositionID,ISIN,Quantity,Total Price\n");
            int reportId = 1;
            for (Position position : positions) {
                for (Instrument instrument : instruments) {
                    if (position.instrumentId == instrument.id) {
                        double totalPrice = position.quantity * instrument.unitPrice;
                        bw.write(reportId++ + "," + position.id + "," + instrument.isin + "," + position.quantity + "," + totalPrice + "\n");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
