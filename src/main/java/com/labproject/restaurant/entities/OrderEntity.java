package com.labproject.restaurant.entities;

import java.sql.Timestamp;

public class OrderEntity {
    private long id;
    private Timestamp orderDate;
    private long userId;
    private long statusId;

    public OrderEntity(long id, Timestamp orderDate, long userId, long statusId) {
        this.id = id;
        this.orderDate = orderDate;
        this.userId = userId;
        this.statusId = statusId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderdate) {
        this.orderDate = orderdate;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }
}
