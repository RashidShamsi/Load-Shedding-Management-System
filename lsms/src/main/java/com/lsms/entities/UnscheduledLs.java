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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author furqan
 */
@Entity
@Table(name = "unscheduled_ls")
public class UnscheduledLs implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int id;

    @NotNull
    @Column(name = "start_time")
    private Time st_Time ;
    
    @Column(name = "end_time")
    private Time en_Time ;
    
    @Column(name = "reason")
    private String reason;
    
    @NotNull
    @Column(name = "entry_date")
    private Date et_Date ;

    @NotNull
    @Column(name = "status")
    private boolean ulsStatus ;
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the st_Time
     */
    public Time getStartTime() {
        return st_Time;
    }

    /**
     * @param st_Time the st_Time to set
     */
    public void setStartTime(Time st_Time) {
        this.st_Time = st_Time;
    }

    /**
     * @return the en_Time
     */
    public Time getEndTime() {
        return en_Time;
    }

    /**
     * @param en_Time the en_Time to set
     */
    public void setEndTime(Time en_Time) {
        this.en_Time = en_Time;
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
     * @return the et_Date
     */
    public Date getEntryDate() {
        return et_Date;
    }

    /**
     * @param et_Date the et_Date to set
     */
    public void setEntryDate(Date et_Date) {
        this.et_Date = et_Date;
    }

    /**
     * @return the ulsStatus
     */
    public boolean getUlsStatus() {
        return ulsStatus;
    }

    /**
     * @param ulsStatus the ulsStatus to set
     */
    public void setUlsStatus(boolean ulsStatus) {
        this.ulsStatus = ulsStatus;
    }

}
    
