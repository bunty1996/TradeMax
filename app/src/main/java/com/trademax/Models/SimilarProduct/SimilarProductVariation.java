
package com.trademax.Models.SimilarProduct;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimilarProductVariation {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("items")
    @Expose
    private List<SimilarProductItems> items = null;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<SimilarProductItems> getItems() {
        return items;
    }

    public void setItems(List<SimilarProductItems> items) {
        this.items = items;
    }

}
