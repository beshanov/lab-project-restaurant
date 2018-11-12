package com.labproject.restaurant.services;

import com.labproject.restaurant.dao.impl.RoleDaoImpl;
import com.labproject.restaurant.entities.Role;
import com.labproject.restaurant.services.impl.RoleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceImplTest {

    @Mock
    private RoleDaoImpl mockRoleDao;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    public void testGetById() {
        long id = 1;
        roleService.getById(id);
        verify(mockRoleDao).getById(id);
    }

    @Test
    public void testInsert() {
        Role role = new Role();
        roleService.insert(role);
        verify(mockRoleDao).insert(role);
    }

    @Test
    public void testUpdate() {
        Role role = new Role();
        roleService.update(role);
        verify(mockRoleDao).update(role);
    }

    @Test
    public void testDelete() {
        Role role = new Role();
        roleService.delete(role);
        verify(mockRoleDao).delete(role);
    }
}
