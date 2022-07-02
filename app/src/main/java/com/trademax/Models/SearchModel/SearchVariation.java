
package com.trademax.Models.SearchModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchVariation {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("items")
    @Expose
    private List<SearchItem> items = null;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<SearchItem> getItems() {
        return items;
    }

    public void setItems(List<SearchItem> items) {
        this.items = items;
    }

}
