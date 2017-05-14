/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.regularls.beans;

import com.lsms.entities.Grids;
import com.lsms.entities.ShededCategory;

/**
 *
 * @author furqan
 */
public class LsGridLoadManager {
    
    private LsCategoryManager lsCategoryManager ;
    private Grids grid ;
    private int loadShed ;
    private int refLoad ;

    
    /**
     * @return the lsCategoryManager
     */
    public LsCategoryManager getLsCategoryManager() {
        return lsCategoryManager;
    }

    /**
     * @param lsCategoryManager the lsCategoryManager to set
     */
    public void setLsCategoryManager(LsCategoryManager lsCategoryManager) {
        this.lsCategoryManager = lsCategoryManager;
    }

    /**
     * @return the grid
     */
    public Grids getGrid() {
        return grid;
    }

    /**
     * @param grid the grid to set
     */
    public void setGrid(Grids grid) {
        this.grid = grid;
    }

    /**
     * @return the loadShed
     */
    public int getLoadShed() {
        return loadShed;
    }

    /**
     * @param loadShed the loadShed to set
     */
    public void setLoadShed(int loadShed) {
        this.loadShed = loadShed;
    }

    /**
     * @return the refLoad
     */
    public int getRefLoad() {
        return refLoad;
    }

    /**
     * @param refLoad the refLoad to set
     */
    public void setRefLoad(int refLoad) {
        this.refLoad = refLoad;
    }
}
