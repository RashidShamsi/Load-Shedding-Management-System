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
@Table(name = "un_sch_ls_grids")
public class UnscheduledLsGrids implements Serializable {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "event_id")
    private UnscheduledLs uslEventId ;
    
    @NotNull
    @OneToOne
    @JoinColumn(name = "grid_id")
    private Grids uslGridId ;
    
    @NotNull
    @Column(name = "grid_load")
    private int gridLoad ;
    
    public int getId() {
        return id;
    }

    /**
     * @return the uslEventId
     */
    public UnscheduledLs getUslEventId() {
        return uslEventId;
    }

    /**
     * @param uslEventId the uslEventId to set
     */
    public void setUslEventId(UnscheduledLs uslEventId) {
        this.uslEventId = uslEventId;
    }

    /**
     * @return the uslGridId
     */
    public Grids getUslGridId() {
        return uslGridId;
    }

    /**
     * @param uslGridId the uslGridId to set
     */
    public void setUslGridId(Grids uslGridId) {
        this.uslGridId = uslGridId;
    }

    /**
     * @return the gridLoad
     */
    public int getGridLoad() {
        return gridLoad;
    }

    /**
     * @param gridLoad the gridLoad to set
     */
    public void setGridLoad(int gridLoad) {
        this.gridLoad = gridLoad;
    }

    

       
}
