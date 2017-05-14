/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.entities;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name = "special_group_feeders")
public class SpecialGroupFeeder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "feeder_id")
    private Feeder feeder ;
    
    @NotNull
    @Column(name = "start_date")
    private Date startDate ;
    
    @NotNull
    @Column(name = "end_date")
    private Date endDate ;
    
    @NotNull
    @Column(name = "entry_date")
    private Date entryDate ;
    
    @NotNull
    @Column(name = "status")
    private boolean spgStatus ;
    
    public int getId() {
        return id;
    }

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
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    /**
     * @return the spgStatus
     */
    public boolean getSpgStatus() {
        return spgStatus;
    }

    /**
     * @param spgStatus the spgStatus to set
     */
    public void setSpgStatus(boolean spgStatus) {
        this.spgStatus = spgStatus;
    }

    
}
