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
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import objects.Camera;

/**
 * FXML Controller class
 *
 * @author farhinmomin
 */
public class ViewCamController implements Initializable {
    @FXML
    private Label camlbl;
    private SafeHome sh;
    @FXML
    private ListView<Camera> list;
    @FXML
    private Button mainbtn;
    @FXML
    private Button viewCamBtn;
    private ObservableList<Camera> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sh = Context.getInstance().getSafeHome();
        data = FXCollections.observableArrayList(sh.getCameras());
        list.setItems(data);
        viewCamBtn.setDisable(true);
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Camera>() {
    @Override
            public void changed(ObservableValue<? extends Camera> observable, Camera oldValue, Camera newValue) {
                viewCamBtn.setDisable(false);
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
    private void handleViewCam(ActionEvent event) throws IOException {
        Camera c = data.get(list.getSelectionModel().getSelectedIndex());
        Context.getInstance().setID(c.getId());
        Parent goMainPage = FXMLLoader.load(getClass().getResource("camera.fxml"));
        Scene goMainScene = new Scene(goMainPage);
        Stage appStage3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage3.hide();
        appStage3.setScene(goMainScene);
        appStage3.show(); 
    }
    
    
}
