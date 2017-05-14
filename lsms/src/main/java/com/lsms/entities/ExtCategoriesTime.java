/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.entities;

import java.io.Serializable;
import java.sql.Time;
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
@Table( name = "ext_cat_time")
public class ExtCategoriesTime implements Serializable {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private int extTimeId ;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "event_id")
    private ExtendedLs extEventId ;
    
    @NotNull
    @OneToOne
    @JoinColumn(name = "cat_id")
    private Categories extCatId ;
    
    @NotNull
    @Column(name = "ext_time")
    private Time extTime ;

    /**
     * @return the extTimeId
     */
    public int getExtTimeId() {
        return extTimeId;
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
     * @return the extCatId
     */
    public Categories getExtCatId() {
        return extCatId;
    }

    /**
     * @param extCatId the extCatId to set
     */
    public void setExtCatId(Categories extCatId) {
        this.extCatId = extCatId;
    }

    /**
     * @return the extTime
     */
    public Time getExtTime() {
        return extTime;
    }

    /**
     * @param extTime the extTime to set
     */
    public void setExtTime(Time extTime) {
        this.extTime = extTime;
    }
    
}
