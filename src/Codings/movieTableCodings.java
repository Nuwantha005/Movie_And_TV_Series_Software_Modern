/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codings;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import classes.objectClasses.Movie;
import classes.objectClasses.MyConn;
import classes.recursiveTreeObjectClasses.movieTreeObject;
import java.sql.ResultSet;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 *
 * @author Nuwantha Kumara
 */
public class movieTableCodings {

    public static void addMovie(Movie movie) throws Exception{
            File outputFile = new File(System.getProperty("user.home") + "/testImg.jpg");
            outputFile.createNewFile();
            BufferedImage bufImg = SwingFXUtils.fromFXImage(movie.getImage(), null);
            ImageIO.write(bufImg, "jpg", outputFile);
            MyConn.save("INSERT INTO `movies_and_tv_series_database`.`movies` (`ID`, `Name`, `Description`, `IMDB_Rating`, `Catagery`, `Franchises_ID`, `Producers_ID`, `Studios_ID`) VALUES "
                    + " ('" + movie.getId() + "', '" +movie.getName()+ "', '" +movie.getDescription() + "', '" + movie.getImdbRating() + "',"
                    + " '" + movie.getCatagery() + "', '" +movie.getFranchise() + "',"
                    + " '" + movie.getProducer() + "', '" + movie.getStudio() + "')");
            PreparedStatement prepared_Statement = MyConn.c.prepareStatement("update movies set image = ? where id = '" + movie.getId()+ "' ");
            prepared_Statement.setBinaryStream(1, new FileInputStream(outputFile), (int) outputFile.length());
            prepared_Statement.executeUpdate();
            prepared_Statement.close();
            outputFile.delete();
    }
    
    public static void refreshMovies(String keyword ,ObservableList<movieTreeObject> movieObservableList) {
        try {
            movieObservableList.remove(0, movieObservableList.size());
            ResultSet movieSet = MyConn.search("select * from movies where name like '" + keyword + "%' ");
            while (movieSet.next()) {
                ResultSet franchiseSet = MyConn.search("select name from franchises where id = " + movieSet.getString(6) + " ");
                while (franchiseSet.next()) {
                    ResultSet producerSet = MyConn.search("select name from producers where id = " + movieSet.getString(7) + " ");
                    while (producerSet.next()) {
                        ResultSet studioSet = MyConn.search(" select name from studios where id = " + movieSet.getString(8) + " ");
                        while (studioSet.next()) {
                            movieObservableList.add(new movieTreeObject(movieSet.getInt(1), movieSet.getString(2), movieSet.getString(5), movieSet.getString(3),
                                    franchiseSet.getString(1), studioSet.getString(1), producerSet.getString(1), new Image(movieSet.getBinaryStream(9)), movieSet.getString(4)));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void updateMovie(Movie movie) throws Exception {
            File outputFile = new File(System.getProperty("user.home") + "/testImg.jpg");
            outputFile.createNewFile();
            BufferedImage bufImg = SwingFXUtils.fromFXImage(movie.getImage(), null);
            ImageIO.write(bufImg, "jpg", outputFile);
            MyConn.save("update movies set Name = '" + movie.getName() + "', Description = '" + movie.getDescription() + "' , IMDB_Rating = '" + movie.getImdbRating() + "' "
                    + " , Catagery = '" +movie.getCatagery() + "' , Franchises_ID =  '" + movie.getFranchise() + "'  "
                    + ", Producers_ID = '" + movie.getProducer() + "', Studios_ID = "
                    + " '" + movie.getStudio() + "' where ID = '" + movie.getId() + "' ");
            PreparedStatement prepared_Statement = MyConn.c.prepareStatement("update movies set image = ? where id = '" + movie.getId() + "' ");
            prepared_Statement.setBinaryStream(1, new FileInputStream(outputFile), (int) outputFile.length());
            prepared_Statement.executeUpdate();
            prepared_Statement.close();
            outputFile.delete();
        
    }
}
