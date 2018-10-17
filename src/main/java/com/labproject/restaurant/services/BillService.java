package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.Bill;

public interface BillService {

    Bill getById(long id);

    void insert(Bill bill);

    void update(Bill bill);

    void delete(Bill bill);
}
