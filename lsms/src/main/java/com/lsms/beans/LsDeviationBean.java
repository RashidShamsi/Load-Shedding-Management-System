/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.*;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.*;

/**
 *
 * @author furqan
 */
@Named
@Stateless
public class LsDeviationBean {

    @PersistenceContext
    EntityManager em ;
    
    Query q ;
    
    private List<String> groupNames ;
    private String selectedGroup ;
    private Groups group ;
    private List<String> gridNames ;
    private String selectedGrid ;
    private Grids selGrid ;
    private List<String> gridCategories ;
    private List<String> selectedCats ;
    private List<DevCats> devCats = new ArrayList<DevCats>();
    private Date stTime;
    private Date endTime ;
    private Date eventDate ; 
    private boolean disable =  true;
    
    private int loadSum ;
    
    /*
    ************************* functionality of LsDeviationBean
    */
    
//    public void onSelection(){
//        System.out.println("****on submit function()");
//        DevGrids grid = new DevGrids();
//        System.out.println(group.getGroupName());
//        grid.setSelectedGroup(group);
//        q = em.createQuery("SELECT g FROM Grids g WHERE g.gridName = :gn").setParameter("gn", getSelectedGrid());
//        selGrid = (Grids)q.getSingleResult() ;
//        System.out.println(selGrid.getGridName());
//        grid.setSelectedGrid(selGrid);
//        grid.setGridCategories(this.getCategories(selGrid, group));
//        dgList.add(grid);
//    }
   
    public void onEventCreation(){
        System.out.println("onEventCreeation()");
        setDisable(false);
//        LsDeviation ld = new LsDeviation();
//        ld.setStTime(new Time(stTime.getHours(), stTime.getMinutes(), stTime.getSeconds()));
//        ld.setEnTime(new Time(endTime.getHours(), endTime.getMinutes(), endTime.getSeconds()));
//        ld.setEventDate(new java.sql.Date(new Date().getTime()));
//        ld.setDevStatus(true);
//        em.persist(ld);
//        em.flush();
//        eventId = ld ;
    }

    public void onSubmit(){
        LsDeviation dev = persistDeviation() ;
        persistDevGrid(dev);
        lsDetailCreater(dev);
    }
    
    private LsDeviation persistDeviation(){
        LsDeviation ld = new LsDeviation();
        ld.setStTime(new Time(stTime.getHours(), stTime.getMinutes(), stTime.getSeconds()));
        ld.setEnTime(new Time(endTime.getHours(), endTime.getMinutes(), endTime.getSeconds()));
        ld.setEventDate(new java.sql.Date(new Date().getTime()));
        ld.setDevStatus(true);
        em.persist(ld);
        em.flush();
        return ld ;
    }
    
    private void persistDevGrid(LsDeviation event){
        loadSum = 0;
        for(DevCats c :devCats){
            DeviationCategory dc = new DeviationCategory();
            dc.setDevEvent(event);
            dc.setGridId(c.getDevGrid());
            em.find(Grids.class, c.getDevGrid().getGridId()).setDeviationStatus(true);
            dc.setDevCat(c.getDevCat());
            dc.setLoad(c.getCatLoad());
            loadSum = loadSum + c.getCatLoad();
            dc.setFeeders(c.getNumOfFeedsDev());
            
            em.persist(dc);
        }
    }
    
