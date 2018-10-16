package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.Bill;

import java.util.List;
import java.util.Set;

public interface BillDao {
    public Bill getById(long id);

    public List<Bill> getByAll();

    public void insert(Bill bill);

    public void update(Bill bill);

    public void deleteById(Long id);
}
