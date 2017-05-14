/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author furqan
 */
@Entity
@Table(name = "categories")
public class Categories implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private int catId;
    
    @NotNull
    @Column(name = "cat_name")
    private String catName ;
    
    @NotNull
    @Column(name = "ls_status")
    private boolean lsStatus ;
    
    
    
    public int getCatId(){
        return catId ;
    }
    
    public String getCatName(){
        return catName ;
    }
    
    public void setCatName(String name){
        this.catName = name ;
    }
    
    @Override
    public String toString() {
        return "com.lsms.entities.Categories[ id=" + catId + " ]";
    }

    /**
     * @return the lsStatus
     */
    public boolean getLsStatus() {
        return lsStatus;
    }

    /**
     * @param lsStatus the lsStatus to set
     */
    public void setLsStatus(boolean lsStatus) {
        this.lsStatus = lsStatus;
    }
    
}
