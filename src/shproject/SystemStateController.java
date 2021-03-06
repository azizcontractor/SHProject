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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import objects.AlertEvent;

/**
 * FXML Controller class
 *
 * @author farhinmomin
 */
public class SystemStateController implements Initializable {
    @FXML
    private Label systemStatelbl;
    @FXML
    private Button homebtn;
    @FXML
    private Button awaybtn;
    @FXML
    private Button travelbtn;
    @FXML
    private Label lblcurrStateEmpty;
    @FXML
    private Label lblstate;
    @FXML
    private Button mainbtn;
    
    private SafeHome sh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sh = Context.getInstance().getSafeHome();
        lblcurrStateEmpty.setText(sh.getCurrentState());
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
    private void btnHomeClicked(ActionEvent event) throws IOException {
        Parent homeState = FXMLLoader.load(getClass().getResource("homeStatePage.fxml"));
        Scene homeScene = new Scene(homeState);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setScene(homeScene);
        appStage.show();
    }

    @FXML
    private void btnAwayClicked(ActionEvent event) throws IOException {
        Parent awayState = FXMLLoader.load(getClass().getResource("awayStatePage.fxml"));
        Scene homeScene = new Scene(awayState);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setScene(homeScene);
        appStage.show();
    }

    @FXML
    private void btnTravelClicked(ActionEvent event) throws IOException {
        Parent travelState = FXMLLoader.load(getClass().getResource("travelStatePage.fxml"));
        Scene homeScene = new Scene(travelState);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setScene(homeScene);
        appStage.show();
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
