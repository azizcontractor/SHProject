package shproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import control.SafeHome;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import jdk.nashorn.internal.runtime.Context;
import objects.AlertEvent;


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
    private SafeHome sh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sh = control.Context.getInstance().getSafeHome();
        descTA.setFocusTraversable(false);
        descTA.setMouseTransparent(true);
        AlertEvent al = sh.genAlarm();
        nameE.setText(al.getSensorName());
        timeE.setText(al.getTimeString());
        descTA.setText(al.getEventDescription());
    }    

    @FXML
    private void goMainPage(ActionEvent event) {
    }

    @FXML
    private void handleDismiss(ActionEvent event) {
    }
    
}
