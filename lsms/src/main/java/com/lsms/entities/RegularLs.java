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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author furqan
 */
@Entity
@Table(name = "regular_ls")
public class RegularLs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "grd_id")
    private Grids grid ;
    
    @NotNull
    @OneToOne
    @JoinColumn(name = "group_id")
    private Groups group ;
    
    @NotNull
    @OneToOne
    @JoinColumn(name = "cycle_id")
    private LsCycle cycle ;
    
    @NotNull
    @Column(name = "ml_load")
    private int mlLoad ;
   
    @NotNull
    @Column(name = "hl_load")
    private int hlLoad ;
    
    @NotNull
    @Column(name = "vhl_load")
    private int vhlLoad ;
    
    @NotNull
    @Column(name = "entry_date")
    private Date date ;
    
    public int getId() {
        return id;
    }

    /**
     * @return the grid
     */
    public Grids getGrid() {
        return grid;
    }

    /**
     * @param grid the gridId to set
     */
    public void setGrid(Grids grid) {
        this.grid = grid;
    }

    /**
     * @return the group
     */
    public Groups getGroup() {
        return group;
    }

    /**
     * @param group the groupId to set
     */
    public void setGroup(Groups group) {
        this.group = group;
    }

    /**
     * @return the cycle
     */
    public LsCycle getCycle() {
        return cycle;
    }

    /**
     * @param cycle the cycle to set
     */
    public void setCycle(LsCycle cycle) {
        this.cycle = cycle;
    }

    /**
     * @return the mlLoad
     */
    public int getMlLoad() {
        return mlLoad;
    }

    /**
     * @param mlLoad the mlLoad to set
     */
    public void setMlLoad(int mlLoad) {
        this.mlLoad = mlLoad;
    }

    /**
     * @return the hlLoad
     */
    public int getHlLoad() {
        return hlLoad;
    }

    /**
     * @param hlLoad the hlLoad to set
     */
    public void setHlLoad(int hlLoad) {
        this.hlLoad = hlLoad;
    }

    /**
     * @return the vhlLoad
     */
    public int getVhlLoad() {
        return vhlLoad;
    }

    /**
     * @param vhlLoad the vhlLoad to set
     */
    public void setVhlLoad(int vhlLoad) {
        this.vhlLoad = vhlLoad;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
}
