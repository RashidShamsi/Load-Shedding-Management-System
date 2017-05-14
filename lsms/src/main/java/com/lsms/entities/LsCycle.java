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
@Table(name = "ls_cycle")
public class LsCycle implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "cycle_id")
    private int cycId ;
    
    @NotNull
    @Column(name = "cycle_name")
    private String cycName ;

    /**
     * @return the cycId
     */
    public int getCycId() {
        return cycId;
    }

    /**
     * @return the cycName
     */
    public String getCycName() {
        return cycName;
    }

    /**
     * @param cycName the cycName to set
     */
    public void setCycName(String cycName) {
        this.cycName = cycName;
    }
    
}
