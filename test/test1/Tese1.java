/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nuwantha Kumara
 */
public class Tese1 extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/main/Main_Frame.fxml"));
        //JFXDecorator decorator = new JFXDecorator(stage, root);
        //decorator.setCustomMaximize(true);
        //decorator.getStylesheets().add(root.getStyle());
        //Scene scene = new Scene(decorator, 1280, 760);
        
        
        
        
        
        
        
        Scene scene = new Scene(root);
        stage.setTitle("Movies and TV Series Software");
        stage.setScene(scene);
        stage.show();
    }
    
}
