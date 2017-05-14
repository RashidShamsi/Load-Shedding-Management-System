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
@Table(name = "ls_details")
public class LsDetails implements Serializable {

    @Id
    @Column(name = "detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detailId;

    @NotNull
    @Column(name = "start_time")
    private Time startTime ;
    
    @Column(name = "end_time")
    private Time endTime ;
    
    @NotNull
    @Column(name = "sheded_MW")
    private int mwhLoad ;
    
    @NotNull
    @Column(name = "reason")
    private String reason ;

    @NotNull
    @Column(name = "entry_date")
    private Date entryDate ;
    
    /**
     * @return the detailId
     */
    public int getDetailId() {
        return detailId;
    }

    /**
     * @return the startTime
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the mwhLoad
     */
    public int getMwhLoad() {
        return mwhLoad;
    }

    /**
     * @param mwhLoad the mwhLoad to set
     */
    public void setMwhLoad(int mwhLoad) {
        this.mwhLoad = mwhLoad;
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
     * @return the entryDate
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * @param entryDate the entryDate to set
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
}
