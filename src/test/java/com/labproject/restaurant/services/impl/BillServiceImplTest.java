package com.labproject.restaurant.services.impl;

import com.labproject.restaurant.dao.BillDao;
import com.labproject.restaurant.dao.impl.BillDaoImpl;
import com.labproject.restaurant.entities.Bill;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BillServiceImplTest {
    private BillDao billDao;
    private BillServiceImpl service;

    @Before
    public void setUp() {
        billDao = mock(BillDaoImpl.class);
        service = new BillServiceImpl();
        service.setBillDao(billDao);
    }

    @Test
    public void getById() {
        long id = 1;
        service.getById(id);
        verify(billDao).getById(id);
    }

    @Test
    public void insert() {
        Bill bill = new Bill();
        service.insert(bill);
        verify(billDao).insert(bill);
    }

    @Test
    public void update() {
        Bill bill = new Bill();
        service.update(bill);
        verify(billDao).update(bill);
    }

    @Test
    public void delete() {
        Bill bill = new Bill();
        bill.setId(1);
        service.delete(bill);
        verify(billDao).deleteById(bill.getId());
    }
}