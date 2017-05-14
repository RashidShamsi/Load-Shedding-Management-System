/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.Feeder;
import com.lsms.entities.LsDetails;
import com.lsms.entities.OverloadEvent;
import com.lsms.entities.OverloadingFeeder;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
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
public class OverloadingBean {

    @PersistenceContext
    private EntityManager em ;
    
    private Query q ;
    
    private List<String> cycleList ;
    private String selectedCycle ;
    
    private List<String> gridList ;
    private String selectedGrid ;

    private List<String> trafoList ;
    private String selectedTrafo ;
    
    private List<String> feederList ;
    private List<String> selectedFeeder ;
    
    private List<OverloadFeeder> ovlFeederList = new ArrayList<OverloadFeeder>() ;
    private Date eventStartTime ;
    private Date eventEndTime ;
    
    private int loadSum ;
    
    /**
     * functionalities of Overloading bean
     * starts from here
    */

    public void onGridSelection(){
        try {
            q = em.createQuery("SELECT tr.tName FROM Transformer tr WHERE tr.gridId.gridName = :gn").
                    setParameter("gn", selectedGrid);
            setTrafoList(q.getResultList());
        } catch (Exception e) {
            System.out.println("Exception in the getTrafoList()");
        }
    }
    
    public void onTrafoSelection(){
        try {
            q = em.createQuery("SELECT fd.feedName FROM Feeder fd WHERE fd.gridId.gridName = :gn AND fd.trafoId.tName = :tn").
                    setParameter("gn", selectedGrid).
                    setParameter("tn", selectedTrafo);
            setFeederList(q.getResultList());
        } catch (Exception e) {
            System.out.println("Exception in the getFeederList()" + e);
        }
    }

    public void onFeederSelection(){
        System.out.println("onFeederSelection()");
        ovlFeederList.clear();
        for (String feeder : selectedFeeder) {
            OverloadFeeder ovlFeeder = new OverloadFeeder(feeder, selectedCycle, em);
            ovlFeederList.add(ovlFeeder);
        }
    }
    
    public void onSubmit(){
        OverloadEvent e = persistOverloadEvent() ;
        persistOverloadingFeeder(e);
        lsDetailCreater(e);
    }
    
    private OverloadEvent persistOverloadEvent(){
        OverloadEvent ove = new OverloadEvent();
        ove.setEventStartTime(new Time(eventStartTime.getHours(), eventStartTime.getMinutes(), eventStartTime.getSeconds()));
        ove.setEventEndTime(new Time(eventEndTime.getHours(), eventEndTime.getMinutes(), eventEndTime.getSeconds()));
        ove.setEventDate(new java.sql.Date(new Date().getTime()));
        ove.setOvlStatus(true);
        em.persist(ove);
        em.flush();
        return ove ;
    }
    
    private void persistOverloadingFeeder(OverloadEvent event){
        loadSum = 0;
        for (OverloadFeeder ovf : ovlFeederList) {
            try {
                OverloadingFeeder f = new OverloadingFeeder();
                f.setOvlEvent(event);
                f.setOvlFeeder(ovf.getFd());
                Feeder feeder = em.find(Feeder.class, ovf.getFd().getFeedId());
                feeder.setOvlStatus(true);
                f.setFdOvlEndTime(new Time(ovf.getOnTime().getHours(),ovf.getOnTime().getMinutes(), ovf.getOnTime().getSeconds()));
                f.setFdOvlStartTime(new Time(ovf.getOffTime().getHours(),ovf.getOffTime().getMinutes(), ovf.getOffTime().getSeconds()));
                f.setFeederLoad(ovf.getLoad());
                loadSum = loadSum + f.getFeederLoad();
                em.persist(f);

            } catch (Exception e) {
                System.out.println("Exception while persisting " + ovf.getFd().getFeedName() + " " + e);
            }
        }
    }
    
