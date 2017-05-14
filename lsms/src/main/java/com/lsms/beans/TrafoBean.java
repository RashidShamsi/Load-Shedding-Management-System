/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.Grids;
import com.lsms.entities.Transformer;
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
public class TrafoBean {
    
    @PersistenceContext
    EntityManager em ;
    
    private String trName ;
    private String grdName ;
    private int trPower;
    private List<String> grdList ;
    private List<Transformer> trList ;
    private Query q ;
    private static final String qForGrd = "select g from Grids g where g.gridName = :gName ";
    
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
     * @return the grdName
     */
    public String getGrdName() {
        return grdName;
    }

    /**
     * @param grdName the grdId to set
     */
    public void setGrdName(String grdName) {
        this.grdName = grdName;
    }

    /**
     * @return the trPower
     */
    public int getTrPower() {
        return trPower;
    }
    
    /**
     * @param trPower the trPower to set
     */
    public void setTrPower(int trPower) {
        this.trPower = trPower;
    }

    /**
     * @return the trList
     */
    public List<Transformer> getTrList() {
        trafoListCreator();
        return trList;
    }

    /**
     * @param trList the trList to set
     */
    public void setTrList(List<Transformer> trList) {
        this.trList = trList;
    }


    /**
     * @return the grdList
     */
    public List<String> getGrdList() {
        gridListCreater();
        return grdList;
    }

    /**
     * @param grdList the grdList to set
     */
    public void setGrdList(List<String> grdList) {
        this.grdList = grdList;
    }

    private void gridListCreater(){
        
        q = em.createQuery("SELECT g.gridName FROM Grids g");
        setGrdList(q.getResultList());
    }
    
    private void trafoListCreator(){
        q = em.createQuery("select t from Transformer t");
        setTrList(q.getResultList());
    }
    
    public void trafoCreater(){
        
        Transformer t = new Transformer();
        Grids g ;
        
        t.setTrafoName(trName);
        q = em.createQuery(qForGrd).setParameter("gName", getGrdName());
        g = (Grids)q.getSingleResult();
        t.setTrafoGridId(g);
        t.setTrafoPower(getTrPower());
        em.persist(t);
    }

    
}
