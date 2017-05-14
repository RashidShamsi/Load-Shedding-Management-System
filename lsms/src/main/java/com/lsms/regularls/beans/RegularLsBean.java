/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.regularls.beans;

import com.lsms.entities.Block;
import com.lsms.entities.Categories;
import com.lsms.entities.Grids;
import com.lsms.entities.Groups;
import com.lsms.entities.LsCycle;
import com.lsms.entities.LsCycleTime;
import com.lsms.entities.LsDetails;
import com.lsms.entities.ShededCategory;
import com.lsms.entities.ShededGrid;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author furqan
 */
@Named
@Stateless
public class RegularLsBean {

    @PersistenceContext
    EntityManager em ;
    
    Query q ;
    
    private List<String> lsCycles ;
    private LsCycle selectedCycle ;
    private String cycle ;
    private LsCategoryManager selectedCategory ;
    private List<LsCategoryManager> group1A = new ArrayList<LsCategoryManager>() ;
    private List<LsCategoryManager> group2A = new ArrayList<LsCategoryManager>() ;
    private List<LsCategoryManager> group3A = new ArrayList<LsCategoryManager>() ;
    private List<LsCategoryManager> group4A = new ArrayList<LsCategoryManager>() ;
    private List<LsCategoryManager> group1B = new ArrayList<LsCategoryManager>() ; 
    private List<LsCategoryManager> group2B = new ArrayList<LsCategoryManager>();
    private List<LsCategoryManager> group3B = new ArrayList<LsCategoryManager>() ; 
    private List<LsCategoryManager> group4B = new ArrayList<LsCategoryManager>() ;
    private List<LsCategoryManager> group1C = new ArrayList<LsCategoryManager>() ; 
    private List<LsCategoryManager> group2C = new ArrayList<LsCategoryManager>() ; 
    private List<LsCategoryManager> group3C = new ArrayList<LsCategoryManager>() ;
    private List<LsCategoryManager> group4C = new ArrayList<LsCategoryManager>() ;
    private List<LsCategoryManager> groupCom = new ArrayList<LsCategoryManager>() ;
    private List<LsGridLoadManager> lsGrids = new ArrayList<LsGridLoadManager>();
    private List<ShededGrid> referenceLoadList = new ArrayList<ShededGrid>();
    private Date referenceDate ;
    
    /**
     * *******************************************
     * Methods for populating the fields of regularLsBean
     */
    public void onCycleSelection(){
        // method to add sheded categories to all the lists
        try {
            groupListPopulator(group1A, "1A");
            groupListPopulator(group2A, "2A");
            groupListPopulator(group3A, "3A");
            groupListPopulator(group4A, "4A");
            groupListPopulator(group1B, "1B");
            groupListPopulator(group2B, "2B");
            groupListPopulator(group3B, "3B");
            groupListPopulator(group4B, "4B");
            groupListPopulator(group1C, "1C");
            groupListPopulator(group2C, "2C");
            groupListPopulator(group3C, "3C");
            groupListPopulator(group4C, "4C");
            groupListPopulator(groupCom, "Commercial");
        } catch (Exception e) {
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            System.out.println("Excetion in the cycle selection " + e);
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        }
    }
    
           /*
        helper function for gridList(). this method will retun the list of objects
        of RegularLs entity for, by first retriving the list of all possible grids
        in that particular group. and then initializing the object of regularls 
        and storing them in a list.
    */
    public void onRowSelection(){
        try {
            
            q = em.createQuery("SELECT DISTINCT f.gridId From Feeder f"
                    + " WHERE f.groupId = :gn AND f.gridId.deviationStatus = FALSE"
                    + " AND f.gridId.extensionStatus = FALSE "
                    + " and f.gridId.unSchLs = FALSE").setParameter("gn", selectedCategory.getGroup());
            lsGrids.clear();
            for (Grids g : (List<Grids>)q.getResultList()) {
                LsGridLoadManager glm = new LsGridLoadManager();
                glm.setLsCategoryManager(selectedCategory);
                glm.setGrid(g);
                lsGrids.add(glm);
            }
            
        } catch (Exception e) {
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            System.out.println("Exception in getting grids for " + selectedCategory.getGroup().getGroupName() + e);
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        }
    }
    
