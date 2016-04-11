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
public class HomeStatePageController implements Initializable {
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private ToggleGroup group1;
    @FXML
    private Label lblHome;
    @FXML
    private TextField dateMonthH;
    @FXML
    private TextField dateDayH;
    @FXML
    private TextField dateYearH;
    @FXML
    private TextField timeHourH;
    @FXML
    private TextField timeMinH;
    @FXML
    private TextField timeAM_PMH;
    @FXML
    private RadioButton rbHome_ActivateNow;
    @FXML
    private RadioButton rbHome_Schedule;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleOK(ActionEvent event) throws IOException{
        
        Parent backSysStageparent = FXMLLoader.load(getClass().getResource("systemState.fxml"));
        Scene date_page_scene = new Scene(backSysStageparent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(date_page_scene);
        app_stage.show();
    }

    @FXML
    private void handleCancel(ActionEvent event) throws IOException{
        Parent backSysStageparent = FXMLLoader.load(getClass().getResource("systemState.fxml"));
        Scene date_page_scene = new Scene(backSysStageparent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(date_page_scene);
        app_stage.show();
    }


    @FXML
    private void handleSchedule(ActionEvent event) {
        dateMonthH.setDisable(false);
        dateDayH.setDisable(false);
        dateYearH.setDisable(false);
        timeHourH.setDisable(false);
        timeMinH.setDisable(false);
        timeAM_PMH.setDisable(false);
        
        
    }

    @FXML
    private void handleActivate(ActionEvent event) {
        dateMonthH.setDisable(true);
        dateDayH.setDisable(true);
        dateYearH.setDisable(true);
        timeHourH.setDisable(true);
        timeMinH.setDisable(true);
        timeAM_PMH.setDisable(true);
    }

    @FXML
    private void setMonth(ActionEvent event) {
    }
    
}
