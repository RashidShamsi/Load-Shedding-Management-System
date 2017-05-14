/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.entities;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name = "power_factor")
public class PowerFactor implements Serializable {

    @Id
    @Column(name = "pf_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pfId;
    
    @NotNull
    @Column(name = "entry_date")
    private Date entryDate ;
    
    @NotNull
    @Column(name = "factor_value")
    private int value ;
    
    @NotNull
    @Column(name = "use_status")
    private boolean inUse ;

    public int getPfId() {
        return pfId;
    }

    public void setPfId(int pfId) {
        this.pfId = pfId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pfId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the pfId fields are not set
        if (!(object instanceof PowerFactor)) {
            return false;
        }
        PowerFactor other = (PowerFactor) object;
        if (this.pfId != other.pfId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lsms.entities.PowerFactor[ id=" + pfId + " ]";
    }

    /**
     * @return the entryDate
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * @param entryDate the entryDate to set
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the inUse
     */
    public boolean isInUse() {
        return inUse;
    }

    /**
     * @param inUse the inUse to set
     */
    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
    
}
