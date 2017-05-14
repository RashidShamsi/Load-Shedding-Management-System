/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.Categories;
import com.lsms.entities.Grids;
import com.lsms.entities.Groups;
import com.lsms.entities.LsCycle;
import com.lsms.entities.RegularLs;
import java.security.acl.Group;
import java.sql.SQLException;
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
//@Named
//@Stateless
public class RegularLsBean1 {

    @PersistenceContext
    EntityManager em ;

    private Query q ;
    private String grpName ;
    private List<String> lsCycles ;
    private String cycle ;
    private LsCycle selectedCycle ;
    private Date refDate ;
   
    
/**   
*    the data fields for regular ls view(rls_1.xhtml)
*    these fields contain time, number of grids and grids data
*/
    
    private String vhlOn1a, vhlOff1a, hlOn1a, hlOff1a, mlOn1a, mlOff1a ; 
    private String vhlOn2a, vhlOff2a, hlOn2a, hlOff2a, mlOn2a, mlOff2a; 
    private String vhlOn3a, vhlOff3a, hlOn3a, hlOff3a, mlOn3a, mlOff3a; 
    private String vhlOn4a, vhlOff4a, hlOn4a, hlOff4a, mlOn4a, mlOff4a; 

    private String vhlOn1b, vhlOff1b, hlOn1b, hlOff1b, mlOn1b, mlOff1b ; 
    private String vhlOn2b, vhlOff2b, hlOn2b, hlOff2b, mlOn2b, mlOff2b; 
    private String vhlOn3b,vhlOff3b, hlOn3b, hlOff3b, mlOn3b, mlOff3b; 
    private String vhlOn4b, vhlOff4b, hlOn4b, hlOff4b, mlOn4b, mlOff4b; 
    
    private String vhlOn1c, vhlOff1c, hlOn1c, hlOff1c, mlOn1c, mlOff1c ; 
    private String vhlOn2c, vhlOff2c, hlOn2c, hlOff2c, mlOn2c, mlOff2c; 
    private String vhlOn3c, vhlOff3c, hlOn3c, hlOff3c, mlOn3c, mlOff3c; 
    private String vhlOn4c, vhlOff4c, hlOn4c, hlOff4c, mlOn4c, mlOff4c; 
    
    private String vhlOnCom, vhlOffCom, hlOnCom, hlOffCom, mlOnCom, mlOffCom; 
    
    private long vhlcount1a, hlcount1a, mlcount1a;
    private long vhlcount2a, hlcount2a, mlcount2a;
    private long vhlcount3a, hlcount3a, mlcount3a;
    private long vhlcount4a, hlcount4a, mlcount4a;
    
    private long vhlcount1b, hlcount1b, mlcount1b;
    private long vhlcount2b, hlcount2b, mlcount2b ;
    private long vhlcount3b, hlcount3b, mlcount3b ;
    private long vhlcount4b, hlcount4b, mlcount4b ;
    
    private long vhlcount1c, hlcount1c, mlcount1c;
    private long vhlcount2c, hlcount2c, mlcount2c ;
    private long vhlcount3c, hlcount3c, mlcount3c ;
    private long vhlcount4c, hlcount4c, mlcount4c ;
    
    private long vhlcountCom, hlcountCom, mlcountCom ;
    
    private List<RegularLsHelper> grids1A, grids2A, grids3A, grids4A;
    private List<RegularLsHelper> grids1B, grids2B, grids3B, grids4B ;
    private List<RegularLsHelper> grids1C, grids2C, grids3C, grids4C ;
    private List<RegularLsHelper> gridsCom ;
    
    /*
        =============the functionality of regular ls starts from here
    */
    
    public void onCycleSelect(){
        try {
            System.out.println("running the function onCycleSelect()");
            System.out.println("Reference date =" + refDate);
            lsTimeSelect();
            feederCount();
            gridList();
            referenceLoadSelection();
        } catch (Exception e) {
            System.out.println("Exception in the method onCycleSelect()" + e);
        }
    }
    
    public void onSubmit(){
        persistRegularLs(grids1A); persistRegularLs(grids2A); persistRegularLs(grids3A); persistRegularLs(grids4A); 
        persistRegularLs(grids1B); persistRegularLs(grids2B); persistRegularLs(grids3B); persistRegularLs(grids4B); 
        persistRegularLs(grids1C); persistRegularLs(grids2C); persistRegularLs(grids3C); persistRegularLs(grids4C); 
        persistRegularLs(gridsCom);
    }
    
