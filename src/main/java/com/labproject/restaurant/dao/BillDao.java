package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.Bill;

import java.util.List;
import java.util.Set;

public interface BillDao {
    Bill getById(long id);

    List<Bill> getAll();

    void insert(Bill bill);

    void update(Bill bill);

    void deleteById(Long id);
}
