package com.labproject.restaurant.entities;

import java.sql.Date;

public class OrderEntity {
    private long id;
    private Date orderdate;
    private long userid;
    private long statusid;

    public OrderEntity(long id, Date orderdate, long userid, long statusid) {
        this.id = id;
        this.orderdate = orderdate;
        this.userid = userid;
        this.statusid = statusid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Date getOrderDate() {
        return orderdate;
    }

    public void setOrderDate(Date orderdate) {
        this.orderdate = orderdate;
    }


    public long getUserId() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }


    public long getStatusId() {
        return statusid;
    }

    public void setStatusid(long statusid) {
        this.statusid = statusid;
    }
}
