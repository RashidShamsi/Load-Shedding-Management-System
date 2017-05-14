/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author furqan
 */

//@Named
@Stateless
public class SpecialCycleBean {

   private String cycleName ;
   private Date stTime ;
   private Date endTime ;
   private int load ;

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
     * @return the stTime
     */
    public Date getStTime() {
        return stTime;
    }

    /**
     * @param stTime the stTime to set
     */
    public void setStTime(Date stTime) {
        this.stTime = stTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the load
     */
    public int getLoad() {
        return load;
    }

    /**
     * @param load the load to set
     */
    public void setLoad(int load) {
        this.load = load;
    }
}
