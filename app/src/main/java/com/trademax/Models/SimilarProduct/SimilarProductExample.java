
package com.trademax.Models.SimilarProduct;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimilarProductExample {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("pageNo")
    @Expose
    private Integer pageNo;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("items")
    @Expose
    private List<SimilarProductItem> items = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<SimilarProductItem> getItems() {
        return items;
    }

    public void setItems(List<SimilarProductItem> items) {
        this.items = items;
    }

}
