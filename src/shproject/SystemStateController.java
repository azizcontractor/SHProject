/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shproject;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SafeHome sh = new SafeHome();
        
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
    private void btnAwayClicked(ActionEvent event) {
    }

    @FXML
    private void btnTravelClicked(ActionEvent event) {
    }
    
}
