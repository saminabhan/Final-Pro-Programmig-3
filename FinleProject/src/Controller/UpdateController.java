/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.appoitments;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import modul.Appoitments;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class UpdateController implements Initializable {
    
    private Appoitments oldApp;
    
    @FXML
    private TextField date;
    @FXML
    private TextField day;
    @FXML
    private TextField time;
    @FXML
    private RadioButton Appfree;
    @FXML
    private ToggleGroup statusRadio;
    @FXML
    private RadioButton Appbook;
    @FXML
    private Button updateapp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.oldApp = Controller.appoitmentsController.selctedAppoitment;
        this.day.setText(oldApp.getAppDay());
        this.date.setText(oldApp.getAppDate());
        this.time.setText(oldApp.getAppTime());
        if (oldApp.getAppStatus().equals("Free")) {
            statusRadio.selectToggle(Appfree);
        } else if (oldApp.getAppStatus().equals("Booked")) {
            statusRadio.selectToggle(Appbook);
            
        }
        
    }
    
    @FXML
    private void updateappBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        String date = this.date.getText();
        String day = this.day.getText();
        String time = this.time.getText();
        String status = ((RadioButton) statusRadio.getSelectedToggle()).getText();
        
        Appoitments user = new Appoitments(date, day, time, status);
        user.setId(oldApp.getId());
        
        user.update();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User updated");
        alert.setContentText("User updated");
        alert.showAndWait();
    }
    
}
