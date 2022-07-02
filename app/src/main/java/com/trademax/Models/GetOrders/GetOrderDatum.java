
package com.trademax.Models.GetOrders;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrderDatum {

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
    private GetOrderUser user;
    @SerializedName("totalAmount")
    @Expose
    private Integer totalAmount;
    @SerializedName("cart")
    @Expose
    private List<GetOrderCart> cart = null;
    @SerializedName("address")
    @Expose
    private GetOrderAddress address;
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

    public GetOrderUser getUser() {
        return user;
    }

    public void setUser(GetOrderUser user) {
        this.user = user;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<GetOrderCart> getCart() {
        return cart;
    }

    public void setCart(List<GetOrderCart> cart) {
        this.cart = cart;
    }

    public GetOrderAddress getAddress() {
        return address;
    }

    public void setAddress(GetOrderAddress address) {
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
