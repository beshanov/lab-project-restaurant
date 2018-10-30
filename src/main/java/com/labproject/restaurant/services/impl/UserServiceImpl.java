package com.labproject.restaurant.services.impl;

import com.labproject.restaurant.dao.RoleDao;
import com.labproject.restaurant.dao.UserDao;
import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public void insert(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.insert(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers(false);
    }

    @Override
    public List<User> getAllUsers(boolean full) {
        return userDao.getAllUsers(full);
    }

    @Override
    public boolean isLoginExist(String login) {
        return userDao.getByLogin(login).getId() != 0;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getByLogin(username);
        if (user.getId() == 0) {
            throw new UsernameNotFoundException("Username not found");
        } else {
            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;
            List<GrantedAuthority> authorities = new ArrayList<>();
            String roleName = roleDao.getById(user.getRole().getId()).getName();
            authorities.add(new SimpleGrantedAuthority(roleName));
            return new org.springframework.security.core.userdetails.User(user.getLogin(),
                    user.getPassword(),
                    enabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    authorities);
        }
    }

    @Override
    public User getLoggedUser() {
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            return null;
        }
        return userDao.getByLogin(email);
    }
}
