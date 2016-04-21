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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import objects.Camera;
import objects.Sensor;

/**
 * FXML Controller class
 *
 * @author Meera
 */
public class LightsController implements Initializable {

    @FXML
    private Label lightslbl;
    @FXML
    private Button mainbtn;
    @FXML
    private Button backbtn;
    
    SafeHome sh;
    @FXML
    private Button btn;
    @FXML
    private ListView<Sensor> list;
    @FXML
    private ListView<String> list2;
    private ObservableList<Sensor> data;
    private ObservableList<String> status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         btn.setDisable(true);
         sh = Context.getInstance().getSafeHome();
         data = FXCollections.observableArrayList(sh.getSensors("Light"));
         status = FXCollections.observableArrayList();
         list.setItems(data);
         for (Sensor s: data){
             status.add(s.getStatus());
             System.out.println("Status = " + s.getStatus());
         }
         list2.setItems(status);
         list2.setMouseTransparent(true);
         list2.setFocusTraversable(false);
         list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Sensor>() {
    @Override
            public void changed(ObservableValue<? extends Sensor> observable, Sensor oldValue, Sensor newValue) {
                btn.setDisable(false);
                if(status.get(list.getSelectionModel().getSelectedIndex()).equals("ON"))
                    btn.setText("Turn OFF");
                else
                    btn.setText("Turn ON");
            }
            });
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
    private void goMainPage(ActionEvent event) throws IOException {
        Parent goMainPage = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene goMainScene = new Scene(goMainPage);
        Stage appStage3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage3.hide();
        appStage3.setScene(goMainScene);
        appStage3.show();    
    }

    @FXML
    private void handleTurn(ActionEvent event) {
        sh.updateSensor(data.get(list.getSelectionModel().getSelectedIndex()));
        status.set(list.getSelectionModel().getSelectedIndex(), data.get(list.getSelectionModel().getSelectedIndex()).getStatus());
        if(status.get(list.getSelectionModel().getSelectedIndex()).equals("ON"))
            btn.setText("Turn OFF");
        else
            btn.setText("Turn ON");
    }
    
}
