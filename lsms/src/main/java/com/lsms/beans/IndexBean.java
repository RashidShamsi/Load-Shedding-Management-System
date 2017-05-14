/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.DeviationCategory;
import com.lsms.entities.ExtCategoriesTime;
import com.lsms.entities.ExtGrid;
import com.lsms.entities.ExtendedLs;
import com.lsms.entities.LsDeviation;
import com.lsms.entities.LsPriority;
import com.lsms.entities.OverloadEvent;
import com.lsms.entities.OverloadingFeeder;
import com.lsms.entities.PriorityCycles;
import com.lsms.entities.UnscheduledLs;
import com.lsms.entities.UnscheduledLsGrids;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author furqan
 */
@Named
@Stateless
public class IndexBean {

   @PersistenceContext
   private EntityManager em ;
   
   private Query q ;
   
   /**
    * Extended Ls related member variables
    */
   
   private List<ExtendedLs> extendedEventList ;
   private List<ExtGrid> extendedEventGrids ;
   private List<ExtCategoriesTime> extendedEventCategories ;
   private ExtendedLs selectedExtEvent ;

   /**
    * Unscheduled Ls related member variables
    */
    
    private List<UnscheduledLs> uslEventsList ;
    private List<UnscheduledLsGrids> uslGrids ;
    
    /**
    * Overloading related member variables
    */
    
    private List<OverloadEvent> ovlEventsList ;
    private List<OverloadingFeeder> ovlFeeders ;
    
    /**
    * Deviation related member variables
    */
    
    private List<LsDeviation> devEventsList ;
    private List<DeviationCategory> devCategories ;
    
    /**
    * Ls priority related member variables
    */
    
    private List<LsPriority> priEventsList ;
    private List<PriorityCycles> priCycles ;
   
   /**
    * Functions for ending
    * the created events
    */
   
   public void onExtRowSelect(ExtendedLs event){
       try {
           selectedExtEvent = event ;
           System.out.println("Runing onExtRowSelect()");
            q = em.createQuery("SELECT g FROM ExtGrid g WHERE g.extEventId = :e")
                    .setParameter("e", selectedExtEvent);
            setExtendedEventGrids(q.getResultList());

            q = em.createQuery("SELECT c FROM ExtCategoriesTime c WHERE c.extEventId = :e")
                    .setParameter("e", selectedExtEvent);
            setExtendedEventCategories(q.getResultList());
       } catch (Exception e) {
           System.out.println("Exception in onExtRowSelect() of IndexBean" + e);
       }
   }
   
    public void stopExtEvent(ExtendedLs event){
        em.find(ExtendedLs.class, event.getId()).setExtStatus(false);
        
    }
       
    public void uslDetailFunction(UnscheduledLs u){
        try {
            q = em.createQuery("SELECT g FROM UnscheduledLsGrids g WHERE g.uslEventId = :e")
                    .setParameter("e", u);
            setUslGrids(q.getResultList());
        } catch (Exception e) {
            System.out.println("Exception in uslDetailFunction(UnscheduledLs u)" + e);
        }
    }
   
    public void stopUslEvent(UnscheduledLs event){
        em.find(UnscheduledLs.class, event.getId()).setUlsStatus(false);
    }
    
    public void ovlDetailFunction(OverloadEvent event){
        try {
            q = em.createQuery("SELECT f FROM OverloadingFeeder f WHERE f.ovlEvent = :e")
                    .setParameter("e", event);
            setOvlFeeders(q.getResultList());
        } catch (Exception e) {
            System.out.println("Exception in ovlDetailFunction()" + e);
        }
    }
   
    public void stopOvlEvent(OverloadEvent event){
        em.find(OverloadEvent.class, event.getOvlEventId()).setOvlStatus(false);
    }
    
    public void devDetailFunction(LsDeviation event){
        try {
            q = em.createQuery("SELECT c FROM DeviationCategory c WHERE c.devEvent = :e")
                    .setParameter("e", event);
            setDevCategories(q.getResultList());
        } catch (Exception e) {
            System.out.println("Exception in devDetailFunction()" + e);
        }
    }
   
    public void stopDevEvent(LsDeviation event){
        em.find(LsDeviation.class, event.getId()).setDevStatus(false);
    }
    
    public void priDetailFunction(LsPriority event){
        try {
            q = em.createQuery("SELECT c FROM PriorityCycles c WHERE c.priority = :e")
                    .setParameter("e", event);
            setPriCycles(q.getResultList());
        } catch (Exception e) {
            System.out.println("Exception in devDetailFunction()" + e);
        }
    }
   
    public void stopPriEvent(LsPriority event){
        em.find(LsPriority.class, event.getId()).setPriorityStatus(false);
    }
    
   //*******************************************
   
    /**
     * @return the extendedEventList
     */
    public List<ExtendedLs> getExtendedEventList() {
        try {
            System.out.println("Baby i've been ,i've been losing sleep");
            System.out.println("dreaming about the things that we could be");
            q = em.createQuery("SELECT e FROM ExtendedLs e WHERE e.extStatus = TRUE");
            extendedEventList = q.getResultList();

        } catch (Exception e) {
            System.out.println("Exception in getExtendedLsEventList()" + e);
        }
        return extendedEventList;
    }

