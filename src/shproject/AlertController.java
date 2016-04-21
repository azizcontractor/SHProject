package shproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author farhinmomin
 */
public class AlertController implements Initializable {
    @FXML
    private Button mainbtn;
    @FXML
    private Label alertlbl;
    @FXML
    private Label nameE;
    @FXML
    private Label timeE;
    @FXML
    private Button dismissBtn;
    @FXML
    private TextArea descTA;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goMainPage(ActionEvent event) {
    }

    @FXML
    private void handleDismiss(ActionEvent event) {
    }
    
}