    private void lsDetailCreater(OverloadEvent event){
            try {
                System.out.println("****************************************");
                System.out.println("Starting lsDetailCreater() to log the newly");
                System.out.println("created Overloading event into the details");
                System.out.println("****************************************");
                q = em.createQuery("SELECT MAX(lsd.detailId) FROM LsDetails lsd");
                LsDetails detail = em.find(LsDetails.class, (Integer)q.getSingleResult());
                if(     new Date().getDate() == detail.getEntryDate().getDate()
                        && new Date().getMonth() == detail.getEntryDate().getMonth()
                        && new Date().getYear() == detail.getEntryDate().getYear()){
                    System.out.println(loadSum);
                    System.out.println("Dates matched, events are of same date");
                    LsDetails newDetail = new LsDetails();
                    newDetail.setEntryDate(event.getEventDate());
                    newDetail.setStartTime(event.getEventStartTime());
                    q = em.createQuery("SELECT f.value FROM PowerFactor f WHERE f.inUse = TRUE");
                    newDetail.setMwhLoad((loadSum/(Integer)q.getSingleResult()) + detail.getMwhLoad());
                    newDetail.setReason("Overloading");
                    em.persist(newDetail);
                    em.find(LsDetails.class, detail.getDetailId()).setEndTime(newDetail.getStartTime());
                }else{
                    System.out.println("Dates didn't matched, events are not of same date");
                    LsDetails newDetail = new LsDetails();
                    newDetail.setEntryDate(event.getEventDate());
                    newDetail.setStartTime(event.getEventStartTime());
                    newDetail.setMwhLoad(loadSum/(Integer)q.getSingleResult());
                    newDetail.setReason("Overloading");
                    em.persist(newDetail);
                }
            } catch (Exception e) {
                System.out.println("No previous event is present, running catch");
                    LsDetails newDetail = new LsDetails();
                    newDetail.setEntryDate(event.getEventDate());
                    newDetail.setStartTime(event.getEventStartTime());
                    newDetail.setMwhLoad(loadSum/(Integer)q.getSingleResult());
                    newDetail.setReason("Extended Ls");
                    em.persist(newDetail);
            }
        }
    
    /**
     * Getters and setters of overloading bean starts from here
     */
    
    /**
     * @return the cycleList
     */
    public List<String> getCycleList() {
        q = em.createQuery("SELECT c.cycName FROM LsCycle c");
        setCycleList(q.getResultList());
        return cycleList;
    }

    /**
     * @param cycleList the cycleList to set
     */
    public void setCycleList(List<String> cycleList) {
        this.cycleList = cycleList;
    }

    /**
     * @return the selectedCycle
     */
    public String getSelectedCycle() {
        return selectedCycle;
    }

    /**
     * @param selectedCycle the selectedCycle to set
     */
    public void setSelectedCycle(String selectedCycle) {
        this.selectedCycle = selectedCycle;
    }
    
    /**
     * @return the gridList
     */
    public List<String> getGridList() {
        q = em.createQuery("SELECT g.gridName FROM Grids g");
        setGridList(q.getResultList());
        return gridList;
    }

    /**
     * @param gridList the gridList to set
     */
    public void setGridList(List<String> gridList) {
        this.gridList = gridList;
    }

    /**
     * @return the selectedGrid
     */
    public String getSelectedGrid() {
        return selectedGrid;
    }

    /**
     * @param selectedGrid the selectedGrid to set
     */
    public void setSelectedGrid(String selectedGrid) {
        this.selectedGrid = selectedGrid;
    }

    /**
     * @return the trafoList
     */
    public List<String> getTrafoList() {
        return trafoList;
    }

    /**
     * @param trafoList the trafoList to set
     */
    public void setTrafoList(List<String> trafoList) {
        this.trafoList = trafoList;
    }

    /**
     * @return the selectedTrafo
     */
    public String getSelectedTrafo() {
        return selectedTrafo;
    }

    /**
     * @param selectedTrafo the selectedTrafo to set
     */
    public void setSelectedTrafo(String selectedTrafo) {
        this.selectedTrafo = selectedTrafo;
    }

    /**
     * @return the feederList
     */
    public List<String> getFeederList() {
        return feederList;
    }

    /**
     * @param feederList the feederList to set
     */
    public void setFeederList(List<String> feederList) {
        this.feederList = feederList;
    }

    /**
     * @return the selectedFeeder
     */
    public List<String> getSelectedFeeder() {
        return selectedFeeder;
    }

    /**
     * @param selectedFeeder the selectedFeeder to set
     */
    public void setSelectedFeeder(List<String> selectedFeeder) {
        this.selectedFeeder = selectedFeeder;
    }

    /**
     * @return the ovlFeederList
     */
    public List<OverloadFeeder> getOvlFeederList() {
        return ovlFeederList;
    }

    /**
     * @param ovlFeederList the ovlFeederList to set
     */
    public void setOvlFeederList(List<OverloadFeeder> ovlFeederList) {
        this.ovlFeederList = ovlFeederList;
    }

    /**
     * @return the eventStartTime
     */
    public Date getEventStartTime() {
        return eventStartTime;
    }

    /**
     * @param eventStartTime the eventStartTime to set
     */
    public void setEventStartTime(Date eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    /**
     * @return the eventEndTime
     */
    public Date getEventEndTime() {
        return eventEndTime;
    }

    /**
     * @param eventEndTime the eventEndTime to set
     */
    public void setEventEndTime(Date eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    
}
