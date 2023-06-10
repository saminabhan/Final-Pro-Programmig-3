/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

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
public class UpdateUserController implements Initializable {

    private User oldUser;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField age;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private RadioButton malerd;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private RadioButton femalrd;
    @FXML
    private RadioButton petiantrd;
    @FXML
    private ToggleGroup roleGroup;
    @FXML
    private RadioButton adminrd;
    @FXML
    private Button cancel;
    @FXML
    private Button update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.oldUser = Controller.AdminDashbordController.selectedUserToUpdate;
        this.username.setText(oldUser.getUsername());
        this.password.setText(oldUser.getPassword());
        this.firstname.setText(oldUser.getFirstname());
        this.lastname.setText(oldUser.getLastname());
        this.age.setText(String.valueOf(oldUser.getAge()));
        this.email.setText(oldUser.getMail());
        this.phone.setText(String.valueOf(oldUser.getPhone()));
        if (oldUser.getGender().equals("Male")) {
            genderGroup.selectToggle(malerd);

        } else if (oldUser.getGender().equals("Female")) {
            genderGroup.selectToggle(femalrd);

        }
        if (oldUser.getRole().equals("Patient")) {
            roleGroup.selectToggle(petiantrd);
        } else if (oldUser.getRole().equals("Admin")) {
            roleGroup.selectToggle(adminrd);

        }
        // TODO
    }

    @FXML
    private void cancelBtn(ActionEvent event) {

        View.ViewManager.CloseUpdateUser();
    }

    @FXML
    private void updateBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        String username = this.username.getText();
        String password = this.password.getText();
        String fname = this.firstname.getText();
        String lname = this.lastname.getText();
        Integer age = Integer.valueOf(this.age.getText());
        String email = this.email.getText();
        Integer phone = Integer.valueOf(this.phone.getText());
        String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
        String role = ((RadioButton) roleGroup.getSelectedToggle()).getText();

        User up = new User(username, password, fname, lname, age, email, phone, gender, role);
        up.setId(oldUser.getId());

        up.update();
        View.ViewManager.CloseUpdateUser();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User updated");
        alert.setContentText("User updated");
        alert.showAndWait();

    }

}
