/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shproject;

import control.Context;
import control.SafeHome;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import objects.AlertEvent;

/**
 * FXML Controller class
 *
 * @author Meera
 */
public class CurrentHomeStatusController implements Initializable {
    
    SafeHome sh;

    @FXML
    private Label CurrentStatuslbl;
    @FXML
    private Button mainbtn;
    @FXML
    private Button CSystemState;
    @FXML
    private Button lightStatuslist;
    @FXML
    private Button doorStatuslist;
    @FXML
    private Button tempbtn1;
    @FXML
    private Button deviceStatuslist;
    @FXML
    private SplitPane btnSet;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         sh = Context.getInstance().getSafeHome();
         if(Context.getInstance().alertGen()){
           AlertEvent al = sh.genAlarm();
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Unauthorized Access Alert");
           alert.setHeaderText("Location: " + al.getSensorName() + "\nTime: " + al.getTimeString());
           alert.setContentText(al.getEventDescription());
           alert.showAndWait();  
        } 
    }    

    @FXML
    private void goMainPage(ActionEvent event) throws IOException {
        Parent goMainPage = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene goMainScene = new Scene(goMainPage);
        Stage appStage3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage3.hide();
        appStage3.setScene(goMainScene);
        appStage3.show(); 

        
        
        
        
        
        
        
        
    }
    
}
