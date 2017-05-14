/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.ExtGrid;
import com.lsms.entities.Grids;
import com.lsms.entities.LsDetails;
import com.lsms.entities.UnscheduledLs;
import com.lsms.entities.UnscheduledLsGrids;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author furqan
 */
@Stateless
@Named
public class UnscheduledLsBean implements Serializable{

    @PersistenceContext
    EntityManager em ;
    
    Query q ;
    
    private Date startTime ;  
    private Date endTime ;
    private Date eventDate ;
    private String reason ;
    /*
    ** this list is used to store the grids which are unscheduly loadshedded
    ** this list will be persisted in the UnscheduledLsGrids entity
    */
    private List<UnscheduledLsGrids> uslg = new ArrayList<UnscheduledLsGrids>();
    
    /* list categories is used to get the current categories
        and list selectedCategories is used to store the selected categories
        for extension
    */
    private List<String> grids ;
    private List<String> selectedGrids ;
    private int loadSum ;

    /*
    ** functionality of unschedule ls starts from here
    */
    public void createUlsEvent(){
        System.out.println("Starting createUlEvent() function");
        UnscheduledLs event = persistEvent();
        persistUlsGrids(event);
        lsDetailCreater(event);
    }
    
    private UnscheduledLs persistEvent(){
        System.out.println("Starting persistEvent() function");
        UnscheduledLs uls = new UnscheduledLs();
        try {
            uls.setStartTime(new Time(startTime.getHours(), startTime.getMinutes(), startTime.getSeconds()));
            uls.setEndTime(new Time(endTime.getHours(), endTime.getMinutes(), endTime.getSeconds()));
            uls.setReason(getReason());
            uls.setEntryDate(new java.sql.Date(new java.util.Date().getTime()));
            uls.setUlsStatus(true);
            em.persist(uls);
            em.flush();
            System.out.println("Unscheduled event id :"+uls.getId());
        } catch (PersistenceException pe) {
            System.out.println("Persistence exception at persistEvent()" + pe);
        }
        return uls;
    }
    
    private void persistUlsGrids(UnscheduledLs uls){
        System.out.println("Starting persistUlsGrids() function");
        try {
            loadSum = 0 ;
              for (UnscheduledLsGrids ug : uslg) {
                Grids g = em.find(Grids.class, ug.getUslGridId().getGridId());
                g.setUnSchLs(true);
                ug.setUslEventId(uls);
                loadSum = loadSum + ug.getGridLoad();
                em.persist(ug);
            }
        } catch (PersistenceException pe) {
            System.out.println("Persistence exception at persistExtGrid() function" + pe);
        }
    }
    
    private void lsDetailCreater(UnscheduledLs event){
            try {
                System.out.println("****************************************");
                System.out.println("Starting lsDetailCreater() to log the newly");
                System.out.println("created unscheduled ls event into the details");
                System.out.println("****************************************");
                q = em.createQuery("SELECT MAX(lsd.detailId) FROM LsDetails lsd");
                LsDetails detail = em.find(LsDetails.class, (Integer)q.getSingleResult());
                if(     new Date().getDate() == detail.getEntryDate().getDate()
                        && new Date().getMonth() == detail.getEntryDate().getMonth()
                        && new Date().getYear() == detail.getEntryDate().getYear()){
                    System.out.println(loadSum);
                    System.out.println("Dates matched, events are of same date");
                    LsDetails newDetail = new LsDetails();
                    newDetail.setEntryDate(event.getEntryDate());
                    newDetail.setStartTime(event.getStartTime());
                    q = em.createQuery("SELECT f.value FROM PowerFactor f WHERE f.inUse = TRUE");
                    newDetail.setMwhLoad((loadSum/(Integer)q.getSingleResult()) + detail.getMwhLoad());
                    newDetail.setReason("Unscheduled Ls");
                    em.persist(newDetail);
                    em.find(LsDetails.class, detail.getDetailId()).setEndTime(newDetail.getStartTime());
                }else{
                    System.out.println("Dates didn't matched, events are not of same date");
                    LsDetails newDetail = new LsDetails();
                    newDetail.setEntryDate(event.getEntryDate());
                    newDetail.setStartTime(event.getStartTime());
                    newDetail.setMwhLoad(loadSum/(Integer)q.getSingleResult());
                    newDetail.setReason("Unscheduled Ls");
                    em.persist(newDetail);
                }
            } catch (Exception e) {
                System.out.println("No previous event is present, running catch");
                    LsDetails newDetail = new LsDetails();
                    newDetail.setEntryDate(event.getEntryDate());
                    newDetail.setStartTime(event.getStartTime());
                    newDetail.setMwhLoad(loadSum/(Integer)q.getSingleResult());
                    newDetail.setReason("Unscheduled Ls");
                    em.persist(newDetail);
            }
        }
    
    public void gridSelectionFunction(){
        System.out.println("****updating the selection of grids*****");
        uslg.clear();
        try {
            for (String gridName : selectedGrids) {
                q = em.createQuery("SELECT g FROM Grids g WHERE g.gridName = :gn").setParameter("gn", gridName);
                UnscheduledLsGrids uGrids = new UnscheduledLsGrids();
                uGrids.setUslGridId((Grids)q.getSingleResult());
                uslg.add(uGrids);
            }
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception at gridExtensionFunction");
        } catch(PersistenceException pe){
            System.out.println("Persistence exception at gridExtensionFunction()" + pe);
        }
    }
    
    /*
    **
    **
    **
    ** getter and setter methods
    **
    **
    */
    
    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    
    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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
     * @return the eventDate
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate the eventDate to set
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
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
     * @return the uslg
     */
    public List<UnscheduledLsGrids> getUslg() {
        return uslg;
    }

    /**
     * @param uslg the uslg to set
     */
    public void setUslg(List<UnscheduledLsGrids> uslg) {
        this.uslg = uslg;
    }

    /**
     * @return the grids
     */
    public List<String> getGrids() {
        try {
            System.out.println("***Selecting grids by running getGrids()***");
            q = em.createQuery("SELECT g.gridName FROM Grids g  WHERE g.extensionStatus = FALSE AND g.unSchLs = FALSE ");
            setGrids(q.getResultList());
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception in selecting grids" + e);
        }
        return grids;
    }

    /**
     * @param grids the grids to set
     */
    public void setGrids(List<String> grids) {
        this.grids = grids;
    }

    /**
     * @return the selectedGrids
     */
    public List<String> getSelectedGrids() {
        return selectedGrids;
    }

    /**
     * @param selectedGrids the selectedGrids to set
     */
    public void setSelectedGrids(List<String> selectedGrids) {
        this.selectedGrids = selectedGrids;
    }
    
    
}
