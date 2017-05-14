/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author furqan
 */
@Entity
@Table(name = "sheded_grid_data")
public class ShededGrid implements Serializable {

    @Id
    @Column(name = "sheded_grid_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "sheded_category_id")
    private ShededCategory shededCategory ;

    @NotNull
    @OneToOne
    @JoinColumn(name = "grid_id")
    private Grids gridId ;
    
    @NotNull
    @Column(name =  "sheded_load")
    private int loadShed ;
    
    
    public int getId() {
        return id;
    }

    /**
     * @return the shededCategory
     */
    public ShededCategory getShededCategory() {
        return shededCategory;
    }

    /**
     * @param shededCategory the shededCategory to set
     */
    public void setShededCategory(ShededCategory shededCategory) {
        this.shededCategory = shededCategory;
    }

    /**
     * @return the gridId
     */
    public Grids getGridId() {
        return gridId;
    }

    /**
     * @param gridId the gridId to set
     */
    public void setGridId(Grids gridId) {
        this.gridId = gridId;
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
}