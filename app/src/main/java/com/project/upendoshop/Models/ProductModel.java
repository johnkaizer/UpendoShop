package com.project.upendoshop.Models;

public class ProductModel {
    String ImageUrl;
    String ProductName;
    String ProductAmount;
    String ProductQuantity;
    String ProductDescription;

    public ProductModel() {
    }

    public ProductModel(String imageUrl, String productName, String productAmount, String productQuantity, String productDescription) {
        ImageUrl = imageUrl;
        ProductName = productName;
        ProductAmount = productAmount;
        ProductQuantity = productQuantity;
        ProductDescription = productDescription;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductAmount() {
        return ProductAmount;
    }

    public void setProductAmount(String productAmount) {
        ProductAmount = productAmount;
    }

    public String getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }
}