    /*
        This function selects the time of loadshedding
    */
    private void lsTimeSelect(){
        
        setVhlOff1a(lsOffTime("1A", "Very High Loss")); setVhlOn1a(lsOnTime("1A", "Very High Loss"));
        setHlOff1a(lsOffTime("1A", "High Loss")); setHlOn1a(lsOnTime("1A", "High Loss"));
        setMlOff1a(lsOffTime("1A", "Medium Loss")); setMlOn1a(lsOnTime("1A", "Medium Loss"));
        
        setVhlOff2a(lsOffTime("2A", "Very High Loss")); setVhlOn2a(lsOnTime("2A", "Very High Loss"));
        setHlOff2a(lsOffTime("2A", "High Loss")); setHlOn2a(lsOnTime("2A", "High Loss"));
        setMlOff2a(lsOffTime("2A", "Medium Loss")); setMlOn2a(lsOnTime("2A", "Medium Loss"));
        
        setVhlOff3a(lsOffTime("3A", "Very High Loss")); setVhlOn3a(lsOnTime("3A", "Very High Loss"));
        setHlOff3a(lsOffTime("3A", "High Loss")); setHlOn3a(lsOnTime("3A", "High Loss"));
        setMlOff3a(lsOffTime("3A", "Medium Loss")); setMlOn3a(lsOnTime("3A", "Medium Loss"));
        
        setVhlOff4a(lsOffTime("4A", "Very High Loss")); setVhlOn4a(lsOnTime("4A", "Very High Loss"));
        setHlOff4a(lsOffTime("4A", "High Loss")); setHlOn4a(lsOnTime("4A", "High Loss"));
        setMlOff4a(lsOffTime("4A", "Medium Loss"));setMlOn4a(lsOnTime("4A", "Medium Loss"));
        
        setVhlOff1b(lsOffTime("1B", "Very High Loss"));setVhlOn1b(lsOnTime("1B", "Very High Loss"));
        setHlOff1b(lsOffTime("1B", "High Loss"));setHlOn1b(lsOnTime("1B", "High Loss"));
        setMlOff1b(lsOffTime("1B", "Medium Loss"));setMlOn1b(lsOnTime("1B", "Medium Loss"));
        
        setVhlOff2b(lsOffTime("2B", "Very High Loss"));setVhlOn2b(lsOnTime("2B", "Very High Loss"));
        setHlOff2b(lsOffTime("2B", "High Loss"));setHlOn2b(lsOnTime("2B", "High Loss"));
        setMlOff2b(lsOffTime("2B", "Medium Loss"));setMlOn2b(lsOnTime("2B", "Medium Loss"));
        
        setVhlOff3b(lsOffTime("3B", "Very High Loss"));setVhlOn3b(lsOnTime("3B", "Very High Loss"));
        setHlOff3b(lsOffTime("3B", "High Loss"));setHlOn3b(lsOnTime("3B", "High Loss"));
        setMlOff3b(lsOffTime("3B", "Medium Loss"));setMlOn3b(lsOnTime("3B", "Medium Loss"));
        
        setVhlOff4b(lsOffTime("4B", "Very High Loss"));setVhlOn4b(lsOnTime("4B", "Very High Loss"));
        setHlOff4b(lsOffTime("4B", "High Loss"));setHlOn4b(lsOnTime("4B", "High Loss"));
        setMlOff4b(lsOffTime("4B", "Medium Loss"));setMlOn4b(lsOnTime("4B", "Medium Loss"));
        
        setVhlOff1c(lsOffTime("1C", "Very High Loss"));setVhlOn1c(lsOnTime("1C", "Very High Loss"));
        setHlOff1c(lsOffTime("1C", "High Loss"));setHlOn1c(lsOnTime("1C", "High Loss"));
        setMlOff1c(lsOffTime("1C", "Medium Loss"));setMlOn1c(lsOnTime("1C", "Medium Loss"));
        
        setVhlOff2c(lsOffTime("2C", "Very High Loss"));setVhlOn2c(lsOnTime("2C", "Very High Loss"));
        setHlOff2c(lsOffTime("2C", "High Loss"));setHlOn2c(lsOnTime("2C", "High Loss"));
        setMlOff2c(lsOffTime("2C", "Medium Loss"));setMlOn2c(lsOnTime("2C", "Medium Loss"));
        
        setVhlOff3c(lsOffTime("3C", "Very High Loss"));setVhlOn3c(lsOnTime("3C", "Very High Loss"));
        setHlOff3c(lsOffTime("3C", "High Loss"));setHlOn3c(lsOnTime("3C", "High Loss"));
        setMlOff3c(lsOffTime("3C", "Medium Loss"));setMlOn3c(lsOnTime("3C", "Medium Loss"));
        
        setVhlOff4c(lsOffTime("4C", "Very High Loss"));setVhlOn4c(lsOnTime("4C", "Very High Loss"));
        setHlOff4c(lsOffTime("4C", "High Loss"));setHlOn4c(lsOnTime("4C", "High Loss"));
        setMlOff4c(lsOffTime("4C", "Medium Loss"));setMlOn4c(lsOnTime("4C", "Medium Loss"));
        
        setVhlOffCom(lsOffTime("Commercial", "Very High Loss"));setVhlOnCom(lsOnTime("Commercial", "Very High Loss"));
        setHlOffCom(lsOffTime("Commercial", "High Loss"));setHlOnCom(lsOnTime("Commercial", "High Loss"));
        setMlOffCom(lsOffTime("Commercial", "Medium Loss"));setMlOnCom(lsOnTime("Commercial", "Medium Loss"));
        
        
    
    }
    /*
     this method will get the count of feeders from all groups and of all categories
    */
    private void feederCount(){
         vhlcount1a = getFeederCount("1A", "Very High Loss");
         hlcount1a = getFeederCount("1A", "High Loss");
         mlcount1a = getFeederCount("1A", "Medium Loss");
         vhlcount2a = getFeederCount("2A", "Very High Loss");
         hlcount2a = getFeederCount("2A", "High Loss");
         mlcount2a = getFeederCount("2A", "Medium Loss");
         vhlcount3a = getFeederCount("3A", "Very High Loss");
         hlcount3a = getFeederCount("3A", "High Loss");
         mlcount3a = getFeederCount("3A", "Medium Loss");
         vhlcount4a = getFeederCount("4A", "Very High Loss");
         hlcount4a = getFeederCount("4A", "High Loss");
         mlcount4a = getFeederCount("4A", "Medium Loss");
         
         setVhlcount1b(getFeederCount("1B", "Very High Loss"));
         setHlcount1b(getFeederCount("1B", "High Loss"));
         setMlcount1b(getFeederCount("1B", "Medium Loss"));
         setVhlcount2b(getFeederCount("2B", "Very High Loss"));
         setHlcount2b(getFeederCount("2B", "High Loss"));
         setMlcount2b(getFeederCount("2B", "Medium Loss"));
         setVhlcount3b(getFeederCount("3B", "Very High Loss"));
         setHlcount3b(getFeederCount("3B", "High Loss"));
         setMlcount3b(getFeederCount("3B", "Medium Loss"));
         setVhlcount4b(getFeederCount("4B", "Very High Loss"));
         setHlcount4b(getFeederCount("4B", "High Loss"));
         setMlcount4b(getFeederCount("4B", "Medium Loss"));
         
         setVhlcount1c(getFeederCount("1C", "Very High Loss"));
         setHlcount1c(getFeederCount("1C", "High Loss"));
         setMlcount1c(getFeederCount("1C", "Medium Loss"));
         setVhlcount2c(getFeederCount("2C", "Very High Loss"));
         setHlcount2c(getFeederCount("2C", "High Loss"));
         setMlcount2c(getFeederCount("2C", "Medium Loss"));
         setVhlcount3c(getFeederCount("3C", "Very High Loss"));
         setHlcount3c(getFeederCount("3C", "High Loss"));
         setMlcount3c(getFeederCount("3C", "Medium Loss"));
         setVhlcount4c(getFeederCount("4C", "Very High Loss"));
         setHlcount4c(getFeederCount("4C", "High Loss"));
         setMlcount4c(getFeederCount("4C", "Medium Loss"));
         
         setVhlcountCom(getFeederCount("Commercial", "Very High Loss"));
         setHlcountCom(getFeederCount("Commercial", "High Loss"));
         setMlcountCom(getFeederCount("Commercial", "Medium Loss"));
    }
    
    /*
        this function will set the list of grids for all the current groups
    */
    private void gridList(){
        setGrids1A(regularLsList("1A"));
        setGrids2A(regularLsList("2A"));
        setGrids3A(regularLsList("3A"));
        setGrids4A(regularLsList("4A"));
        
        setGrids1B(regularLsList("1B"));
        setGrids2B(regularLsList("2B"));
        setGrids3B(regularLsList("3B"));
        setGrids4B(regularLsList("4B"));
        
        setGrids1C(regularLsList("1C"));
        setGrids2C(regularLsList("2C"));
        setGrids3C(regularLsList("3C"));
        setGrids4C(regularLsList("4C"));
        
        setGridsCom(regularLsList("Commercial"));
    }
    /*
        this method will set the reference load for all grids in the regular ls 
        list by using the helper function refernceLoad(List<RegularLs>)
    */
    private void referenceLoadSelection(){
        referenceLoad(grids1A); referenceLoad(grids2A); referenceLoad(grids3A); referenceLoad(grids4A);
        referenceLoad(grids1B); referenceLoad(grids2B); referenceLoad(grids3B); referenceLoad(grids4B);
        referenceLoad(grids1C); referenceLoad(grids2C); referenceLoad(grids3C); referenceLoad(grids4C);
    }
            
    /*
    ==== the function for selecting the time of loadshedding 
    */
    
    private String lsOffTime(String groupName , String catName){
        try {
            System.out.println("Running lsOffTime()");
            q = em.createQuery("SELECT lst.offTime FROM LsCycleTime lst WHERE lst.groupId.groupName = :gn AND lst.ctId.catName = :cn AND lst.cycleId.cycName = :cyn").
                    setParameter("gn", groupName).
                    setParameter("cn", catName).
                    setParameter("cyn", cycle);
            Time lsTime = (Time)q.getSingleResult();
            System.out.println( "retrieved off time for group :" + groupName + " and cycle " + selectedCycle + "and category :"+ catName + "is =" + lsTime);
            return lsTime.getHours() + ":" +lsTime.getMinutes() ;
        } catch (PersistenceException  e) {
            System.err.println("The name of category or group does not match with the stored name. in LsOffTime()" + groupName + " " + catName);
            return null ;
        }
    }
    
