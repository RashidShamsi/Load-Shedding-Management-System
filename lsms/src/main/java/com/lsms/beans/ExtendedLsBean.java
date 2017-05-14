/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.Categories;
import com.lsms.entities.ExtCategoriesTime;
import com.lsms.entities.ExtGrid;
import com.lsms.entities.ExtendedLs;
import com.lsms.entities.Grids;
import com.lsms.entities.LsDetails;
import com.lsms.entities.ShededCategory;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Schedule;
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
//@ManagedBean(name = "extendedBean")
@Named
@Stateless
public class ExtendedLsBean implements Serializable{

    @PersistenceContext
    EntityManager em ;
    
    private Query q ;
    
    private String reason ;
    private Date startTime ;
    private Date endTime ;
    
/*  this list is used to store the extended categories and 
    their timmings for ExtCategoriesTime entity */
    private List<ExtCatTime> ectList = new ArrayList<ExtCatTime>();

    /*  this list is used to store the extended grids for ExtGrid entity */
    private List<ExtGrid> egrdList = new ArrayList<ExtGrid>();
    
    /* list categories is used to get the current categories
        and list selectedCategories is used to store the selected categories
        for extension
    */
    private List<String> categories ;
    private List<String> selectedCategories ;
    
    /* list grids is used to get the current grids
        and list selectedGrids is used to store the selected grids
        for extension
    */
    private List<String> grids ;
    private List<String> selectedGrids ;
    
    private int loadSum ;

/* 
**
**
**========    functionality of extended ls bean starts here
**
**
**
**/    
    
    public void submitSelectedCats(){
        System.out.println("****updating the selection of Categories*****");
        ectList.clear();
        try {
            for (String c : selectedCategories) {
                q = em.createQuery("SELECT c FROM Categories c WHERE c.catName = :cn").setParameter("cn", c);
                ExtCatTime ect = new ExtCatTime();
                ect.setCat((Categories)q.getSingleResult());
                ectList.add(ect);
            }
        } catch (NullPointerException npe) {
            System.err.println("null pointer exception at submitSelectedCats function" + npe);
        } catch(PersistenceException pe){
            System.out.println("Persistence exception at submitSelectedCats()" + pe);
        }

    }
    
    public void gridExtensionFunction(){
        System.out.println("****updating the selection of grids*****");
        egrdList.clear();
        try {
            for (String gridName : selectedGrids) {
                q = em.createQuery("SELECT g FROM Grids g WHERE g.gridName = :gn").setParameter("gn", gridName);
                ExtGrid eg = new ExtGrid();
                eg.setExtGridId((Grids)q.getSingleResult());
                egrdList.add(eg);
            }
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception at gridExtensionFunction");
        } catch(PersistenceException pe){
            System.out.println("Persistence exception at gridExtensionFunction()" + pe);
        }
    }
   
    public void createEvent(){
        System.out.println("******starting createEvent() function********");
        ExtendedLs event = persistEvent();
        persistCatTime(event);
        persistExtGrid(event);
        lsDetailCreater(event);
    }
    /*
    ** this Function is used to persist ExtendedLs(the event)
    ** and return the auto generated event id, that id will be used by 
    ** all the other persisting functions will use that id to get event.
    */
    private ExtendedLs persistEvent(){
        System.out.println("persisting extended ls entity");
        ExtendedLs els = new ExtendedLs();
        try {
            els.setStTime(new Time(startTime.getHours(), startTime.getMinutes(), startTime.getSeconds()));
            els.setEnTime(new Time(endTime.getHours(), endTime.getMinutes(), endTime.getSeconds()));
            els.setReason(reason);
            els.setEventDate( new java.sql.Date(new java.util.Date().getTime()));
            els.setExtStatus(true);
            em.persist(els);
            em.flush();
            
        } catch (PersistenceException e) {
            System.out.println("Persistence exception at persist event function" + e);
        }
        return els;
    }
    
    
        
    

    
    /*
    ** this method is used for persisting the extended time of the categories.
    */
    private void persistCatTime(ExtendedLs event){
        try {
            System.out.println("persisting ExtCatTime entity");
            for (ExtCatTime ect : ectList) {
                ExtCategoriesTime ecTime = new ExtCategoriesTime();
                ecTime.setExtEventId(event);
                ecTime.setExtCatId(ect.getCat());
                ecTime.setExtTime(new Time(ect.getExtendedTime().getHours(), 
                        ect.getExtendedTime().getMinutes(), ect.getExtendedTime().getSeconds()));
                em.persist(ecTime);
            }
        } catch (PersistenceException pe) {
            System.out.println("Persistence exception at persistCatTimeFunction" + pe);
        }
    }
    
