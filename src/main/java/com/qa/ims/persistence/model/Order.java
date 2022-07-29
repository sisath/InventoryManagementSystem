package com.qa.ims.persistence.model;

import java.util.Objects;

public class Order {
    private long orderId;
    private Customer customer;
    private long cost;

    public Customer getCustomerID() {
        return customer;
    }

    public Order(long orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public Order(long orderId, Customer customer, long cost) {
        this.orderId = orderId;
        this.customer = customer;
        this.cost = cost;
    }

    public Order() {

    }
    public Order(Customer customer) {
        this.customer = customer;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customer=" + customer +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return getOrderId() == order.getOrderId() && getCost() == order.getCost()
                && Objects.equals(getCustomer(), order.getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getCustomer(), getCost());
    }
}
