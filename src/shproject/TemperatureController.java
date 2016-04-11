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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private TextField temptext;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void setTemp(ActionEvent event) {
    }

    @FXML
    private void setTemptext(ActionEvent event) {
    }
    
    
    
    
    
    
}
