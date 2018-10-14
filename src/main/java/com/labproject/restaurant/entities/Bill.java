package com.labproject.restaurant.entities;

import java.sql.Timestamp;

public class Bill {
    private long id;
    private long orderId;
    private long adminId;
    private long amount;
    private Timestamp date;
    private long status;

    public Bill(long id, long orderId, long adminId, long amount, Timestamp date, long status) {
        this.id = id;
        this.orderId = orderId;
        this.adminId = adminId;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
