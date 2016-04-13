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
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Aziz
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Label hiddenlabel;
    @FXML
    private Button submit;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    
    private SafeHome sh;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sh = Context.getInstance().getSafeHome();
    }    


    @FXML
    private void handleSubmit(ActionEvent event) throws IOException {
        if(sh.login(username.getText(),password.getText())){
            Parent welcomeScreenParent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
            Scene welcomScreenScene = new Scene(welcomeScreenParent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.hide();
            appStage.setScene(welcomScreenScene);
            appStage.show();
        }
        else{
            hiddenlabel.setText("Invalid Username or Password");
        }
    }
    
}
