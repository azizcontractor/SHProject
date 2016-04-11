/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author farhinmomin
 */
public class HomeStatePageController implements Initializable {
    @FXML
    private TextField dateMonth;
    @FXML
    private TextField dateDay;
    @FXML
    private TextField dateYear;
    @FXML
    private TextField timeHour;
    @FXML
    private TextField timeMin;
    @FXML
    private TextField timeAM_PM;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
