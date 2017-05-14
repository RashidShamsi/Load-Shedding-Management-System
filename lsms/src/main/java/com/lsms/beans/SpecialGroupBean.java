
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.Feeder;
import com.lsms.entities.SpecialCycle;
import com.lsms.entities.SpecialGroupFeeder;
import java.sql.Time;
import java.util.ArrayList;
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
public class SpecialGroupBean {

    @PersistenceContext
    private EntityManager em ;
    
    private Query q ;
    
    private List<String> gridNames ;
    private String selectedGrid ;

    private List<String> feederList ;
    private String selectedFeeder ;
    
    private List<SpecialCycleBean> feederCycles = new ArrayList<SpecialCycleBean>();
    
    private Date startDate ;
    private Date endDate ; 
    
    /**
     * =========== these methods are all used in spg_1.xhtml
     */
    
    public void addCycle(){
        SpecialCycleBean cycle = new SpecialCycleBean();
        feederCycles.add(cycle);
    }
    
    public void delete(SpecialCycleBean cb){
        feederCycles.remove(cb);
    }
    
    public void specialGroupCreator(){
        persistCycle(persistFeeder());
    }
    
    private SpecialGroupFeeder persistFeeder(){
        SpecialGroupFeeder f = new SpecialGroupFeeder();
        q = em.createQuery("SELECT f FROM Feeder f WHERE f.feedName = :fn")
                .setParameter("fn", selectedFeeder);
        
        Feeder feeder = (Feeder)q.getSingleResult();
        em.find(Feeder.class,feeder.getFeedId() );
        feeder.setSpecialGroup(true);
        f.setFeeder((Feeder)q.getSingleResult());
        f.setStartDate(new java.sql.Date(startDate.getDay(), startDate.getMonth(), startDate.getYear()));
        f.setEndDate(new java.sql.Date(endDate.getDay(), endDate.getMonth(), endDate.getYear()));
        f.setEntryDate(new java.sql.Date(new java.util.Date().getTime()));
        f.setSpgStatus(true);
        em.persist(f);
        em.flush();
        return f ;
    }
    
    private void persistCycle(SpecialGroupFeeder feeder){
        for (SpecialCycleBean cycle : feederCycles) {
            SpecialCycle c = new SpecialCycle();
            c.setCycleName(cycle.getCycleName());
            c.setStTime(new Time(cycle.getStTime().getHours(), cycle.getStTime().getMinutes(),cycle.getStTime().getSeconds()));
            c.setEndTime(new Time(cycle.getEndTime().getHours(), cycle.getEndTime().getMinutes(),cycle.getEndTime().getSeconds()));
            c.setSpFeeder(feeder);
            em.persist(c);
            System.out.println("Persisted the cycle " + cycle.getCycleName());
        }
    }
    /**
     * ============= getters and setters of SpecialGroupBean
     */
    
    /**
     * @return the gridNames
     */
    public List<String> getGridNames() {
        try {
            System.out.println("Starting function getgridnames() to get grid names");
            q = em.createQuery("SELECT g.gridName FROM Grids g ");
            setGridNames(q.getResultList());
        }  catch (NullPointerException e) {
            System.out.println("null pointer exception in getGridsNames()" + e);
        }
        return gridNames;
    }

    /**
     * @param gridNames the gridNames to set
     */
    public void setGridNames(List<String> gridNames) {
        this.gridNames = gridNames;
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
     * @return the feederList
     */
    public List<String> getFeederList() {
        try {
            q = em.createQuery("SELECT f.feedName FROM Feeder f WHERE f.gridId.gridName = :gn").
                    setParameter("gn", selectedGrid);
            setFeederList(q.getResultList());
        } catch (Exception e) {
            System.out.println("Exception in the selection of FeederList in getFeederList()" + e);
        }
        return feederList;
    }

    /**
     * @param feederList the feederList to set
     */
    public void setFeederList(List<String> feederList) {
        this.feederList = feederList;
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
     * @return the feederCycles
     */
    public List<SpecialCycleBean> getFeederCycles() {
        return feederCycles;
    }

    /**
     * @param feederCycles the feederCycles to set
     */
    public void setFeederCycles(List<SpecialCycleBean> feederCycles) {
        this.feederCycles = feederCycles;
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