     private String lsOnTime(String groupName , String catName){
        try {
            System.out.println("Running lsOnTime()");
            q = em.createQuery("SELECT lst.onTime FROM LsCycleTime lst WHERE lst.groupId.groupName = :gn AND lst.ctId.catName = :cn AND lst.cycleId.cycName = :cyn").
                    setParameter("gn", groupName).
                    setParameter("cn", catName).
                    setParameter("cyn", cycle);
            Time lsTime = (Time)q.getSingleResult();
            System.out.println( "retrieved off time for group :" + groupName + " and cycle " + selectedCycle.getCycName() + "and category :"+ catName + "is =" + lsTime);
            return lsTime.getHours() + ":" + lsTime.getMinutes() ;
        } catch (PersistenceException  e) {
            System.err.println("The name of category or group does not match with the stored name. in lsOnTime()" + groupName + " " + catName);
            return null ;
        }
    }
     
     /*
        helper function for feederCount() and gets the count of feeders for
        the required group and category
     */
    private long getFeederCount(String groupName, String cat_id){
        try {
            q = em.createQuery("SELECT f.feedName FROM Feeder f WHERE f.ovlStatus = FALSE"
                    + " AND f.exemptionStatus = FALSE "
                    + "AND f.lsPriority = FALSE AND f.specialGroup = FALSE"
                    + " And f.groupId.groupName = :gn AND f.categoryId.catName = :cn").
                    setParameter("gn", groupName).
                    setParameter("cn", cat_id);
            long count = q.getResultList().size();
            System.out.println("retrieved feeder count for group : " + groupName + " and category : " + cat_id + " is " + count);
            return count ;
        } catch (Exception e) {
            System.out.println("exception in retrieving count of feeders from data base getFeederCount()" + e);
            return 0 ;
        }
    }
    
    /*
        helper function for gridList(). this method will retun the list of objects
        of RegularLs entity for, by first retriving the list of all possible grids
        in that particular group. and then initializing the object of regularls 
        and storing them in a list.
    */
    private List<RegularLsHelper> regularLsList(String group){
            List<RegularLsHelper> rlsList = new ArrayList<RegularLsHelper>();
        try {
            
            q = em.createQuery("SELECT g FROM Groups g WHERE g.groupName = :gn").setParameter("gn", group);
            Groups grp = (Groups)q.getSingleResult();
            System.out.println("Group name =" + grp.getGroupName() );
            q = em.createQuery("SELECT DISTINCT f.gridId From Feeder f"
                    + " WHERE f.groupId.groupName = :gn AND f.gridId.deviationStatus = FALSE"
                    + " AND f.gridId.extensionStatus = FALSE "
                    + " and f.gridId.unSchLs = FALSE").setParameter("gn", group);
         
            for (Grids g : (List<Grids>)q.getResultList()) {
                System.out.println("Retrieved grids for group " + group + "is " + g.getGridName());
                RegularLs regls = new RegularLs(); 
                RegularLsHelper rlsh = new RegularLsHelper();
                regls.setDate(new java.sql.Date(new Date().getTime()));
                regls.setGrid(g);
                regls.setGroup(grp);
                rlsh.setRegularls(regls);
                rlsList.add(rlsh);
            }
            
            return rlsList ;
        } catch (Exception e) {
            System.err.println("Exception in the method of regularLs() for group" + group);
            return null ;
        }
    }
    
    /*
        This method will get the refernce load for all the grids present in 
        the parameter passed list and set the regularLs object's refernce load
        variables with the retrieved load.
    */
    private void referenceLoad(List<RegularLsHelper> gl){
        for (RegularLsHelper rlsHelp : gl) {
            try {
                q = em.createQuery("SELECT rls.vhlLoad FROM RegularLs rls WHERE rls.grid.gridName = :gn AND rls.date = :d").
                        setParameter("gn", rlsHelp.getRegularls().getGrid().getGridName()).
                        setParameter("d", new java.sql.Date(getRefDate().getYear(), getRefDate().getMonth(), getRefDate().getDay()));
                rlsHelp.setVhlRefLoad((Long)q.getSingleResult());

                q = em.createQuery("SELECT rls.hlLoad FROM RegularLs rls WHERE rls.grid.gridName = :gn AND rls.date = :d").
                        setParameter("gn", rlsHelp.getRegularls().getGrid().getGridName()).
                        setParameter("d", new java.sql.Date(getRefDate().getYear(), getRefDate().getMonth(), getRefDate().getDay()));
                rlsHelp.setHlRefLoad((Long)q.getSingleResult());

                q = em.createQuery("SELECT rls.mlLoad FROM RegularLs rls WHERE rls.grid.gridName = :gn AND rls.date = :d").
                        setParameter("gn", rlsHelp.getRegularls().getGrid().getGridName()).
                        setParameter("d", new java.sql.Date(getRefDate().getYear(), getRefDate().getMonth(), getRefDate().getDay()));
                rlsHelp.setMlRefLoad((Long)q.getSingleResult());
            } catch (Exception e) {
                System.out.println("Null pionter Exception in the function referencedLoad()" + gl.toString());
            }
        }
    }
    
    private void persistRegularLs(List<RegularLsHelper> gridList){
        try {
            for (RegularLsHelper regularLsHelper : gridList) {
                System.out.println("Persisting the regular ls grid" + regularLsHelper.getRegularls().getGrid().getGridName());
                RegularLs regularLs = regularLsHelper.getRegularls();
                regularLs.setCycle(selectedCycle);
                em.persist(regularLs);
            }
        } catch (Exception e) {
            System.out.println("Exception in persisting " + "Null pointer exception");
        }
    }
    
    /**
     * @return the grpName
     */
    public String getGrpName() {
        return grpName;
    }

