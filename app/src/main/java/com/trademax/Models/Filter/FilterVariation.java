package com.trademax.Models.Filter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilterVariation {
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("items")
    @Expose
    private List<FilterItem> items = null;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<FilterItem> getItems() {
        return items;
    }

    public void setItems(List<FilterItem> items) {
        this.items = items;
    }
}
