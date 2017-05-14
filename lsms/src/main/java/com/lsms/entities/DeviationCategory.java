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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author furqan
 */
@Entity
@Table(name = "dev_category")
public class DeviationCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dev_cat_id")
    private int generatedId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dev_event")
    private LsDeviation devEvent ;

    @NotNull
    @OneToOne
    @JoinColumn(name = "dev_grid_id")
    private Grids gridId ;
    
    @NotNull
    @OneToOne
    @JoinColumn(name = "dev_category")
    private Categories devCat ;
    
    @NotNull
    @Column(name = "feeders")
    private int feeders ;
    
    @NotNull
    @Column(name = "cat_load")
    private int load ;

    /**
     * @return the generatedId
     */
    public int getGeneratedId() {
        return generatedId;
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
     * @return the load
     */
    public int getLoad() {
        return load;
    }

    /**
     * @param load the load to set
     */
    public void setLoad(int load) {
        this.load = load;
    }

    /**
     * @return the devEvent
     */
    public LsDeviation getDevEvent() {
        return devEvent;
    }

    /**
     * @param devEvent the devEvent to set
     */
    public void setDevEvent(LsDeviation devEvent) {
        this.devEvent = devEvent;
    }

    /**
     * @return the feeders
     */
    public int getFeeders() {
        return feeders;
    }

    /**
     * @param feeders the feeders to set
     */
    public void setFeeders(int feeders) {
        this.feeders = feeders;
    }
    
}
