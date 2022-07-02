package com.trademax.Models.PainterPlasterLikeProduct;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PainterPlasterLikeProductExample {
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private PainterPlasterLikeProductdata data;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PainterPlasterLikeProductdata getData() {
        return data;
    }

    public void setData(PainterPlasterLikeProductdata data) {
        this.data = data;
    }

}
