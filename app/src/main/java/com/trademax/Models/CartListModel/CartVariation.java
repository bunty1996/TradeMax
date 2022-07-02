
package com.trademax.Models.CartListModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartVariation {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("items")
    @Expose
    private List<CartItem> items = null;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

}
