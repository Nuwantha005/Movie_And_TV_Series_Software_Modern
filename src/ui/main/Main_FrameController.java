/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.main;

import classes.objectClasses.MyConn;
import classes.objectClasses.Movie;
import Codings.CodingUtils;
import Codings.movieTableCodings;
import classes.recursiveTreeObjectClasses.movieTreeObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Nuwantha Kumara
 */
public class Main_FrameController implements Initializable {

    private ObservableList<Movie> movieObservableList = FXCollections.observableArrayList();
    private ObservableList<String> movieNameList = FXCollections.observableArrayList();
    private ObservableList<movieTreeObject> movieTreeList = FXCollections.observableArrayList();

    @FXML // fx:id="root"
    private StackPane root; // Value injected by FXMLLoader

    @FXML // fx:id="mainAP"
    private AnchorPane mainAP; // Value injected by FXMLLoader

    @FXML // fx:id="overviewAP"
    private AnchorPane overviewAP; // Value injected by FXMLLoader

    @FXML // fx:id="moviePoster"
    private ImageView moviePoster; // Value injected by FXMLLoader

    @FXML // fx:id="movienamelbl"
    private Label movienamelbl; // Value injected by FXMLLoader

    @FXML // fx:id="moviecatagerylbl"
    private Label moviecatagerylbl; // Value injected by FXMLLoader

    @FXML // fx:id="movieimdblbl"
    private Label movieimdblbl; // Value injected by FXMLLoader

    @FXML // fx:id="moviefranchiselbl"
    private Label moviefranchiselbl; // Value injected by FXMLLoader

    @FXML // fx:id="movieproducerlbl"
    private Label movieproducerlbl; // Value injected by FXMLLoader

    @FXML // fx:id="moviestudiolbl"
    private Label moviestudiolbl; // Value injected by FXMLLoader

    @FXML // fx:id="moviedescriptionltextarea"
    private JFXTextArea moviedescriptionltextarea; // Value injected by FXMLLoader

    @FXML // fx:id="movieSearchBar"
    private JFXTextField movieSearchBar; // Value injected by FXMLLoader

    @FXML // fx:id="movieList"
    private JFXListView<String> movieList; // Value injected by FXMLLoader

    @FXML // fx:id="moviePoster1"
    private ImageView moviePoster1; // Value injected by FXMLLoader

    @FXML // fx:id="movienamelbl1"
    private Label movienamelbl1; // Value injected by FXMLLoader

    @FXML // fx:id="moviecatagerylbl1"
    private Label moviecatagerylbl1; // Value injected by FXMLLoader

    @FXML // fx:id="movieimdblbl1"
    private Label movieimdblbl1; // Value injected by FXMLLoader

    @FXML // fx:id="moviefranchiselbl1"
    private Label moviefranchiselbl1; // Value injected by FXMLLoader

    @FXML // fx:id="movieproducerlbl1"
    private Label movieproducerlbl1; // Value injected by FXMLLoader

    @FXML // fx:id="moviestudiolbl1"
    private Label moviestudiolbl1; // Value injected by FXMLLoader

    @FXML // fx:id="moviedescriptionltextarea1"
    private JFXTextArea moviedescriptionltextarea1; // Value injected by FXMLLoader

    @FXML // fx:id="movieSearchBar1"
    private JFXTextField movieSearchBar1; // Value injected by FXMLLoader

    @FXML // fx:id="movieList1"
    private JFXListView<String> movieList1; // Value injected by FXMLLoader

    @FXML // fx:id="movieManageAP"
    private AnchorPane movieManageAP; // Value injected by FXMLLoader

    @FXML // fx:id="movieIDField"
    private JFXTextField movieIDField; // Value injected by FXMLLoader

    @FXML // fx:id="movieNameField"
    private JFXTextField movieNameField; // Value injected by FXMLLoader

    @FXML // fx:id="movieCatageryField"
    private JFXTextField movieCatageryField; // Value injected by FXMLLoader

    @FXML // fx:id="movieIMDBField"
    private JFXTextField movieIMDBField; // Value injected by FXMLLoader

    @FXML // fx:id="movieFranchiseCombo"
    private JFXComboBox<String> movieFranchiseCombo; // Value injected by FXMLLoader

    @FXML // fx:id="movieProducerCombo"
    private JFXComboBox<String> movieProducerCombo; // Value injected by FXMLLoader

    @FXML // fx:id="movieStudioCombo"
    private JFXComboBox<String> movieStudioCombo; // Value injected by FXMLLoader

