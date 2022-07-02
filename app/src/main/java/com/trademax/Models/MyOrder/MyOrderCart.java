
package com.trademax.Models.MyOrder;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyOrderCart {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("cartId")
    @Expose
    private MyOrderCartId cartId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MyOrderCartId getCartId() {
        return cartId;
    }

    public void setCartId(MyOrderCartId cartId) {
        this.cartId = cartId;
    }

}
