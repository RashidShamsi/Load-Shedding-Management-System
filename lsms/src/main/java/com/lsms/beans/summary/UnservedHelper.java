/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans.summary;

import java.sql.Time;

/**
 *
 * @author furqan
 */
public class UnservedHelper {
    
    private String type ;
    private Time startTime ;
    private Time endTime ;
    private long mwh ;

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the startTime
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
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

    /**
     * @return the mwh
     */
    public long getMwh() {
        return mwh;
    }

    /**
     * @param mwh the mwh to set
     */
    public void setMwh(long mwh) {
        this.mwh = mwh;
    }
}
