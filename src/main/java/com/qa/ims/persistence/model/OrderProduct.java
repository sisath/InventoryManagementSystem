package com.qa.ims.persistence.model;

import java.util.Objects;

public class OrderProduct {
    private long orderProductId;
    private Product product;
    private Order order;
    private long quantity;

    public OrderProduct(Product product, Order order, long quantity) {
        this.setProduct(product);
        this.setOrder(order);
        this.setQuantity(quantity);

    }
    public OrderProduct(long orderProductId, Product product, Order order, long quantity) {
        this.setOrderProductId(orderProductId);
        this.setProduct(product);
        this.setOrder(order);
        this.setQuantity(quantity);
    }

    public long getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(long orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "orderProductId=" + orderProductId +
                ", product=" + product +
                ", order=" + order +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProduct that)) return false;
        return getOrderProductId() == that.getOrderProductId()
                && getQuantity() == that.getQuantity()
                && Objects.equals(getProduct(), that.getProduct())
                && Objects.equals(getOrder(), that.getOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderProductId(), getProduct(), getOrder(), getQuantity());
    }
}
