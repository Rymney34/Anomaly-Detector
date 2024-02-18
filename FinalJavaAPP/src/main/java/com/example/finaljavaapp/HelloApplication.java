package com.example.finaljavaapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class HelloApplication extends Application {
    TextFlow text1;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        HelloController con = new HelloController();
        con = fxmlLoader.getController();

        stage.setTitle("Anomaly Detector");

        stage.setScene(scene);

        stage.show();

        //When button pressed window closes
        con.exit.setOnAction(event01 -> {
            stage.close();
        });

    }

    public static void main(String[] args) {
        launch();

    }
}