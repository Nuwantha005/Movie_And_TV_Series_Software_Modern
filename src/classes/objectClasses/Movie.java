/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.objectClasses;

import javafx.scene.image.Image;

/**
 *
 * @author Nuwantha Kumara
 */
public class Movie {
    private int id ;
    private String name , catagery , description , franchise , studio , producer ;
    private Image image ;
    private String imdbRating ;
    public Movie(int id, String name, String catagery, String description, String franchise, String studio, String producer, Image image, String imdbRating) {
        this.id = id;
        this.name = name;
        this.catagery = catagery;
        this.description = description;
        this.franchise = franchise;
        this.studio = studio;
        this.producer = producer;
        this.image = image;
        this.imdbRating = imdbRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatagery() {
        return catagery;
    }

    public void setCatagery(String catagery) {
        this.catagery = catagery;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFranchise() {
        return franchise;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

}
