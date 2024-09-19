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
public class Actor {
    private int id ;
    private String name , birthday,gender , situation  ;
    private Image image;

    public Actor(int id, String name, String birthday, String gender, String is_Active, Image image) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.situation = is_Active;
        this.image = image;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
   
    
    
    
}
