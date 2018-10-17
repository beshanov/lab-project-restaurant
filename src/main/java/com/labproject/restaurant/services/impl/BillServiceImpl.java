package com.labproject.restaurant.services.impl;

import com.labproject.restaurant.dao.BillDao;
import com.labproject.restaurant.entities.Bill;
import com.labproject.restaurant.services.BillService;

public class BillServiceImpl implements BillService {

    private BillDao billDao;

    public void setBillDao(BillDao billDao) {
        this.billDao = billDao;
    }

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
