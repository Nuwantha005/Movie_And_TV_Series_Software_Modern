/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.objectClasses;

import Codings.CodingUtils;
import DatabaseSetUp.Preferences;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import movie_and_tv_series_software_modern.Movie_And_TV_Series_Software_Modern;

/**
 *
 * @author Nuwantha Kumara
 */
public class MyConn {

    public static Connection c;

    public static void getMyConn() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Preferences pref = Preferences.getPreferences();
        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies_and_tv_series_database?useUnicode=yes&characterEncoding=UTF-8", pref.getUserName(), pref.getPassword());

    }

    public static void save(String sql) throws Exception {
        if (c == null) {
            getMyConn();
        }
        c.createStatement().executeUpdate(sql);
    }

    public static ResultSet search(String sql) throws Exception {
        if (c == null) {
            getMyConn();
        }
        return c.createStatement().executeQuery(sql);
    }
}
