package com.labproject.restaurant.entities;

import java.sql.Date;

public class OrderEntity {
    private long id;
    private Date orderDate;
    private long userId;
    private long statusId;

    public OrderEntity(long id, Date orderDate, long userId, long statusId) {
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


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderdate) {
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
