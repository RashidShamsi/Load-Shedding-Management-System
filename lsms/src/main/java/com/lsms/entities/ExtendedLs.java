/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author furqan
 */
@Entity
@Table(name = "extended_ls")
public class ExtendedLs implements Serializable {
  
    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Column(name = "start_time")
    private Time stTime ;
    
    @NotNull
    @Column(name = "end_time")
    private Time enTime ;
    
    @NotNull
    @Column(name = "event_date")
    private Date eventDate ;
    
    @Column(name = "reason")
    private String reason ;
    
    @Column(name = "ext_status")
    private boolean extStatus ;
      /**
     * @return the id
-     */
    public int getId() {
        return id;
    }

    /**
     * @return the stTime
     */
    public Time getStTime() {
        return stTime;
    }

    /**
     * @param stTime the stTime to set
     */
    public void setStTime(Time stTime) {
        this.stTime = stTime;
    }

    /**
     * @return the enTime
     */
    public Time getEnTime() {
        return enTime;
    }

    /**
     * @param enTime the enTime to set
     */
    public void setEnTime(Time enTime) {
        this.enTime = enTime;
    }

    /**
     * @return the entryDate
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * @param entryDate the entryDate to set
     */
    public void setEventDate(Date entryDate) {
        this.eventDate = entryDate;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the extStatus
     */
    public boolean getExtStatus() {
        return extStatus;
    }

    /**
     * @param extStatus the extStatus to set
     */
    public void setExtStatus(boolean extStatus) {
        this.extStatus = extStatus;
    }

}
    
