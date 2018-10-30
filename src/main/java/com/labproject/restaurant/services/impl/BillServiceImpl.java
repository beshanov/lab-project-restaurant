package com.labproject.restaurant.services.impl;

import com.labproject.restaurant.dao.BillDao;
import com.labproject.restaurant.entities.Bill;
import com.labproject.restaurant.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillDao billDao;

    @Override
    public Bill getById(long id) {
        return billDao.getById(id);
    }

    @Override
    public void insert(Bill bill) {
        billDao.insert(bill);
    }

    @Override
    public void update(Bill bill) {
        billDao.update(bill);
    }

    @Override
    public void delete(Bill bill) {
        billDao.deleteById(bill.getId());
    }
}
