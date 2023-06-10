/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class appUpdate extends Stage{
    appUpdate() throws IOException{
        FXMLLoader lode = new FXMLLoader(getClass().getResource("Update.fxml"));
        Parent p = lode.load();
        Scene s  =new Scene(p);
        this.setScene(s);
        this.setTitle("Update appointments");
        this.show();
    }
    
}
