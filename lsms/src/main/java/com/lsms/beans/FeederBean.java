/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.Categories;
import com.lsms.entities.Feeder;
import com.lsms.entities.Grids;
import com.lsms.entities.Groups;
import com.lsms.entities.Transformer;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Calendar;
//import org.joda.time.LocalTime;

/**
 *
 * @author furqan
 */
@Named
@Stateless
public class FeederBean {
    
    @PersistenceContext
    EntityManager em ;
    
    private String fdName ;
    private String ctName ;
    private String grName ;
    private String trName ;
    private String grpName ;
    private String blkName ;
    private boolean exStatus ;
    private String reason ;
    private Groups gp ;
    private Grids g ;
    private Query q ;
    private List<String> ctList ;
    private List<String> grList ;
    private List<String> trList;
    private List<String> gpList ;
    private List<Feeder> fdList;
          
//*********************************************getters and setters for feeder input fields**************************************************************
    
    /**
     * @return the fdName
     */
    public String getFdName() {
        return fdName;
    }

    /**
     * @param fdName the fdName to set
     */
    public void setFdName(String fdName) {
        this.fdName = fdName;
    }

    /**
     * @return the ctName
     */
    public String getCtName() {
        return ctName;
    }

    /**
     * @param ctName the ctName to set
     */
    public void setCtName(String ctName) {
        this.ctName = ctName;
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
     * @return the grName
     */
    public String getGrName() {
        return grName;
    }

    /**
     * @param grName the grName to set
     */
    public void setGrName(String grName) {
        this.grName = grName;
    }

    /**
     * @return the trName
     */
    public String getTrName() {
        return trName;
    }

    /**
     * @param trName the trName to set
     */
    public void setTrName(String trName) {
        this.trName = trName;
    }

    /**
     * @return the exStatus
     */
    public boolean getExStatus() {
        return exStatus;
    }

    /**
     * @param exStatus the exStatus to set
     */
    public void setExStatus(boolean exStatus) {
        this.exStatus = exStatus;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    
    /**
     * @return the ctList
     */
    public List<String> getCtList() {
        q = em.createQuery("SELECT cat.catName FROM Categories cat");
        setCtList(q.getResultList());
        return ctList;
    }

    /**
     * @param ctList the ctList to set
     */
    public void setCtList(List<String> ctList) {
        this.ctList = ctList;
    }

    /**
     * @return the grList
     */
    public List<String> getGrList() {
        q = em.createQuery("SELECT g.gridName FROM Grids g");
        setGrList(q.getResultList());
        return grList;
    }

    /**
     * @param grList the grList to set
     */
    public void setGrList(List<String> grList) {
        this.grList = grList;
    }

    /**
     * @return the trList
     */
    public List<String> getTrList() {
//        onGridNameChange();
        return trList;
    }

    /**
     * @param trList the trList to set
     */
    public void setTrList(List<String> trList) {
        this.trList = trList;
    }

    /**
     * @return the gpList
     */
    public List<String> getGpList() {
//        q = em.createQuery("SELECT g.block.blockName FROM Grids g WHERE g.gridName = :gn").setParameter("gn", getGrName());
//        q = em.createQuery("SELECT g.groupName FROM Groups g WHERE g.block.blockName = :bn").setParameter("bn", (String)q.getSingleResult());
//        setGpList(q.getResultList());
        return gpList;
    }

    /**
     * @param gpList the gpList to set
     */
    public void setGpList(List<String> gpList) {
        this.gpList = gpList;
    }

    /**
     * @return the fdList
     */
    public List<Feeder> getFdList() {
        q = em.createQuery("SELECT fd FROM Feeder fd");
        setFdList(q.getResultList());
        for (Feeder f : (List<Feeder>)q.getResultList()) {
            System.out.println(f.getFeedName() + f.getGroupId().getGroupName());
            
        }
        return fdList;
    }  

    /**
     * @param fdList the fdList to set
     */
    public void setFdList(List<Feeder> fdList) {
        this.fdList = fdList;
    }

//********************    method to get list of all categories
    
    
   
   public void onGridNameChange(){
       
       System.out.println("On grid name change");
       
       q = em.createQuery("SELECT g FROM Grids g WHERE g.gridName = :gn").setParameter("gn", getGrName());
       g = (Grids)q.getSingleResult();
       q = em.createQuery("SELECT t.tName FROM Transformer t WHERE t.gridId.gridName = :g").setParameter("g", getGrName());
       setTrList(q.getResultList());
       
//       q = em.createQuery("SELECT g.block.blockName FROM Grids g WHERE g.gridName = :gn").setParameter("gn", getGrName());
//       blkName = (String)q.getSingleResult() ;
       q = em.createQuery("SELECT grp.groupName FROM Groups grp");
       setGpList(q.getResultList());
   }
   
   public void feederCreater(){
       Feeder f = new Feeder();
       f.setFeedName(getFdName());
       q = em.createQuery("SELECT grp FROM Groups grp WHERE grp.groupName = :gn ")
                                                                        .setParameter("gn", getGrpName());
       f.setGroupId((Groups)q.getSingleResult());
       q = em.createQuery("SELECT c FROM Categories c WHERE c.catName = :cn").setParameter("cn", getCtName());
       f.setCategoryId((Categories)q.getSingleResult());
       f.setGridId(g);
       q = em.createQuery("SELECT t FROM Transformer t WHERE t.tName = :tn AND t.gridId.gridName = :gn")
                                                                        .setParameter("tn", getTrName())
                                                                        .setParameter("gn", getGrName());
       f.setTrafoId((Transformer)q.getSingleResult());
       f.setExemptionStatus(getExStatus());
       f.setExemptReason(getReason());
       em.persist(f);
   }
}
