/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.Block;
import com.lsms.entities.Grids;
import com.lsms.entities.Groups;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author furqan
 */
@Named
@Stateless
public class GridBean {

    @PersistenceContext
    EntityManager em;

    private String grName ;
    private String blName ;
    private boolean extState;                              //    for chescking extended ls status of grid
    private boolean unSchLsState ;                         //    for chescking unscheduled ls status of grid
    private boolean deviationState ;                        //    for chescking overloading status of grid
    private Query q;
    private List<String> nameList ;
    private List<Grids> gridsList;
    private Grids selectedGrid ;
    private String newGridName ;
    private String newBlockName ;
   
    public void onGridSelection(Grids g){
        System.out.println("selected grid "+g.getGridName());
        selectedGrid = g ;
    }
    
    public void gridEditer(){
        try {
            System.out.println("grid Name " + newGridName + " block name "+ newBlockName);
            
            if(!newGridName.equals("")){
                System.out.println("entered first condition");
                em.find(Grids.class, selectedGrid.getGridId()).setGridName(newGridName);
                em.find(Grids.class, selectedGrid.getGridId()).setExtensionStatus(extState);
                em.find(Grids.class, selectedGrid.getGridId()).setDeviationStatus(deviationState);
                em.find(Grids.class, selectedGrid.getGridId()).setUnSchLs(unSchLsState);
            }
            if (!newBlockName.equals("")) {
                System.out.println("entered second condition");
                q = em.createQuery("SELECT b FROM Block b WHERE b.blockName = :bn")
                        .setParameter("bn", getNewBlockName());
                em.find(Grids.class, selectedGrid.getGridId()).setBlockId((Block)q.getSingleResult());
                em.find(Grids.class, selectedGrid.getGridId()).setExtensionStatus(extState);
                em.find(Grids.class, selectedGrid.getGridId()).setDeviationStatus(deviationState);
                em.find(Grids.class, selectedGrid.getGridId()).setUnSchLs(unSchLsState);
            }
            if (newGridName.equals("") && newBlockName.equals("")) {
                System.out.println("entered third condition");
                em.find(Grids.class, selectedGrid.getGridId()).setExtensionStatus(extState);
                em.find(Grids.class, selectedGrid.getGridId()).setDeviationStatus(deviationState);
                em.find(Grids.class, selectedGrid.getGridId()).setUnSchLs(unSchLsState);
            }
        } catch (Exception e) {
            System.out.println("Exception in the method gridEditer()" + e);
        }
    }
    
    public void gridDeleter(Grids g) {
        em.remove(em.find(Grids.class, g.getGridId()));
    }
    
    /**
     * @return the grName
     */
    public String getGrName() {
        return grName;
    }

    /**
     * @param grName the grName to set
     */
    public void setGrName(String grName) {
        this.grName = grName;
    }

    /**
     * @return the blName
     */
    public String getBlName() {
        return blName;
    }

    /**
     * @param blName the blName to set
     */
    public void setBlName(String blName) {
        this.blName = blName;
    }
   
    /**
     * @return the nameList
     */
    public List<String> getNameList() {
        blockNames();
        return nameList;
    }

    /**
     * @param nameList the nameList to set
     */
    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }
    
    /**
     * @return the extState
     */
    public boolean getExtState() {
        return extState;
    }

    /**
     * @param extState the extState to set
     */
    public void setExtState(boolean extState) {
        this.extState = extState;
    }

    /**
     * @return the unSchLsState
     */
    public boolean getUnSchLsState() {
        return unSchLsState;
    }

    /**
     * @param unSchLsState the unSchLsState to set
     */
    public void setUnSchLsState(boolean unSchLsState) {
        this.unSchLsState = unSchLsState;
    }

    /**
     * @return the deviationState
     */
    public boolean getDeviationState() {
        return deviationState;
    }

    /**
     * @param deviationState the deviationState to set
     */
    public void setDeviationState(boolean deviationState) {
        this.deviationState = deviationState;
    }

    public void blockNames(){
        q = em.createQuery("SELECT b.blockName FROM Block b");
        setNameList(q.getResultList());
    }
    
    public void gridCreator(){
        
        Grids g = new Grids();
        Block b ;
        
        g.setGridName(grName);
        q = em.createQuery("SELECT b FROM Block b WHERE b.blockName = :bn").setParameter("blName", getBlName());
        b = (Block) q.getSingleResult();
        g.setBlockId(b);
        em.persist(g);
    }

   

    /**
     * @return the selectedGrid
     */
    public Grids getSelectedGrid() {
        return selectedGrid;
    }

    /**
     * @param selectedGrid the selectedGrid to set
     */
    public void setSelectedGrid(Grids selectedGrid) {
        this.selectedGrid = selectedGrid;
    }

    /**
     * @return the newGridName
     */
    public String getNewGridName() {
        return newGridName;
    }

    /**
     * @param newGridName the newGridName to set
     */
    public void setNewGridName(String newGridName) {
        this.newGridName = newGridName;
    }

    /**
     * @return the gridsList
     */
    public List<Grids> getGridsList() {
        q = em.createQuery("SELECT g FROM Grids g");
        setGridsList(q.getResultList());
        return gridsList;
    }

    /**
     * @param gridsList the gridsList to set
     */
    public void setGridsList(List<Grids> gridsList) {
        this.gridsList = gridsList;
    }

    /**
     * @return the newBlockName
     */
    public String getNewBlockName() {
        return newBlockName;
    }

    /**
     * @param newBlockName the newBlockName to set
     */
    public void setNewBlockName(String newBlockName) {
        this.newBlockName = newBlockName;
    }


}
