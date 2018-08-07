package com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.bottomcollection;

import java.io.Serializable;
import java.util.Date;

public class DbnmIdEntity implements Serializable {

    private Date first;

    private Date next;

    private String kgbh;

    private String stationid;

    public Date getFirst() {
        return first;
    }

    public void setFirst(Date first) {
        this.first = first;
    }

    public Date getNext() {
        return next;
    }

    public void setNext(Date next) {
        this.next = next;
    }

    public String getKgbh() {
        return kgbh;
    }

    public void setKgbh(String kgbh) {
        this.kgbh = kgbh;
    }

    public String getStationid() {
        return stationid;
    }

    public void setStationid(String stationid) {
        this.stationid = stationid;
    }
}
