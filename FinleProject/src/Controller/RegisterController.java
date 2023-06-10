/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.io.IOException;
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
import modul.User;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class RegisterController implements Initializable {

    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private ToggleGroup roleGroup;
    @FXML
    private Button register;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField age;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private Button log;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void registerBtn(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        String username = this.username.getText();
        String password = this.password.getText();
        String fname = this.fname.getText();
        String lname = this.lname.getText();
        Integer age = Integer.valueOf(this.age.getText());
        String email = this.email.getText();
        Integer phone = Integer.valueOf(this.phone.getText());
        String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
        String role = ((RadioButton) roleGroup.getSelectedToggle()).getText();

        User r = new User(username, password, fname, lname, age, email, phone, gender, role);
        r.save();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("New User Addedd succussfully");
        a.showAndWait();
     View.ViewManager.CloseRegister();

    }

    @FXML
    private void logbtn(ActionEvent event) throws IOException {
        View.ViewManager.CloseRegister();
        View.ViewManager.openLogin();
    }

}
