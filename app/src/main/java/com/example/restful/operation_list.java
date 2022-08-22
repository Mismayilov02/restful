package com.example.restful;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class operation_list {

    private String operationName;
    private String operationtype;
    public String getOperationName() {
        return operationName;
    }
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
    public String getOperationtype() {
        return operationtype;
    }
    public void setOperationtype(String operationtype) {
        this.operationtype = operationtype;
    }


}
