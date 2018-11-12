package com.labproject.restaurant.services;

import com.labproject.restaurant.dao.impl.RoleDaoImpl;
import com.labproject.restaurant.dao.impl.UserDaoImpl;
import com.labproject.restaurant.entities.Role;
import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Mock
    private UserDaoImpl mockUserDao;
    @Mock
    private PasswordEncoder mockEncoder;
    @Mock
    private RoleDaoImpl mockRoleDao;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        User user = new User();
        user.setId(1);
        Role role = new Role();
        role.setId(1);
        when(mockUserDao.getById(anyLong())).thenReturn(user);
        when(mockRoleDao.getById(anyLong())).thenReturn(role);
    }


    @Test
    public void testGetById() {
        long id = 1;
        userService.getById(id);
        verify(mockUserDao).getById(id);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setPassword("Password");
        userService.insert(user);
        verify(mockUserDao).insert(user);
    }

    @Test
    public void testUpdateRole() {
        long userId = 1;
        long roleId = 1;
        userService.updateRole(userId, roleId);
        verify(mockUserDao).getById(anyLong());
        verify(mockUserDao).updateRole(any(User.class));
    }
    @Test
    public void testUpdatePassword() {
        User user = new User();
        String password = "password";
        userService.updatePassword(user, password);
        verify(mockUserDao).updatePassword(any(User.class));
    }

    @Test
    public void delete() {
        User user = new User();
        userService.delete(user);
        verify(mockUserDao).delete(user);
    }
}