/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.LsCycle;
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
@Stateless
@Named
public class LsCycleBean {

    @PersistenceContext
    EntityManager em ;
    
    Query q ;
    private String cycleName ;
    private List<LsCycle> cycList ;
    
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
     * @return the cycList
     */
    public List<LsCycle> getCycList() {
        q = em.createQuery("SELECT c FROM LsCycle c");
        setCycList(q.getResultList());
        return cycList;
    }

    /**
     * @param cycList the cycList to set
     */
    public void setCycList(List<LsCycle> cycList) {
        this.cycList = cycList;
    }
    
    public void cycleCreater(){
    
        LsCycle lsc = new LsCycle();
        lsc.setCycName(cycleName);
        em.persist(lsc);
    }
}
