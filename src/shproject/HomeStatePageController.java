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
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
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
    private TextFieldLimited timeInHr;
    @FXML
    private TextFieldLimited timeInMin;
    @FXML
    private TextFieldLimited timeOutHr;
    @FXML
    private TextFieldLimited timeOutMin;
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
    private SafeHome sh;
    @FXML
    private RadioButton rbActivateNow;
    @FXML
    private RadioButton rbSchedule;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sh = Context.getInstance().getSafeHome();
        btnOk.setDisable(true);

        timeInAmPm.setItems(amPmList);
        
        timeOutAmPm.setItems(amPmList);
        
    }    

    @FXML
    private void handleOK(ActionEvent event) throws IOException{
        StringBuilder timeInStr = new StringBuilder(),timeOutStr = new StringBuilder();
        boolean valid = true;
        boolean good = true;
        if(rbActivateNow.isSelected()){
            sh.setNewState("Home");
        }
        else{
            int x = -1;
            TextField[] fieldsIn = {timeInHr,timeInMin};
            TextField[] fieldsOut = {timeOutHr,timeOutMin};
            for(int i = 0; i < fieldsIn.length; i++){
                try{
                    x = Integer.parseInt(fieldsIn[i].getText());
                }catch(Exception e){
                    emptyLabel.setText("Invalid time!! Please re-enter!");
                    valid = false;
                }
                if(timeInAmPm.getValue().equals("pm") && i == 0)
                    x += 12;
                if(x < 10)
                    timeInStr.append("0");
                timeInStr.append(x);
                timeInStr.append(":");
            }
            timeInStr.append("00");
            for(int i = 0; i < fieldsOut.length;i++){
                try{
                    x = Integer.parseInt(fieldsOut[i].getText());
                }catch(Exception e){
                    emptyLabel.setText("Invalid time!! Please re-enter!");
                    valid = false;
                }
                if(timeOutAmPm.getValue().equals("pm") && i == 0)
                    x += 12;
                if(x < 10)
                    timeOutStr.append("0");
                timeOutStr.append(x);
                timeOutStr.append(":");
            }
            timeOutStr.append("00");
            Timestamp t = new Timestamp(System.currentTimeMillis());
            timeInStr.insert(0, t.toString().substring(0, 11));
            timeOutStr.insert(0, t.toString().substring(0, 11));
            if(Timestamp.valueOf(timeInStr.toString()).getTime() < System.currentTimeMillis()){
                valid = false;
                emptyLabel.setText("Invalid time!! Please re-enter!");
            }
            if(timeInAmPm.getValue().equals("pm") && timeOutAmPm.getValue().equals("am")){
                t = Timestamp.valueOf(timeOutStr.toString() + 86400);
                timeOutStr = new StringBuilder(t.toString());
            }
            if(valid)
                good = sh.scheduleNewState("Home",timeInStr.toString(), timeOutStr.toString());
        }
        if(!good)
            emptyLabel.setText("Another state scheduled!!");
        if(valid && good){
            Parent backSysStageparent = FXMLLoader.load(getClass().getResource("systemState.fxml"));
            Scene date_page_scene = new Scene(backSysStageparent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide(); //optional
            app_stage.setScene(date_page_scene);
            app_stage.show();
        }
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
        BooleanBinding booleanBind = timeInHr.textProperty().isEmpty()
                            .or(timeInMin.textProperty().isEmpty())
                                      .or(timeOutHr.textProperty().isEmpty()
                                      .or(timeOutMin.textProperty().isEmpty())
                                      .or(timeInAmPm.valueProperty().isNull())
                                      .or(timeOutAmPm.valueProperty().isNull()));
        btnOk.disableProperty().bind(booleanBind);
    }

    @FXML
    private void handleActivate(ActionEvent event) {
        btnOk.disableProperty().unbind();
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
