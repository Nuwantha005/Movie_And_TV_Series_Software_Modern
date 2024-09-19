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
public class Character {
    
    private int id ;
    private String name , franchise  , description ;
    private Image image;

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

    public String getFranchise() {
        return franchise;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public Character(int id, String name, String franchise, String description, Image image) {
        this.id = id;
        this.name = name;
        this.franchise = franchise;
        this.description = description;
        this.image = image;
    }
   
}