    /**
     * @param grpName the grpName to set
     */
    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }

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
        selectedCycle = (LsCycle)q.getSingleResult();
        System.out.println("**** retrieving the cycle from database");
        System.out.println("the retrieved cycle =" + selectedCycle.getCycName());
        
    }

    /**
     * @return the refDate
     */
    public Date getRefDate() {
        return refDate;
    }

    /**
     * @param refDate the refDate to set
     */
    public void setRefDate(Date refDate) {
        this.refDate = refDate;
    }

    /*
    * getter and setter for the ls times of all groups
    * Due to lack of time I am hard coding all the values for
    * category names, group names. It will throw an error and
    * produce bugs in future
    */

    /**
     * @return the vhlOn1a
     */
    public String getVhlOn1a() {
        return vhlOn1a;
    }

    /**
     * @param vhlOn1a the vhlOn1a to set
     */
    public void setVhlOn1a(String vhlOn1a) {
        this.vhlOn1a = vhlOn1a;
    }

    /**
     * @return the vhlOff1a
     */
    public String getVhlOff1a() {
        return vhlOff1a;
    }

    /**
     * @param vhlOff1a the vhlOff1a to set
     */
    public void setVhlOff1a(String vhlOff1a) {
        this.vhlOff1a = vhlOff1a;
    }

    /**
     * @return the hlOn1a
     */
    public String getHlOn1a() {
        return hlOn1a;
    }

    /**
     * @param hlOn1a the hlOn1a to set
     */
    public void setHlOn1a(String hlOn1a) {
        this.hlOn1a = hlOn1a;
    }

    /**
     * @return the hlOff1a
     */
    public String getHlOff1a() {
        return hlOff1a;
    }

    /**
     * @param hlOff1a the hlOff1a to set
     */
    public void setHlOff1a(String hlOff1a) {
        this.hlOff1a = hlOff1a;
    }

    /**
     * @return the mlOn1a
     */
    public String getMlOn1a() {
        return mlOn1a;
    }

    /**
     * @param mlOn1a the mlOn1a to set
     */
    public void setMlOn1a(String mlOn1a) {
        this.mlOn1a = mlOn1a;
    }

    /**
     * @return the mlOff1a
     */
    public String getMlOff1a() {
        return mlOff1a;
    }

    /**
     * @param mlOff1a the mlOff1a to set
     */
    public void setMlOff1a(String mlOff1a) {
        this.mlOff1a = mlOff1a;
    }

    /**
     * @return the vhlOn2a
     */
    public String getVhlOn2a() {
        return vhlOn2a;
    }

    /**
     * @param vhlOn2a the vhlOn2a to set
     */
    public void setVhlOn2a(String vhlOn2a) {
        this.vhlOn2a = vhlOn2a;
    }

    /**
     * @return the vhlOff2a
     */
    public String getVhlOff2a() {
        return vhlOff2a;
    }

    /**
     * @param vhlOff2a the vhlOff2a to set
     */
    public void setVhlOff2a(String vhlOff2a) {
        this.vhlOff2a = vhlOff2a;
    }

    /**
     * @return the hlOn2a
     */
    public String getHlOn2a() {
        return hlOn2a;
    }

    /**
     * @param hlOn2a the hlOn2a to set
     */
    public void setHlOn2a(String hlOn2a) {
        this.hlOn2a = hlOn2a;
    }

    /**
     * @return the hlOff2a
     */
    public String getHlOff2a() {
        return hlOff2a;
    }

    /**
     * @param hlOff2a the hlOff2a to set
     */
    public void setHlOff2a(String hlOff2a) {
        this.hlOff2a = hlOff2a;
    }

    /**
     * @return the mlOn2a
     */
    public String getMlOn2a() {
        return mlOn2a;
    }

    /**
     * @param mlOn2a the mlOn2a to set
     */
    public void setMlOn2a(String mlOn2a) {
        this.mlOn2a = mlOn2a;
    }

    /**
     * @return the mlOff2a
     */
    public String getMlOff2a() {
        return mlOff2a;
    }

    /**
     * @param mlOff2a the mlOff2a to set
     */
    public void setMlOff2a(String mlOff2a) {
        this.mlOff2a = mlOff2a;
    }

    /**
     * @return the vhlOn3a
     */
    public String getVhlOn3a() {
        return vhlOn3a;
    }

    /**
     * @param vhlOn3a the vhlOn3a to set
     */
    public void setVhlOn3a(String vhlOn3a) {
        this.vhlOn3a = vhlOn3a;
    }

    /**
     * @return the vhlOff3a
     */
    public String getVhlOff3a() {
        return vhlOff3a;
    }

    /**
     * @param vhlOff3a the vhlOff3a to set
     */
    public void setVhlOff3a(String vhlOff3a) {
        this.vhlOff3a = vhlOff3a;
    }

    /**
     * @return the hlOn3a
     */
    public String getHlOn3a() {
        return hlOn3a;
    }

    /**
     * @param hlOn3a the hlOn3a to set
     */
    public void setHlOn3a(String hlOn3a) {
        this.hlOn3a = hlOn3a;
    }

    /**
     * @return the hlOff3a
     */
    public String getHlOff3a() {
        return hlOff3a;
    }

    /**
     * @param hlOff3a the hlOff3a to set
     */
    public void setHlOff3a(String hlOff3a) {
        this.hlOff3a = hlOff3a;
    }

    /**
     * @return the mlOn3a
     */
    public String getMlOn3a() {
        return mlOn3a;
    }

    /**
     * @param mlOn3a the mlOn3a to set
     */
    public void setMlOn3a(String mlOn3a) {
        this.mlOn3a = mlOn3a;
    }

    /**
     * @return the mlOff3a
     */
    public String getMlOff3a() {
        return mlOff3a;
    }

    /**
     * @param mlOff3a the mlOff3a to set
     */
    public void setMlOff3a(String mlOff3a) {
        this.mlOff3a = mlOff3a;
    }

    /**
     * @return the vhlOn4a
     */
    public String getVhlOn4a() {
        return vhlOn4a;
    }

    /**
     * @param vhlOn4a the vhlOn4a to set
     */
    public void setVhlOn4a(String vhlOn4a) {
        this.vhlOn4a = vhlOn4a;
    }

    /**
     * @return the vhlOff4a
     */
    public String getVhlOff4a() {
        return vhlOff4a;
    }

    /**
     * @param vhlOff4a the vhlOff4a to set
     */
    public void setVhlOff4a(String vhlOff4a) {
        this.vhlOff4a = vhlOff4a;
    }

    /**
     * @return the hlOn4a
     */
    public String getHlOn4a() {
        return hlOn4a;
    }

    /**
     * @param hlOn4a the hlOn4a to set
     */
    public void setHlOn4a(String hlOn4a) {
        this.hlOn4a = hlOn4a;
    }

    /**
     * @return the hlOff4a
     */
    public String getHlOff4a() {
        return hlOff4a;
    }

    /**
     * @param hlOff4a the hlOff4a to set
     */
    public void setHlOff4a(String hlOff4a) {
        this.hlOff4a = hlOff4a;
    }

    /**
     * @return the mlOn4a
     */
    public String getMlOn4a() {
        return mlOn4a;
    }

    /**
     * @param mlOn4a the mlOn4a to set
     */
    public void setMlOn4a(String mlOn4a) {
        this.mlOn4a = mlOn4a;
    }

    /**
     * @return the mlOff4a
     */
    public String getMlOff4a() {
        return mlOff4a;
    }

    /**
     * @param mlOff4a the mlOff4a to set
     */
    public void setMlOff4a(String mlOff4a) {
        this.mlOff4a = mlOff4a;
    }

    /**
     * @return the hlOff1b
     */
    public String getHlOff1b() {
        return hlOff1b;
    }

    /**
     * @param hlOff1b the hlOff1b to set
     */
    public void setHlOff1b(String hlOff1b) {
        this.hlOff1b = hlOff1b;
    }

    /**
     * @return the mlOn1b
     */
    public String getMlOn1b() {
        return mlOn1b;
    }

    /**
     * @param mlOn1b the mlOn1b to set
     */
    public void setMlOn1b(String mlOn1b) {
        this.mlOn1b = mlOn1b;
    }

    /**
     * @return the mlOff1b
     */
    public String getMlOff1b() {
        return mlOff1b;
    }

    /**
     * @param mlOff1b the mlOff1b to set
     */
    public void setMlOff1b(String mlOff1b) {
        this.mlOff1b = mlOff1b;
    }

    /**
     * @return the vhlOn2b
     */
    public String getVhlOn2b() {
        return vhlOn2b;
    }

    /**
     * @param vhlOn2b the vhlOn2b to set
     */
    public void setVhlOn2b(String vhlOn2b) {
        this.vhlOn2b = vhlOn2b;
    }

    /**
     * @return the vhlOff2b
     */
    public String getVhlOff2b() {
        return vhlOff2b;
    }

    /**
     * @param vhlOff2b the vhlOff2b to set
     */
    public void setVhlOff2b(String vhlOff2b) {
        this.vhlOff2b = vhlOff2b;
    }

    /**
     * @return the hlOn2b
     */
    public String getHlOn2b() {
        return hlOn2b;
    }

    /**
     * @param hlOn2b the hlOn2b to set
     */
    public void setHlOn2b(String hlOn2b) {
        this.hlOn2b = hlOn2b;
    }

    /**
     * @return the hlOff2b
     */
    public String getHlOff2b() {
        return hlOff2b;
    }

    /**
     * @param hlOff2b the hlOff2b to set
     */
    public void setHlOff2b(String hlOff2b) {
        this.hlOff2b = hlOff2b;
    }

    /**
     * @return the mlOn2b
     */
    public String getMlOn2b() {
        return mlOn2b;
    }

    /**
     * @param mlOn2b the mlOn2b to set
     */
    public void setMlOn2b(String mlOn2b) {
        this.mlOn2b = mlOn2b;
    }

    /**
     * @return the mlOff2b
     */
    public String getMlOff2b() {
        return mlOff2b;
    }

    /**
     * @param mlOff2b the mlOff2b to set
     */
    public void setMlOff2b(String mlOff2b) {
        this.mlOff2b = mlOff2b;
    }

    /**
     * @return the vhlOn3b
     */
    public String getVhlOn3b() {
        return vhlOn3b;
    }

    /**
     * @param vhlOn3b the vhlOn3b to set
     */
    public void setVhlOn3b(String vhlOn3b) {
        this.vhlOn3b = vhlOn3b;
    }

    /**
     * @return the vhlOff3b
     */
    public String getVhlOff3b() {
        return vhlOff3b;
    }

    /**
     * @param vhlOff3b the vhlOff3b to set
     */
    public void setVhlOff3b(String vhlOff3b) {
        this.vhlOff3b = vhlOff3b;
    }

    /**
     * @return the hlOn3b
     */
    public String getHlOn3b() {
        return hlOn3b;
    }

    /**
     * @param hlOn3b the hlOn3b to set
     */
    public void setHlOn3b(String hlOn3b) {
        this.hlOn3b = hlOn3b;
    }

    /**
     * @return the hlOff3b
     */
    public String getHlOff3b() {
        return hlOff3b;
    }

    /**
     * @param hlOff3b the hlOff3b to set
     */
    public void setHlOff3b(String hlOff3b) {
        this.hlOff3b = hlOff3b;
    }

    /**
     * @return the mlOn3b
     */
    public String getMlOn3b() {
        return mlOn3b;
    }

    /**
     * @param mlOn3b the mlOn3b to set
     */
    public void setMlOn3b(String mlOn3b) {
        this.mlOn3b = mlOn3b;
    }

    /**
     * @return the mlOff3b
     */
    public String getMlOff3b() {
        return mlOff3b;
    }

    /**
     * @param mlOff3b the mlOff3b to set
     */
    public void setMlOff3b(String mlOff3b) {
        this.mlOff3b = mlOff3b;
    }

    /**
     * @return the vhlOn4b
     */
    public String getVhlOn4b() {
        return vhlOn4b;
    }

    /**
     * @param vhlOn4b the vhlOn4b to set
     */
    public void setVhlOn4b(String vhlOn4b) {
        this.vhlOn4b = vhlOn4b;
    }

    /**
     * @return the vhlOff4b
     */
    public String getVhlOff4b() {
        return vhlOff4b;
    }

    /**
     * @param vhlOff4b the vhlOff4b to set
     */
    public void setVhlOff4b(String vhlOff4b) {
        this.vhlOff4b = vhlOff4b;
    }

    /**
     * @return the hlOn4b
     */
    public String getHlOn4b() {
        return hlOn4b;
    }

    /**
     * @param hlOn4b the hlOn4b to set
     */
    public void setHlOn4b(String hlOn4b) {
        this.hlOn4b = hlOn4b;
    }

    /**
     * @return the hlOff4b
     */
    public String getHlOff4b() {
        return hlOff4b;
    }

    /**
     * @param hlOff4b the hlOff4b to set
     */
    public void setHlOff4b(String hlOff4b) {
        this.hlOff4b = hlOff4b;
    }

    /**
     * @return the mlOn4b
     */
    public String getMlOn4b() {
        return mlOn4b;
    }

    /**
     * @param mlOn4b the mlOn4b to set
     */
    public void setMlOn4b(String mlOn4b) {
        this.mlOn4b = mlOn4b;
    }

    /**
     * @return the mlOff4b
     */
    public String getMlOff4b() {
        return mlOff4b;
    }

    /**
     * @param mlOff4b the mlOff4b to set
     */
    public void setMlOff4b(String mlOff4b) {
        this.mlOff4b = mlOff4b;
    }

    /**
     * @return the vhlcount1a
     */
    public long getVhlcount1a() {
        return vhlcount1a;
    }

    /**
     * @param vhlcount1a the vhlcount1a to set
     */
    public void setVhlcount1a(long vhlcount1a) {
        this.vhlcount1a = vhlcount1a;
    }

    /**
     * @return the hlcount1a
     */
    public long getHlcount1a() {
        return hlcount1a;
    }

    /**
     * @param hlcount1a the hlcount1a to set
     */
    public void setHlcount1a(long hlcount1a) {
        this.hlcount1a = hlcount1a;
    }

    /**
     * @return the mlcount1a
     */
    public long getMlcount1a() {
        return mlcount1a;
    }

    /**
     * @param mlcount1a the mlcount1a to set
     */
    public void setMlcount1a(long mlcount1a) {
        this.mlcount1a = mlcount1a;
    }

    /**
     * @return the vhlcount2a
     */
    public long getVhlcount2a() {
        return vhlcount2a;
    }

    /**
     * @param vhlcount2a the vhlcount2a to set
     */
    public void setVhlcount2a(long vhlcount2a) {
        this.vhlcount2a = vhlcount2a;
    }

    /**
     * @return the hlcount2a
     */
    public long getHlcount2a() {
        return hlcount2a;
    }

    /**
     * @param hlcount2a the hlcount2a to set
     */
    public void setHlcount2a(long hlcount2a) {
        this.hlcount2a = hlcount2a;
    }

    /**
     * @return the mlcount2a
     */
    public long getMlcount2a() {
        return mlcount2a;
    }

    /**
     * @param mlcount2a the mlcount2a to set
     */
    public void setMlcount2a(long mlcount2a) {
        this.mlcount2a = mlcount2a;
    }

    /**
     * @return the vhlcount3a
     */
    public long getVhlcount3a() {
        return vhlcount3a;
    }

    /**
     * @param vhlcount3a the vhlcount3a to set
     */
    public void setVhlcount3a(long vhlcount3a) {
        this.vhlcount3a = vhlcount3a;
    }

    /**
     * @return the hlcount3a
     */
    public long getHlcount3a() {
        return hlcount3a;
    }

    /**
     * @param hlcount3a the hlcount3a to set
     */
    public void setHlcount3a(long hlcount3a) {
        this.hlcount3a = hlcount3a;
    }

    /**
     * @return the mlcount3a
     */
    public long getMlcount3a() {
        return mlcount3a;
    }

    /**
     * @param mlcount3a the mlcount3a to set
     */
    public void setMlcount3a(long mlcount3a) {
        this.mlcount3a = mlcount3a;
    }

    /**
     * @return the vhlcount4a
     */
    public long getVhlcount4a() {
        return vhlcount4a;
    }

    /**
     * @param vhlcount4a the vhlcount4a to set
     */
    public void setVhlcount4a(long vhlcount4a) {
        this.vhlcount4a = vhlcount4a;
    }

    /**
     * @return the hlcount4a
     */
    public long getHlcount4a() {
        return hlcount4a;
    }

    /**
     * @param hlcount4a the hlcount4a to set
     */
    public void setHlcount4a(long hlcount4a) {
        this.hlcount4a = hlcount4a;
    }

    /**
     * @return the mlcount4a
     */
    public long getMlcount4a() {
        return mlcount4a;
    }

    /**
     * @param mlcount4a the mlcount4a to set
     */
    public void setMlcount4a(long mlcount4a) {
        this.mlcount4a = mlcount4a;
    }

    /**
     * @return the vhlcount1b
     */
    public long getVhlcount1b() {
        return vhlcount1b;
    }

    /**
     * @param vhlcount1b the vhlcount1b to set
     */
    public void setVhlcount1b(long vhlcount1b) {
        this.vhlcount1b = vhlcount1b;
    }

    /**
     * @return the hlcount1b
     */
    public long getHlcount1b() {
        return hlcount1b;
    }

    /**
     * @param hlcount1b the hlcount1b to set
     */
    public void setHlcount1b(long hlcount1b) {
        this.hlcount1b = hlcount1b;
    }

    /**
     * @return the mlcount1b
     */
    public long getMlcount1b() {
        return mlcount1b;
    }

    /**
     * @param mlcount1b the mlcount1b to set
     */
    public void setMlcount1b(long mlcount1b) {
        this.mlcount1b = mlcount1b;
    }

    /**
     * @return the vhlcount2b
     */
    public long getVhlcount2b() {
        return vhlcount2b;
    }

    /**
     * @param vhlcount2b the vhlcount2b to set
     */
    public void setVhlcount2b(long vhlcount2b) {
        this.vhlcount2b = vhlcount2b;
    }

    /**
     * @return the hlcount2b
     */
    public long getHlcount2b() {
        return hlcount2b;
    }

    /**
     * @param hlcount2b the hlcount2b to set
     */
    public void setHlcount2b(long hlcount2b) {
        this.hlcount2b = hlcount2b;
    }

    /**
     * @return the mlcount2b
     */
    public long getMlcount2b() {
        return mlcount2b;
    }

    /**
     * @param mlcount2b the mlcount2b to set
     */
    public void setMlcount2b(long mlcount2b) {
        this.mlcount2b = mlcount2b;
    }

    /**
     * @return the vhlcount3b
     */
    public long getVhlcount3b() {
        return vhlcount3b;
    }

    /**
     * @param vhlcount3b the vhlcount3b to set
     */
    public void setVhlcount3b(long vhlcount3b) {
        this.vhlcount3b = vhlcount3b;
    }

    /**
     * @return the hlcount3b
     */
    public long getHlcount3b() {
        return hlcount3b;
    }

    /**
     * @param hlcount3b the hlcount3b to set
     */
    public void setHlcount3b(long hlcount3b) {
        this.hlcount3b = hlcount3b;
    }

    /**
     * @return the mlcount3b
     */
    public long getMlcount3b() {
        return mlcount3b;
    }

    /**
     * @param mlcount3b the mlcount3b to set
     */
    public void setMlcount3b(long mlcount3b) {
        this.mlcount3b = mlcount3b;
    }

    /**
     * @return the vhlcount4b
     */
    public long getVhlcount4b() {
        return vhlcount4b;
    }

    /**
     * @param vhlcount4b the vhlcount4b to set
     */
    public void setVhlcount4b(long vhlcount4b) {
        this.vhlcount4b = vhlcount4b;
    }

    /**
     * @return the hlcount4b
     */
    public long getHlcount4b() {
        return hlcount4b;
    }

    /**
     * @param hlcount4b the hlcount4b to set
     */
    public void setHlcount4b(long hlcount4b) {
        this.hlcount4b = hlcount4b;
    }

    /**
     * @return the mlcount4b
     */
    public long getMlcount4b() {
        return mlcount4b;
    }

    /**
     * @param mlcount4b the mlcount4b to set
     */
    public void setMlcount4b(long mlcount4b) {
        this.mlcount4b = mlcount4b;
    }

    /**
     * @return the grids1A
     */
    public List<RegularLsHelper> getGrids1A() {
        return grids1A;
    }

    /**
     * @param grids1A the grids1A to set
     */
    public void setGrids1A(List<RegularLsHelper> grids1A) {
        this.grids1A = grids1A;
    }

    /**
     * @return the grids2A
     */
    public List<RegularLsHelper> getGrids2A() {
        return grids2A;
    }

    /**
     * @param grids2A the grids2A to set
     */
    public void setGrids2A(List<RegularLsHelper> grids2A) {
        this.grids2A = grids2A;
    }

    /**
     * @return the grids3A
     */
    public List<RegularLsHelper> getGrids3A() {
        return grids3A;
    }

    /**
     * @param grids3A the grids3A to set
     */
    public void setGrids3A(List<RegularLsHelper> grids3A) {
        this.grids3A = grids3A;
    }

    /**
     * @return the grids4A
     */
    public List<RegularLsHelper> getGrids4A() {
        return grids4A;
    }

    /**
     * @param grids4A the grids4A to set
     */
    public void setGrids4A(List<RegularLsHelper> grids4A) {
        this.grids4A = grids4A;
    }

    /**
     * @return the grids1B
     */
    public List<RegularLsHelper> getGrids1B() {
        return grids1B;
    }

    /**
     * @param grids1B the grids1B to set
     */
    public void setGrids1B(List<RegularLsHelper> grids1B) {
        this.grids1B = grids1B;
    }

    /**
     * @return the grids2B
     */
    public List<RegularLsHelper> getGrids2B() {
        return grids2B;
    }

    /**
     * @param grids2B the grids2B to set
     */
    public void setGrids2B(List<RegularLsHelper> grids2B) {
        this.grids2B = grids2B;
    }

    /**
     * @return the grids3B
     */
    public List<RegularLsHelper> getGrids3B() {
        return grids3B;
    }

    /**
     * @param grids3B the grids3B to set
     */
    public void setGrids3B(List<RegularLsHelper> grids3B) {
        this.grids3B = grids3B;
    }

    /**
     * @return the grids4B
     */
    public List<RegularLsHelper> getGrids4B() {
        return grids4B;
    }

    /**
     * @param grids4B the grids4B to set
     */
    public void setGrids4B(List<RegularLsHelper> grids4B) {
        this.grids4B = grids4B;
    }

    /**
     * @return the vhlOn1b
     */
    public String getVhlOn1b() {
        return vhlOn1b;
    }

    /**
     * @param vhlOn1b the vhlOn1b to set
     */
    public void setVhlOn1b(String vhlOn1b) {
        this.vhlOn1b = vhlOn1b;
    }

    /**
     * @return the vhlOff1b
     */
    public String getVhlOff1b() {
        return vhlOff1b;
    }

    /**
     * @param vhlOff1b the vhlOff1b to set
     */
    public void setVhlOff1b(String vhlOff1b) {
        this.vhlOff1b = vhlOff1b;
    }

    /**
     * @return the hlOn1b
     */
    public String getHlOn1b() {
        return hlOn1b;
    }

    /**
     * @param hlOn1b the hlOn1b to set
     */
    public void setHlOn1b(String hlOn1b) {
        this.hlOn1b = hlOn1b;
    }

    /**
     * @return the vhlOn1c
     */
    public String getVhlOn1c() {
        return vhlOn1c;
    }

    /**
     * @param vhlOn1c the vhlOn1c to set
     */
    public void setVhlOn1c(String vhlOn1c) {
        this.vhlOn1c = vhlOn1c;
    }

    /**
     * @return the vhlOff1c
     */
    public String getVhlOff1c() {
        return vhlOff1c;
    }

    /**
     * @param vhlOff1c the vhlOff1c to set
     */
    public void setVhlOff1c(String vhlOff1c) {
        this.vhlOff1c = vhlOff1c;
    }

    /**
     * @return the hlOn1c
     */
    public String getHlOn1c() {
        return hlOn1c;
    }

    /**
     * @param hlOn1c the hlOn1c to set
     */
    public void setHlOn1c(String hlOn1c) {
        this.hlOn1c = hlOn1c;
    }

    /**
     * @return the hlOff1c
     */
    public String getHlOff1c() {
        return hlOff1c;
    }

    /**
     * @param hlOff1c the hlOff1c to set
     */
    public void setHlOff1c(String hlOff1c) {
        this.hlOff1c = hlOff1c;
    }

    /**
     * @return the mlOn1c
     */
    public String getMlOn1c() {
        return mlOn1c;
    }

    /**
     * @param mlOn1c the mlOn1c to set
     */
    public void setMlOn1c(String mlOn1c) {
        this.mlOn1c = mlOn1c;
    }

    /**
     * @return the mlOff1c
     */
    public String getMlOff1c() {
        return mlOff1c;
    }

    /**
     * @param mlOff1c the mlOff1c to set
     */
    public void setMlOff1c(String mlOff1c) {
        this.mlOff1c = mlOff1c;
    }

    /**
     * @return the vhlOn2c
     */
    public String getVhlOn2c() {
        return vhlOn2c;
    }

    /**
     * @param vhlOn2c the vhlOn2c to set
     */
    public void setVhlOn2c(String vhlOn2c) {
        this.vhlOn2c = vhlOn2c;
    }

    /**
     * @return the vhlOff2c
     */
    public String getVhlOff2c() {
        return vhlOff2c;
    }

    /**
     * @param vhlOff2c the vhlOff2c to set
     */
    public void setVhlOff2c(String vhlOff2c) {
        this.vhlOff2c = vhlOff2c;
    }

    /**
     * @return the hlOn2c
     */
    public String getHlOn2c() {
        return hlOn2c;
    }

    /**
     * @param hlOn2c the hlOn2c to set
     */
    public void setHlOn2c(String hlOn2c) {
        this.hlOn2c = hlOn2c;
    }

    /**
     * @return the hlOff2c
     */
    public String getHlOff2c() {
        return hlOff2c;
    }

    /**
     * @param hlOff2c the hlOff2c to set
     */
    public void setHlOff2c(String hlOff2c) {
        this.hlOff2c = hlOff2c;
    }

    /**
     * @return the mlOn2c
     */
    public String getMlOn2c() {
        return mlOn2c;
    }

    /**
     * @param mlOn2c the mlOn2c to set
     */
    public void setMlOn2c(String mlOn2c) {
        this.mlOn2c = mlOn2c;
    }

    /**
     * @return the mlOff2c
     */
    public String getMlOff2c() {
        return mlOff2c;
    }

    /**
     * @param mlOff2c the mlOff2c to set
     */
    public void setMlOff2c(String mlOff2c) {
        this.mlOff2c = mlOff2c;
    }

    /**
     * @return the vhlOn3c
     */
    public String getVhlOn3c() {
        return vhlOn3c;
    }

    /**
     * @param vhlOn3c the vhlOn3c to set
     */
    public void setVhlOn3c(String vhlOn3c) {
        this.vhlOn3c = vhlOn3c;
    }

    /**
     * @return the vhlOff3c
     */
    public String getVhlOff3c() {
        return vhlOff3c;
    }

    /**
     * @param vhlOff3c the vhlOff3c to set
     */
    public void setVhlOff3c(String vhlOff3c) {
        this.vhlOff3c = vhlOff3c;
    }

    /**
     * @return the hlOn3c
     */
    public String getHlOn3c() {
        return hlOn3c;
    }

    /**
     * @param hlOn3c the hlOn3c to set
     */
    public void setHlOn3c(String hlOn3c) {
        this.hlOn3c = hlOn3c;
    }

    /**
     * @return the hlOff3c
     */
    public String getHlOff3c() {
        return hlOff3c;
    }

    /**
     * @param hlOff3c the hlOff3c to set
     */
    public void setHlOff3c(String hlOff3c) {
        this.hlOff3c = hlOff3c;
    }

    /**
     * @return the mlOn3c
     */
    public String getMlOn3c() {
        return mlOn3c;
    }

    /**
     * @param mlOn3c the mlOn3c to set
     */
    public void setMlOn3c(String mlOn3c) {
        this.mlOn3c = mlOn3c;
    }

    /**
     * @return the mlOff3c
     */
    public String getMlOff3c() {
        return mlOff3c;
    }

    /**
     * @param mlOff3c the mlOff3c to set
     */
    public void setMlOff3c(String mlOff3c) {
        this.mlOff3c = mlOff3c;
    }

    /**
     * @return the vhlOn4c
     */
    public String getVhlOn4c() {
        return vhlOn4c;
    }

    /**
     * @param vhlOn4c the vhlOn4c to set
     */
    public void setVhlOn4c(String vhlOn4c) {
        this.vhlOn4c = vhlOn4c;
    }

    /**
     * @return the vhlOff4c
     */
    public String getVhlOff4c() {
        return vhlOff4c;
    }

    /**
     * @param vhlOff4c the vhlOff4c to set
     */
    public void setVhlOff4c(String vhlOff4c) {
        this.vhlOff4c = vhlOff4c;
    }

    /**
     * @return the hlOn4c
     */
    public String getHlOn4c() {
        return hlOn4c;
    }

    /**
     * @param hlOn4c the hlOn4c to set
     */
    public void setHlOn4c(String hlOn4c) {
        this.hlOn4c = hlOn4c;
    }

    /**
     * @return the hlOff4c
     */
    public String getHlOff4c() {
        return hlOff4c;
    }

    /**
     * @param hlOff4c the hlOff4c to set
     */
    public void setHlOff4c(String hlOff4c) {
        this.hlOff4c = hlOff4c;
    }

    /**
     * @return the mlOn4c
     */
    public String getMlOn4c() {
        return mlOn4c;
    }

    /**
     * @param mlOn4c the mlOn4c to set
     */
    public void setMlOn4c(String mlOn4c) {
        this.mlOn4c = mlOn4c;
    }

    /**
     * @return the mlOff4c
     */
    public String getMlOff4c() {
        return mlOff4c;
    }

    /**
     * @param mlOff4c the mlOff4c to set
     */
    public void setMlOff4c(String mlOff4c) {
        this.mlOff4c = mlOff4c;
    }

    /**
     * @return the vhlcount1c
     */
    public long getVhlcount1c() {
        return vhlcount1c;
    }

    /**
     * @param vhlcount1c the vhlcount1c to set
     */
    public void setVhlcount1c(long vhlcount1c) {
        this.vhlcount1c = vhlcount1c;
    }

    /**
     * @return the hlcount1c
     */
    public long getHlcount1c() {
        return hlcount1c;
    }

    /**
     * @param hlcount1c the hlcount1c to set
     */
    public void setHlcount1c(long hlcount1c) {
        this.hlcount1c = hlcount1c;
    }

    /**
     * @return the mlcount1c
     */
    public long getMlcount1c() {
        return mlcount1c;
    }

    /**
     * @param mlcount1c the mlcount1c to set
     */
    public void setMlcount1c(long mlcount1c) {
        this.mlcount1c = mlcount1c;
    }

    /**
     * @return the vhlcount2c
     */
    public long getVhlcount2c() {
        return vhlcount2c;
    }

    /**
     * @param vhlcount2c the vhlcount2c to set
     */
    public void setVhlcount2c(long vhlcount2c) {
        this.vhlcount2c = vhlcount2c;
    }

    /**
     * @return the hlcount2c
     */
    public long getHlcount2c() {
        return hlcount2c;
    }

    /**
     * @param hlcount2c the hlcount2c to set
     */
    public void setHlcount2c(long hlcount2c) {
        this.hlcount2c = hlcount2c;
    }

    /**
     * @return the mlcount2c
     */
    public long getMlcount2c() {
        return mlcount2c;
    }

    /**
     * @param mlcount2c the mlcount2c to set
     */
    public void setMlcount2c(long mlcount2c) {
        this.mlcount2c = mlcount2c;
    }

    /**
     * @return the vhlcount3c
     */
    public long getVhlcount3c() {
        return vhlcount3c;
    }

    /**
     * @param vhlcount3c the vhlcount3c to set
     */
    public void setVhlcount3c(long vhlcount3c) {
        this.vhlcount3c = vhlcount3c;
    }

    /**
     * @return the hlcount3c
     */
    public long getHlcount3c() {
        return hlcount3c;
    }

    /**
     * @param hlcount3c the hlcount3c to set
     */
    public void setHlcount3c(long hlcount3c) {
        this.hlcount3c = hlcount3c;
    }

    /**
     * @return the mlcount3c
     */
    public long getMlcount3c() {
        return mlcount3c;
    }

    /**
     * @param mlcount3c the mlcount3c to set
     */
    public void setMlcount3c(long mlcount3c) {
        this.mlcount3c = mlcount3c;
    }

    /**
     * @return the vhlcount4c
     */
    public long getVhlcount4c() {
        return vhlcount4c;
    }

    /**
     * @param vhlcount4c the vhlcount4c to set
     */
    public void setVhlcount4c(long vhlcount4c) {
        this.vhlcount4c = vhlcount4c;
    }

    /**
     * @return the hlcount4c
     */
    public long getHlcount4c() {
        return hlcount4c;
    }

    /**
     * @param hlcount4c the hlcount4c to set
     */
    public void setHlcount4c(long hlcount4c) {
        this.hlcount4c = hlcount4c;
    }

    /**
     * @return the mlcount4c
     */
    public long getMlcount4c() {
        return mlcount4c;
    }

    /**
     * @param mlcount4c the mlcount4c to set
     */
    public void setMlcount4c(long mlcount4c) {
        this.mlcount4c = mlcount4c;
    }

    /**
     * @return the grids1C
     */
    public List<RegularLsHelper> getGrids1C() {
        return grids1C;
    }

    /**
     * @param grids1C the grids1C to set
     */
    public void setGrids1C(List<RegularLsHelper> grids1C) {
        this.grids1C = grids1C;
    }

    /**
     * @return the grids2C
     */
    public List<RegularLsHelper> getGrids2C() {
        return grids2C;
    }

    /**
     * @param grids2C the grids2C to set
     */
    public void setGrids2C(List<RegularLsHelper> grids2C) {
        this.grids2C = grids2C;
    }

    /**
     * @return the grids3C
     */
    public List<RegularLsHelper> getGrids3C() {
        return grids3C;
    }

    /**
     * @param grids3C the grids3C to set
     */
    public void setGrids3C(List<RegularLsHelper> grids3C) {
        this.grids3C = grids3C;
    }

    /**
     * @return the grids4C
     */
    public List<RegularLsHelper> getGrids4C() {
        return grids4C;
    }

    /**
     * @param grids4C the grids4C to set
     */
    public void setGrids4C(List<RegularLsHelper> grids4C) {
        this.grids4C = grids4C;
    }

    /**
     * @return the vhlOnCom
     */
    public String getVhlOnCom() {
        return vhlOnCom;
    }

    /**
     * @param vhlOnCom the vhlOnCom to set
     */
    public void setVhlOnCom(String vhlOnCom) {
        this.vhlOnCom = vhlOnCom;
    }

    /**
     * @return the vhlOffCom
     */
    public String getVhlOffCom() {
        return vhlOffCom;
    }

    /**
     * @param vhlOffCom the vhlOffCom to set
     */
    public void setVhlOffCom(String vhlOffCom) {
        this.vhlOffCom = vhlOffCom;
    }

    /**
     * @return the hlOnCom
     */
    public String getHlOnCom() {
        return hlOnCom;
    }

    /**
     * @param hlOnCom the hlOnCom to set
     */
    public void setHlOnCom(String hlOnCom) {
        this.hlOnCom = hlOnCom;
    }

    /**
     * @return the hlOffCom
     */
    public String getHlOffCom() {
        return hlOffCom;
    }

    /**
     * @param hlOffCom the hlOffCom to set
     */
    public void setHlOffCom(String hlOffCom) {
        this.hlOffCom = hlOffCom;
    }

    /**
     * @return the mlOnCom
     */
    public String getMlOnCom() {
        return mlOnCom;
    }

    /**
     * @param mlOnCom the mlOnCom to set
     */
    public void setMlOnCom(String mlOnCom) {
        this.mlOnCom = mlOnCom;
    }

    /**
     * @return the mlOffCom
     */
    public String getMlOffCom() {
        return mlOffCom;
    }

    /**
     * @param mlOffCom the mlOffCom to set
     */
    public void setMlOffCom(String mlOffCom) {
        this.mlOffCom = mlOffCom;
    }

    /**
     * @return the vhlcountCom
     */
    public long getVhlcountCom() {
        return vhlcountCom;
    }

    /**
     * @param vhlcountCom the vhlcountCom to set
     */
    public void setVhlcountCom(long vhlcountCom) {
        this.vhlcountCom = vhlcountCom;
    }

    /**
     * @return the hlcountCom
     */
    public long getHlcountCom() {
        return hlcountCom;
    }

    /**
     * @param hlcountCom the hlcountCom to set
     */
    public void setHlcountCom(long hlcountCom) {
        this.hlcountCom = hlcountCom;
    }

    /**
     * @return the mlcountCom
     */
    public long getMlcountCom() {
        return mlcountCom;
    }

    /**
     * @param mlcountCom the mlcountCom to set
     */
    public void setMlcountCom(long mlcountCom) {
        this.mlcountCom = mlcountCom;
    }

    /**
     * @return the gridsCom
     */
    public List<RegularLsHelper> getGridsCom() {
        return gridsCom;
    }

    /**
     * @param gridsCom the gridsCom to set
     */
    public void setGridsCom(List<RegularLsHelper> gridsCom) {
        this.gridsCom = gridsCom;
    }
    
    
}
