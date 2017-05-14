/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.Feeder;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author furqan
 */
@Named
@Stateless
public class SpecialFeederBean {

    public SpecialFeederBean(){}
    
    private Feeder spFeeder ;
    private Date startDate ;
    private Date endDate ;

    /**
     * @return the spFeeder
     */
    public Feeder getSpFeeder() {
        return spFeeder;
    }

    /**
     * @param spFeeder the spFeeder to set
     */
    public void setSpFeeder(Feeder spFeeder) {
        this.spFeeder = spFeeder;
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
}