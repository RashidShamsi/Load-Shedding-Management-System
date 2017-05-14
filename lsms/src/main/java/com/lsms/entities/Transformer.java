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
@Table(name = "trafo")
public class Transformer implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trafo_id")
    private int trafoId ;
    
    @NotNull
    @Column(name = "trafo_name")
    private String tName ;
    
    @NotNull
    @Column(name = "trafo_power")
    private int tPower ;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "gird_id")
    private Grids gridId;
    
    public int getTrafoId(){
        return trafoId ;
    }
    public String getTrafoName(){
        return tName ;
    }
    
    public void setTrafoName(String trafoName){
        this.tName = trafoName ;
    }
    
    public int getTrafoPower(){
        return tPower ;
    }
    
    public void setTrafoPower(int tP){
        this.tPower = tP ;
    }
    
    public Grids getTrafoGridId(){
        return gridId ;
    }
    
    public void setTrafoGridId(Grids tg){
        this.gridId = tg ;
    }
    
    
    @Override
    public String toString() {
        return "com.lsms.entities.NewEntity[ id=" + trafoId + " ]";
    }
    
}
