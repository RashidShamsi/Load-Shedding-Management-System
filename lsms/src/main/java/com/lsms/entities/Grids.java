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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author furqan
 */
@Entity
@Table(name = "grids")
public class Grids implements Serializable {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grid_id")
    private int gridId ;
    
    @Column(name = "grid_name")
    private String gridName;
    
    @ManyToOne
    @JoinColumn(name = "block_id")
    private Block block;
   
    
//    for chescking extended ls status of grid
    @Column(name = "ext_status")
    private boolean extensionStatus;
    
    //    for chescking unscheduled ls status of grid
    @Column(name = "usl_status")
    private boolean unSchLs ;
    
//    for chescking deviation status of grid
    @Column(name = "dev_status")
    private boolean deviationStatus ;
    
    
   
    public int getGridId(){
        return gridId ;
    }
    
    public String getGridName(){
        return gridName ;
    }
   
    public void setGridName(String gName){
        this.gridName = gName ;
    }
    
    public Block getBlockId(){
        return block ;
    }
    
    public void setBlockId(Block b){
        this.block = b ;
    }

    /**
     * @return the extensionStatus
     */
    public boolean getExtensionStatus() {
        return extensionStatus;
    }

    /**
     * @param extensionStatus the extensionStatus to set
     */
    public void setExtensionStatus(boolean extensionStatus) {
        this.extensionStatus = extensionStatus;
    }

    /**
     * @return the unSchLs
     */
    public boolean getUnSchLs() {
        return unSchLs;
    }

    /**
     * @param unSchLs the unSchLs to set
     */
    public void setUnSchLs(boolean unSchLs) {
        this.unSchLs = unSchLs;
    }

   
    /**
     * @return the deviationStatus
     */
    public boolean getDeviationStatus() {
        return deviationStatus;
    }

    /**
     * @param deviationStatus the deviationStatus to set
     */
    public void setDeviationStatus(boolean deviationStatus) {
        this.deviationStatus = deviationStatus;
    }
    
 
}