    @FXML // fx:id="moviedescriptionltextareaEdit"
    private JFXTextArea moviedescriptionltextareaEdit; // Value injected by FXMLLoader

    @FXML // fx:id="movieURLField"
    private JFXTextField movieURLField; // Value injected by FXMLLoader

    @FXML // fx:id="moviePosterEdit"
    private ImageView moviePosterEdit; // Value injected by FXMLLoader

    @FXML // fx:id="movieSearchKeyowrd"
    private JFXTextField movieSearchKeyowrd; // Value injected by FXMLLoader

    @FXML // fx:id="movieTable"
    private JFXTreeTableView<movieTreeObject> movieTable; // Value injected by FXMLLoader
    @FXML
    private JFXTabPane mainTabPane;
    @FXML
    private Tab movieTab1;
    @FXML
    private JFXTabPane movieTabPane;
    @FXML
    private Tab movieManageTab;
    @FXML
    private Tab movieSearchTab;
    @FXML
    private ImageView movieNextIcon;
    @FXML
    private ImageView moviePreviusImage;
    @FXML
    private ImageView movieRefreshImage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshMovies("");

        CodingUtils.update_Combo_Box("franchises", movieFranchiseCombo);
        CodingUtils.update_Combo_Box("studios", movieStudioCombo);
        CodingUtils.update_Combo_Box("producers", movieProducerCombo);

        movieIDField.setText(CodingUtils.getID("movies") + "");

