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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author furqan
 */
@Entity
@Table(name = "ls_priority")
public class LsPriority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "feeder_id")
    private Feeder feeder ;
    
    @NotNull
    @Column(name = "start_date")
    private Date stDate ;
    
    @Column(name = "end_date")
    private Date endDate ;
    
    @NotNull
    @Column(name = "start_time")
    private Time stTime ;
    
    @NotNull
    @Column(name = "end_time")
    private Time endTime ;
    
    @Column(name = "informed_to")
    private String sboName ;
    
    public int getId() {
        return id;
    }
    
    @NotNull
    @Column(name = "status")
    private boolean priorityStatus ;

    /**
     * @return the feeder
     */
    public Feeder getFeeder() {
        return feeder;
    }

    /**
     * @param feeder the feeder to set
     */
    public void setFeeder(Feeder feeder) {
        this.feeder = feeder;
    }

    /**
     * @return the stDate
     */
    public Date getStDate() {
        return stDate;
    }

    /**
     * @param stDate the stDate to set
     */
    public void setStDate(Date stDate) {
        this.stDate = stDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the stTime
     */
    public Time getStTime() {
        return stTime;
    }

    /**
     * @param sTime the stTime to set
     */
    public void setStTime(Time sTime) {
        this.stTime = sTime;
    }

    /**
     * @return the endTime
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * @param eTime the endTime to set
     */
    public void setEndTime(Time eTime) {
        this.endTime = eTime;
    }

    /**
     * @return the sboName
     */
    public String getSboName() {
        return sboName;
    }

    /**
     * @param sboName the sboName to set
     */
    public void setSboName(String sboName) {
        this.sboName = sboName;
    }

    /**
     * @return the priorityStatus
     */
    public boolean getPriorityStatus() {
        return priorityStatus;
    }

    /**
     * @param priorityStatus the priorityStatus to set
     */
    public void setPriorityStatus(boolean priorityStatus) {
        this.priorityStatus = priorityStatus;
    }

    
}
