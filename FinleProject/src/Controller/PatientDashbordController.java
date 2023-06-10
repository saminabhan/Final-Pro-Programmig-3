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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import modul.Appoitments;
import modul.DB;
import modul.LoginP;
import modul.Patient;
import modul.User;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class PatientDashbordController implements Initializable {

    int LogUserId = LoginP.getId();
    String userLog = LoginP.getUsername();

    @FXML
    private Button FAppitments;

    @FXML
    private Button ShowFreeApp;

    @FXML
    private Button WAppoitment;

    @FXML
    private Button logout11;

    @FXML
    private Button logout;
    @FXML
    private TableColumn<Appoitments, String> AppDate;
    @FXML
    private TableColumn<Appoitments, String> AppDay;
    @FXML
    private TableColumn<Appoitments, String> AppTime;
    @FXML
    private TableColumn<Appoitments, String> AppStatus;
    @FXML
    private TableView<Appoitments> AppTable;

    @FXML
    private TableColumn<Appoitments, Integer> AppID;

    @FXML
    private Button bookApp;
    @FXML
    private TextArea doctorCommecnt;

    @FXML
    private Button dcomShow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppTable.refresh();
        AppID.setCellValueFactory(new PropertyValueFactory("id"));
        AppDate.setCellValueFactory(new PropertyValueFactory("AppDate"));
        AppDay.setCellValueFactory(new PropertyValueFactory("AppDay"));
        AppTime.setCellValueFactory(new PropertyValueFactory("AppTime"));
        AppStatus.setCellValueFactory(new PropertyValueFactory("AppStatus"));
        AppTable.refresh();

    }

    @FXML
    private void logoutBtn(ActionEvent event) throws IOException {
        View.ViewManager.ClosePatientDashbord();
        View.ViewManager.openLogin();
        AppTable.refresh();

    }

    @FXML
    void BookAppBtn(ActionEvent event) throws SQLException, ClassNotFoundException {

        Appoitments a = AppTable.getSelectionModel().getSelectedItem();

        if (a != null) {

            Patient p = new Patient();
            p.bookAppointment(a, this.LogUserId);

            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Booked Confarmation Messeaag");
            al.setContentText("Appoitment With Id :" + a.getId() + "\n" + "Booked For User: " + userLog + "\n" + "With Id: " + LogUserId);
            al.showAndWait();

        }

    }

    @FXML
    void FinshedAppitmentsBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Appoitments> fis = FXCollections.observableList(Patient.getAllFinshedAppoitments());
        AppTable.setItems(fis);

    }

    @FXML
    void ShowBookedAppoitmentsBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        Patient p = new Patient();
        ObservableList<Appoitments> booked = FXCollections.observableList(Patient.GetAllBookedAppointments());
        AppTable.setItems(booked);

    }

    @FXML
    void ShowFreeAppoitmentsBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Appoitments> free = FXCollections.observableList(Patient.getAllFreeAppoitments());
        AppTable.setItems(free);

    }

    @FXML
    void WatingAppoitmentBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Appoitments> Wat = FXCollections.observableList(Patient.GetAllWaitingAppointments());
        AppTable.setItems(Wat);

    }

    @FXML
    void ShowDoctorCommecnt(ActionEvent event) throws SQLException, ClassNotFoundException {
        Appoitments ap = AppTable.getSelectionModel().getSelectedItem();

        if (ap != null) {
            int id = LoginP.getId();
            Connection c = DB.getInstance().getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String Sql = "SELECT doctor_comment FROM appointments JOIN booked_appointments ON appointments.id = booked_appointments.appointment_id WHERE booked_appointments.user_id=? AND booked_appointments.status = 'Finished' AND appointments.id=?";
            ps = c.prepareStatement(Sql);
            ps.setInt(1, id);
            ps.setInt(2, ap.getId()); // Assuming ap.getId() returns the appointment ID for the selected row
            rs = ps.executeQuery();

            if (rs.next()) {
                String comment = rs.getString("doctor_comment");
                doctorCommecnt.setText(comment);
            }
        }

    }
}
