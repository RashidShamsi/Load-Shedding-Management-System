/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.Feeder;
import com.lsms.entities.Grids;
import com.lsms.entities.LsCycleTime;
import com.lsms.entities.LsPriority;
import com.lsms.entities.PriorityCycles;
import java.sql.Time;
import java.util.Date;
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
public class PriorityBean {

    @PersistenceContext
    EntityManager em ;
    
    Query q ;
    
    private List<String> gridNames ;
    private List<String> feederNames ;
    private String selectedGrid ;
    private String selectedFeeder ;
    private Feeder feeder ;
    private Date startTime ;
    private Date endTime ;
    private Date startDate ;
    private Date endDate ;
    private List<LsCycleTime> cycleList ;
    private List<LsCycleTime> selectedCycles ;
    private String name ;
    
    
    public void onGridSelection(){
        System.out.println("grid name " + selectedGrid);
        q = em.createQuery("SELECT f.feedName FROM Feeder f WHERE f.gridId.gridName = :gn").
                setParameter("gn", selectedGrid);
        setFeederNames(q.getResultList());
    }

    public void onFeederSelection(){
        q = em.createQuery("SELECT f FROM Feeder f WHERE f.feedName = :fn").
                setParameter("fn", selectedFeeder);
        feeder = (Feeder)q.getSingleResult();
        
        q = em.createQuery("SELECT l FROM LsCycleTime l WHERE l.ctId = :cat AND l.groupId = :gid").
                setParameter("cat", feeder.getCategoryId()).
                setParameter("gid", feeder.getGroupId());
        cycleList = q.getResultList();
    }
    
    public void onSubmit(){
        cyclePersist(priorityPersist());
        System.out.println("Persisted the priority");
    }
    
    private LsPriority priorityPersist(){
        LsPriority lp = new LsPriority();
        try {
            lp.setFeeder(feeder);
            em.find(Feeder.class, feeder.getFeedId()).setLsPriority(true);
            lp.setStDate(new java.sql.Date(startDate.getYear() , startDate.getMonth() , startDate.getDate() ));
            lp.setEndDate(new java.sql.Date(endDate.getYear() , endDate.getMonth() , endDate.getDate() ));
            lp.setStTime(new Time(startTime.getHours(), startTime.getMinutes(), startTime.getSeconds()));
            lp.setEndTime(new Time(endTime.getHours(), endTime.getMinutes(), endTime.getSeconds()));
            lp.setSboName(name);
            lp.setPriorityStatus(true);
            em.persist(lp);
            em.flush();
        } catch (Exception e) {
            System.out.println("Exception in persisting " + feeder.getFeedName());
        }
        return lp ;
    }
    
    private void cyclePersist(LsPriority lp){
        PriorityCycles pc = new PriorityCycles();
        try {
            for (LsCycleTime c : selectedCycles) {
                pc.setPriority(lp);
                pc.setExemptedCycle(c.getCycleId());
                em.persist(pc);
            }
        } catch (Exception e) {
            System.out.println("Exception in cyclePersist()");
        }
    }
    
    /**
     * ========= getters and setters of priority bean
     */
    
    /**
     * @return the gridNames
     */
    public List<String> getGridNames() {
        q = em.createQuery("SELECT g.gridName FROM Grids g");
        setGridNames(q.getResultList());
        return gridNames;
    }

    /**
     * @param gridNames the gridNames to set
     */
    public void setGridNames(List<String> gridNames) {
        this.gridNames = gridNames;
    }

    /**
     * @return the feederNames
     */
    public List<String> getFeederNames() {
        return feederNames;
    }

    /**
     * @param feederNames the feederNames to set
     */
    public void setFeederNames(List<String> feederNames) {
        this.feederNames = feederNames;
    }

    /**
     * @return the selectedGrid
     */
    public String getSelectedGrid() {
        return selectedGrid;
    }

    /**
     * @param selectedGrid the selectedGrid to set
     */
    public void setSelectedGrid(String selectedGrid) {
        this.selectedGrid = selectedGrid;
    }

    /**
     * @return the selectedFeeder
     */
    public String getSelectedFeeder() {
        return selectedFeeder;
    }

    /**
     * @param selectedFeeder the selectedFeeder to set
     */
    public void setSelectedFeeder(String selectedFeeder) {
        this.selectedFeeder = selectedFeeder;
    }

    /**
     * @return the feeder
     */
    public Feeder getFeeder() {
        return feeder;
    }

    /**
     * @param feeder the feeder to set
     */
    public void setFeeder(Feeder feeder) {
        this.feeder = feeder;
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

    /**
     * @return the cycleList
     */
    public List<LsCycleTime> getCycleList() {
        return cycleList;
    }

    /**
     * @param cycleList the cycleList to set
     */
    public void setCycleList(List<LsCycleTime> cycleList) {
        this.cycleList = cycleList;
    }

    /**
     * @return the selectedCycles
     */
    public List<LsCycleTime> getSelectedCycles() {
        return selectedCycles;
    }

    /**
     * @param selectedCycles the selectedCycles to set
     */
    public void setSelectedCycles(List<LsCycleTime> selectedCycles) {
        this.selectedCycles = selectedCycles;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
