package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.Bill;

public interface BillService {

    Bill getById(long id);

    void insert(Bill role);

    void update(Bill role);

    void delete(Bill role);
}
