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
import javafx.scene.control.TextField;
import modul.LoginP;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class LoginController implements Initializable {

    @FXML
    private Button login;
    @FXML
    private Button register;
    @FXML
    private TextField password;
    @FXML
    private TextField username;
    @FXML
    private Button AdminLoginPage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loginBtn(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        String username = this.username.getText();
        String password = this.password.getText();

        if (username.equals("") || password.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input?");
            alert.setContentText(" Fill The Inputs");
            alert.showAndWait();
        } else {

            LoginP l = new LoginP(username, password);

            l.PatientLogin();

        }

    }

    @FXML
    void AdminLoginPageBtn(ActionEvent event) throws IOException {
        View.ViewManager.CloseLogin();
        View.ViewManager.OpenAdminLogin();

    }

    @FXML
    void registerBtn(ActionEvent event) throws IOException {
        View.ViewManager.CloseLogin();
        View.ViewManager.openRegister();

    }

}
