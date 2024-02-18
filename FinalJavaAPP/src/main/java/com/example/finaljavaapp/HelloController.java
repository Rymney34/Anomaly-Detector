package com.example.finaljavaapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class HelloController {

    @FXML
    public MenuItem choosing;

    @FXML
    public MenuItem openSce;

    @FXML
    public MenuItem displ1;



    @FXML
    //File chooser
    public void Button1Action (ActionEvent event) {
        FileChooser filechoo = new FileChooser();
        File file = filechoo.showOpenDialog(null);
    }




    @FXML
    public Button exit;





    @FXML
    //Methods make the CalculateScene open when button pressed in menu
    void initialize(){
        initialize1();//opening the Scene/pane with section of the traffic for July
        openSce.setOnAction(even -> {
            openSce.getParentPopup().hide();
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("/com/example/finaljavaapp/CalculateScene.fxml"));

            try{
                Parent root = loader2.load();
                CalcController callc = loader2.getController();

                openNewStage(root, callc);




            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }

    public void openNewStage(Parent root, CalcController calculateSceneController) {

        Stage stage1 = new Stage();

        calculateSceneController.send();


        stage1.setScene(new Scene(root));

        stage1.show();

        calculateSceneController.send2();

    }
    public SectionController secCon = new SectionController();
    @FXML
    // Methods make the section of the traffic open when button pressed in menu
    void initialize1() {

        SectionMain secMain = new SectionMain();
        displ1.setOnAction(event2 ->{
            displ1.getParentPopup().hide();
            FXMLLoader loader3 = new FXMLLoader();
            loader3.setLocation(getClass().getResource("/com/example/finaljavaapp/SectionOf.fxml"));
            Parent root1;

            try{
                root1 = loader3.load();
                secCon = loader3.getController();

                Stage stage = new Stage();
                stage.setScene(new Scene(root1));

                stage.show();

                secCon.sendSection();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

}