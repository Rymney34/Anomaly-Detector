package com.example.finaljavaapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CalcController {

    private String stringForPrint;
    public CalcAverage calc = new CalcAverage();
    @FXML
    public TextField textFi;

    @FXML
    public ScrollPane scroll = new ScrollPane();

    @FXML
    public TextFlow textflow = new TextFlow();

    @FXML
    public Button calcu1;

    public Button saveAver1;

   HelloController hello = new HelloController();

    @FXML
    public void add_text(String text) {
        Text s = new Text(text + "\n");
        textflow.getChildren().add(s);

    }

    @FXML
    // add_info show the information in textflow
    public void add_info(String infoList) {
        Text s = new Text(infoList + "\n");
        s.setFont(new Font("Arial", 14));
        textflow.getChildren().add(s);
    }

    @FXML
    //add scroll to textflow
    public void add_scroll() {

        ScrollBar sp = new ScrollBar();

        scroll.setContent(textflow);

    }

    //when button pressed calculation for the average flow should be taken from CalcAverage and hsowing all result in textflow
    public void send () {
        calcu1.setOnAction(e -> {
            calc.setIp(textFi.getText());

            CalcAverage calc = new CalcAverage();

            Map<String, Map<String, List<Integer>>> data = calc.getData();


            String ip = calc.getIpp();

            try {
                calc.readCsvData("src/main/java/com/example/finaljavaapp/otherFiles/TestFull.csv");
            } catch (FileNotFoundException et) {
                System.out.println("CSV file not found.");
            }

            calc.calculateAverage(calc.getData(), calc.getIpp());

            add_scroll();


            calc.printFilteredData();
            stringForPrint = calc.printFilteredData().toString().replaceAll("[\\s\\[\\]]", "    ").replaceAll(",", "\n");
            add_info(stringForPrint);//aading to textflow

            textFi.clear();


        });
    }

        //Saving result by making a copy//
        public void send2(){
            saveAver1.setOnAction(e2 ->{

                try {
                    FileWriter myWriter = new FileWriter("src/main/java/com/example/finaljavaapp/SavedStatistics/AverageStatistics.txt");
                    myWriter.write("       Date " + "      Ip" +  "    Average flows " +"\n");
                    myWriter.write(stringForPrint);

                    myWriter.close();

                    Stage stage1 = (Stage) saveAver1.getScene().getWindow();

                    stage1.close();


                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }

}
