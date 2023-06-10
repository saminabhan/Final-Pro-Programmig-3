/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.io.IOException;

/**
 *
 * @author hp
 */
public class ViewManager {

    public static registerPlode rg;
    public static LoginPage lp;
    public static AdminDashbord admin;
    public static updateuser upuser;
    public static PatientDashbord pd;
    public static AdminLogin al;
    public static appoitments ap;
    public static appUpdate au;

    private ViewManager() {
    }
public static void openAppoitments() throws IOException {
        if (ap == null) {

            ap = new appoitments();
            ap.show();
        } else {
            ap.show();
        }

    }

    public static void closeAppoitments() {
        if (ap != null) {

            ap.close();
        }

    }
    public static void OpenUpdate() throws IOException {
        if (au == null) {

            au = new appUpdate();
            au.show();
        } else {
            au.show();
        }

    }

    public static void CloseUpdate() {
        if (au != null) {

            au.close();
        }

    }
    public static void openRegister() throws IOException {
        if (rg == null) {

            rg = new registerPlode();
            rg.show();
        } else {
            rg.show();
        }

    }

    public static void CloseRegister() {
        if (rg != null) {

            rg.close();
        }

    }

    public static void OpenAdminLogin() throws IOException {
        if (al == null) {

            al = new AdminLogin();
            al.show();
        } else {
            al.show();
        }

    }

    public static void CloseAdminLogin() {
        if (al != null) {

            al.close();
        }

    }

    public static void openLogin() throws IOException {
        if (lp == null) {

            lp = new LoginPage();
            lp.show();
        } else {
            lp.show();
        }

    }

    public static void CloseLogin() {
        if (lp != null) {

            lp.close();
        }

    }

    public static void OpenAdmin() throws IOException {
        if (admin == null) {

            admin = new AdminDashbord();
            admin.show();
        } else {
            admin.show();
        }

    }

    public static void CloseAdmin() {
        if (admin != null) {

            admin.close();
        }

    }

    public static void OpenUpdateUser() throws IOException {
        if (upuser == null) {

            upuser = new updateuser();
            upuser.show();
        } else {
            upuser.show();
        }

    }

    public static void CloseUpdateUser() {
        if (upuser != null) {

            upuser.close();
        }

    }

    public static void OpenPatientDashbord() throws IOException {
        if (pd == null) {

            pd = new PatientDashbord();
            pd.show();
        } else {
            pd.show();
        }

    }

    public static void ClosePatientDashbord() {
        if (pd != null) {

            pd.close();
        }

    }

}
