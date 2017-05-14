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
@Table(name = "sheded_category_data")
public class ShededCategory implements Serializable {
    
    @Id
    @Column(name = "data_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "group_id")
    private Groups groupId ;
    
    @NotNull
    @OneToOne
    @JoinColumn(name = "category_id")
    private Categories categoryId ;
    
    @NotNull
    @OneToOne
    @JoinColumn(name = "ls_cycle_id")
    private LsCycleTime cycleTimes ;
    
    @NotNull
    @Column(name = "total_mwh_shed")
    private int toalLoad ;
    
    @NotNull
    @Column(name = "total_feeders")
    private long feederCount ;
    
    @NotNull
    @Column(name = "entry_date")
    private Date entryDate ;
    
    @Column(name = "shedding_status")
    private boolean status ;
    
    public int getId() {
        return id;
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
     * @return the categoryId
     */
    public Categories getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(Categories categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the toalLoad
     */
    public int getToalLoad() {
        return toalLoad;
    }

    /**
     * @param toalLoad the toalLoad to set
     */
    public void setToalLoad(int toalLoad) {
        this.toalLoad = toalLoad;
    }

    /**
     * @return the feederCount
     */
    public long getFeederCount() {
        return feederCount;
    }

    /**
     * @param feederCount the feederCount to set
     */
    public void setFeederCount(long feederCount) {
        this.feederCount = feederCount;
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
     * @return the cycleTimes
     */
    public LsCycleTime getCycleTimes() {
        return cycleTimes;
    }

    /**
     * @param cycleTimes the cycleTimes to set
     */
    public void setCycleTimes(LsCycleTime cycleTimes) {
        this.cycleTimes = cycleTimes;
    }

    /**
     * @return the status
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

}
