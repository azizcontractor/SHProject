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
import javafx.stage.Stage;

/**
 * FXML Controller class for viewing devices which are connected to the SafeHome.
 *
 * @author Meera
 */
public class DevicesController implements Initializable {

    @FXML
    private Button mainbtn;
    @FXML
    private Button backbtn;
    @FXML
    private Label devicelbl;
    
    SafeHome sh;
    @FXML
    private ListView<?> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         sh = Context.getInstance().getSafeHome();
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
    
}
