package com.qa.ims.persistence.model;

import java.util.Objects;

public class Product {
    private long productId;
    private double price;
    private String productName;
    private String productDescription;

    public Product (long productId, double price, String productName, String productDescription) {
        this.setProductId(productId);
        this.setPrice(price);
        this.setProductName(productName);
        this.setProductDescription(productDescription);
    }

    public Product(double price, String productName, String productDescription) {
        this.setPrice(price);
        this.setProductName(productName);
        this.setProductDescription(productDescription);
    }

    public Product() {

    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", price=" + price +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return getProductId() == product.getProductId()
                && Double.compare(product.getPrice(), getPrice()) == 0
                && Objects.equals(getProductName(), product.getProductName())
                && Objects.equals(getProductDescription(), product.getProductDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getPrice(), getProductName(), getProductDescription());
    }
}
