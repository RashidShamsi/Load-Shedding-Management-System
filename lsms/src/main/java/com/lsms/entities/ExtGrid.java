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
@Table(name = "extended_grid")
public class ExtGrid implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ext_id")
    private int gridExtensionId ;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "event_id")
    private ExtendedLs extEventId ;
    
    @NotNull
    @OneToOne
    @JoinColumn(name = "grid_id")
    private Grids extGridId ;
    
    @NotNull
    @Column(name = "grid_load")
    private int gridLoad ;

    /**
     * @return the gridExtensionId
     */
    public int getGridExtensionId() {
        return gridExtensionId;
    }

    /**
     * @return the extEventId
     */
    public ExtendedLs getExtEventId() {
        return extEventId;
    }

    /**
     * @param extEventId the extEventId to set
     */
    public void setExtEventId(ExtendedLs extEventId) {
        this.extEventId = extEventId;
    }

    /**
     * @return the extGridId
     */
    public Grids getExtGridId() {
        return extGridId;
    }

    /**
     * @param extGridId the extGridId to set
     */
    public void setExtGridId(Grids extGridId) {
        this.extGridId = extGridId;
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