/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.Block;
import com.lsms.entities.Grids;
import com.lsms.entities.Groups;
import com.lsms.entities.LsCycle;
import com.lsms.entities.LsCycleTime;
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
public class GroupsViewBean {

    @PersistenceContext
    EntityManager em ;
    
    Query q ;
    
    private Groups selectedGroup ;
    private List<Groups> groupsList ;
    private String newGroupName ;
    private String newBlockName ;
    private List<String> nameList ;
    private List<CycleTimeBean> ctb = new ArrayList<CycleTimeBean>();
    /**
     * Functionalities of This bean
     */
    
    public void onGroupSelection(Groups g){
        System.out.println("Selected group " + g.getGroupName());
        setSelectedGroup(g) ;
    }
    
    public void groupDeleter(Groups g) {
        em.remove(em.find(Groups.class, g.getGroupId()));
    }
    
        public void groupEditer(){
        try {
            System.out.println("group Name " + getNewGroupName() + " block name "+ newBlockName);
            
            if(!newGroupName.equals("")){
                System.out.println("entered first condition");
                em.find(Groups.class, selectedGroup.getGroupId()).setGroupName(getNewGroupName());
            }
            if (!newBlockName.equals("")) {
                System.out.println("entered second condition");
                q = em.createQuery("SELECT b FROM Block b WHERE b.blockName = :bn")
                        .setParameter("bn", getNewBlockName());
                em.find(Groups.class, selectedGroup.getGroupId()).setBlock((Block)q.getSingleResult());
            }
        } catch (Exception e) {
            System.out.println("Exception in the method groupEditer()" + e);
        }
    }


    
    /**
     * @return the groupsList
     */
    public List<Groups> getGroupsList() {
        q = em.createQuery("SELECT g FROM Groups g");
        setGroupsList(q.getResultList());
        return groupsList;
    }

    /**
     * @param groupsList the groupsList to set
     */
    public void setGroupsList(List<Groups> groupsList) {
        this.groupsList = groupsList;
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
     * @return the newGroupName
     */
    public String getNewGroupName() {
        return newGroupName;
    }

    /**
     * @param newGroupName the newGroupName to set
     */
    public void setNewGroupName(String newGroupName) {
        this.newGroupName = newGroupName;
    }

    /**
     * @return the newBlockName
     */
    public String getNewBlockName() {
        return newBlockName;
    }

    /**
     * @param newBlockName the newBlockName to set
     */
    public void setNewBlockName(String newBlockName) {
        this.newBlockName = newBlockName;
    }

    /**
     * @return the nameList
     */
    public List<String> getNameList() {
        q = em.createQuery("SELECT b.blockName FROM Block b");
        setNameList(q.getResultList());
        return nameList;
    }

    /**
     * @param nameList the nameList to set
     */
    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    /**
     * @return the ctb
     */
    public List<CycleTimeBean> getCtb() {
        q = em.createQuery("SELECT cyc FROM LsCycleTime cyc");
        for (LsCycleTime lct  : (List<LsCycleTime>)q.getResultList()) {
            CycleTimeBean ctbean = new CycleTimeBean();
            ctbean.setGroupName(lct.getGroupId().getGroupName());
            ctbean.setCycleName(lct.getCycleId().getCycName());
            ctbean.setCatName(lct.getCtId().getCatName());
            ctbean.setOffTime(lct.getOffTime());
            ctbean.setOnTime(lct.getOnTime());
            ctb.add(ctbean);
        }
        return ctb;
    }

    /**
     * @param ctb the ctb to set
     */
    public void setCtb(List<CycleTimeBean> ctb) {
        this.ctb = ctb;
    }
}