    /*
    ** this method is used to persist ExtGrid entity which will store the 
    ** data of grids selected for extension
    */
    private void persistExtGrid(ExtendedLs event){
        try {
            loadSum = 0 ;
              for (ExtGrid eg : egrdList) {
                Grids g = em.find(Grids.class, eg.getExtGridId().getGridId());
                g.setExtensionStatus(true   );
                eg.setExtEventId(event);
                loadSum = loadSum + eg.getGridLoad() ;
                em.persist(eg);
            }
        } catch (PersistenceException pe) {
            System.out.println("Persistence exception at persistExtGrid() function" + pe);
        }
    }
    
    private void lsDetailCreater(ExtendedLs event){
            try {
                System.out.println("****************************************");
                System.out.println("Starting lsDetailCreater() to log the newly");
                System.out.println("created extended ls event into the details");
                System.out.println("****************************************");
                q = em.createQuery("SELECT MAX(lsd.detailId) FROM LsDetails lsd");
                LsDetails detail = em.find(LsDetails.class, (Integer)q.getSingleResult());
                if(     new Date().getDate() == detail.getEntryDate().getDate()
                        && new Date().getMonth() == detail.getEntryDate().getMonth()
                        && new Date().getYear() == detail.getEntryDate().getYear()){
                    System.out.println(loadSum);
                    System.out.println("Dates matched, events are of same date");
                    LsDetails newDetail = new LsDetails();
                    newDetail.setEntryDate(event.getEventDate());
                    newDetail.setStartTime(event.getStTime());
                    q = em.createQuery("SELECT f.value FROM PowerFactor f WHERE f.inUse = TRUE");
                    newDetail.setMwhLoad((loadSum/(Integer)q.getSingleResult()) + detail.getMwhLoad());
                    newDetail.setReason("Extended Ls");
                    em.persist(newDetail);
                    em.find(LsDetails.class, detail.getDetailId()).setEndTime(newDetail.getStartTime());
                }else{
                    System.out.println("Dates didn't matched, events are not of same date");
                    LsDetails newDetail = new LsDetails();
                    newDetail.setEntryDate(event.getEventDate());
                    newDetail.setStartTime(event.getStTime());
                    newDetail.setMwhLoad(loadSum/(Integer)q.getSingleResult());
                    newDetail.setReason("Extended Ls");
                    em.persist(newDetail);
                }
            } catch (Exception e) {
                System.out.println("No previous event is present, running catch");
                    LsDetails newDetail = new LsDetails();
                    newDetail.setEntryDate(event.getEventDate());
                    newDetail.setStartTime(event.getStTime());
                    newDetail.setMwhLoad(loadSum/(Integer)q.getSingleResult());
                    newDetail.setReason("Extended Ls");
                    em.persist(newDetail);
            }
        }
    /*
    **  
    **
    ** ============ getters and setters =========
    **
    **
    **
    **/
    
    /**
     * @return the ectList
     */
    public List<ExtCatTime> getEctList() {
        return ectList;
    }

    /**
     * @param ectList the ectList to set
     */
    public void setEctList(List<ExtCatTime> ectList) {
        this.ectList = ectList;
    }
        
    /**
     * @return the categories
     */
    public List<String> getCategories() {
        q = em.createQuery("SELECT c.catName FROM Categories c");
        setCategories(q.getResultList());
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * @return the selectedCategories
     */
    public List<String> getSelectedCategories() {
        return selectedCategories;
    }

    /**
     * @param selectedCategories the selectedCategories to set
     */
    public void setSelectedCategories(List<String> selectedCategories) {
        this.selectedCategories = selectedCategories;
    }


    /**
     * @return the grids
     */
    public List<String> getGrids() {
        q = em.createQuery("SELECT g.gridName FROM Grids g WHERE g.extensionStatus = FALSE AND g.unSchLs = FALSE AND g.deviationStatus = FALSE");
        setGrids(q.getResultList());
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

    /**
     * @return the egrdList
     */
    public List<ExtGrid> getEgrdList() {
        return egrdList;
    }

    /**
     * @param egrdList the egrdList to set
     */
    public void setEgrdList(List<ExtGrid> egrdList) {
        this.egrdList = egrdList;
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
    
    // =========== getters and setters ==========
}
