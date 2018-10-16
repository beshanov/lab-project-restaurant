package com.labproject.restaurant.dao;

import com.labproject.restaurant.dao.impl.DishDaoImpl;
import com.labproject.restaurant.entities.DishEntity;
import org.junit.Test;

public class DishDaoImplTest {
    DishDaoImpl dishDao = new DishDaoImpl();

    @Test
    public void save() {
        DishEntity dishEntity = new DishEntity();
        dishEntity.setName("HUBABA");
        dishEntity.setDescription("Very delicious hubabuba");
        dishEntity.setPrice(12);
    }

}
