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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author furqan
 */
@Entity
@Table(name = "ls_cycle_time")
public class LsCycleTime implements Serializable {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cyc_time_id")
    private int cycleTimeId ;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "gp_id")
    private Groups groupId ;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "ct_id")
    private Categories ctId ;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "cyc_id")
    private LsCycle cycleId ;
    
    @NotNull
    @Column(name = "on_time")
    private Time onTime;
    
    @NotNull
    @Column(name = "off_time")
    private Time offTime;

    /**
     * @return the cycleId
     */
    public int getCycleTimeId() {
        return cycleTimeId;
    }

    /**
     * @return the groupId
     */
    public Groups getGroupId() {
        return groupId;
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(Groups groupId) {
        this.groupId = groupId;
    }

    /**
     * @return the ctId
     */
    public Categories getCtId() {
        return ctId;
    }

    /**
     * @param ctId the ctId to set
     */
    public void setCtId(Categories ctId) {
        this.ctId = ctId;
    }

    /**
     * @return the onTime
     */
    public Time getOnTime() {
        return onTime;
    }

    /**
     * @param onTime the onTime to set
     */
    public void setOnTime(Time onTime) {
        this.onTime = onTime;
    }

    /**
     * @return the offTime
     */
    public Time getOffTime() {
        return offTime;
    }

    /**
     * @param offTime the offTime to set
     */
    public void setOffTime(Time offTime) {
        this.offTime = offTime;
    }

    /**
     * @return the cycleId
     */
    public LsCycle getCycleId() {
        return cycleId;
    }

    /**
     * @param cycleId the cycleId to set
     */
    public void setCycleId(LsCycle cycleId) {
        this.cycleId = cycleId;
    }
    
}
