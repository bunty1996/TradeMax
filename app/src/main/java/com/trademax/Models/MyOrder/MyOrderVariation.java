
package com.trademax.Models.MyOrder;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyOrderVariation {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("items")
    @Expose
    private List<MyOrderItem> items = null;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<MyOrderItem> getItems() {
        return items;
    }

    public void setItems(List<MyOrderItem> items) {
        this.items = items;
    }

}
