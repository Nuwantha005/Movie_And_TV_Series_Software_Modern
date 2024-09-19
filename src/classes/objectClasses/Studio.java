/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.objectClasses;

import java.sql.Date;
import javafx.scene.image.Image;

/**
 *
 * @author Nuwantha Kumara
 */
public class Studio {

    private int studio_ID;
    private String studio_Name, Studio_Description ,studio_Started_Date;

    public Studio(int studio_ID,  String studio_Name,String studio_Started_Date, String Studio_Description, Image studio_Badge) {
        this.studio_ID = studio_ID;
        this.studio_Started_Date = studio_Started_Date;
        this.studio_Name = studio_Name;
        this.Studio_Description = Studio_Description;
        this.studio_Badge = studio_Badge;
    }
    private Image studio_Badge;

    public int getStudio_ID() {
        return studio_ID;
    }

    public void setStudio_ID(int studio_ID) {
        this.studio_ID = studio_ID;
    }

    public String getStudio_Started_Date() {
        return studio_Started_Date;
    }

    public void setStudio_Started_Date(String studio_Started_Date) {
        this.studio_Started_Date = studio_Started_Date;
    }

    public String getStudio_Name() {
        return studio_Name;
    }

    public void setStudio_Name(String studio_Name) {
        this.studio_Name = studio_Name;
    }

    public String getStudio_Description() {
        return Studio_Description;
    }

    public void setStudio_Description(String Studio_Description) {
        this.Studio_Description = Studio_Description;
    }

    public Image getStudio_Badge() {
        return studio_Badge;
    }

    public void setStudio_Badge(Image studio_Badge) {
        this.studio_Badge = studio_Badge;
    }
}
