
package com.trademax.Models.MyOrder;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyOrderDatum {

    @SerializedName("tax")
    @Expose
    private Integer tax;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("paymentStatus")
    @Expose
    private String paymentStatus;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("orderID")
    @Expose
    private String orderID;
    @SerializedName("user")
    @Expose
    private MyOrderUser user;
    @SerializedName("totalAmount")
    @Expose
    private String totalAmount;
    @SerializedName("cart")
    @Expose
    private List<MyOrderCart> cart = null;
    @SerializedName("address")
    @Expose
    private MyOrderAddress address;
    @SerializedName("createdOn")
    @Expose
    private String createdOn;
    @SerializedName("updatedOn")
    @Expose
    private String updatedOn;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public MyOrderUser getUser() {
        return user;
    }

    public void setUser(MyOrderUser user) {
        this.user = user;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<MyOrderCart> getCart() {
        return cart;
    }

    public void setCart(List<MyOrderCart> cart) {
        this.cart = cart;
    }

    public MyOrderAddress getAddress() {
        return address;
    }

    public void setAddress(MyOrderAddress address) {
        this.address = address;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
