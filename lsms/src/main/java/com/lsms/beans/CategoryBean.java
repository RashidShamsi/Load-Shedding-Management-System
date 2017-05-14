/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.Categories;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author furqan
 */
@Named
@Stateless
public class CategoryBean {

   @PersistenceContext
   EntityManager em ;
   
   private String catName ;
   private List<Categories> cList;
   
   private Categories selectedCategory ;
   private String newCategoryName ;
   
   public void onCatSelection(Categories c){
       System.out.println("There's a raging fire in my heart tonight");
       selectedCategory = c ;
   }
   
   public void categoryEditor(){
       em.find(Categories.class, selectedCategory.getCatId()).setCatName(newCategoryName);
   }
   
   public void categoryDeleter(Categories c){
       em.remove(em.find(Categories.class, c.getCatId()));
   }
   
    /**
     * @return the catName
     */
    public String getCatName() {
        return catName;
    }

    /**
     * @param cn the catName to set
     */
    public void setCatName(String cn) {
        this.catName = cn ;
    }
    
    /**
     * @return the cList
     */
    public List<Categories> getCList() {
        listOfCategories();
        return cList;
    }

    /**
     * @param cList the cList to set
     */
    public void setCList(List<Categories> cList) {
        this.cList = cList;
    }
    
    public void createCategory(){
        
        Categories c = new Categories();
        c.setCatName(catName);
        em.persist(c);
        
    }

    public void listOfCategories(){
       
        
            Query q = em.createQuery("SELECT c FROM Categories c");
            setCList(q.getResultList());
        
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
     * @return the newCategoryName
     */
    public String getNewCategoryName() {
        return newCategoryName;
    }

    /**
     * @param newCategoryName the newCategoryName to set
     */
    public void setNewCategoryName(String newCategoryName) {
        this.newCategoryName = newCategoryName;
    }
}
