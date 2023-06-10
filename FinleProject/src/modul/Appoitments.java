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

/**
 *
 * @author hp
 */
public class Appoitments {

    int id;
    String AppDate;
    String AppDay;
    String AppTime;
    String AppStatus;
    String Doctor_commecnt;

   public Appoitments() {

    }

    public Appoitments(String AppDate, String AppDay, String AppTime, String AppStatus) {
        this.AppDate = AppDate;
        this.AppDay = AppDay;
        this.AppTime = AppTime;
        this.AppStatus = AppStatus;
        this.Doctor_commecnt = Doctor_commecnt;
    }

  
    public String getAppDate() {
        return AppDate;
    }

    public void setAppDate(String AppDate) {
        this.AppDate = AppDate;
    }

    public String getAppDay() {
        return AppDay;
    }

    public void setAppDay(String AppDay) {
        this.AppDay = AppDay;
    }

    public String getAppTime() {
        return AppTime;
    }

    public void setAppTime(String AppTime) {
        this.AppTime = AppTime;
    }

    public String getAppStatus() {
        return AppStatus;
    }

    public void setAppStatus(String AppStatus) {
        this.AppStatus = AppStatus;
    }

    public String getDoctor_commecnt() {
        return Doctor_commecnt;
    }

    public void setDoctor_commecnt(String Doctor_commecnt) {
        this.Doctor_commecnt = Doctor_commecnt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static ArrayList<Appoitments> getAllAppoitments() throws SQLException, ClassNotFoundException {

        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Appoitments> app = new ArrayList<>();
        String sql = "SELECT * FROM appointments ";
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

    public int save() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "INSERT INTO appointments  (id,  appointment_date,appointment_day	,appointment_time,status) VALUES (? ,? ,? ,? , ?)";
        ps = c.prepareStatement(sql);
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setString(2, this.getAppDate());
        ps.setString(3, this.getAppDay());
        ps.setString(4, this.getAppTime());
        ps.setString(5, this.getAppStatus());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {

        }
        if (ps != null) {
            ps.close();
        }

        c.close();
        return recordCounter;

    }

    public int update() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "UPDATE appointments SET appointment_date=?,  appointment_day=? ,appointment_time=?,status=? WHERE ID=?";
        ps = c.prepareStatement(sql);
        ps.setString(1, this.getAppDate());
        ps.setString(2, this.getAppDay());
        ps.setString(3, this.getAppTime());
        ps.setString(4, this.getAppStatus());
        ps.setInt(5, this.getId());

        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public int delete() throws SQLException, ClassNotFoundException {
       Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "DELETE FROM appointments WHERE ID=?";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The user with id:" + this.getId() + "was deleted sucess");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;


}
}