/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author furqan
 */
@Entity
@Table(name = "ovl_event")
public class OverloadEvent implements Serializable {
    
    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ovlEventId;

    
    @NotNull
    @Column(name = "start_time")
    private Time eventStartTime ;
    
    @NotNull
    @Column(name = "end_time")
    private Time eventEndTime ;
    
    @NotNull
    @Column(name = "event_date")
    private Date eventDate ;
    
    @NotNull
    @Column(name = "status")
    private boolean ovlStatus ;

    /**
     * @return the ovlEventId
     */
    public int getOvlEventId() {
        return ovlEventId;
    }

    /**
     * @return the eventStartTime
     */
    public Time getEventStartTime() {
        return eventStartTime;
    }

    /**
     * @param eventStartTime the eventStartTime to set
     */
    public void setEventStartTime(Time eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    /**
     * @return the eventEndTime
     */
    public Time getEventEndTime() {
        return eventEndTime;
    }

    /**
     * @param eventEndTime the eventEndTime to set
     */
    public void setEventEndTime(Time eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    /**
     * @return the eventDate
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate the eventDate to set
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return the ovlStatus
     */
    public boolean getOvlStatus() {
        return ovlStatus;
    }

    /**
     * @param ovlStatus the ovlStatus to set
     */
    public void setOvlStatus(boolean ovlStatus) {
        this.ovlStatus = ovlStatus;
    }
    

    
}
