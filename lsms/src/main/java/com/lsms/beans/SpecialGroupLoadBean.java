/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.SpecialCycle;
import com.lsms.entities.SpecialCycleLoad;
import com.lsms.entities.SpecialGroupFeeder;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NameBinding;

/**
 *
 * @author furqan
 */
@Named
@Stateless
public class SpecialGroupLoadBean {
    @PersistenceContext
    EntityManager em ;
    
    Query q ;
    
    private List<String> feederName ;
    private String selectedFeeder ;
    private SpecialGroupFeeder feeder ;
    private List<String> cycleNames ;
    private String selectedCycle ;
    private SpecialCycle cycle ;
    private int cLoad ;
    
    public void onFeederSelection(){
        q = em.createQuery("SELECT f FROM SpecialGroupFeeder f  WHERE f.feeder.feedName = :fn")
                .setParameter("fn", selectedFeeder);
        feeder = (SpecialGroupFeeder)q.getSingleResult();
        
        q = em.createQuery("SELECT c.cycleName FROM SpecialCycle c WHERE c.spFeeder = :f")
                .setParameter("f", feeder);
        setCycleNames(q.getResultList());
    }
    
    public void onCycleSelection(){
        q = em.createQuery("SELECT c FROM SpecialCycle c WHERE c.cycleName = :cn AND c.spFeeder = :f")
                .setParameter("cn", getSelectedCycle())
                .setParameter("f", feeder);
        cycle = (SpecialCycle)q.getSingleResult();
    }
    
    public void onLoadSubmit(){
        System.out.println("Load = " + cLoad);
        SpecialCycleLoad cycleLoad = new SpecialCycleLoad();
        cycleLoad.setCycle(cycle);
        cycleLoad.setLoad(cLoad);
        cycleLoad.setDate(new java.sql.Date(new Date().getTime()));
        em.persist(cycleLoad);
    }
    
    /**
     * @return the feederName
     */
    public List<String> getFeederName() {
        q = em.createQuery("SELECT f.feeder.feedName FROM SpecialGroupFeeder f");
        setFeederName(q.getResultList());
        return feederName;
    }

    /**
     * @param feederName the feederName to set
     */
    public void setFeederName(List<String> feederName) {
        this.feederName = feederName;
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
    public SpecialGroupFeeder getFeeder() {
        return feeder;
    }

    /**
     * @param feeder the feeder to set
     */
    public void setFeeder(SpecialGroupFeeder feeder) {
        this.feeder = feeder;
    }

    /**
     * @return the cycleNames
     */
    public List<String> getCycleNames() {
        return cycleNames;
    }

    /**
     * @param cycleNames the cycleNames to set
     */
    public void setCycleNames(List<String> cycleNames) {
        this.cycleNames = cycleNames;
    }

    /**
     * @return the cycle
     */
    public SpecialCycle getCycle() {
        return cycle;
    }

    /**
     * @param cycle the cycle to set
     */
    public void setCycle(SpecialCycle cycle) {
        this.cycle = cycle;
    }

    /**
     * @return the selectedCycle
     */
    public String getSelectedCycle() {
        return selectedCycle;
    }

    /**
     * @param selectedCycle the selectedCycle to set
     */
    public void setSelectedCycle(String selectedCycle) {
        this.selectedCycle = selectedCycle;
    }

    /**
     * @return the cLoad
     */
    public int getCLoad() {
        return cLoad;
    }

    /**
     * @param cLoad the cLoad to set
     */
    public void setCLoad(int cLoad) {
        this.cLoad = cLoad;
    }
}
