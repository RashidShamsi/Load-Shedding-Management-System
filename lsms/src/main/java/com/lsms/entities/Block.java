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
@Table(name="block")
public class Block implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="b_id")
    private int blockId;
    
    @NotNull
    @Column(name="block_name")
    private String blockName ;
    
    
    public int getBlockId() {
        return blockId ;
    }
    
    public String getBlockName() {
        return blockName ;
    }
  
    public void setBlockName(String bName){
        this.blockName = bName ;
    }

    @Override
    public String toString() {
        return "com.lsms.entities.Block[ id=" + blockId + " ]";
    }
    
}
