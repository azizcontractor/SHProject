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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.AlertEvent;

/**
 * FXML Controller class
 *
 * @author Meera
 */
public class TemperatureController implements Initializable {

    @FXML
    private Button backbtn;
    @FXML
    private Button setbtn;
    @FXML
    private TextFieldLimited temptext;
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
        if(Context.getInstance().alertGen()){
           AlertEvent al = sh.genAlarm();
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Unauthorized Access Alert");
           alert.setHeaderText("Location: " + al.getSensorName() + "\nTime: " + al.getTimeString());
           alert.setContentText(al.getEventDescription());
           alert.showAndWait();  
        } 
        temptext.setText(sh.showCurrentTemp());
    }    
    
     @FXML
    private void goBack(ActionEvent event) throws IOException {
        Parent goBackParent = FXMLLoader.load(getClass().getResource("homeSettings.fxml"));
        Scene goBackScene = new Scene(goBackParent);
        Stage appStage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage2.hide();
        appStage2.setScene(goBackScene);
        appStage2.show();
    }

    @FXML
    private void setTemp(ActionEvent event) throws IOException{
        SafeHome sh = new SafeHome();
        sh.setTemp(Integer.parseInt(temptext.getText()));
        Parent goBackParent = FXMLLoader.load(getClass().getResource("homeSettings.fxml"));
        Scene goBackScene = new Scene(goBackParent);
        Stage appStage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage2.hide();
        appStage2.setScene(goBackScene);
        appStage2.show();
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
