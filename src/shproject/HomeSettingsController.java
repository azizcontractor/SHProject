/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Meera
 */
public class HomeSettingsController implements Initializable {

    @FXML
    private Button tempbtn;
    @FXML
    private Button lightsbtn;
    @FXML
    private Button doorsbtn;
    @FXML
    private Button devicesbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    private void manageTemp(ActionEvent event) throws IOException {
        Parent manageTempParent = FXMLLoader.load(getClass().getResource("Temperature.fxml"));
        Scene tempScene = new Scene(manageTempParent);
        Stage appStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage1.hide();
        appStage1.setScene(tempScene);
        appStage1.show();
    }
    
    @FXML
     private void manageLights(ActionEvent event) throws IOException {
        Parent manageLightsParent = FXMLLoader.load(getClass().getResource("Lights.fxml"));
        Scene LightScene = new Scene(manageLightsParent);
        Stage appStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage1.hide();
        appStage1.setScene(LightScene);
        appStage1.show();
    }
    
    /*private void manageDoors(ActionEvent event )throws IOException {
        Parent manageDoorsParent = FXMLLoader.load(getClass().getResource("Doors.fxml"));
        Scene DoorScene = new Scene(manageDoorsParent);
        Stage appStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage1.hide();
        appStage1.setScene(DoorScene);
        appStage1.show();
               
    }    */
    
    
}
