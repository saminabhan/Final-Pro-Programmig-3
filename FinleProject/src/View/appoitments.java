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
public class appoitments extends Stage{
    appoitments() throws IOException{
        FXMLLoader lode = new FXMLLoader(getClass().getResource("appoitments.fxml"));
        Parent p = lode.load();
        Scene s  =new Scene(p);
        this.setScene(s);
        this.setTitle("Appoitmnts");
        this.show();
    }
    
}
