/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codings;

import classes.objectClasses.MyConn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.File;
import java.sql.ResultSet;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

/**
 *
 * @author Nuwantha Kumara
 */
public class CodingUtils {

    public static String SOFTWARE_ICON_LOCATION = "/resources/images/softwareIcon.png";

    public static void update_Combo_Box(String tableName, ComboBox comboBox) {
        try {
            ResultSet rs = MyConn.search("select * from " + tableName + " ");
            ObservableList list = FXCollections.observableArrayList();
            while (rs.next()) {
                list.add(rs.getString(1) + " - " + rs.getString(2));
            }
            comboBox.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Image browse_Image(Parent root) {
        FileChooser image_Chooser = new FileChooser();
        image_Chooser.setTitle("Choose Image");
        image_Chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg"));
        image_Chooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Pictures"));
        File user_Selected_Image_File = image_Chooser.showOpenDialog(root.getScene().getWindow());
        Image image = null;
        if (user_Selected_Image_File != null) {
            image = new Image(user_Selected_Image_File.toURI().toString());

        }
        return image;
    }

    public static int getID(String table_name) {
        int id = 0;
        try {
            ResultSet rs = MyConn.search("select * from " + table_name + " ");
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        id++;
        return id;
    }

    public static void delete_from_Database(String table_Name, String ID) throws Exception {
        MyConn.save("delete from " + table_Name + " where id = '" + ID + "' ");
    }

    public static void showMaterialDialog(StackPane root, Node nodeToBeBlurred, List<JFXButton> controls, String header, String body) {
        BoxBlur blur = new BoxBlur(3, 3, 3);
        if (controls.isEmpty()) {
            JFXButton okBtn = new JFXButton("Okay");
            controls.add(okBtn);

        }
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.TOP);
        controls.forEach(controlButton -> {
            if (header.contains("Not") || header.contains("Invalid") || header.contains("Failed")) {
                controlButton.getStyleClass().add("error-button");
            } else {
                controlButton.getStyleClass().add("dialog-button");
            }
            controlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                dialog.close();
            });
        });
        ImageView img = new ImageView("/resources/images/OK.png");
        img.setFitHeight(60);
        img.setFitWidth(60);
        if (header.contains("Not") || header.contains("Invalid") || header.contains("Failed")) {
            dialogLayout.getStyleClass().add("error");
            img.setImage(new Image("/resources/images/Error2.png"));
        } else if (header.contains("Confrimation")) {
            img.setImage(new Image("/resources/images/question1.png"));
        }
        Label bodyLbl = new Label(body);
        bodyLbl.getStyleClass().add("dialog-body-lbl");

        HBox hb = new HBox(img, bodyLbl);
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(10);

        dialogLayout.setHeading(new Label(header));
        dialogLayout.setBody(hb);
        dialogLayout.setActions(controls);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
            nodeToBeBlurred.setEffect(null);
        });
        nodeToBeBlurred.setEffect(blur);
    }

}
