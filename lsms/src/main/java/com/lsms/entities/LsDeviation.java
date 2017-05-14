
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
@Table(name = "ls_deviation")
public class LsDeviation implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int id ;
    
    @NotNull
    @Column(name = "start_time")
    private Time stTime ;
    
    @Column(name = "end_time")
    private Time enTime ;
    
    @NotNull
    @Column(name = "event_date")
    private Date eventDate ;
    
    @NotNull
    @Column(name = "dev_status")
    private boolean devStatus ;
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the eventDate
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate the eventDate to set
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return the devStatus
     */
    public boolean getDevStatus() {
        return devStatus;
    }

    /**
     * @param devStatus the devStatus to set
     */
    public void setDevStatus(boolean devStatus) {
        this.devStatus = devStatus;
    }
}
