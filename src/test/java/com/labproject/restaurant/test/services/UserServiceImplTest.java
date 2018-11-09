//package com.labproject.restaurant.test.services;
//
//import com.labproject.restaurant.dao.impl.UserDaoImpl;
//import com.labproject.restaurant.entities.User;
//import com.labproject.restaurant.services.impl.UserServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//
//public class UserServiceImplTest {
//    private UserDaoImpl mockUserDao;
//    private UserServiceImpl userService;
//
//    @Before
//    public void setUp() throws Exception {
//        mockUserDao = mock(UserDaoImpl.class);
//        userService = new UserServiceImpl();
//        userService.setUserDao(mockUserDao);
//    }
//
//    @Test
//    public void getById() {
//        long id = 1;
//        userService.getById(id);
//        verify(mockUserDao).getById(id);
//    }
//
//    @Test
//    public void insert() {
//        User user = new User();
//        userService.insert(user);
//        verify(mockUserDao).insert(user);
//    }
//
//    @Test
//    public void updateWithoutPasswordAndRole() {
//        User user = new User();
//        userService.updateWithoutPasswordAndRole(user);
//        verify(mockUserDao).updateWithoutPasswordAndRole(user);
//    }
//    @Test
//    public void delete() {
//        User user = new User();
//        userService.delete(user);
//        verify(mockUserDao).delete(user);
//    }
//}