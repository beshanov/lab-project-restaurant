package com.labproject.restaurant.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Bill {
    private long id;
    private Order order;
    private User admin;
    private BigDecimal amount;
    private Timestamp date;
    private boolean status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrderId() {
        return order;
    }

    public void setOrderId(Order order) {
        this.order = order;
    }

    public User getAdminId() {
        return admin;
    }

    public void setAdminId(User admin) {
        this.admin = admin;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
