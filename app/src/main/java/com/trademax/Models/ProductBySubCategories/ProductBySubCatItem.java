
package com.trademax.Models.ProductBySubCategories;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductBySubCatItem {

    @SerializedName("variation")
    @Expose
    private ProductBySubCatVariation variation;
    @SerializedName("subCategory")
    @Expose
    private ProductBySubCatSubCategory subCategory;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("isLiked")
    @Expose
    private Boolean isLiked;
    @SerializedName("likeCount")
    @Expose
    private String likeCount;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("units")
    @Expose
    private Integer units;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("createdOn")
    @Expose
    private String createdOn;
    @SerializedName("updatedOn")
    @Expose
    private String updatedOn;
    @SerializedName("productFiles")
    @Expose
    private List<ProductBySubCatProductFile> productFiles = null;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public ProductBySubCatVariation getVariation() {
        return variation;
    }

    public void setVariation(ProductBySubCatVariation variation) {
        this.variation = variation;
    }

    public ProductBySubCatSubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(ProductBySubCatSubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(Boolean isLiked) {
        this.isLiked = isLiked;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public List<ProductBySubCatProductFile> getProductFiles() {
        return productFiles;
    }

    public void setProductFiles(List<ProductBySubCatProductFile> productFiles) {
        this.productFiles = productFiles;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
