/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseSetUp;

import Codings.CodingUtils;
import static DatabaseSetUp.Preferences.writePreferenceToFile;
import classes.objectClasses.MyConn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import movie_and_tv_series_software_modern.Movie_And_TV_Series_Software_Modern;

/**
 * FXML Controller class
 *
 * @author Nuwantha Kumara
 */
public class DatabaseSetUpController implements Initializable {

    @FXML
    private StackPane stack_Pane;
    @FXML
    private AnchorPane main_AP;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleSetUpAction(ActionEvent event) {
        Preferences pref = new Preferences(username.getText(), password.getText());
        writePreferenceToFile(pref);
        
        
        
        try {
            MyConn.getMyConn();
            try {
                Stage stage = (Stage) stack_Pane.getScene().getWindow();
                Parent root = FXMLLoader.load(Movie_And_TV_Series_Software_Modern.class.getResource("/ui/main/Main_Frame.fxml"));
                Scene scene = new Scene(root);
                stage.setTitle("Movies and TV Series Software");
                stage.setScene(scene);
                stage.getIcons().add(new Image(CodingUtils.SOFTWARE_ICON_LOCATION));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(DatabaseSetUpController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            CodingUtils.showMaterialDialog(stack_Pane, password, new ArrayList<JFXButton>(), "SetUp Failed", "Please Check usernam and password again");
        }

    }

}
