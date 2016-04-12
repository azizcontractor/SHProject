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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author farhinmomin
 */
public class AwayStatePageController implements Initializable {
    @FXML
    private Label lblaway;
    @FXML
    private RadioButton rbA_activateNow;
    @FXML
    private RadioButton rbA_schedule;
    @FXML
    private Button btnCancel;
    @FXML
    private ToggleGroup group2;
    private TextField timeAm_Pm;
    @FXML
    private Label lblTimeIn;
    @FXML
    private Label lblTimeOut;
    @FXML
    private TextField timeInHr;
    @FXML
    private TextField timeInMin;
    @FXML
    private TextField timeOutHr;
    @FXML
    private TextField timeOutMin;
    @FXML
    private MenuButton timeInAmPm;
    @FXML
    private MenuItem timeInPm;
    @FXML
    private MenuItem timeOutAM;
    @FXML
    private MenuItem timeOutPm;
    @FXML
    private MenuItem timeInAm;
    @FXML
    private MenuButton timeOutAmPm;
    @FXML
    private Button btnOk;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnOk.setDisable(true);
        // TODO
    }    

    @FXML
    private void handleOK(ActionEvent event) throws IOException  {
        SafeHome sh = new SafeHome();
        sh.setNewState("Away");
        Parent backSysStageparent = FXMLLoader.load(getClass().getResource("systemState.fxml"));
        Scene date_page_scene = new Scene(backSysStageparent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(date_page_scene);
        app_stage.show();
    }

    @FXML
    private void handleCancel(ActionEvent event)throws IOException {
        Parent backSysStageparent = FXMLLoader.load(getClass().getResource("systemState.fxml"));
        Scene date_page_scene = new Scene(backSysStageparent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(date_page_scene);
        app_stage.show();
    }

    @FXML
    private void handleAway_ActivateNow(ActionEvent event) {
        btnOk.setDisable(false);
        timeInHr.setDisable(true);
        timeInMin.setDisable(true);
        timeOutHr.setDisable(true);
        timeOutMin.setDisable(true);
        timeInAmPm.setDisable(true);
        timeOutAmPm.setDisable(true);
       
        
    }

    @FXML
    private void handleAway_Schedule(ActionEvent event) {
        btnOk.setDisable(true);
        timeInHr.setDisable(false);
        timeInMin.setDisable(false);
        timeOutHr.setDisable(false);
        timeOutMin.setDisable(false);
        timeInAmPm.setDisable(false);
        timeOutAmPm.setDisable(false);
    }
    
}