    public void onLoadSubmit(){
        System.out.println("**********************************************");
        System.out.println("running the onLoadSubmit() function to");
        System.out.println("**********************************************");
        LsCategoryManager lsc = lsGrids.get(0).getLsCategoryManager();
        lsc.setTotalLoad(totalMWHCalculator());
        
        ShededCategory sc = new ShededCategory();
        
        System.out.println("_______________________________________________");
        System.out.println("persisting the Sheded Category" + lsc.getCategory().getCatName());
        System.out.println("_______________________________________________");
        
        sc.setCategoryId(lsc.getCategory());
        sc.setEntryDate(new java.sql.Date(new java.util.Date().getTime()));
        sc.setFeederCount(lsc.getFeederCount());
        sc.setGroupId(lsc.getGroup());
        sc.setCycleTimes(lsc.getLsTime());
        sc.setToalLoad(lsc.getTotalLoad());
        sc.setStatus(true);
        em.persist(sc);
        em.flush();
        
        lsDetailCreater(sc);
        
        for (LsGridLoadManager g : lsGrids) {
            ShededGrid sg = new ShededGrid();
            
            System.out.println("_______________________________________________");
            System.out.println("persisting the Sheded grid" + g.getGrid().getGridName());
            System.out.println("_______________________________________________");
            
            sg.setGridId(g.getGrid());
            sg.setLoadShed(g.getLoadShed());
            sg.setShededCategory(sc);
            em.persist(sg);
        }
    }
    
    public void onReferenceDateSelection(){
        for (LsGridLoadManager grid : lsGrids) {
            q = em.createQuery("SELECT g FROM ShededGrid g WHERE g.gridId = :gdid ANd g.shededCategory.categoryId = :cid AND g.shededCategory.cycleTimes = :ct AND g.shededCategory.groupId = :gpid AND g.shededCategory.entryDate = :ed")
                    .setParameter("gdid", grid.getGrid())
                    .setParameter("cid", grid.getLsCategoryManager().getCategory())
                    .setParameter("ct", grid.getLsCategoryManager().getLsTime())
                    .setParameter("gpid", grid.getLsCategoryManager().getGroup())
                    .setParameter("ed", referenceDate);
            referenceLoadList.add((ShededGrid)q.getSingleResult());
        }
    }
    
    private void lsDetailCreater(ShededCategory sCat){
        try {
            System.out.println("****************************************");
            System.out.println("Starting lsDetailCreater() to log the newly");
            System.out.println("created regular ls event into the details");
            System.out.println("****************************************");
            q = em.createQuery("SELECT MAX(lsd.detailId) FROM LsDetails lsd");
            LsDetails detail = em.find(LsDetails.class, (Integer)q.getSingleResult());
            if(     new Date().getDate() == detail.getEntryDate().getDate()
                    && new Date().getMonth() == detail.getEntryDate().getMonth()
                    && new Date().getYear() == detail.getEntryDate().getYear()){
                System.out.println("Dates matched, events are of same date");
                LsDetails newDetail = new LsDetails();
                newDetail.setEntryDate(sCat.getEntryDate());
                newDetail.setStartTime(sCat.getCycleTimes().getOffTime());
                newDetail.setMwhLoad(sCat.getToalLoad() + detail.getMwhLoad());
                newDetail.setReason("Resgular Ls");
                em.persist(newDetail);
                em.find(LsDetails.class, detail.getDetailId()).setEndTime(newDetail.getStartTime());
            }else{
                System.out.println("Dates didn't matched, events are not of same date");
                LsDetails newDetail = new LsDetails();
                newDetail.setEntryDate(sCat.getEntryDate());
                newDetail.setStartTime(sCat.getCycleTimes().getOffTime());
                newDetail.setMwhLoad(sCat.getToalLoad());
                newDetail.setReason("Resgular Ls");
                em.persist(newDetail);
            }
        } catch (Exception e) {
            System.out.println("No previous event is present, running catch");
                LsDetails newDetail = new LsDetails();
                newDetail.setEntryDate(sCat.getEntryDate());
                newDetail.setStartTime(sCat.getCycleTimes().getOffTime());
                newDetail.setMwhLoad(sCat.getToalLoad());
                newDetail.setReason("Resgular Ls");
                em.persist(newDetail);
        }
        
    }
    
    private void groupListPopulator(List<LsCategoryManager> l, String group){
        try {
            System.out.println("*****************************************");
            System.out.println("Populating the group " + group );
            System.out.println("*****************************************");
            l.clear();
            q = em.createQuery("SELECT c FROM Categories c WHERE c.lsStatus = TRUE");
            
            for (Categories cat : (List<Categories>)q.getResultList()) {
            
                LsCategoryManager lcm = new LsCategoryManager();
                
                System.out.println("-----------------------------------------");
                System.out.println("adding the category " + cat + " to the list");
                System.out.println("-----------------------------------------");
                lcm.setCategory(cat);
                
                q = em.createQuery("SELECT gp FROM Groups gp WHERE gp.groupName = :gn")
                        .setParameter("gn", group);
                System.out.println(group);
                lcm.setGroup((Groups)q.getSingleResult());
                
                lcm.setFeederCount(getFeederCount(lcm.getGroup(), lcm.getCategory()));
                
                q = em.createQuery("SELECT t FROM LsCycleTime t WHERE t.groupId.groupName = :gn AND t.ctId = :cn AND t.cycleId.cycName = :cyn")
                        .setParameter("gn", group)
                        .setParameter("cn", cat)
                        .setParameter("cyn", cycle);
                System.out.println((LsCycleTime)q.getSingleResult());
                lcm.setLsTime((LsCycleTime)q.getSingleResult());
                lsStatuses(lcm);
                System.out.println("---------------------------------------------------------");
                System.out.println("Successfully added the category " + cat + " to the list");
                System.out.println("---------------------------------------------------------");
                l.add(lcm);
            }
        } catch (Exception e) {
            System.out.println("*****************************************");
            System.out.println("Exception in populating the group " + group + " " + e);
            System.out.println("*****************************************");
        }
    }
    
