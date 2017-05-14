/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lsms.beans;

import com.lsms.entities.RegularLs;
import javax.inject.Named;

/**
 *
 * @author furqan
 */
@Named
public class RegularLsHelper {
    
    private Long vhlRefLoad ;
    private Long hlRefLoad ;
    private Long mlRefLoad ;
    private RegularLs regularls ;

    /**
     * @return the vhlRefLoad
     */
    public Long getVhlRefLoad() {
        return vhlRefLoad;
    }

    /**
     * @param vhlRefLoad the vhlRefLoad to set
     */
    public void setVhlRefLoad(Long vhlRefLoad) {
        this.vhlRefLoad = vhlRefLoad;
    }

    /**
     * @return the hlRefLoad
     */
    public Long getHlRefLoad() {
        return hlRefLoad;
    }

    /**
     * @param hlRefLoad the hlRefLoad to set
     */
    public void setHlRefLoad(Long hlRefLoad) {
        this.hlRefLoad = hlRefLoad;
    }

    /**
     * @return the mlRefLoad
     */
    public Long getMlRefLoad() {
        return mlRefLoad;
    }

    /**
     * @param mlRefLoad the mlRefLoad to set
     */
    public void setMlRefLoad(Long mlRefLoad) {
        this.mlRefLoad = mlRefLoad;
    }

    /**
     * @return the regularls
     */
    public RegularLs getRegularls() {
        return regularls;
    }

    /**
     * @param regularls the regularls to set
     */
    public void setRegularls(RegularLs regularls) {
        this.regularls = regularls;
    }
}
