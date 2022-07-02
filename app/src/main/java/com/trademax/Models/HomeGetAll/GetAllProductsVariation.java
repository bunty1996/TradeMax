
package com.trademax.Models.HomeGetAll;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllProductsVariation {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("items")
    @Expose
    private List<GetAllProductsItem__1> items = null;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<GetAllProductsItem__1> getItems() {
        return items;
    }

    public void setItems(List<GetAllProductsItem__1> items) {
        this.items = items;
    }

}
