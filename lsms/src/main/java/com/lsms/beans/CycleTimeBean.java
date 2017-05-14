/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;


import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author furqan
 */
@Named
@Stateless
public class CycleTimeBean {

    @PersistenceContext
    EntityManager em ;
    
    private String cycleName ;
    private String groupName ;
    private String catName ;
    private Date offTime ;
    private Date onTime ;

    /**
     * @return the cycleName
     */
    public String getCycleName() {
        return cycleName;
    }

    /**
     * @param cycleName the cycleName to set
     */
    public void setCycleName(String cycleName) {
        this.cycleName = cycleName;
    }

    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return the catName
     */
    public String getCatName() {
        return catName;
    }

    /**
     * @param catName the catName to set
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }

    /**
     * @return the offTime
     */
    public Date getOffTime() {
        return offTime;
    }

    /**
     * @param offTime the offTime to set
     */
    public void setOffTime(Date offTime) {
        this.offTime = offTime;
    }

    /**
     * @return the onTime
     */
    public Date getOnTime() {
        return onTime;
    }

    /**
     * @param onTime the onTime to set
     */
    public void setOnTime(Date onTime) {
        this.onTime = onTime;
    }
    
}
