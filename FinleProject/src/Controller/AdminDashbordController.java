/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modul.DB;
import modul.User;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AdminDashbordController implements Initializable {

    public static User selectedUserToUpdate;
    public static Stage updateStage;
    @FXML
    private TextField ID;

    @FXML
    private TableColumn<User, String> Passwordc;

    @FXML
    private TableView<User> PatientTabel;

    @FXML
    private Button addNew;

    @FXML
    private RadioButton adminrd;

    @FXML
    private TextField age;

    @FXML
    private TableColumn<User, Integer> agec;

    @FXML
    private Button appointments;

    @FXML
    private Button del;

    @FXML
    private TextField email;

    @FXML
    private TableColumn<User, String> emailc;

    @FXML
    private RadioButton femalrd;

    @FXML
    private TextField firstname;

    @FXML
    private TableColumn<User, String> fnamec;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private TableColumn<User, String> genderc;

    @FXML
    private TableColumn<User, Integer> idc;

    @FXML
    private TextField lastname;

    @FXML
    private TableColumn<User, String> lnamec;

    @FXML
    private Button logout;

    @FXML
    private RadioButton malerd;

    @FXML
    private TextField password;

    @FXML
    private RadioButton petiantrd;

    @FXML
    private TextField phone;

    @FXML
    private TableColumn<User, Integer> phonec;

    @FXML
    private ToggleGroup roleGroup;

    @FXML
    private TableColumn<User, String> rolec;

    @FXML
    private Button showall;

    @FXML
    private Button update;

    @FXML
    private Button userMang;

    @FXML
    private TextField username;

    @FXML
    private TableColumn<User, String> usernamec;
    @FXML
    private TextField fnameTXT;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {

        idc.setCellValueFactory(new PropertyValueFactory("id"));
        usernamec.setCellValueFactory(new PropertyValueFactory("username"));
        Passwordc.setCellValueFactory(new PropertyValueFactory("password"));
        fnamec.setCellValueFactory(new PropertyValueFactory("firstname"));
        lnamec.setCellValueFactory(new PropertyValueFactory("lastname"));
        agec.setCellValueFactory(new PropertyValueFactory("age"));
        emailc.setCellValueFactory(new PropertyValueFactory("mail"));
        phonec.setCellValueFactory(new PropertyValueFactory("phone"));
        genderc.setCellValueFactory(new PropertyValueFactory("gender"));
        rolec.setCellValueFactory(new PropertyValueFactory("role"));

    }

    @FXML
    private void logoutBtn(ActionEvent event) throws IOException {
        View.ViewManager.CloseAdmin();
        View.ViewManager.closeAppoitments();
        View.ViewManager.openLogin();
    }

    @FXML
    private void appointmentsBtn(ActionEvent event) throws IOException {
        View.ViewManager.CloseAdmin();
        View.ViewManager.openAppoitments();
    }

    @FXML
    private void userMangBtn(ActionEvent event) throws IOException {
        View.ViewManager.OpenAdmin();

        View.ViewManager.closeAppoitments();
    }

    @FXML
    void updateBtn(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        if (PatientTabel.getSelectionModel().getSelectedItem() != null) {
            selectedUserToUpdate = PatientTabel.getSelectionModel().getSelectedItem();
            View.ViewManager.OpenUpdateUser();

        }
    }

    @FXML
    void deleteBtn(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        User user = PatientTabel.getSelectionModel().getSelectedItem();
        if (user != null) {
            int id = user.getId();

            Alert del = new Alert(Alert.AlertType.CONFIRMATION);
            del.setTitle("User delete");
            del.setContentText("Are you sure to delete this user ?");
            del.showAndWait().ifPresent(e -> {
                if (e == ButtonType.OK) {
                    try {
                        Connection c = DB.getInstance().getConnection();
                        String sql = "DELETE FROM USERS WHERE ID=? ";
                        PreparedStatement ps = c.prepareStatement(sql);
                        ps.setInt(1, id);
                        ps.executeUpdate();
                        Alert deleted = new Alert(Alert.AlertType.INFORMATION);
                        deleted.setTitle("User deleted");
                        deleted.setContentText("User Deleted Successfully");
                        deleted.showAndWait();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }

                }
            });

        }

    }

    @FXML
    void searchBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        String sql = "SELECT * FROM USERS WHERE firstname=? ";
        PreparedStatement ps = c.prepareStatement(sql);
        ArrayList<User> users = new ArrayList<>();
        ps.setString(1, this.fnameTXT.getText());
        ResultSet rs = ps.executeQuery();
        if (rs.next() == true) {
            User name = new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10));
            name.setId(rs.getInt(1));
            users.add(name);
            ObservableList<User> usersList
                    = FXCollections.observableArrayList(users);
            PatientTabel.setItems(usersList);

        } else {
            Alert noname = new Alert(Alert.AlertType.WARNING);
            noname.setContentText(this.fnameTXT.getText() + " Not Found ");
            noname.setTitle("No User Found !");
            noname.showAndWait();

        }

    }

    @FXML
    void addNewBtn(ActionEvent event) throws IOException {
        View.ViewManager.openRegister();
        View.ViewManager.CloseLogin();

    }

    @FXML
    void showallBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<User> usersList
                = FXCollections.observableArrayList(User.getAllUsers());

        PatientTabel.setItems(usersList);

    }

}
