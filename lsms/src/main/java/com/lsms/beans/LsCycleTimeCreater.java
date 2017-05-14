/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.Categories;
import com.lsms.entities.Groups;
import com.lsms.entities.LsCycle;
import com.lsms.entities.LsCycleTime;
import java.sql.Time;
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
public class LsCycleTimeCreater {

    @PersistenceContext
    EntityManager em ;
    
    Query q ;

    private List<String> groupNamesList ;
    private List<String> catNamesList ;
    private String groupName ;
    private String catName ;
    private Groups selectedGroup ;
    private List<String> LsCycleList ;
    private Categories selectedCategory ;
    private List<CycleTimeBean> timeList = new ArrayList<CycleTimeBean>();
    
    /**
     * Functionality of This bean
     */
    
    public void onGroupSelection(){
        getTimeList().clear();
        CycleTimeBean ctb = new CycleTimeBean();
        ctb.setGroupName(groupName);
        ctb.setCatName(catName);
        getTimeList().add(ctb);
    }
    
    public void persistTime(){
        q = em.createQuery("SELECT g FROM Groups g WHERE g.groupName = :groupName").
                setParameter("groupName", groupName);
        setSelectedGroup((Groups)q.getSingleResult());
        q = em.createQuery("SELECT c FROM Categories c WHERE c.catName = :categoryName").
                setParameter("categoryName", catName);
        setSelectedCategory((Categories)q.getSingleResult());
        for(CycleTimeBean cb : timeList) {
            LsCycleTime lc = new LsCycleTime();
            q = em.createQuery("SELECT lc FROM LsCycle lc WHERE lc.cycName = :cycn").
                    setParameter("cycn", cb.getCycleName());
            lc.setCycleId((LsCycle)q.getSingleResult());
            lc.setGroupId(selectedGroup);
            lc.setCtId(selectedCategory);
            lc.setOffTime(new Time(cb.getOffTime().getHours(), cb.getOffTime().getMinutes(), cb.getOffTime().getSeconds()));
            lc.setOnTime(new Time(cb.getOnTime().getHours(), cb.getOnTime().getMinutes(), cb.getOnTime().getSeconds()));
            em.persist(lc);
            }
    }

    public void add(){
        CycleTimeBean ctb = new CycleTimeBean();
        ctb.setGroupName(groupName);
        ctb.setCatName(catName);
        getTimeList().add(ctb);
    }
    
    public void cancel(CycleTimeBean ctb){
        System.out.println("the row element" + ctb.getCycleName() + ctb.getGroupName());
        getTimeList().remove(ctb);
    }
    
    //**************************************************8
    
    /**
     * Getters And Setters
     */
    
    /**
     * @return the groupNamesList
     */
    public List<String> getGroupNamesList() {
        q = em.createQuery("SELECT g.groupName FROM Groups g");
        setGroupNamesList(q.getResultList());
        return groupNamesList;
    }

    /**
     * @param groupNamesList the groupNamesList to set
     */
    public void setGroupNamesList(List<String> groupNamesList) {
        this.groupNamesList = groupNamesList;
    }

    /**
     * @return the catNamesList
     */
    public List<String> getCatNamesList() {
        q = em.createQuery("SELECT c.catName FROM Categories c");
        setCatNamesList(q.getResultList());
        return catNamesList;
    }

    /**
     * @param catNamesList the catNamesList to set
     */
    public void setCatNamesList(List<String> catNamesList) {
        this.catNamesList = catNamesList;
    }

    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return the catName
     */
    public String getCatName() {
        return catName;
    }

    /**
     * @param catName the catName to set
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }

    /**
     * @return the selectedGroup
     */
    public Groups getSelectedGroup() {
        return selectedGroup;
    }

    /**
     * @param selectedGroup the selectedGroup to set
     */
    public void setSelectedGroup(Groups selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    /**
     * @return the selectedCategory
     */
    public Categories getSelectedCategory() {
        return selectedCategory;
    }

    /**
     * @param selectedCategory the selectedCategory to set
     */
    public void setSelectedCategory(Categories selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    /**
     * @return the timeList
     */
    public List<CycleTimeBean> getTimeList() {
        return timeList;
    }

    /**
     * @param timeList the timeList to set
     */
    public void setTimeList(List<CycleTimeBean> timeList) {
        this.timeList = timeList;
    }

    /**
     * @return the LsCycleList
     */
    public List<String> getLsCycleList() {
        q = em.createQuery("SELECT c.cycName FROM LsCycle c");
        setLsCycleList(q.getResultList());
        return LsCycleList;
    }

    /**
     * @param LsCycleList the LsCycleList to set
     */
    public void setLsCycleList(List<String> LsCycleList) {
        this.LsCycleList = LsCycleList;
    }
    
    
}
