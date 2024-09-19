/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.objectClasses;

/**
 *
 * @author Nuwantha Kumara
 */
public class Franchise {
    private int franchise_ID ;
    private String franchise_Name;
    private String franchise_Description ;
    
    public Franchise(int  franchise_ID ,String franchise_Name , String franchise_Description){
        this.franchise_ID = franchise_ID ;
        this.franchise_Name = franchise_Name ;
        this.franchise_Description = franchise_Description ;
    }

    /**
     * @return the franchise_ID
     */
    public int getFranchise_ID() {
        return franchise_ID;
    }

    /**
     * @param franchise_ID the franchise_ID to set
     */
    public void setFranchise_ID(int franchise_ID) {
        this.franchise_ID = franchise_ID;
    }

    /**
     * @return the franchise_Name
     */
    public String getFranchise_Name() {
        return franchise_Name;
    }

    /**
     * @param franchise_Name the franchise_Name to set
     */
    public void setFranchise_Name(String franchise_Name) {
        this.franchise_Name = franchise_Name;
    }

    /**
     * @return the franchise_Description
     */
    public String getFranchise_Description() {
        return franchise_Description;
    }

    /**
     * @param franchise_Description the franchise_Description to set
     */
    public void setFranchise_Description(String franchise_Description) {
        this.franchise_Description = franchise_Description;
    }
    
}
