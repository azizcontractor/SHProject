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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author farhinmomin
 */
public class TravelStatePageController implements Initializable {
    @FXML
    private Label lblTravel;
    @FXML
    private Label lblStart;
    @FXML
    private Label lblArrival;
    @FXML
    private Label lblStDate;
    @FXML
    private Label lblStTime;
    @FXML
    private Label lblArDate;
    @FXML
    private Label lblArTime;
    @FXML
    private Button btnOK;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField STmonth;
    @FXML
    private TextField STday;
    @FXML
    private TextField STyear;
    @FXML
    private TextField AImonth;
    @FXML
    private TextField AIday;
    @FXML
    private TextField AIyear;
    @FXML
    private TextField SThr;
    @FXML
    private TextField AIHr;
    @FXML
    private TextField STmin;
    @FXML
    private TextField AImin;
    @FXML
    private TextField STam_pm;
    @FXML
    private TextField AIam_pm;

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
    private void handleCancel(ActionEvent event) throws IOException {
        Parent backSysStageparent = FXMLLoader.load(getClass().getResource("systemState.fxml"));
        Scene date_page_scene = new Scene(backSysStageparent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(date_page_scene);
        app_stage.show();
    }
    
}
