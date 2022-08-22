package com.example.restful;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class name_price {

    private String name;
    private Integer price;
    private String coloR;
    private Integer period;
    private String perioDTYPE;
    private String groupname;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getColoR() {
        return coloR;
    }
    public void setColoR(String coloR) {
        this.coloR = coloR;
    }
    public Integer getPeriod() {
        return period;
    }
    public void setPeriod(Integer period) {
        this.period = period;
    }
    public String getPerioDTYPE() {
        return perioDTYPE;
    }
    public void setPerioDTYPE(String perioDTYPE) {
        this.perioDTYPE = perioDTYPE;
    }
    public String getGroupname() {
        return groupname;
    }
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

}