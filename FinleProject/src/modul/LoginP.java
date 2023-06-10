/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul;

import com.mysql.cj.protocol.Resultset;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author hp
 */
public class LoginP {

    private static String username;
    private String password;
    public static int id;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        LoginP.id = id;

    }

    public LoginP(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        LoginP.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void Login() throws SQLException, ClassNotFoundException, IOException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement statement = c.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
        statement.setString(1, this.getUsername());
        statement.setString(2, this.getPassword());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next() == true) {

            View.ViewManager.CloseAdminLogin();
            View.ViewManager.OpenAdmin();

        } else if (resultSet.next() == false) {
            Alert nouser = new Alert(Alert.AlertType.WARNING);
            nouser.setContentText("No User Found");
            nouser.showAndWait();

        }

        if (statement != null) {

            statement.close();
        }

        c.close();

    }

    public void PatientLogin() throws SQLException, ClassNotFoundException, IOException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement statement = c.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ? AND ROLE = 'Patient' ");
        statement.setString(1, this.getUsername());
        statement.setString(2, this.getPassword());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next() == true) {
            int logid = resultSet.getInt("id");
            this.setId(logid);
            View.ViewManager.CloseLogin();
            View.ViewManager.OpenPatientDashbord();

            System.out.println("user login with name: " + this.getUsername() + "ID " + resultSet.getInt("id"));

        } else if (resultSet.next() == false) {
            Alert nouser = new Alert(Alert.AlertType.WARNING);
            nouser.setContentText("No User Found");
            nouser.showAndWait();

        }

        if (statement != null) {

            statement.close();
        }

        c.close();

    }

}
