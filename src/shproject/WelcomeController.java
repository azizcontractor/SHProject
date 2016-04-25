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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import objects.AlertEvent;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class WelcomeController implements Initializable {
    @FXML
    private Label SHlbl;
    @FXML
    private Button sysStatebtn;
    @FXML
    private Button homeSetbtn;
    @FXML
    private Button viewCambtn;
    private SafeHome sh;
    @FXML
    private MenuButton menu;
    @FXML
    private MenuItem viewAlertLog;
    @FXML
    private MenuItem viewSchedule;
    @FXML
    private AnchorPane pane1;
    @FXML
    private Pane hiddenPane;
    @FXML
    private Button dismiss;
    @FXML
    private ListView<String> list;
    @FXML
    private Label hidPaneTitle;
    @FXML
    private Button outbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sh = Context.getInstance().getSafeHome();
        if(Context.getInstance().alertGen()){
           AlertEvent al = sh.genAlarm();
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Unauthorized Access Alert");
           alert.setHeaderText("Location: " + al.getSensorName() + "\nTime: " + al.getTimeString());
           alert.setContentText(al.getEventDescription());
           alert.showAndWait();  
        } 
    }    

    @FXML
    private void handleSysState(ActionEvent event) throws IOException {
        Parent sysStateParent = FXMLLoader.load(getClass().getResource("systemState.fxml"));
        Scene sysStateScene = new Scene(sysStateParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setScene(sysStateScene);
        appStage.show();
    }

    @FXML
    private void handleHomeSet(ActionEvent event) throws IOException {
       Parent sysHomeSetParent = FXMLLoader.load(getClass().getResource("homeSettings.fxml"));
       Scene sysHomeSetScene = new Scene(sysHomeSetParent);
       Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       appStage.hide();
       appStage.setScene(sysHomeSetScene);
       appStage.show();
    }

    @FXML
    private void handleViewCam(ActionEvent event) throws IOException {
       Parent signOutParent = FXMLLoader.load(getClass().getResource("viewCam.fxml"));
       Scene signOutScene = new Scene(signOutParent);
       Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       appStage.hide();
       appStage.setScene(signOutScene);
       appStage.show();
    }

    @FXML
    private void signOut(ActionEvent event) throws IOException {
        
       Parent signOutParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       Scene signOutScene = new Scene(signOutParent);
       Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       appStage.hide();
       appStage.setScene(signOutScene);
       appStage.show();
      }

    @FXML
    private void viewAlertLog(ActionEvent event) throws IOException {
        hidPaneTitle.setText("Alert Log");
        sh = Context.getInstance().getSafeHome();
        ObservableList<String> data = FXCollections.observableArrayList(sh.getAlerts());
        list.setItems(data);
        menu.setDisable(true);
        SHlbl.setDisable(true);
        sysStatebtn.setDisable(true);
        homeSetbtn.setDisable(true);
        viewCambtn.setDisable(true);
        outbtn.setDisable(true);
        hiddenPane.setVisible(true);
    }

    @FXML
    private void viewSchedule(ActionEvent event) {
        hidPaneTitle.setText("Schedule");
        sh = Context.getInstance().getSafeHome();
        ObservableList<String> data = FXCollections.observableArrayList(sh.getSchedule());
        list.setItems(data);
        menu.setDisable(true);
        SHlbl.setDisable(true);
        sysStatebtn.setDisable(true);
        homeSetbtn.setDisable(true);
        viewCambtn.setDisable(true);
        outbtn.setDisable(true);
        hiddenPane.setVisible(true);
    }

    @FXML
    private void handleDismiss(ActionEvent event) {
        hiddenPane.setVisible(false);
        menu.setDisable(false);
        SHlbl.setDisable(false);
        sysStatebtn.setDisable(false);
        homeSetbtn.setDisable(false);
        viewCambtn.setDisable(false);
        outbtn.setDisable(false);
    }

    
}