    private void lsDetailCreater(LsDeviation event){
            try {
                System.out.println("****************************************");
                System.out.println("Starting lsDetailCreater() to log the newly");
                System.out.println("created ls Deviation event into the details");
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
                    newDetail.setReason("Ls Deviation");
                    em.persist(newDetail);
                    em.find(LsDetails.class, detail.getDetailId()).setEndTime(newDetail.getStartTime());
                }else{
                    System.out.println("Dates didn't matched, events are not of same date");
                    LsDetails newDetail = new LsDetails();
                    newDetail.setEntryDate(event.getEventDate());
                    newDetail.setStartTime(event.getStTime());
                    newDetail.setMwhLoad(loadSum/(Integer)q.getSingleResult());
                    newDetail.setReason("Ls Deviation");
                    em.persist(newDetail);
                }
            } catch (Exception e) {
                System.out.println("No previous event is present, running catch");
                    LsDetails newDetail = new LsDetails();
                    newDetail.setEntryDate(event.getEventDate());
                    newDetail.setStartTime(event.getStTime());
                    newDetail.setMwhLoad(loadSum/(Integer)q.getSingleResult());
                    newDetail.setReason("Ls Deviation");
                    em.persist(newDetail);
            }
        }
    
    public void onGroupSelection(){
        System.out.println("Executing function OnGroupSelection()");
        q = em.createQuery("SELECT g FROM Groups g WHERE g.groupName = :gn").setParameter("gn", getSelectedGroup());
        group = (Groups)q.getSingleResult() ;
        System.out.println("Group name =" + group.getGroupName());
    }
    
    public void onGridSelection(){
        System.out.println("Executing function OnGridSelection()");
        q = em.createQuery("SELECT g FROM Grids g WHERE g.gridName = :gn").setParameter("gn", getSelectedGrid());
        selGrid = (Grids)q.getSingleResult() ;
        gridCategories(selGrid, group);
        System.out.println("Grid name =" + selGrid.getGridName());
    }
    
    public void onCatSelection(){
        try {
            devCats.clear();
            for (String c : selectedCats) {
                DevCats dc = new DevCats();
                dc.setDevGrid(selGrid);
                q = em.createQuery("SELECT c FROM Categories c WHERE c.catName = :cn")
                                    .setParameter("cn", c);
                dc.setDevCat((Categories)q.getSingleResult());
                q = em.createQuery("SELECT f FROM Feeder f "
                        + "WHERE f.groupId = :gpid AND f.gridId = :gdid AND f.categoryId = :cid")
                        .setParameter("gpid", group)
                        .setParameter("gdid", selGrid)
                        .setParameter("cid", dc.getDevCat());
                dc.setNumberOfFeeders(q.getResultList().size());
                System.out.println(dc.getNumberOfFeeders());
                devCats.add(dc);
            }
        } catch (NullPointerException e) {
            System.out.println("Nullpointer exception at onCatSelection()" + e );
        }
    }
    
    /*
    ** this method is used to get the list of all the categories that exist 
    ** in the selected grid.It takes selected grids' and groups' id as parameter
    ** and returns the list.
    */
    private void gridCategories( Grids gridId,Groups groupId){
        System.out.println("executing function getCategories()");
        try {
            
            q = em.createQuery("SELECT DISTINCT f.categoryId.catName FROM Feeder f WHERE f.gridId = :gridid AND f.groupId = :gpid")
                            .setParameter("gridid", gridId)
                            .setParameter("gpid", groupId);
            setGridCategories(q.getResultList());
            
        }catch(NullPointerException e){
            System.out.println("null pointer exception at getCategories()" + e);
        }
        catch (PersistenceException e) {
            System.out.println("Sql exception at getCategories()" + e);
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
                    newDetail.setReason("Extended Ls");
                    em.persist(newDetail);
                }
            } catch (Exception e) {
                System.out.println("No previous event is present, running catch");
                    LsDetails newDetail = new LsDetails();
                    newDetail.setEntryDate(event.getEntryDate());
                    newDetail.setStartTime(event.getStartTime());
                    newDetail.setMwhLoad(loadSum/(Integer)q.getSingleResult());
                    newDetail.setReason("Extended Ls");
                    em.persist(newDetail);
            }
        }
    
    /*
    ****************************** getter and Setters
    */
    
    
    /**
     * @return the groupsNames
     */
    public List<String> getGroupNames() {
        try {
            System.out.println("Starting function getgroupnames() to get group names");
            q = em.createQuery("SELECT g.groupName FROM Groups g");
            setGroupNames(q.getResultList());
            
        } catch (NullPointerException e) {
            System.out.println("null pointer exception in getGroupsNames()" + e);
        }
        return groupNames;
    }

    /**
     * @param groupsNames the groupsNames to set
     */
    public void setGroupNames(List<String> groupsNames) {
        this.groupNames = groupsNames;
    }

    /**
     * @return the gridCategories
     */
    public List<String> getGridCategories() {
        return gridCategories;
    }

    /**
     * @param gridCategories the gridCategories to set
     */
    public void setGridCategories(List<String> gridCategories) {
        this.gridCategories = gridCategories;
    }

    /**
     * @return the selectedGroup
     */
    public String getSelectedGroup() {
        return selectedGroup;
    }

    /**
     * @param selectedGroup the selectedGroup to set
     */
    public void setSelectedGroup(String selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    /**
     * @return the gridNames
     */
    public List<String> getGridNames() {
        try {
            System.out.println("Starting function getgridnames() to get grid names");
            q = em.createQuery("SELECT g.gridName FROM Grids g WHERE g.block.blockId = :bid")
                    .setParameter("bid", group.getBlock().getBlockId());
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
     * @return the selectedCats
     */
    public List<String> getSelectedCats() {
        return selectedCats;
    }

    /**
     * @param selectedCats the selectedCats to set
     */
    public void setSelectedCats(List<String> selectedCats) {
        this.selectedCats = selectedCats;
    }

    /**
     * @return the devCats
     */
    public List<DevCats> getDevCats() {
        return devCats;
    }

    /**
     * @return the stTime
     */
    public Date getStTime() {
        return stTime;
    }

    /**
     * @param stTime the stTime to set
     */
    public void setStTime(Date stTime) {
        this.stTime = stTime;
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
     * @param devCats the devCats to set
     */
    public void setDevCats(List<DevCats> devCats) {
        this.devCats = devCats;
    }

    /**
     * @return the disable
     */
    public boolean getDisable() {
        return disable;
    }

    /**
     * @param disable the disable to set
     */
    public void setDisable(boolean disable) {
        this.disable = disable;
    }

}
