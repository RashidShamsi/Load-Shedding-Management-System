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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author furqan
 */
@Entity
@Table(name = "ovl_feeder")
public class OverloadingFeeder implements Serializable {

    @Id
    @Column(name = "ovl_fd_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ovlFeederId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ovl_event_id")
    private OverloadEvent ovlEvent ;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "feeder_id")
    private Feeder ovlFeeder ;
    
    @NotNull
    @Column(name = "start_time")
    private Time fdOvlStartTime ;
    
    @Column(name = "end_time")
    private Time fdOvlEndTime ;
    
    @NotNull
    @Column(name = "fd_load")
    private int feederLoad ;
    

    /**
     * @return the ovlFeederId
     */
    public int getOvlFeederId() {
        return ovlFeederId;
    }

    /**
     * @return the ovlEvent
     */
    public OverloadEvent getOvlEvent() {
        return ovlEvent;
    }

    /**
     * @param ovlEvent the ovlEvent to set
     */
    public void setOvlEvent(OverloadEvent ovlEvent) {
        this.ovlEvent = ovlEvent;
    }

    /**
     * @return the ovlFeeder
     */
    public Feeder getOvlFeeder() {
        return ovlFeeder;
    }

    /**
     * @param ovlFeeder the ovlFeeder to set
     */
    public void setOvlFeeder(Feeder ovlFeeder) {
        this.ovlFeeder = ovlFeeder;
    }

    /**
     * @return the fdOvlStartTime
     */
    public Time getFdOvlStartTime() {
        return fdOvlStartTime;
    }

    /**
     * @param fdOvlStartTime the fdOvlStartTime to set
     */
    public void setFdOvlStartTime(Time fdOvlStartTime) {
        this.fdOvlStartTime = fdOvlStartTime;
    }

    /**
     * @return the fdOvlEndTime
     */
    public Time getFdOvlEndTime() {
        return fdOvlEndTime;
    }

    /**
     * @param fdOvlEndTime the fdOvlEndTime to set
     */
    public void setFdOvlEndTime(Time fdOvlEndTime) {
        this.fdOvlEndTime = fdOvlEndTime;
    }

    /**
     * @return the feederLoad
     */
    public int getFeederLoad() {
        return feederLoad;
    }

    /**
     * @param feederLoad the feederLoad to set
     */
    public void setFeederLoad(int feederLoad) {
        this.feederLoad = feederLoad;
    }

    
}
