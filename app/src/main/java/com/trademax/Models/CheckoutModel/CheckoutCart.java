
package com.trademax.Models.CheckoutModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckoutCart {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("cartId")
    @Expose
    private String cartId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

}
