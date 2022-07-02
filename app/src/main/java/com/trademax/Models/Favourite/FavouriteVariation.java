
package com.trademax.Models.Favourite;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavouriteVariation {

    @SerializedName("key")
    @Expose
    private String key;

    @SerializedName("items")
    @Expose
    private List<FavouriteItem> items = null;

    public List<FavouriteItem> getItems() {
        return items;
    }

    public void setItems(List<FavouriteItem> items) {
        this.items = items;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
