/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.regularls.beans;

import com.lsms.entities.Categories;
import com.lsms.entities.Groups;
import com.lsms.entities.LsCycleTime;
import java.sql.Time;

/**
 *
 * @author furqan
 */

public class LsCategoryManager {
    
    private Groups group ;
    private Categories category ;
    private int totalLoad ;
    private long feederCount ;
    private LsCycleTime lsTime ;
    private Time offTime ;
    private Time onTime ;
    private String lsStatus ;
    private String presentStatus ;
    
    

    /**
     * @return the group
     */
    public Groups getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(Groups group) {
        this.group = group;
    }

    /**
     * @return the category
     */
    public Categories getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Categories category) {
        this.category = category;
    }

    /**
     * @return the totalLoad
     */
    public int getTotalLoad() {
        return totalLoad;
    }

    /**
     * @param totalLoad the totalLoad to set
     */
    public void setTotalLoad(int totalLoad) {
        this.totalLoad = totalLoad;
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
     * @return the lsTime
     */
    public LsCycleTime getLsTime() {
        return lsTime;
    }

    /**
     * @param lsTime the lsTime to set
     */
    public void setLsTime(LsCycleTime lsTime) {
        this.lsTime = lsTime;
    }

    /**
     * @return the lsStatus
     */
    public String getLsStatus() {
        return lsStatus;
    }

    /**
     * @param lsStatus the lsStatus to set
     */
    public void setLsStatus(String lsStatus) {
        this.lsStatus = lsStatus;
    }

    /**
     * @return the presentStatus
     */
    public String getPresentStatus() {
        return presentStatus;
    }

    /**
     * @param presentStatus the presentStatus to set
     */
    public void setPresentStatus(String presentStatus) {
        this.presentStatus = presentStatus;
    }

}
