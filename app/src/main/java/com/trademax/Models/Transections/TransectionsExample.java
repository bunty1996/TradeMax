
package com.trademax.Models.Transections;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransectionsExample {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private TransectionMessage message;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public TransectionMessage getMessage() {
        return message;
    }

    public void setMessage(TransectionMessage message) {
        this.message = message;
    }

}
