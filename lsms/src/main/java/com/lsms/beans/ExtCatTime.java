/****************************************************
 * This class is used for getting data for 
 * ExtCategoriesTime entity and is used in
 * ExtendedLs
 ****************************************************/

package com.lsms.beans;

import com.lsms.entities.Categories;
import java.util.Date;

/**
 * @author furqan
 */
public class ExtCatTime {
    
    private Categories cat ;
    private Date extendedTime ;

    /**
     * @return the cat
     */
    public Categories getCat() {
        return cat;
    }

    /**
     * @param cat the cat to set
     */
    public void setCat(Categories cat) {
        this.cat = cat;
    }

    /**
     * @return the extendedTime
     */
    public Date getExtendedTime() {
        return extendedTime;
    }

    /**
     * @param extendedTime the extendedTime to set
     */
    public void setExtendedTime(Date extendedTime) {
        this.extendedTime = extendedTime;
    }
}
