/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.Block;
import com.lsms.entities.Categories;
import com.lsms.entities.Groups;
import com.lsms.entities.LsCycle;
import com.lsms.entities.LsCycleTime;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author furqan
 */
@Named
@Stateless
public class GroupsBean {
    
    @PersistenceContext
    EntityManager em ;
    
    Groups g = new Groups();
    private String groupName ;
    private String blockName ;
    private String cycleName ;
    private String catName ;
    private Groups selectedGroup ;
    private DataTable dt ;
    private List<CycleTimeBean> cycleTimeList = new ArrayList<CycleTimeBean>();
    private List<Block> blockList ;
    private List<String> catList ;
    private List<Groups> groupList ;
    private List<String> LsCycleList ;
    private Query q ;
    
    @PostConstruct
    public void init(){
        System.out.println("post construct");
        cycleTimeList.add(new CycleTimeBean());
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
     * @return the blockName
     */
    public String getBlockName() {
        return blockName;
    }

    /**
     * @param blockName the blockName to set
     */
    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    /**
     * @return the cycleName
     */
    public String getCycleName() {
        return cycleName;
    }

    /**
     * @param cycleName the cycleName to set
     */
    public void setCycleName(String cycleName) {
        this.cycleName = cycleName;
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
     * @return the blockList
     */
    public List<Block> getBlockList() {

        q = em.createQuery("SELECT b.blockName FROM Block b");
        setBlockList(q.getResultList());
        return blockList;
    }

    /**
     * @param blockList the blockList to set
     */
    public void setBlockList(List<Block> blockList) {
        this.blockList = blockList;
    }

    /**
     * @return the catList
     */
    public List<String> getCatList() {
        q = em.createQuery("SELECT ct.catName FROM Categories ct");
        setCatList(q.getResultList());
        return catList;
    }

    /**
     * @param catList the catList to set
     */
    public void setCatList(List<String> catList) {
        this.catList = catList;
    }
    
    
    /**
     * @return the dt
     */
    public DataTable getDt() {
        return dt;
    }

    /**
     * @param dt the dt to set
     */
    public void setDt(DataTable dt) {
        this.dt = dt;
    }

    /**
     * @return the groupList
     */
    public List<Groups> getGroupList() {
        q = em.createQuery("SELECT g FROM Groups g");
        setGroupList(q.getResultList());
        return groupList;
    }

    /**
     * @param groupList the groupList to set
     */
    public void setGroupList(List<Groups> groupList) {
        this.groupList = groupList;
    }

    /**
     * @return the LsCycleList
     */
    public List<String> getLsCycleList() {
        q = em.createQuery("SELECT lsc.cycName FROM LsCycle lsc ");
        setLsCycleList(q.getResultList());
        return LsCycleList;
    }

    /**
     * @param LsCycleList the LsCycleList to set
     */
    public void setLsCycleList(List<String> LsCycleList) {
        this.LsCycleList = LsCycleList;
    }
    
    public void add(){
        cycleTimeList.add(new CycleTimeBean());
    }
    
    public void cancel(CycleTimeBean ctb){
        System.out.println("the row element" + ctb.getCycleName() + ctb.getGroupName());
        cycleTimeList.remove(ctb);
    }
    
    public void groupCreater(){
        g.setGroupName(groupName);
        q = em.createQuery("SELECT b FROM Block b where b.blockName = :bn").setParameter("bn", getBlockName());
        g.setBlock((Block)q.getSingleResult());
        em.persist(g);
    }

    public void cycleCreater() {
        LsCycleTime lc = new LsCycleTime();
        CycleTimeBean cb = (CycleTimeBean)dt.getRowData();
        q = em.createQuery("SELECT lc FROM LsCycle lc WHERE lc.cycName = :cycn").setParameter("cycn", cb.getCycleName());
        lc.setCycleId((LsCycle)q.getSingleResult());
        q = em.createQuery("SELECT g FROM Groups g where g.groupName = :gn").setParameter("gn", getGroupName());
        lc.setGroupId((Groups)q.getSingleResult());
        q = em.createQuery("SELECT c FROM Categories c where c.catName = :cn").setParameter("cn", cb.getCatName());
        lc.setCtId((Categories)q.getSingleResult());
        lc.setOffTime(new Time(cb.getOffTime().getHours(), cb.getOffTime().getMinutes(), cb.getOffTime().getSeconds()));
        lc.setOnTime(new Time(cb.getOnTime().getHours(), cb.getOnTime().getMinutes(), cb.getOnTime().getSeconds()));
        em.persist(lc); 
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
     * @return the cycleTimeList
     */
    public List<CycleTimeBean> getCycleTimeList() {
        return cycleTimeList;
    }

    /**
     * @param cycleTimeList the cycleTimeList to set
     */
    public void setCycleTimeList(List<CycleTimeBean> cycleTimeList) {
        this.cycleTimeList = cycleTimeList;
    }

    
}
