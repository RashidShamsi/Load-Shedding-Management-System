/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.*;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author furqan
 */
@Stateless
public class TimerLogicBean {

    @PersistenceContext
    EntityManager em ;

    private Query q ;
    
    private List<ExtendedLs> extEventList ; 
    private List<UnscheduledLs> ulsEventList ; 
    private List<OverloadEvent> ovlEventList ; 
    private List<LsDeviation> lsDevEventList ; 
    private List<LsPriority> lsPriorityEventList ; 
    private List<ShededCategory> regularLsEventList ; 
    
    @Schedule( minute="*/1",hour="*")
    private void timerFunction(){
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("======Executing timerFunction() at " + Calendar.getInstance().getTime() + " to close expired events======");
        System.out.println("***********************************************");
        System.out.println("------Checking Regular Ls (sheded category) for expired events------");
        regularLsChecker(Calendar.getInstance());
        System.out.println("***********************************************");
        System.out.println("***********************************************");
        System.out.println("------Checking ExtendedLs for expired events------");
        extendeLsChecker(Calendar.getInstance());
        System.out.println("***********************************************");
        System.out.println("***********************************************");
        System.out.println("------Checking UnscheduledLs for expired events------");
        unscheduledLsChecker(Calendar.getInstance());
        System.out.println("***********************************************");
        System.out.println("***********************************************");
        System.out.println("------Checking OverloadEvent for expired events------");
        overloadingChecker(Calendar.getInstance());
        System.out.println("***********************************************");
        System.out.println("***********************************************");
        System.out.println("------Checking DeviaitonEvent for expired events------");
        lsDeviationChecker(Calendar.getInstance());
        System.out.println("***********************************************");
        System.out.println("***********************************************");
        System.out.println("------Checking LsPriorityEvent for expired events------");
        lsPriorityChecker(Calendar.getInstance());
        System.out.println("***********************************************");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    
    private void extendeLsChecker(Calendar currentTime){
        try {
            q = em.createQuery("SELECT ext FROM ExtendedLs ext WHERE ext.eventDate = :eventDate AND ext.extStatus = TRUE")
                    .setParameter("eventDate", new java.sql.Date(new java.util.Date().getTime()));
            extEventList = q.getResultList();
            if(!extEventList.isEmpty()){
                System.out.println("^^^^^^Selected ExtendedLs events of current day^^^^^^");
                List<ExtGrid> extGrids ;

                for (ExtendedLs extendedLs : extEventList) {
                    System.out.println("------Processing event number " + extendedLs.getId() + "------");
                    if(extendedLs.getEnTime().getHours() <= currentTime.getTime().getHours() 
                        && extendedLs.getEnTime().getMinutes() <= currentTime.getTime().getMinutes()){
                            em.find(ExtendedLs.class, extendedLs.getId()).setExtStatus(false);
                            System.out.println("/////Changed the event status to inactive/////");
                            q = em.createQuery("SELECT g FROM ExtGrid g WHERE g.extEventId = :eid")
                                    .setParameter("eid", extendedLs);
                            System.out.println("////selected the grids extended in the event number" + extendedLs.getId());
                            extGrids = q.getResultList();
                            for(ExtGrid extGrid : extGrids){
                                q = em.createQuery("SELECT g FROM Grids g WHERE g.gridId = :gid")
                                        .setParameter("gid", extGrid.getExtGridId().getGridId());
                                Grids grd = (Grids)q.getSingleResult();
                                em.find(Grids.class,extGrid.getExtGridId().getGridId()).setExtensionStatus(false);
                                System.out.println("////removed the grid from extended ls/////");
                            }
                        }else{
                            System.out.println("In event number " + extendedLs.getId() + "," 
                            + (extendedLs.getEnTime().getMinutes() - currentTime.getTime().getMinutes()) + " minutes are remaining"
                            + (extendedLs.getEnTime().getHours()- currentTime.getTime().getHours()) + " hours are remaining");
                        }
                }
            } else {
                System.out.println("^^^^^^No events present^^^^^^");
            }
        } catch (Exception e) {
            System.out.println("Exception in the method extendedLsChecker()" + e);
        }
    }
    
    private void unscheduledLsChecker(Calendar currentTime){
        try {
            q = em.createQuery("SELECT ule FROM UnscheduledLs ule WHERE ule.ulsStatus = TRUE");
            ulsEventList = q.getResultList();
            if(!ulsEventList.isEmpty()){
                System.out.println("^^^^^^Selected UnscheduledLs events that are active^^^^^^");
                List<UnscheduledLsGrids> ulsGrids ;

                for (UnscheduledLs unscheduledLs : ulsEventList) {
                    System.out.println("------Processing event number " + unscheduledLs.getId() + "------");
                    if( unscheduledLs.getEndTime().getHours() <= currentTime.getTime().getHours()
                        && unscheduledLs.getEndTime().getMinutes() <= currentTime.getTime().getMinutes()){
                            q = em.createQuery("SELECT ulsg FROM UnscheduledLsGrids ulsg WHERE ulsg.uslEventId = :event")
                                    .setParameter("event", unscheduledLs);
                            ulsGrids = q.getResultList();
                            System.out.println("////selected the grids in UnscheduledLs havinh the event number" + unscheduledLs.getId());
                            em.find(UnscheduledLs.class, unscheduledLs.getId()).setUlsStatus(false);
                            System.out.println("/////Changed the event status to inactive/////");
                            for(UnscheduledLsGrids grid : ulsGrids){
                                em.find(Grids.class, grid.getUslGridId().getGridId()).setUnSchLs(false);
                                System.out.println("////removed the grid from unscheduled ls/////");
                            }
                        }else{
                            System.out.println("In UnscheduledLs event number " + unscheduledLs.getId() + ","
                            + (unscheduledLs.getEndTime().getMinutes() - currentTime.getTime().getMinutes()) + " minutes are remaining"
                            + (unscheduledLs.getEndTime().getHours()- currentTime.getTime().getHours()) + " hours are remaining");
                        }
                }
            } else {
                System.out.println("^^^^^^No events present^^^^^^");
            }
        } catch (Exception e) {
            System.out.println("Exception in the method unscheduledLsChecker()" + e);
        }
    }
    
    private void overloadingChecker(Calendar currentTime){
        try {
            q = em.createQuery("SELECT ovlEvent FROM OverloadEvent ovlEvent WHERE OVLEVENT.ovlStatus = TRUE");
            ovlEventList = q.getResultList();
            if(!ovlEventList.isEmpty()){
                System.out.println("^^^^^^Selected Overloading events that are active^^^^^^");
                List<OverloadingFeeder> ovlFeedersList ;

                for (OverloadEvent event : ovlEventList) {
                    System.out.println("------Processing event number " + event.getOvlEventId() + "------");
                    if( event.getEventEndTime().getHours() <= currentTime.getTime().getHours()
                            && event.getEventEndTime().getMinutes() <= currentTime.getTime().getMinutes()){
                            q = em.createQuery("SELECT ovlFeeders FROM OverloadingFeeder ovlFeeders WHERE OVLFEEDERS.ovlEvent = :eventId")
                                    .setParameter("eventId", event);
                            ovlFeedersList = q.getResultList();
                            System.out.println("////selected the feeders in Overloading having the event number" + event.getOvlEventId());
                            em.find(OverloadEvent.class, event.getOvlEventId()).setOvlStatus(false);
                            System.out.println("/////Changed the event status to inactive/////");
                            for(OverloadingFeeder feeder: ovlFeedersList){
                                em.find(Feeder.class, feeder.getOvlFeeder().getFeedId()).setOvlStatus(false);
                                System.out.println("////removed the feeder from overloading/////");
                            }
                        }else{
                            System.out.println("In Overloading event number " + event.getOvlEventId() + "," 
                            + (event.getEventEndTime().getMinutes() - currentTime.getTime().getMinutes()) + " minutes are remaining"
                            + (event.getEventEndTime().getHours()- currentTime.getTime().getHours()) + " hours are remaining");
                        }
                }
            } else {
                System.out.println("^^^^^^No events present^^^^^^");
            }
        } catch (Exception e) {
            System.out.println("Exception in the method overloadChecker()" + e);
        }
    }
    
    private void lsDeviationChecker(Calendar currentTime){
        try {
            q = em.createQuery("SELECT devEvent FROM LsDeviation devEvent WHERE devEvent.devStatus = TRUE");
            lsDevEventList = q.getResultList();
            if(!lsDevEventList.isEmpty()){
                System.out.println("^^^^^^Selected LsDeviation events that are active^^^^^^");
                List<DeviationCategory> devGridsList ;

                for (LsDeviation event : lsDevEventList) {
                    System.out.println("------Processing event number " + event.getId() + "------");
                    if( event.getEnTime().getHours() <= currentTime.getTime().getHours() 
                            && event.getEnTime().getMinutes() <= currentTime.getTime().getMinutes()){
                            q = em.createQuery("SELECT devGrids FROM DeviationCategory devGrids WHERE devGrids.devEvent = :eventId")
                                    .setParameter("eventId", event);
                            devGridsList = q.getResultList();
                            System.out.println("////selected the grids in Deviation having the event number" + event.getId());
                            em.find(LsDeviation.class, event.getId()).setDevStatus(false);
                            System.out.println("/////Changed the event status to inactive/////");
                            for(DeviationCategory grids : devGridsList){
                                em.find(Grids.class, grids.getGridId().getGridId()).setDeviationStatus(false);
                                System.out.println("////removed the grid from deviation/////");
                            }
                        }else{
                            System.out.println("In Deviation event number " + event.getId() 
                            + "," + (event.getEnTime().getMinutes() - currentTime.getTime().getMinutes()) + " minutes are remaining"
                            + (event.getEnTime().getHours()- currentTime.getTime().getHours()) + " hours are remaining");
                        }
                }
            } else {
                System.out.println("^^^^^^No events present^^^^^^");
            }
        } catch (Exception e) {
            System.out.println("Exception in the method lsDeviationChecker()" + e);
        }
    }
    
    private void lsPriorityChecker(Calendar currentTime){
        try {
            q = em.createQuery("SELECT priority FROM LsPriority priority WHERE PRIORITY.priorityStatus = TRUE");
            lsPriorityEventList = q.getResultList();
            if(!lsPriorityEventList.isEmpty()){
                System.out.println("^^^^^^Selected LsPriority events that are active^^^^^^");
                for (LsPriority event : lsPriorityEventList) {
                    System.out.println("------Processing event number " + event.getId() + "------");
                    if(event.getEndDate().getYear() <= currentTime.getTime().getYear() 
                            && event.getEndDate().getMonth() <= currentTime.getTime().getMonth()
                            && event.getEndDate().getDate() <= currentTime.getTime().getDate()){
                                if( event.getEndTime().getHours() <= currentTime.getTime().getHours() 
                                        && event.getEndTime().getMinutes() <= currentTime.getTime().getMinutes()){
                                    em.find(LsPriority.class, event.getId()).setPriorityStatus(false);
                                    System.out.println("/////Changed the event status to inactive/////");
                                    em.find(Feeder.class, event.getFeeder().getFeedId()).setLsPriority(false);
                                    System.out.println("////removed the feeder from priority/////");
                                }else{
                                    System.out.println("In priority event number " + event.getId() + 
                                    "," + (event.getEndTime().getMinutes() - currentTime.getTime().getMinutes())
                                    + " minutes are remaining," 
                                    + (event.getEndTime().getHours()- currentTime.getTime().getHours()) +
                                            " hours are remaining");
                                    }
                            }else{
                                System.out.println("In Priority event number " + event.getId() + "," 
                                + (event.getEndDate().getDate()- currentTime.getTime().getDate()) + " days are remaining, "
                                + (event.getEndDate().getMonth()- currentTime.getTime().getMonth() + " months are remaining, ")
                                + (event.getEndDate().getYear()- currentTime.getTime().getYear()) + " years are remaining");
                            }
                }
            } else {
                System.out.println("^^^^^^No events present^^^^^^");
            }
            
        } catch (Exception e) {
            System.out.println("Exception in the method lsPriorityChecker()" + e);
        }
    }

    private void regularLsChecker(Calendar currentTime){
        try {
            q = em.createQuery("SELECT regular FROM ShededCategory regular WHERE regular.status = TRUE");
            regularLsEventList = q.getResultList();
            if(!regularLsEventList.isEmpty()){
                System.out.println("^^^^^^Selected Regular ls events that are active^^^^^^");
                for (ShededCategory sc : regularLsEventList) {
                    System.out.println("------Processing regular ls event number " + sc.getId() + "------");
                    if(sc.getEntryDate().getYear() <= currentTime.getTime().getYear() 
                            && sc.getEntryDate().getMonth() <= currentTime.getTime().getMonth()
                            && sc.getEntryDate().getDate() <= currentTime.getTime().getDate()){
                                if( sc.getCycleTimes().getOnTime().getHours() <= currentTime.getTime().getHours() 
                                        && sc.getCycleTimes().getOnTime().getMinutes() <= currentTime.getTime().getMinutes()){
                                    em.find(ShededCategory.class, sc.getId()).setStatus(false);
                                    System.out.println("/////Changed the regular event status to normal/////");
                                }else{
                                    System.out.println("In regular ls event number " + sc.getId() + 
                                    "," + (sc.getCycleTimes().getOnTime().getMinutes() - currentTime.getTime().getMinutes())
                                    + " minutes are remaining," 
                                    + (sc.getCycleTimes().getOnTime().getHours()- currentTime.getTime().getHours()) +
                                            " hours are remaining");
                                    }
                            }
                }
            } else {
                System.out.println("^^^^^^No events present^^^^^^");
            }
            
        } catch (Exception e) {
            System.out.println("Exception in the method regularLsChecker()" + e);
        }
    }

}
