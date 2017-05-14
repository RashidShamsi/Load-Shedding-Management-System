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
@Table(name = "feeders")
public class Feeder implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feeder_id")
    private int feedId ;
    
    @NotNull
    @Column(name = "feeder_name")
    private String feedName ;
    
    @Column(name = "exempt")
    private boolean exemptionStatus;
    
    @Column(name = "reason")
    private String exemptReason ;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "trafo_id")
    private Transformer trafoId;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Categories categoryId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "grid_id")
    private Grids gridId ;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groups groupId ;
    
    @Column(name = "overload_status")
    private boolean ovlStatus ;

    @Column(name = "priority_status")
    private boolean lsPriority ;
    
    @Column(name = "special_gp_status")
    private boolean specialGroup ;
    
    /**
     * @return the feedId
     */
    public int getFeedId() {
        return feedId;
    }

    /**
     * @return the feedName
     */
    public String getFeedName() {
        return feedName;
    }

    /**
     * @param feedName the feedName to set
     */
    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }

    /**
     * @return the exemptionStatus
     */
    public boolean getExemptionStatus() {
        return exemptionStatus;
    }

    /**
     * @param exemptionStatus the exemptionStatus to set
     */
    public void setExemptionStatus(boolean exemptionStatus) {
        this.exemptionStatus = exemptionStatus;
    }

    /**
     * @return the exemptReason
     */
    public String getExemptReason() {
        return exemptReason;
    }

    /**
     * @param exemptReason the exemptReason to set
     */
    public void setExemptReason(String exemptReason) {
        this.exemptReason = exemptReason;
    }

    /**
     * @return the trafoId
     */
    public Transformer getTrafoId() {
        return trafoId;
    }

    /**
     * @param trafoId the trafoId to set
     */
    public void setTrafoId(Transformer trafoId) {
        this.trafoId = trafoId;
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
     * @return the gridId
     */
    public Grids getGridId() {
        return gridId;
    }

    /**
     * @param gridId the gridId to set
     */
    public void setGridId(Grids gridId) {
        this.gridId = gridId;
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
     * @return the ovlStatus
     */
    public boolean getOvlStatus() {
        return ovlStatus;
    }

    /**
     * @param ovlStatus the ovlStatus to set
     */
    public void setOvlStatus(boolean ovlStatus) {
        this.ovlStatus = ovlStatus;
    }

    /**
     * @return the lsPriority
     */
    public boolean getLsPriority() {
        return lsPriority;
    }

    /**
     * @param lsPriority the lsPriority to set
     */
    public void setLsPriority(boolean lsPriority) {
        this.lsPriority = lsPriority;
    }

    /**
     * @return the specialGroup
     */
    public boolean getSpecialGroup() {
        return specialGroup;
    }

    /**
     * @param specialGroup the specialGroup to set
     */
    public void setSpecialGroup(boolean specialGroup) {
        this.specialGroup = specialGroup;
    }
    
  
}
