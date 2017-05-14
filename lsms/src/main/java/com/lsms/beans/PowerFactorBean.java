/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.PowerFactor;
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
public class PowerFactorBean {

    @PersistenceContext
    EntityManager em ;
    
    Query q ;
    
    private int factorValue;
    private boolean inUse ;

    public void createFactor(){
        PowerFactor pf = new PowerFactor();
        pf.setEntryDate(new java.sql.Date(new java.util.Date().getTime()));
        pf.setValue(factorValue);
        pf.setInUse(true);
        em.persist(pf);
    }
    
    /**
     * @return the factorValue
     */
    public int getFactorValue() {
        return factorValue;
    }

    /**
     * @param factorValue the factorValue to set
     */
    public void setFactorValue(int factorValue) {
        this.factorValue = factorValue;
    }

    /**
     * @return the inUse
     */
    public boolean getInUse() {
        return inUse;
    }

    /**
     * @param inUse the inUse to set
     */
    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}
