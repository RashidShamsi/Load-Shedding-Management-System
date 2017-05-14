/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.Categories;
import com.lsms.entities.Grids;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author furqan
 */
@Named
@Stateless
public class DevCats {
  
    private Grids devGrid ;
    private Categories devCat ;
    private int catLoad ;
    private int NumberOfFeeders ;
    private int numOfFeedsDev ;
    
    
    /**
     * @return the devCat
     */
    public Categories getDevCat() {
        return devCat;
    }

    /**
     * @param devCat the devCat to set
     */
    public void setDevCat(Categories devCat) {
        this.devCat = devCat;
    }

    /**
     * @return the catLoad
     */
    public int getCatLoad() {
        return catLoad;
    }

    /**
     * @param catLoad the catLoad to set
     */
    public void setCatLoad(int catLoad) {
        this.catLoad = catLoad;
    }

    /**
     * @return the devGrid
     */
    public Grids getDevGrid() {
        return devGrid;
    }

    /**
     * @param devGrid the devGrid to set
     */
    public void setDevGrid(Grids devGrid) {
        this.devGrid = devGrid;
    }

    /**
     * @return the NumberOfFeeders
     */
    public int getNumberOfFeeders() {
        return NumberOfFeeders;
    }

    /**
     * @param NumberOfFeeders the NumberOfFeeders to set
     */
    public void setNumberOfFeeders(int NumberOfFeeders) {
        this.NumberOfFeeders = NumberOfFeeders;
    }

    /**
     * @return the numOfFeedsDev
     */
    public int getNumOfFeedsDev() {
        return numOfFeedsDev;
    }

    /**
     * @param numOfFeedsDev the numOfFeedsDev to set
     */
    public void setNumOfFeedsDev(int numOfFeedsDev) {
        this.numOfFeedsDev = numOfFeedsDev;
    }
    
}
