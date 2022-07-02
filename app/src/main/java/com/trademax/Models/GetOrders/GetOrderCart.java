
package com.trademax.Models.GetOrders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrderCart {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("cartId")
    @Expose
    private GetOrderCartId cartId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GetOrderCartId getCartId() {
        return cartId;
    }

    public void setCartId(GetOrderCartId cartId) {
        this.cartId = cartId;
    }

}
