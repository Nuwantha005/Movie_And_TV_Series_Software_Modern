/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.objectClasses;

import java.io.InputStream;
import javafx.scene.image.Image;

/**
 *
 * @author Nuwantha Kumara
 */
public class Fan {

    private int fan_ID;
    private double panti_Level, attack_Level, patience_Level, comic_Knowledge;
    private String fan_Name, special_Abilities, fan_Franchise_Name;
    private Image fanImage;

    public Image getFanImage() {
        return fanImage;
    }

    public void setFanImage(Image fanImage) {
        this.fanImage = fanImage;
    }

    public Fan(int fan_ID, double panti_Level, double attack_Level, double patience_Level, double comic_Knowledge, String fan_Name, String special_Abilities, String fan_Franchise_Name, Image fanImage) {
        this.fan_ID = fan_ID;
        this.panti_Level = panti_Level;
        this.attack_Level = attack_Level;
        this.patience_Level = patience_Level;
        this.comic_Knowledge = comic_Knowledge;
        this.fan_Name = fan_Name;
        this.special_Abilities = special_Abilities;
        this.fan_Franchise_Name = fan_Franchise_Name;
        this.fanImage = fanImage;
    }

    public int getFan_ID() {
        return fan_ID;
    }

    public void setFan_ID(int fan_ID) {
        this.fan_ID = fan_ID;
    }

    public double getPanti_Level() {
        return panti_Level;
    }

    public void setPanti_Level(double panti_Level) {
        this.panti_Level = panti_Level;
    }

    public double getAttack_Level() {
        return attack_Level;
    }

    public void setAttack_Level(double attack_Level) {
        this.attack_Level = attack_Level;
    }

    public double getPatience_Level() {
        return patience_Level;
    }

    public void setPatience_Level(double patience_Level) {
        this.patience_Level = patience_Level;
    }

    public double getComic_Knowledge() {
        return comic_Knowledge;
    }

    public void setComic_Knowledge(double comic_Knowledge) {
        this.comic_Knowledge = comic_Knowledge;
    }

    public String getFan_Name() {
        return fan_Name;
    }

    public void setFan_Name(String fan_Name) {
        this.fan_Name = fan_Name;
    }

    public String getSpecial_Abilities() {
        return special_Abilities;
    }

    public void setSpecial_Abilities(String special_Abilities) {
        this.special_Abilities = special_Abilities;
    }

    public String getFan_Franchise_Name() {
        return fan_Franchise_Name;
    }

    public void setFan_Franchise_Name(String fan_Franchise_Name) {
        this.fan_Franchise_Name = fan_Franchise_Name;
    }

}