    public int totalMWHCalculator(){
        int sum = 0; 
        try {
            for (LsGridLoadManager grid : lsGrids) {
                sum = grid.getLoadShed() + sum ;
            }
            q = em.createQuery("SELECT pf.value FROM PowerFactor pf WHERE pf.inUse = TRUE");
        } catch (Exception e) {
            System.out.println("Exception in getting the total mwh " + e);
            return -9999 ;
        }
        return Math.round(sum/(Integer)q.getSingleResult());
    }
    
     /*
        helper function for feederCount() and gets the count of feeders for
        the required group and category
     */
    private long getFeederCount(Groups groupId, Categories cat_id){
        try {
            q = em.createQuery("SELECT f.feedName FROM Feeder f "
                    + "WHERE f.ovlStatus = FALSE AND f.exemptionStatus = FALSE "
                    + "AND f.lsPriority = FALSE AND f.specialGroup = FALSE "
                    + "And f.groupId = :g AND f.categoryId = :c" )
                    .setParameter("g", groupId)
                    .setParameter("c", cat_id);
            long count = q.getResultList().size();
            return count ;
        } catch (Exception e) {
            System.out.println("exception in retrieving count of feeders from data base getFeederCount()" + e);
            return 0 ;
        }
    }
    
   private void lsStatuses(LsCategoryManager c){
       System.out.println("runing stataus");
       q = em.createQuery("SELECT MAX(c.id) FROM ShededCategory c WHERE c.categoryId = :cid AND c.cycleTimes = :ct AND c.groupId = :gid AND c.entryDate = :ed")
               .setParameter("cid", c.getCategory())
               .setParameter("ct", c.getLsTime())
               .setParameter("gid", c.getGroup())
               .setParameter("ed", new java.sql.Date(new Date().getTime()));
       try {
            ShededCategory sCat = em.find(ShededCategory.class, (Integer)q.getSingleResult());
                System.out.println("the category is not null");
                if(sCat.getStatus() == false && sCat.getToalLoad() != 0){
                    System.out.println("entering second condition");
                    c.setLsStatus("Shedded");
                    c.setPresentStatus("Normal");
                }else if(sCat.getStatus() == true ){
                    c.setLsStatus("In regular Shedding");
                    c.setPresentStatus("In regular Shedding");
                }
       } catch (Exception e) {
               c.setLsStatus("Not Shedded yet");
               c.setPresentStatus("Normal");
       }
           
   }

    
    /**
     * ***********************
     * Getter and Setters
     * ***********************
     */
    
    /**
     * @return the lsCycles
     */
    public List<String> getLsCycles() {
        q = em.createQuery("SELECT lc.cycName FROM LsCycle lc");
        setLsCycles(q.getResultList());
        return lsCycles;
    }

    /**
     * @param lsCycles the lsCycles to set
     */
    public void setLsCycles(List<String> lsCycles) {
        this.lsCycles = lsCycles;
    }
    
    /**
     * @return the cycle
     */
    public String getCycle() {
        return cycle;
    }
    
    /**
     * @param cycle the cycle to set
     */
    public void setCycle(String cycle) {
        this.cycle = cycle;
        q = em.createQuery("SELECT c FROM LsCycle c WHERE c.cycName = :cn")
                .setParameter("cn", getCycle());
        setSelectedCycle((LsCycle)q.getSingleResult());
    }

    /**
     * @return the selectedCycle
     */
    public LsCycle getSelectedCycle() {
        return selectedCycle;
    }

    /**
     * @param selectedCycle the selectedCycle to set
     */
    public void setSelectedCycle(LsCycle selectedCycle) {
        this.selectedCycle = selectedCycle;
    }

    /**
     * @return the group1A
     */
    public List<LsCategoryManager> getGroup1A() {
        return group1A;
    }

