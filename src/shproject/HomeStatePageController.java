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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    
    ObservableList<String> amPmList = FXCollections.observableArrayList("am", "pm");
    
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;
    @FXML
    private ToggleGroup group1;
    @FXML
    private Label lblHome;
    @FXML
    private RadioButton rbHome_ActivateNow;
    @FXML
    private RadioButton rbHome_Schedule;
    @FXML
    private TextField timeInHr;
    @FXML
    private TextField timeInMin;
    @FXML
    private TextField timeOutHr;
    @FXML
    private TextField timeOutMin;
    @FXML
    private Label lblTimeIn;
    @FXML
    private Label lblTimeOut;
    @FXML
    private ComboBox<String> timeInAmPm;
    @FXML
    private ComboBox<String> timeOutAmPm;
    @FXML
    private Label emptyLabel;
    @FXML
    private Label colonLbl;
    @FXML
    private Label colonLbl1;
    @FXML
    private Label lblcurrStateEmpty;
    SafeHome sh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnOk.setDisable(true);

        timeInAmPm.setItems(amPmList);
        
        timeOutAmPm.setItems(amPmList);
        
    }    

    @FXML
    private void handleOK(ActionEvent event) throws IOException{
        sh = Context.getInstance().getSafeHome();
        sh.setNewState("Home");
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
        btnOk.setDisable(true);
        timeInHr.setDisable(false);
        timeInMin.setDisable(false);
        timeOutHr.setDisable(false);
        timeOutMin.setDisable(false);
        timeInAmPm.setDisable(false);
        timeOutAmPm.setDisable(false);
        
        
    }

    @FXML
    private void handleActivate(ActionEvent event) {
        btnOk.setDisable(false);
        timeInHr.setDisable(true);
        timeInMin.setDisable(true);
        timeOutHr.setDisable(true);
        timeOutMin.setDisable(true);
        timeInAmPm.setDisable(true);
        timeOutAmPm.setDisable(true);
    }

    @FXML
    private void handleTimeInAmPm(ActionEvent event) {
    }

    @FXML
    private void handleTimeOutAmPm(ActionEvent event) {
    }



    
}
