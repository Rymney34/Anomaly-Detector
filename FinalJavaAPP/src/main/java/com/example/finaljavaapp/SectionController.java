package com.example.finaljavaapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;

public class SectionController {
    private String stringForPrint1;

    @FXML
    public Button saveButton;

    @FXML
    public TextFlow textflow1;

    @FXML
    public ScrollPane scroll = new ScrollPane();

    @FXML
    //method that adds scroll to the textflow
    public void add_scroll() {

        ScrollBar sp = new ScrollBar();

        scroll.setContent(textflow1);

    }

    //Addign information to text flow
    @FXML
    public void add_info1(String infoList) {

        Text s = new Text(infoList);
        s.setFont(new Font("Arial", 14));

        textflow1.getChildren().add(s);
    }

    public SectionMain sectionMain= new SectionMain();

    //Method that displays and information to textflow  and the writes it to txt file which will be as a copy
    public void sendSection(){

        sectionMain.loadFile();
        stringForPrint1 = sectionMain.loadFile().toString().replaceAll("[\\s\\[\\]]", "  ").replaceAll(",", "\n");
        add_info1(stringForPrint1);
        add_scroll();

        saveButton.setOnAction(eventt -> {

            try {
                FileWriter myWriter = new FileWriter("src/main/java/com/example/finaljavaapp/SavedStatistics/StatisticsForJuly.txt");
                myWriter.write("       Date " + "  Ip" +  "  Remote ASN "+ " Flows " +"\n");
                myWriter.write(stringForPrint1);

                myWriter.close();

                Stage stage = (Stage) saveButton.getScene().getWindow();

                stage.close();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}
