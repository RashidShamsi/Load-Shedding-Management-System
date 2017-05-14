/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.*;
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
public class LsCycleTimeViewBean {

    @PersistenceContext
    private EntityManager em ;
    
    private Query q ;
    
    private List<LsCycleTime> ctb ; 
    
    
        


    /**
     * @return the ctb
     */
    public List<LsCycleTime> getCtb() {
        q = em.createQuery("SELECT time FROM LsCycleTime time");
        setCtb(q.getResultList());
        return ctb;
    }

    /**
     * @param ctb the ctb to set
     */
    public void setCtb(List<LsCycleTime> ctb) {
        this.ctb = ctb;
    }
    
    
}
