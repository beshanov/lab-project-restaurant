//package com.labproject.restaurant.test.services;
//
//import com.labproject.restaurant.dao.impl.RoleDaoImpl;
//import com.labproject.restaurant.entities.Role;
//import com.labproject.restaurant.services.impl.RoleServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//
//public class RoleServiceImplTest {
//
//    private RoleDaoImpl mockRoleDao;
//    private RoleServiceImpl roleService;
//
//    @Before
//    public void setUp(){
//        mockRoleDao = mock(RoleDaoImpl.class);
//        roleService = new RoleServiceImpl();
//        roleService.setRoleDao(mockRoleDao);
//    }
//
//    @Test
//    public void testGetById() {
//        long id = 1;
//        roleService.getById(id);
//        verify(mockRoleDao).getById(id);
//    }
//
//    @Test
//    public void testInsert() {
//        Role role = new Role();
//        roleService.insert(role);
//        verify(mockRoleDao).insert(role);
//    }
//
//    @Test
//    public void testUpdate() {
//        Role role = new Role();
//        roleService.updateWithoutPasswordAndRole(role);
//        verify(mockRoleDao).updateWithoutPasswordAndRole(role);
//    }
//
//    @Test
//    public void testDelete() {
//        Role role = new Role();
//        roleService.delete(role);
//        verify(mockRoleDao).delete(role);
//    }
//}