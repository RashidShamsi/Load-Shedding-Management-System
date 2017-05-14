/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.LsDetails;
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
public class LsDetailBean {
    
    @PersistenceContext
    EntityManager em ;
    
    Query q ;
    
    private Date startTIme ;
    private Date endIme ;
    private Date selectedDate ;
    private List<LsDetails> detailList ;

    public void onSubmit(){
//        q = em.createQuery("SELECT d FROM LsDetails d WHERE d.entryDate = :ed")
//                .setParameter("ed", )
    }
    
    /**
     * @return the startTIme
     */
    public Date getStartTIme() {
        return startTIme;
    }

    /**
     * @param startTIme the startTIme to set
     */
    public void setStartTIme(Date startTIme) {
        this.startTIme = startTIme;
    }

    /**
     * @return the endIme
     */
    public Date getEndIme() {
        return endIme;
    }

    /**
     * @param endIme the endIme to set
     */
    public void setEndIme(Date endIme) {
        this.endIme = endIme;
    }

    /**
     * @return the selectedDate
     */
    public Date getSelectedDate() {
        return selectedDate;
    }

    /**
     * @param selectedDate the selectedDate to set
     */
    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    /**
     * @return the detailList
     */
    public List<LsDetails> getDetailList() {
        q = em.createQuery("SELECT lsd FROM LsDetails lsd");
        setDetailList(q.getResultList());
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<LsDetails> detailList) {
        this.detailList = detailList;
    }
    
}
