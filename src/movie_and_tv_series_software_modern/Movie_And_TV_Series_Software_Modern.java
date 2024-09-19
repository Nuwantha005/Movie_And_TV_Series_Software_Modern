/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_and_tv_series_software_modern;

import Codings.CodingUtils;
import classes.objectClasses.MyConn;
import static classes.objectClasses.MyConn.c;
import java.sql.DriverManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Nuwantha Kumara
 */
public class Movie_And_TV_Series_Software_Modern extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        try {
            MyConn.getMyConn();
            Parent root = FXMLLoader.load(getClass().getResource("/ui/main/Main_Frame.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Movies and TV Series Software");
            stage.setScene(scene);
            stage.getIcons().add(new Image(CodingUtils.SOFTWARE_ICON_LOCATION));
            stage.show();
        } catch (Exception e) {
            Parent root = FXMLLoader.load(Movie_And_TV_Series_Software_Modern.class.getResource("/DatabaseSetUp/DatabaseSetUp.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Movies and TV Series Software");
            stage.setScene(scene);
            stage.getIcons().add(new Image(CodingUtils.SOFTWARE_ICON_LOCATION));
            stage.show();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
