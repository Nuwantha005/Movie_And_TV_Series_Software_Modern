/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;
import java.lang.String;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Nuwantha Kumara
 */
public class FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        JFXTreeTableColumn<User, String> deptColumn = new JFXTreeTableColumn<>("Department");
        deptColumn.setPrefWidth(150);
        deptColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) -> {
            if (deptColumn.validateValue(param)) {
                return param.getValue().getValue().department;
            } else {
                return deptColumn.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<User, String> empColumn = new JFXTreeTableColumn<>("Employee");
        empColumn.setPrefWidth(150);
        empColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) -> {
            if (empColumn.validateValue(param)) {
                return param.getValue().getValue().userName;
            } else {
                return empColumn.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<User, String> ageColumn = new JFXTreeTableColumn<>("Age");
        ageColumn.setPrefWidth(150);
        ageColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) -> {
            if (ageColumn.validateValue(param)) {
                return param.getValue().getValue().age;
            } else {
                return ageColumn.getComputedValue(param);
            }
        });
        
        ageColumn.setCellFactory(new Callback<TreeTableColumn<User, String>, TreeTableCell<User, String>>() {
            @Override
            public TreeTableCell<User, String> call(TreeTableColumn<User, String> param) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });


// data
        ObservableList<User> users = FXCollections.observableArrayList();
        users.add(new User("Computer Department", "23", "CD 1"));
        users.add(new User("Sales Department", "22", "Employee 1"));
        users.add(new User("Sales Department", "22", "Employee 2"));
        users.add(new User("Sales Department", "25", "Employee 4"));
        users.add(new User("Sales Department", "25", "Employee 5"));
        users.add(new User("IT Department", "42", "ID 2"));
        users.add(new User("HR Department", "22", "HR 1"));
        users.add(new User("HR Department", "22", "HR 2"));

        for (int i = 0; i < 40000; i++) {
            users.add(new User("HR Department", i % 10 + "", "HR 2" + i));
        }
        for (int i = 0; i < 40000; i++) {
            users.add(new User("Computer Department", i % 20 + "", "CD 2" + i));
        }

        for (int i = 0; i < 40000; i++) {
            users.add(new User("IT Department", i % 5 + "", "HR 2" + i));
        }

// build tree
        final TreeItem<User> root = new RecursiveTreeItem<User>(users, RecursiveTreeObject::getChildren);

        JFXTreeTableView<User> treeView = new JFXTreeTableView<User>(root);
        treeView.setShowRoot(false);
        treeView.setEditable(true);
        treeView.getColumns().setAll(deptColumn, ageColumn, empColumn);

        JFXTextField filterField = new JFXTextField();
        filterField.textProperty().addListener((o, oldVal, newVal) -> {
            treeView.setPredicate(user -> user.getValue().age.get().contains(newVal)
                    || user.getValue().department.get().contains(newVal)
                    || user.getValue().userName.get().contains(newVal));
        });

        Label size = new Label();
        size.textProperty().bind(Bindings.createStringBinding(() -> treeView.getCurrentItemsCount() + "",
                treeView.currentItemsCountProperty()));
    }

}

class User extends RecursiveTreeObject<User> {

    StringProperty userName;
    StringProperty age;
    StringProperty department;

    public User(String department, String age, String userName) {
        this.department = new SimpleStringProperty(department);
        this.userName = new SimpleStringProperty(userName);
        this.age = new SimpleStringProperty(age);
    }
}
