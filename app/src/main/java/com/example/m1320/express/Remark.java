package com.example.m1320.express;

import java.io.Serializable;

/**
 * Created by m1320 on 2016/8/4.
 */
public class Remark implements Serializable{
    private String id;
    private String datetime;
    private String remark;
    private String zonen;

    public void Remark(){

    }

    public Remark(String datetime, String remark, String zonen) {
        this.datetime = datetime;
        this.remark = remark;
        this.zonen = zonen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getZonen() {
        return zonen;
    }

    public void setZonen(String zonen) {
        this.zonen = zonen;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
