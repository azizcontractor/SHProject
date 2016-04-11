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
import javafx.scene.control.Label;
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
    private Label lblDate;
    @FXML
    private Label lbltime;
    @FXML
    private Button btnOK;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField dateMonthA;
    @FXML
    private TextField dateDayA;
    @FXML
    private TextField dateYearA;
    @FXML
    private TextField timeHrA;
    @FXML
    private TextField timeMinA;
    @FXML
    private ToggleGroup group2;
    @FXML
    private TextField timeAm_Pm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleOK(ActionEvent event) throws IOException  {
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
        dateMonthA.setDisable(true);
        dateDayA.setDisable(true);
        dateYearA.setDisable(true);
        timeHrA.setDisable(true);
        timeMinA.setDisable(true);
        //time
        
    }

    @FXML
    private void handleAway_Schedule(ActionEvent event) {
    }
    
}
