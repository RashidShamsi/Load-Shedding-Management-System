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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author furqan
 */
@Entity
@Table(name = "ext_cycle")
public class ExtendedCycle implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int cycleExtId ;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "event_id")
    private ExtendedLs extEventId ;
    
    @NotNull
    @OneToOne
    @JoinColumn(name = "cyc_id")
    private LsCycle extendedCycleId ;

    /**
     * @return the cycleExtId
     */
    public int getCycleExtId() {
        return cycleExtId;
    }

    /**
     * @return the extEventId
     */
    public ExtendedLs getExtEventId() {
        return extEventId;
    }

    /**
     * @param extEventId the extEventId to set
     */
    public void setExtEventId(ExtendedLs extEventId) {
        this.extEventId = extEventId;
    }

    /**
     * @return the extendedCycleId
     */
    public LsCycle getExtendedCycleId() {
        return extendedCycleId;
    }

    /**
     * @param extendedCycleId the extendedCycleId to set
     */
    public void setExtendedCycleId(LsCycle extendedCycleId) {
        this.extendedCycleId = extendedCycleId;
    }
    
    }