        setMovieTableCells();

    }

    void setMovieTableCells() {
        JFXTreeTableColumn<movieTreeObject, Integer> idCol = new JFXTreeTableColumn<movieTreeObject, Integer>("ID");
        idCol.setPrefWidth(70);
        idCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("id"));

        JFXTreeTableColumn<movieTreeObject, String> nameCol = new JFXTreeTableColumn<movieTreeObject, String>("Name");
        nameCol.setPrefWidth(250);
        nameCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));

        JFXTreeTableColumn<movieTreeObject, String> imdbCol = new JFXTreeTableColumn<movieTreeObject, String>("IMDB");
        imdbCol.setPrefWidth(250);
        imdbCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("imdbRating"));

        JFXTreeTableColumn<movieTreeObject, String> catageryCol = new JFXTreeTableColumn<movieTreeObject, String>("Catagery");
        catageryCol.setPrefWidth(250);
        catageryCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("catagery"));

        JFXTreeTableColumn<movieTreeObject, String> producerCol = new JFXTreeTableColumn<movieTreeObject, String>("Producer");
        producerCol.setPrefWidth(250);
        producerCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("producer"));

        JFXTreeTableColumn<movieTreeObject, String> franchiseCol = new JFXTreeTableColumn<movieTreeObject, String>("Franchise");
        franchiseCol.setPrefWidth(250);
        franchiseCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("franchise"));

        JFXTreeTableColumn<movieTreeObject, String> studioCol = new JFXTreeTableColumn<movieTreeObject, String>("Studio");
        studioCol.setPrefWidth(250);
        studioCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("studio"));

        movieTable.getColumns().addAll(idCol, nameCol, imdbCol, catageryCol, franchiseCol, producerCol, studioCol);

    }

    public void refreshMovies(String keyword) {
        try {
            movieObservableList.remove(0, movieObservableList.size());
            movieNameList.remove(0, movieNameList.size());

            ResultSet movieSet = MyConn.search("select * from movies where name like '" + keyword + "%' order by name asc ");

            while (movieSet.next()) {
                movieNameList.add(movieSet.getString(2));
                ResultSet franchiseSet = MyConn.search("select name from franchises where id = " + movieSet.getString(6) + " ");
                while (franchiseSet.next()) {
                    ResultSet producerSet = MyConn.search("select name from producers where id = " + movieSet.getString(7) + " ");
                    while (producerSet.next()) {
                        ResultSet studioSet = MyConn.search(" select name from studios where id = " + movieSet.getString(8) + " ");
                        while (studioSet.next()) {
                            movieObservableList.add(new Movie(movieSet.getInt(1), movieSet.getString(2), movieSet.getString(5), movieSet.getString(3),
                                    franchiseSet.getString(1), studioSet.getString(1), producerSet.getString(1), new Image(movieSet.getBinaryStream(9)), movieSet.getString(4)));
                        }
                    }
                }
            }
            movieList.refresh();
            movieList.setItems(movieNameList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void movieListMouseClickedAction(MouseEvent event) {
        for (int i = 0; i < movieObservableList.size(); i++) {
            if (movieObservableList.get(i).getName().equals(movieList.getSelectionModel().getSelectedItem())) {
                Movie selectedMovie = movieObservableList.get(i);
                moviePoster.setImage(selectedMovie.getImage());
                movienamelbl.setText(selectedMovie.getName());
                movieimdblbl.setText("IMDB : " + selectedMovie.getImdbRating());
                moviecatagerylbl.setText(selectedMovie.getCatagery());
                moviedescriptionltextarea.setText(selectedMovie.getDescription());
                movieproducerlbl.setText("Producer : " + selectedMovie.getProducer());
                moviestudiolbl.setText("By " + selectedMovie.getStudio());
                moviefranchiselbl.setText("Franchise : " + selectedMovie.getFranchise());
            }
        }

    }

    @FXML
    private void overview_Movie_Poster_Imageview_Mouse_Scrolled_Action(ScrollEvent event) {
        moviePoster.setFitHeight(moviePoster.getFitHeight() + event.getDeltaY());
        moviePoster.setFitWidth(moviePoster.getFitWidth() + event.getDeltaY());
    }

    @FXML
    private void edit_Movie_Poster_Imageview_Mouse_Scrolled_Action(ScrollEvent event) {
        moviePosterEdit.setFitHeight(moviePosterEdit.getFitHeight() + event.getDeltaY());
        moviePosterEdit.setFitWidth(moviePosterEdit.getFitWidth() + event.getDeltaY());
    }

    @FXML
    private void browseMovieImageAction(ActionEvent event) {
        moviePosterEdit.setImage(CodingUtils.browse_Image(mainAP));
    }

    @FXML
    private void addmovieAction(ActionEvent event) {

        try {
            Integer.parseInt(movieIDField.getText());
            try {
                Double.parseDouble(movieIMDBField.getText());
                if (movieNameField.getText().equals("")) {
                    CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Not Enough Data", "Please enter Movie Name");
                } else if (Double.parseDouble(movieIMDBField.getText()) > 10) {
                    CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Invalid IMDB Rating Value", "Please enter a Valid IMDB Rating");
                } else if (movieProducerCombo.getSelectionModel().getSelectedIndex() == -1) {
                    CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Not Enough Data", "Please Select the Producer od the Movie");
                } else if (movieStudioCombo.getSelectionModel().getSelectedIndex() == -1) {
                    CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Not Enough Data", "Please Select Movie owner Studio");
                } else if (movieFranchiseCombo.getSelectionModel().getSelectedIndex() == -1) {
                    CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Not Enough Data", "Please Select Franchise that Movie Belongs");
                } else if (moviePosterEdit.getImage() == null) {
                    CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Not Enough Data", "Please add Movie Poster");
                } else {
                    Movie movie = new Movie(Integer.parseInt(movieIDField.getText()), movieNameField.getText(), movieCatageryField.getText(), moviedescriptionltextareaEdit.getText(),
                            movieFranchiseCombo.getSelectionModel().getSelectedItem().split(" - ")[0], movieStudioCombo.getSelectionModel().getSelectedItem().split(" - ")[0],
                            movieProducerCombo.getSelectionModel().getSelectedItem().split(" - ")[0], moviePosterEdit.getImage(), movieIMDBField.getText());
                    try {
                        movieTableCodings.addMovie(movie);
                        CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Completed", "Movie " + movieNameField.getText() + " has been added to database Successfully");
                    } catch (Exception ex) {
                        CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Failed to add new Movie", "Error : " + ex.toString());
                    }
                }
            } catch (NumberFormatException e) {
                CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Invalid IMDB Rating Value", "Please Enter a valid IMDB Rating");
            }
        } catch (NumberFormatException e) {
            CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Invalid ID", "Please Enter a valid ID");
        }

    }

    @FXML
    private void editMoiveAction(ActionEvent event) {
        //Update Database
        try {
            Integer.parseInt(movieIDField.getText());
            try {
                Double.parseDouble(movieIMDBField.getText());
                if (movieNameField.getText().equals("")) {
                    CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Not Enough Data", "Please enter Movie Name");
                } else if (Double.parseDouble(movieIMDBField.getText()) > 10) {
                    CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Invalid IMDB Rating Value", "Please enter a Valid IMDB Rating");
                } else if (movieProducerCombo.getSelectionModel().getSelectedIndex() == -1) {
                    CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Not Enough Data", "Please Select the Producer od the Movie");
                } else if (movieStudioCombo.getSelectionModel().getSelectedIndex() == -1) {
                    CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Not Enough Data", "Please Select Movie owner Studio");
                } else if (movieFranchiseCombo.getSelectionModel().getSelectedIndex() == -1) {
                    CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Not Enough Data", "Please Select Franchise that Movie Belongs");
                } else if (moviePosterEdit.getImage() == null) {
                    CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Not Enough Data", "Please add Movie Poster");
                } else {
                    Movie movie = new Movie(Integer.parseInt(movieIDField.getText()), movieNameField.getText(), movieCatageryField.getText(), moviedescriptionltextareaEdit.getText(),
                            movieFranchiseCombo.getSelectionModel().getSelectedItem().split(" - ")[0], movieStudioCombo.getSelectionModel().getSelectedItem().split(" - ")[0],
                            movieProducerCombo.getSelectionModel().getSelectedItem().split(" - ")[0], moviePosterEdit.getImage(), movieIMDBField.getText());
                    try {
                        movieTableCodings.updateMovie(movie);
                        CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Completed", "Movie " + movieNameField.getText() + " has been updated to database Successfully");
                    } catch (Exception ex) {
                        CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Failed to add new Movie", "Error : " + ex.toString());
                    }
                }
            } catch (Exception e) {
                CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Invalid IMDB Rating Value", "Please Enter a valid IMDB Rating");
            }
        } catch (Exception e) {
            CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Invalid ID", "Please Enter a valid ID");
        }
    }

    @FXML
    private void search_Movies_Action(ActionEvent event) {
        movieTableCodings.refreshMovies("", movieTreeList);
        final TreeItem<movieTreeObject> movieTableRoot = new RecursiveTreeItem<movieTreeObject>(movieTreeList, RecursiveTreeObject::getChildren);
        movieTable.setRoot(movieTableRoot);

        //
    }

    @FXML
    private void deleteMovieAction(ActionEvent event) {

        JFXButton yesBtn = new JFXButton("Yse");
        JFXButton noBtn = new JFXButton("No");

        yesBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CodingUtils.delete_from_Database("movies", movieIDField.getText());
                    CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Completed", "Movie " + movieNameField.getText() + " has been deleted from database Successfully");
                } catch (Exception ex) {
                    CodingUtils.showMaterialDialog(root, mainAP, new ArrayList<>(), "Failed to delete Movie " + movieNameField.getText(), "Error : " + ex.getMessage());
                }
            }
        });

        CodingUtils.showMaterialDialog(root, mainAP, Arrays.asList(yesBtn, noBtn), "Confrimation Delete", "Are you sure you wan to delete " + movieNameField.getText() + " movie from database ?");

    }

    @FXML
    private void newMovieAction(ActionEvent event) {
        movieIDField.setText(CodingUtils.getID("movies") + "");
        movieNameField.setText("");
        movieIMDBField.setText("");
        moviedescriptionltextareaEdit.setText("");
    }

    @FXML
    private void movieTableKeyRelesedAction(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            movieTreeObject selectedMovie = movieTable.getSelectionModel().getSelectedItem().getValue();
            movieIDField.setText(selectedMovie.getId() + "");
            movieNameField.setText(selectedMovie.getName());
            movieCatageryField.setText(selectedMovie.getCatagery());
            movieIMDBField.setText(selectedMovie.getImdbRating());
            moviePosterEdit.setImage(selectedMovie.getImage());
            moviedescriptionltextareaEdit.setText(selectedMovie.getDescription());

            int franchise_ID = 0, producer_ID = 0, studio_ID = 0;
            try {
                ResultSet idSet = MyConn.search("select Franchises_ID, Producers_ID, Studios_ID from movies where id = " + selectedMovie.getId() + " ");
                while (idSet.next()) {
                    franchise_ID = idSet.getInt(1);
                    producer_ID = idSet.getInt(2);
                    studio_ID = idSet.getInt(3);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            SingleSelectionModel franchiseSelectedModel = movieFranchiseCombo.getSelectionModel();
            franchiseSelectedModel.select(franchise_ID + " - " + selectedMovie.getFranchise());
            movieFranchiseCombo.setSelectionModel(franchiseSelectedModel);

            SingleSelectionModel studioSelecedModel = movieStudioCombo.getSelectionModel();
            studioSelecedModel.select(studio_ID + " - " + selectedMovie.getStudio());
            movieStudioCombo.setSelectionModel(studioSelecedModel);

            SingleSelectionModel producerSelectedModel = movieProducerCombo.getSelectionModel();
            producerSelectedModel.select(producer_ID + " - " + selectedMovie.getProducer());
            movieProducerCombo.setSelectionModel(producerSelectedModel);

            SingleSelectionModel<Tab> selectedTab = movieTabPane.getSelectionModel();
            selectedTab.select(movieManageTab);
            movieTabPane.setSelectionModel(selectedTab);

        }
    }
    
    /*
        elearning.lk >>> learn to learn >>>
        elearning.lk >>> learn to learn >>>
        elearning.lk >>> learn to learn >>>
        elearning.lk >>> learn to learn >>>
        elearning.lk >>> learn to learn >>>
    */
    
    //    elearning.lk >>> learn to learn >>>
    
    
    
    @FXML
    private void movie_Search_Bar_Key_Relesed_Action(KeyEvent event) {
        refreshMovies(movieSearchBar.getText());
    }

    @FXML
    private void movie_URL_Field_Key_Relesed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            ImageView sampleView = new ImageView(movieURLField.getText());
            moviePosterEdit.setImage(sampleView.getImage());
        }
    }

    @FXML
    private void nextIconMouseRelesedAction(MouseEvent event) {
        movieNextIcon.setFitHeight(60);
        movieNextIcon.setFitWidth(60);
    }

    @FXML
    private void nextIconMouseExitedAction(MouseEvent event) {
        movieNextIcon.setFitHeight(50);
        movieNextIcon.setFitWidth(50);
    }

    @FXML
    private void nextIconMouseEnteredAction(MouseEvent event) {
        movieNextIcon.setFitHeight(60);
        movieNextIcon.setFitWidth(60);
    }

    @FXML
    private void nextIconMouscCileckedAction(MouseEvent event) {
        MultipleSelectionModel<String> selectionModel = movieList.getSelectionModel();
        if (selectionModel.isSelected(movieNameList.size() - 1)) {
            selectionModel.selectFirst();
        } else {
            selectionModel.selectNext();
        }
        movieList.setSelectionModel(selectionModel);
        movieListMouseClickedAction(event);
    }

    @FXML
    private void nextIconMousePressedAction(MouseEvent event) {
        movieNextIcon.setFitHeight(55);
        movieNextIcon.setFitWidth(55);
    }

    @FXML
    private void moviePreviousImageMouseRelesed(MouseEvent event) {
        moviePreviusImage.setFitHeight(60);
        moviePreviusImage.setFitWidth(60);
    }

    @FXML
    private void moviePreviousImageMouseExited(MouseEvent event) {
        moviePreviusImage.setFitHeight(50);
        moviePreviusImage.setFitWidth(50);
    }

    @FXML
    private void moviePreviousImageMouseEntered(MouseEvent event) {
        moviePreviusImage.setFitHeight(60);
        moviePreviusImage.setFitWidth(60);
    }

    @FXML
    private void moviePreviousImageMouseClicked(MouseEvent event) {
        MultipleSelectionModel<String> selectionModel = movieList.getSelectionModel();
        if (selectionModel.isSelected(0)) {
            selectionModel.selectLast();
        } else {
            selectionModel.selectPrevious();
        }
        movieList.setSelectionModel(selectionModel);
        movieListMouseClickedAction(event);
    }

    @FXML
    private void moviePreviousImageMousePressed(MouseEvent event) {
        moviePreviusImage.setFitHeight(55);
        moviePreviusImage.setFitWidth(55);
    }

    @FXML
    private void movieRefreshImageMouseRelesed(MouseEvent event) {
        movieRefreshImage.setFitHeight(50);
        movieRefreshImage.setFitWidth(50);
    }

    @FXML
    private void movieRefreshImageMouseExited(MouseEvent event) {
        movieRefreshImage.setFitHeight(40);
        movieRefreshImage.setFitWidth(40);
    }

    @FXML
    private void movieRefreshImageMouseEntered(MouseEvent event) {
        movieRefreshImage.setFitHeight(50);
        movieRefreshImage.setFitWidth(50);
    }

    @FXML
    private void movieRefreshImageMouseClicked(MouseEvent event) {
        refreshMovies("");
    }

    @FXML
    private void movieRefreshImageMousePressed(MouseEvent event) {
        movieRefreshImage.setFitHeight(45);
        movieRefreshImage.setFitWidth(45);
    }

    @FXML
    private void movie_Search_Bar_Edit_Key_Relesed_Action(KeyEvent event) {
        movieTableCodings.refreshMovies(movieSearchKeyowrd.getText(), movieTreeList);
        final TreeItem<movieTreeObject> movieTableRoot = new RecursiveTreeItem<movieTreeObject>(movieTreeList, RecursiveTreeObject::getChildren);
        movieTable.setRoot(movieTableRoot);
    }

}