    /**
     * @param group1A the group1A to set
     */
    public void setGroup1A(List<LsCategoryManager> group1A) {
        this.group1A = group1A;
    }

    /**
     * @return the group2A
     */
    public List<LsCategoryManager> getGroup2A() {
        return group2A;
    }

    /**
     * @param group2A the group2A to set
     */
    public void setGroup2A(List<LsCategoryManager> group2A) {
        this.group2A = group2A;
    }

    /**
     * @return the group3A
     */
    public List<LsCategoryManager> getGroup3A() {
        return group3A;
    }

    /**
     * @param group3A the group3A to set
     */
    public void setGroup3A(List<LsCategoryManager> group3A) {
        this.group3A = group3A;
    }

    /**
     * @return the group4A
     */
    public List<LsCategoryManager> getGroup4A() {
        return group4A;
    }

    /**
     * @param group4A the group4A to set
     */
    public void setGroup4A(List<LsCategoryManager> group4A) {
        this.group4A = group4A;
    }

    /**
     * @return the group1B
     */
    public List<LsCategoryManager> getGroup1B() {
        return group1B;
    }

    /**
     * @param group1B the group1B to set
     */
    public void setGroup1B(List<LsCategoryManager> group1B) {
        this.group1B = group1B;
    }

    /**
     * @return the group2B
     */
    public List<LsCategoryManager> getGroup2B() {
        return group2B;
    }

    /**
     * @param group2B the group2B to set
     */
    public void setGroup2B(List<LsCategoryManager> group2B) {
        this.group2B = group2B;
    }

    /**
     * @return the group3B
     */
    public List<LsCategoryManager> getGroup3B() {
        return group3B;
    }

    /**
     * @param group3B the group3B to set
     */
    public void setGroup3B(List<LsCategoryManager> group3B) {
        this.group3B = group3B;
    }

    /**
     * @return the group4B
     */
    public List<LsCategoryManager> getGroup4B() {
        return group4B;
    }

    /**
     * @param group4B the group4B to set
     */
    public void setGroup4B(List<LsCategoryManager> group4B) {
        this.group4B = group4B;
    }

    /**
     * @return the group1C
     */
    public List<LsCategoryManager> getGroup1C() {
        return group1C;
    }

    /**
     * @param group1C the group1C to set
     */
    public void setGroup1C(List<LsCategoryManager> group1C) {
        this.group1C = group1C;
    }

    /**
     * @return the group2C
     */
    public List<LsCategoryManager> getGroup2C() {
        return group2C;
    }

    /**
     * @param group2C the group2C to set
     */
    public void setGroup2C(List<LsCategoryManager> group2C) {
        this.group2C = group2C;
    }

    /**
     * @return the group3C
     */
    public List<LsCategoryManager> getGroup3C() {
        return group3C;
    }

    /**
     * @param group3C the group3C to set
     */
    public void setGroup3C(List<LsCategoryManager> group3C) {
        this.group3C = group3C;
    }

    /**
     * @return the group4C
     */
    public List<LsCategoryManager> getGroup4C() {
        return group4C;
    }

    /**
     * @param group4C the group4C to set
     */
    public void setGroup4C(List<LsCategoryManager> group4C) {
        this.group4C = group4C;
    }

    /**
     * @return the groupCom
     */
    public List<LsCategoryManager> getGroupCom() {
        return groupCom;
    }

    /**
     * @param groupCom the groupCom to set
     */
    public void setGroupCom(List<LsCategoryManager> groupCom) {
        this.groupCom = groupCom;
    }

    /**
     * @return the selectedCategory
     */
    public LsCategoryManager getSelectedCategory() {
        return selectedCategory;
    }

    /**
     * @param selectedCategory the selectedCategory to set
     */
    public void setSelectedCategory(LsCategoryManager selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    /**
     * @return the lsGrids
     */
    public List<LsGridLoadManager> getLsGrids() {
        return lsGrids;
    }

    /**
     * @param lsGrids the lsGrids to set
     */
    public void setLsGrids(List<LsGridLoadManager> lsGrids) {
        this.lsGrids = lsGrids;
    }

    /**
     * @return the referenceLoadList
     */
    public List<ShededGrid> getReferenceLoadList() {
        return referenceLoadList;
    }

    /**
     * @param referenceLoadList the referenceLoadList to set
     */
    public void setReferenceLoadList(List<ShededGrid> referenceLoadList) {
        this.referenceLoadList = referenceLoadList;
    }

    /**
     * @return the referenceDate
     */
    public Date getReferenceDate() {
        return referenceDate;
    }

    /**
     * @param referenceDate the referenceDate to set
     */
    public void setReferenceDate(Date referenceDate) {
        this.referenceDate = referenceDate;
    }

    
}
 