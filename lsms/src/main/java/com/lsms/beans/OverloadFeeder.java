/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.Feeder;
import java.sql.Time;
import java.util.Date;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author furqan
 */
@Named
public final class OverloadFeeder {

    EntityManager em ;
    
    Query q ;
    
    private Feeder fd ;
    private Date onTime ;
    private Date offTime ;
    private Time schOnTime ;
    private Time schOffTime ;
    private int load ;

    public OverloadFeeder(){}
    
    public OverloadFeeder(String feederName, String cycleName, EntityManager eman){
        System.out.println(feederName);
        System.out.println(cycleName);
        em = eman ;
        setFd(feederName);
        setSchOffTime(cycleName);
        setSchOnTime(cycleName);
    }
    
    /**
     * @return the fd
     */
    public Feeder getFd() {
        return fd;
    }

    /**
     * @param fdName the fd to set
     */
    public void setFd(String fdName) {
        try {
            q = em.createQuery("SELECT f FROM Feeder f WHERE f.feedName = :fn").
                    setParameter("fn", fdName);
            this.fd = (Feeder)q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Exception int the method setFd(" + fdName + ")" + " " + e);
        }
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
     * @return the schOnTime
     */
    public Time getSchOnTime() {
        return schOnTime;
    }

    /**
     * @param cn
     * This function takes cycle name as parameter and initializes the schOnTime
     * (Scheduled on time) field with the time retrieved from the database. 
     * This function uses the field fd(feeder) to retrieve category id, group id,
     * to select the time
     */
    public void setSchOnTime(String cn) {
        try {
            q = em.createQuery("SELECT lst.onTime FROM LsCycleTime lst WHERE lst.ctId = :cat AND lst.cycleId.cycName = :cycn and lst.groupId = :gpid").
                    setParameter("cat", getFd().getCategoryId()).
                    setParameter("cycn", cn).
                    setParameter("gpid", getFd().getGroupId());
            this.schOnTime = (Time)q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Exception in the function setSchOnTime()" + e);
        }
    }

    /**
     * @return the schOffTime
     */
    public Time getSchOffTime() {
        return schOffTime;
    }

    /**
     * @param cn
     * This function takes cycle name as parameter and initializes the schOffTime
     * (Scheduled on time) field with the time retrieved from the database. 
     * This function uses the field fd(feeder) to retrieve category id, group id,
     * to select the time
     */
    public void setSchOffTime(String cn) {
        try {
            q = em.createQuery("SELECT lst.offTime FROM LsCycleTime lst WHERE lst.ctId = :cat AND lst.cycleId.cycName = :cycn and lst.groupId = :gpid").
                    setParameter("cat", getFd().getCategoryId()).
                    setParameter("cycn", cn).
                    setParameter("gpid", getFd().getGroupId());
            this.schOffTime = (Time)q.getSingleResult();
        } catch (Exception e) {
            System.out.println("exception at function setSchOffTime()" + e);
        }
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
