/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.entities;

import java.io.Serializable;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author furqan
 */
@Entity
@Table(name = "special_feeder_cycles")
public class SpecialCycle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sp_fd_id")
    private SpecialGroupFeeder spFeeder ;
    
    @NotNull
    @Column(name = "cycle_name")
    private String cycleName ;
    
    @NotNull
    @Column(name = "start_time")
    private Time stTime ;
    
    @NotNull
    @Column(name = "end_time")
    private Time endTime ;
    
    public int getId() {
        return id;
    }

    /**
     * @return the spFeeder
     */
    public SpecialGroupFeeder getSpFeeder() {
        return spFeeder;
    }

    /**
     * @param spFeeder the spFeeder to set
     */
    public void setSpFeeder(SpecialGroupFeeder spFeeder) {
        this.spFeeder = spFeeder;
    }

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
     * @return the stTime
     */
    public Time getStTime() {
        return stTime;
    }

    /**
     * @param stTime the stTime to set
     */
    public void setStTime(Time stTime) {
        this.stTime = stTime;
    }

    /**
     * @return the endTime
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    

}
