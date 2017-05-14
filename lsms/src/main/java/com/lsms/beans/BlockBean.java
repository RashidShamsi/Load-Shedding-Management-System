/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.Block;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author furqan
 */
@Named
@Stateless
public class BlockBean {

    @PersistenceContext
    EntityManager em ;
    
    private String blockName;
    private List<Block> blockList ;
    
    private Block selectedBlock ;
    private String newBlockName ;
    
    public void onBlockSelection(Block b){
        setSelectedBlock(b) ;
    }
    
    public void blockEditor(){
        em.find(Block.class, getSelectedBlock().getBlockId()).setBlockName(getNewBlockName());
    }

    public void blockDeleter(Block b){
        em.remove(em.find(Block.class, b.getBlockId()));
    }
    
    /**
     * @return the blockName
     */
    public String getBlockName() {
        return blockName;
    }

    /**
     * @param blockname the blockName to set
     */
    public void setBlockName(String blockname) {
        this.blockName = blockname;
    }
    
    /**
     * @return the blockList
     */
    public List<Block> getBlockList() {
        listOfBlocks();
        return blockList;
    }

    /**
     * @param bockList the blockList to set
     */
    public void setBlockList(List<Block> bockList) {
        this.blockList = bockList;
    }
    
    public void beanCreator(){
        
        Block b = new Block();
        b.setBlockName(getBlockName());
        em.persist(b);
        
    }

    public void listOfBlocks(){
        try {
            Query q = em.createQuery("select b from Block b");
            setBlockList(q.getResultList());
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the selectedBlock
     */
    public Block getSelectedBlock() {
        return selectedBlock;
    }

    /**
     * @param selectedBlock the selectedBlock to set
     */
    public void setSelectedBlock(Block selectedBlock) {
        this.selectedBlock = selectedBlock;
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
