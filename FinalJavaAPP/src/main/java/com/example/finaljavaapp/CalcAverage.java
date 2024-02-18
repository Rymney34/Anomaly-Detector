package com.example.finaljavaapp;
import javafx.scene.text.TextFlow;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CalcAverage {

    public static String chosenIpAddress = "";

    public void setIp(String chooseIp) { chosenIpAddress = chooseIp;}


    public static void main(String[] args) {

        String inputCsvFilePath = "src/main/java/com/example/finaljavaapp/otherFiles/TestFull.csv";

        try {
            CalcAverage cal = new CalcAverage();

            Map<String, Map<String, List<Integer>>> data = readCsvData(inputCsvFilePath);

            // Calculate average flow for the chosen IP address
            calculateAverage(data, chosenIpAddress);

            // Print filtered data to console
            cal.printFilteredData();
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found: " + inputCsvFilePath);
        }

    }

    public static Map<String, Map<String, List<Integer>>> data = new LinkedHashMap<>();//creating hash map

    public Map<String, Map<String, List<Integer>>> getData() {
        return data;
    }

    public String getIpp() {

        return chosenIpAddress;
    }

    //reading file
    public static Map<String, Map<String, List<Integer>>> readCsvData(String filePath) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(filePath));

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] fields = line.split(",");

                String date = fields[0];
                String ipAddress = fields[1];
                int flow = Integer.parseInt(fields[3]); // make the  flow column numeric


            data.computeIfAbsent(date, k -> new LinkedHashMap<>())
                        .computeIfAbsent(ipAddress, k -> new ArrayList<>())
                        .add(flow);

            }
            return data;
        }


        //calculating average flow
        public static void calculateAverage (Map < String, Map < String, List < Integer >>> data, String chosenIpAddress)
        {
            for (Map<String, List<Integer>> dateEntry : data.values()) {
                List<Integer> flows = dateEntry.get(chosenIpAddress);

                if (flows != null && !flows.isEmpty()) {
                    int totalFlow = 0;
                    for (int flow : flows) {
                        totalFlow += flow;
                    }
                    int averageFlow = totalFlow / flows.size();

                    dateEntry.put(chosenIpAddress, Collections.singletonList(averageFlow));
                }
            }
        }

        //printing final filtered Data
        public List<String> printFilteredData () {

            List<String> result = new ArrayList<>(92);

            TextFlow textFlow = new TextFlow();

            for (Map.Entry<String, Map<String, List<Integer>>> dateEntry : data.entrySet()) {
                String date = dateEntry.getKey();
                Map<String, List<Integer>> ipEntries = dateEntry.getValue();

                // Print data for the chosen IP address
                if (ipEntries.containsKey(chosenIpAddress)) {
                    int averageFlow = ipEntries.get(chosenIpAddress).get(0);

                    String entry = date + " " + getIpp() + " " + averageFlow;

                    result.add(entry + "\n");

                }

            }
            return result;
        }

}


