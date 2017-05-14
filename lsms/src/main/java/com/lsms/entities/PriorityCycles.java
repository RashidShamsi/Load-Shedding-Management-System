/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.entities;

import java.io.Serializable;
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
@Table(name = "priority_cycle_exempt")
@Entity
public class PriorityCycles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "priority_id")
    private LsPriority priority ;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "cycle_id")
    private LsCycle exemptedCycle ;
    
    public int getId() {
        return id;
    }

    /**
     * @return the priority
     */
    public LsPriority getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(LsPriority priority) {
        this.priority = priority;
    }

    /**
     * @return the exemptedCycle
     */
    public LsCycle getExemptedCycle() {
        return exemptedCycle;
    }

    /**
     * @param exemptedCycle the exemptedCycle to set
     */
    public void setExemptedCycle(LsCycle exemptedCycle) {
        this.exemptedCycle = exemptedCycle;
    }
}
