/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hp
 */
public class Patient {

    static LoginP logid;

    public static LoginP getLogid() {
        return logid;
    }

    public static void setLogid(LoginP logid) {
        Patient.logid = logid;
    }

    Appoitments app;

    public Appoitments getApp() {
        return app;
    }

    public void setApp(Appoitments app) {
        this.app = app;
    }

    public void bookAppointment(Appoitments app, int patientId) throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        PreparedStatement psbook = null;
        int recordCounter = 0;
        int bookRecord = 0;
        String sql = "INSERT INTO booked_appointments (appointment_id, user_id ,status ,doctor_comment) VALUES (?, ? ,? ,?)";
        String booksql = "UPDATE appointments SET status=? WHERE id=?";

        ps = c.prepareStatement(sql);
        psbook = c.prepareStatement(booksql);

        ps.setInt(1, app.getId());
        ps.setInt(2, patientId);
        ps.setString(3, "waiting");
        ps.setString(4, "notheing now");
        psbook.setString(1, "Booked");
        psbook.setInt(2, app.getId());
        recordCounter = ps.executeUpdate();
        bookRecord = psbook.executeUpdate();
        if (bookRecord > 0) {
            System.out.println("Appoitment Status Updated");
        }
        if (recordCounter > 0) {

            System.out.println("Appoitmet Booked: " + app.getId() + " For " + patientId);
        }

        if (ps != null) {
            ps.close();
        }

        c.close();

    }

    public static ArrayList<Appoitments> getAllFreeAppoitments() throws SQLException, ClassNotFoundException {

        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Appoitments> app = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE status='free'";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Appoitments ap = new Appoitments(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            ap.setId(rs.getInt(1));
            app.add(ap);
        }
        if (ps != null) {
            ps.close();
        }

        return app;
    }

    public static ArrayList<Appoitments> GetAllBookedAppointments() throws SQLException, ClassNotFoundException {
        Appoitments bok = new Appoitments();
        int id = LoginP.getId();
        ArrayList<Appoitments> app = new ArrayList<Appoitments>();
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM appointments JOIN booked_appointments ON appointments.id = booked_appointments.appointment_id WHERE booked_appointments.user_id=? AND appointments.status='Booked'";
        ps = c.prepareStatement(sql);
        ps.setInt(1, id);

        rs = ps.executeQuery();
        while (rs.next()) {
            Appoitments ap = new Appoitments(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            ap.setId(rs.getInt(1));
            app.add(ap);
            System.out.println(rs.getInt(1));
        }

        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        return app;
    }

    public static ArrayList<Appoitments> GetAllWaitingAppointments() throws SQLException, ClassNotFoundException {
        Appoitments bok = new Appoitments();
        int id = LoginP.getId();
        ArrayList<Appoitments> app = new ArrayList<Appoitments>();
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT appointments.*, booked_appointments.status AS booking_status FROM appointments INNER JOIN booked_appointments ON appointments.id = booked_appointments.appointment_id WHERE booked_appointments.user_id=? AND booked_appointments.status = 'waiting';";
        ps = c.prepareStatement(sql);
        ps.setInt(1, id);

        rs = ps.executeQuery();
        while (rs.next()) {
            Appoitments ap = new Appoitments(rs.getString("appointment_date"), rs.getString("appointment_day"), rs.getString("appointment_time"), rs.getString("booking_status"));
            ap.setId(rs.getInt("id"));
            app.add(ap);
            System.out.println(rs.getInt("id"));
        }

        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        return app;

    }

    public static ArrayList<Appoitments> getAllFinshedAppoitments() throws SQLException, ClassNotFoundException {
        Appoitments bok = new Appoitments();
        int id = LoginP.getId();
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Appoitments> app = new ArrayList<>();
        String sql = "SELECT * FROM appointments  JOIN booked_appointments  ON appointments.id = booked_appointments.appointment_id WHERE booked_appointments.user_id=? AND booked_appointments.status = 'Finished'";
        ps = c.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        while (rs.next()) {
            Appoitments ap = new Appoitments(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            ap.setId(rs.getInt(1));
            app.add(ap);
        }
        if (ps != null) {
            ps.close();
        }

        return app;
    }

}
