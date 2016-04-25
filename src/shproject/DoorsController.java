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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import objects.AlertEvent;
import objects.Sensor;

/**
 * FXML Controller class
 *
 * @author Meera
 */
public class DoorsController implements Initializable {

    @FXML
    private Label doorlbl;
    @FXML
    private Button mainbtn;
    @FXML
    private Button backbtn;

    private SafeHome sh; 
    @FXML
    private ListView<Sensor> list;
    @FXML
    private ListView<String> list2;
    @FXML
    private Label statusLbl;
    @FXML
    private Button btn;
    private ObservableList<Sensor> data;
    private ObservableList<String> status;
    @FXML
    private Label hiddenlabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         btn.setDisable(true);
         sh = Context.getInstance().getSafeHome();
         if(Context.getInstance().alertGen()){
           AlertEvent al = sh.genAlarm();
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Unauthorized Access Alert");
           alert.setHeaderText("Location: " + al.getSensorName() + "\nTime: " + al.getTimeString());
           alert.setContentText(al.getEventDescription());
           alert.showAndWait();  
        } 
         data = FXCollections.observableArrayList(sh.getSensors("Access"));
         status = FXCollections.observableArrayList();
         list.setItems(data);
         for (Sensor s: data){
             status.add(s.getStatus());
         }
         list2.setItems(status);
         list2.setMouseTransparent(true);
         list2.setFocusTraversable(false);
         list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Sensor>() {
    @Override
            public void changed(ObservableValue<? extends Sensor> observable, Sensor oldValue, Sensor newValue) {
                btn.setDisable(false);
                if(status.get(list.getSelectionModel().getSelectedIndex()).equals("LOCKED"))
                    btn.setText("UNLOCK");
                else
                    btn.setText("LOCK");
            }
            });
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
    private void handleTurn(ActionEvent event) {
        boolean stateC = sh.updateSensor(data.get(list.getSelectionModel().getSelectedIndex()));
        hiddenlabel.setText(null);
        if(stateC)
            hiddenlabel.setText("System state switched to Home");
        status.set(list.getSelectionModel().getSelectedIndex(), data.get(list.getSelectionModel().getSelectedIndex()).getStatus());
        if(status.get(list.getSelectionModel().getSelectedIndex()).equals("LOCKED"))
            btn.setText("UNLOCK");
        else
            btn.setText("LOCK");
    }
    
}
