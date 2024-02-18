package com.example.finaljavaapp;

import javafx.scene.text.TextFlow;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SectionMain {

    public static void main(String[] args) {
        SectionMain section = new SectionMain();

        section.loadFile();
    }
    //Reading the file and sorting the file for july and printing all information for this month
    public List<String> loadFile() {

        List<String> sectionResult = new ArrayList<>(92);

        try {
            File myObj = new File("src/main/java/com/example/finaljavaapp/otherFiles/TestFull.csv");
            Scanner myReader = new Scanner(myObj);
            String[] fields;
            String line;
            String targetMonth = "07";

            while (myReader.hasNextLine()) {
                line = myReader.nextLine();
                fields = line.split(",");

                String date = fields[0];

                if (date.startsWith(targetMonth + "/")) {

                    String formatted = String.format("%-11s%-4s%-6s%-1s", fields[0], fields[1], fields[2], fields[3]);
                    sectionResult.add(formatted);
                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        return sectionResult;
    }
}