    /**
     * @param extendedEventList the extendedEventList to set
     */
    public void setExtendedEventList(List<ExtendedLs> extendedEventList) {
        this.extendedEventList = extendedEventList;
    }

    /**
     * @return the selectedExtEvent
     */
    public ExtendedLs getSelectedExtEvent() {
        return selectedExtEvent;
    }

    /**
     * @param selectedExtEvent the selectedExtEvent to set
     */
    public void setSelectedExtEvent(ExtendedLs selectedExtEvent) {
        this.selectedExtEvent = selectedExtEvent;
    }

    /**
     * @return the extendedEventGrids
     */
    public List<ExtGrid> getExtendedEventGrids() {
        return extendedEventGrids;
    }

    /**
     * @param extendedEventGrids the extendedEventGrids to set
     */
    public void setExtendedEventGrids(List<ExtGrid> extendedEventGrids) {
        this.extendedEventGrids = extendedEventGrids;
    }

    /**
     * @return the extendedEventCategories
     */
    public List<ExtCategoriesTime> getExtendedEventCategories() {
        return extendedEventCategories;
    }

    /**
     * @param extendedEventCategories the extendedEventCategories to set
     */
    public void setExtendedEventCategories(List<ExtCategoriesTime> extendedEventCategories) {
        this.extendedEventCategories = extendedEventCategories;
    }

    /**
     * @return the uslEventsList
     */
    public List<UnscheduledLs> getUslEventsList() {
        try {
            System.out.println("Runing getUslEventsList()");
            q = em.createQuery("SELECT u FROM UnscheduledLs u WHERE u.ulsStatus = TRUE");
            setUslEventsList(q.getResultList());
        } catch (Exception e) {
            System.out.println("Exception in getUslEventsList() method" + e);
        }
        return uslEventsList;
    }

    /**
     * @param uslEventsList the uslEventsList to set
     */
    public void setUslEventsList(List<UnscheduledLs> uslEventsList) {
        this.uslEventsList = uslEventsList;
    }

    /**
     * @return the uslGrids
     */
    public List<UnscheduledLsGrids> getUslGrids() {
        return uslGrids;
    }

    /**
     * @param uslGrids the uslGrids to set
     */
    public void setUslGrids(List<UnscheduledLsGrids> uslGrids) {
        this.uslGrids = uslGrids;
    }

    /**
     * @return the ovlEventsList
     */
    public List<OverloadEvent> getOvlEventsList() {
        try {
            System.out.println("Runing getOvlEventsList()");
            q = em.createQuery("SELECT o FROM OverloadEvent o WHERE o.ovlStatus = TRUE");
            setOvlEventsList(q.getResultList());
        } catch (Exception e) {
            System.out.println("Exception in getOvlEventsList() method" + e);
        }
        return ovlEventsList;
    }

    /**
     * @param ovlEventsList the ovlEventsList to set
     */
    public void setOvlEventsList(List<OverloadEvent> ovlEventsList) {
        this.ovlEventsList = ovlEventsList;
    }

    /**
     * @return the ovlFeeders
     */
    public List<OverloadingFeeder> getOvlFeeders() {
        return ovlFeeders;
    }

    /**
     * @param ovlFeeders the ovlFeeders to set
     */
    public void setOvlFeeders(List<OverloadingFeeder> ovlFeeders) {
        this.ovlFeeders = ovlFeeders;
    }

    /**
     * @return the devEventsList
     */
    public List<LsDeviation> getDevEventsList() {
        try {
            System.out.println("Runing getDevlEventsList()");
            q = em.createQuery("SELECT d FROM LsDeviation d WHERE d.devStatus = TRUE");
            setDevEventsList(q.getResultList());
        } catch (Exception e) {
            System.out.println("Exception in getDevEventsList() method" + e);
        }
        return devEventsList;
    }

    /**
     * @param devEventsList the devEventsList to set
     */
    public void setDevEventsList(List<LsDeviation> devEventsList) {
        this.devEventsList = devEventsList;
    }

    /**
     * @return the devCategories
     */
    public List<DeviationCategory> getDevCategories() {
        return devCategories;
    }

    /**
     * @param devCategories the devCategories to set
     */
    public void setDevCategories(List<DeviationCategory> devCategories) {
        this.devCategories = devCategories;
    }

    /**
     * @return the priEventsList
     */
    public List<LsPriority> getPriEventsList() {
         try {
            System.out.println("Runing getprilEventsList()");
            q = em.createQuery("SELECT p FROM LsPriority p WHERE p.priorityStatus = TRUE");
            setPriEventsList(q.getResultList());
        } catch (Exception e) {
            System.out.println("Exception in getPriEventsList() method" + e);
        }
        return priEventsList;
    }

    /**
     * @param priEventsList the priEventsList to set
     */
    public void setPriEventsList(List<LsPriority> priEventsList) {
        this.priEventsList = priEventsList;
    }

    /**
     * @return the priCycles
     */
    public List<PriorityCycles> getPriCycles() {
        return priCycles;
    }

    /**
     * @param priCycles the priCycles to set
     */
    public void setPriCycles(List<PriorityCycles> priCycles) {
        this.priCycles = priCycles;
    }
   
   
}
