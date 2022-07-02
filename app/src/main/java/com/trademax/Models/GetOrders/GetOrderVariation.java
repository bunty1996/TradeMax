
package com.trademax.Models.GetOrders;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrderVariation {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("items")
    @Expose
    private List<GetOrderItem> items = null;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<GetOrderItem> getItems() {
        return items;
    }

    public void setItems(List<GetOrderItem> items) {
        this.items = items;
    }

}
