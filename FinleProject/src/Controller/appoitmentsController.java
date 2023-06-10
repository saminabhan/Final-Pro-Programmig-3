/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import modul.Appoitments;
import modul.DB;
import modul.User;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class appoitmentsController implements Initializable {

    public static Appoitments selctedAppoitment;

    @FXML
    private Button search;

    @FXML
    private TextField fnameTXT;
    @FXML
    private Button userMang;
    @FXML
    private Button appointments;
    @FXML
    private Button logout;
    @FXML
    private TableView<Appoitments> AppointmentTable;
    @FXML
    private TableColumn<Appoitments, Integer> idapp;
    @FXML
    private TableColumn<Appoitments, String> appdate;
    @FXML
    private TableColumn<Appoitments, String> appday;
    @FXML
    private TableColumn<Appoitments, String> apptime;
    @FXML
    private TableColumn<Appoitments, String> appstatus;
    @FXML
    private Button finish;
    @FXML
    private Button updateapp;
    @FXML
    private Button delapp;
    @FXML
    private Button showallapp;
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
    private Button addapp;
    @FXML
    private TextField searchfield;

    @FXML
    private TextArea doctorCommecnt;
    
  


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idapp.setCellValueFactory(new PropertyValueFactory("id"));
        appdate.setCellValueFactory(new PropertyValueFactory("AppDate"));
        appday.setCellValueFactory(new PropertyValueFactory("AppDay"));
        apptime.setCellValueFactory(new PropertyValueFactory("AppTime"));
        appstatus.setCellValueFactory(new PropertyValueFactory("AppStatus"));
        
       
    }

    @FXML
    private void userMangBtn(ActionEvent event) throws IOException {
        View.ViewManager.closeAppoitments();
        View.ViewManager.OpenAdmin();

    }

    @FXML
    private void appointmentsBtn(ActionEvent event) throws IOException {
        View.ViewManager.CloseAdmin();
        View.ViewManager.openAppoitments();
    }

    @FXML
    private void logoutBtn(ActionEvent event) throws IOException {
        View.ViewManager.CloseAdmin();
        View.ViewManager.closeAppoitments();
        View.ViewManager.openLogin();

    }

    @FXML
    private void updateappBtn(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        if (AppointmentTable.getSelectionModel().getSelectedItem() != null) {
            selctedAppoitment = AppointmentTable.getSelectionModel().getSelectedItem();

            View.ViewManager.OpenUpdate();
        }
    }

    @FXML
    private void delappbtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        Appoitments ap = AppointmentTable.getSelectionModel().getSelectedItem();

        if (ap != null) {
            int id = ap.getId();
            Alert del = new Alert(Alert.AlertType.CONFIRMATION);
            del.setContentText("Appoitments Delete");
            del.setTitle(" Appoitments Delete? ");
            del.showAndWait().ifPresent(e -> {
                if (e == ButtonType.OK) {
                    try {
                        Connection c = DB.getInstance().getConnection();
                        String sql = "DELETE FROM appointments WHERE ID=?";
                        PreparedStatement ps = c.prepareStatement(sql);
                        ps.setInt(1, id);
                        ps.executeUpdate();
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setContentText("Appoitments Delete");
                        a.setTitle(" Appoitments Deleteted ");
                        a.showAndWait();

                    } catch (SQLException ex) {
                        Logger.getLogger(appoitmentsController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(appoitmentsController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {

                }

            });

        }

    }

    @FXML
    private void showallappBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Appoitments> appointmentList = FXCollections.observableList(Appoitments.getAllAppoitments());
        AppointmentTable.setItems(appointmentList);

    }

    @FXML
    private void addappbtn(ActionEvent event) throws SQLException, ClassNotFoundException {

        String date = this.date.getText();
        String time = this.time.getText();
        String day = this.day.getText();
        String statu = ((RadioButton) statusRadio.getSelectedToggle()).getText();

        Appoitments ap = new Appoitments(date, day, time, statu);
        ap.save();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("New Appoitment Addedd Successfully");
        a.showAndWait();

    }

    @FXML
    private ArrayList<Appoitments> searchBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        String searchValue = fnameTXT.getText();

        if (searchValue.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please enter a search value.");
            alert.setTitle("Empty Search Field");
            alert.showAndWait();

        }

        Connection c = DB.getInstance().getConnection();
        if (c == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to establish a database connection.");
            alert.setTitle("Connection Error");
            alert.showAndWait();

        }
        ArrayList<Appoitments> app = new ArrayList<>();
        String sql = "SELECT appointments.* "
                + "FROM users "
                + "JOIN booked_appointments ON users.id = booked_appointments.user_id "
                + "JOIN appointments ON booked_appointments.appointment_id = appointments.id "
                + "WHERE users.firstname = ? AND appointments.status = 'Booked'";

        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, searchValue);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Appoitments ap = new Appoitments(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            ap.setId(rs.getInt(1));
            app.add(ap);
            ObservableList<Appoitments> usersList
                    = FXCollections.observableArrayList(app);

            AppointmentTable.setItems(usersList);

        } else {
            Alert noname = new Alert(Alert.AlertType.WARNING);
            noname.setContentText(searchValue + " Not Found");
            noname.setTitle("No User Found!");
            noname.showAndWait();
        }
        return app;
    }

    @FXML
    void finishbtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        Appoitments ap = AppointmentTable.getSelectionModel().getSelectedItem();
        if (ap != null) {
            try {
                Connection c = DB.getInstance().getConnection();
                PreparedStatement ps = null;
                PreparedStatement psb = null;
                int recordCounter = 0;

                String bsql = "UPDATE booked_appointments SET status=? WHERE status='waiting' AND appointment_id=?";
                ps = c.prepareStatement(bsql);
                ps.setString(1, "Finished");
                ps.setInt(2, ap.getId());
                recordCounter = ps.executeUpdate();

                String asql = "UPDATE appointments SET status=? WHERE status='Booked' AND id=?";
                ps = c.prepareStatement(asql);
                ps.setString(1, "Finished");
                ps.setInt(2, ap.getId());
                recordCounter += ps.executeUpdate();

                String doctorCommentSql = "UPDATE booked_appointments SET doctor_comment=? WHERE appointment_id=?";
                psb = c.prepareStatement(doctorCommentSql);
                psb.setString(1, this.doctorCommecnt.getText());
                psb.setInt(2, ap.getId());
                psb.executeUpdate();

                if (recordCounter > 0) {
                    System.out.println("Done");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
