/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.ExtCategoriesTime;
import com.lsms.entities.ExtGrid;
import com.lsms.entities.ExtendedLs;
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


public class ExtendedLsEvent {

    
    @PersistenceContext
    private EntityManager em ; 
    
    private Query q ;
    
    private ExtendedLs extEvent ;
    private List<ExtGrid> extGrid ;
    private List<ExtCategoriesTime> categories ;

        
    public void initializerFunction(ExtendedLs event){
        
        try {
            extEvent = event ;

            q = em.createQuery("SELECT extg FROM ExtGrid extg WHERE extg.extEventId.id = :event")
                    .setParameter("event", event.getId());
            extGrid = q.getResultList();
            for (ExtGrid extGrid1 : extGrid) {
                System.out.println(extGrid1.getExtGridId().getGridName());
            }
            
            q = em.createQuery("SELECT extc FROM ExtCategoriesTime extc WHERE extc.extEventId.id = :event")
                    .setParameter("event", event.getId());
            categories = q.getResultList();
            for (ExtCategoriesTime extTime : categories) {
                System.out.println(extTime.getExtCatId().getCatName());
            }
        } catch (Exception e) {
            System.out.println("Exception in intilizing the members of ExtendedLsEvent " + e);
        }
        
        
    }
    
    /**
     * @return the extGrid
     */
    public List<ExtGrid> getExtGrid() {
        return extGrid;
    }

    /**
     * @param extGrid the extGrid to set
     */
    public void setExtGrid(List<ExtGrid> extGrid) {
        this.extGrid = extGrid;
    }

    /**
     * @return the categories
     */
    public List<ExtCategoriesTime> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(List<ExtCategoriesTime> categories) {
        this.categories = categories;
    }

    /**
     * @return the extEvent
     */
    public ExtendedLs getExtEvent() {
        return extEvent;
    }

    /**
     * @param extEvent the extEvent to set
     */
    public void setExtEvent(ExtendedLs extEvent) {
        this.extEvent = extEvent;
    }
    
}
