package com.labproject.restaurant.test.dao;

import com.labproject.restaurant.dao.Impl.RoleDaoImpl;
import com.labproject.restaurant.dao.Impl.UserDaoImpl;
import com.labproject.restaurant.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    private DataSource dataSource;
    private UserDaoImpl userDao;
    private RoleDaoImpl roleDao;
    private List<Long> idForDeleteAfterTests = new ArrayList<>();

    private long createNewUser(String login) {
        long id = 0;
        String firstname = "TestPersonFirstName";
        String lastname = "TestPersonLastName";
        String pwd = "TestPersonPassword";
        long roleId = 2;
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO user (lastname, firstname,login,pwd,roleid) VALUES (?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, lastname);
            statement.setString(2, firstname);
            statement.setString(3, login);
            statement.setString(4, pwd);
            statement.setLong(5, roleId);
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            result.next();
            id = result.getLong(1);
            idForDeleteAfterTests.add(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private User createNewUserEntity(String login) {
        User user = new User();
        user.setFirstname("firstname");
        user.setLastname("lasttname");
        user.setLogin(login);
        user.setPassword("password");
        user.setRole(roleDao.getById(1));
        return user;
    }

    @Before
    public void setUp() throws Exception {
        dataSource = new DriverManagerDataSource(
                "jdbc:mysql://localhost:3306/restaurant?useSSL=false&serverTimezone=UTC",
                "root",
                "password");
        userDao = new UserDaoImpl();
        userDao.setDataSource(dataSource);
        roleDao = new RoleDaoImpl();
        roleDao.setDataSource(dataSource);
    }

    @After
    public void tearDown() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM user WHERE id = ?");
            for (Long id : idForDeleteAfterTests) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetById() {
        String login = "newUser";
        long id = createNewUser(login);
        User user = userDao.getById(id);
        assertEquals(id, user.getId());
        assertEquals(login, user.getLogin());
    }

    @Test
    public void testInsert() {
        String login = "UserForInsert";
        User newUser = createNewUserEntity(login);
        userDao.insert(newUser);
        assertFalse(newUser.getId() == 0);
        try (Connection conn = dataSource.getConnection()) {
            ResultSet result = conn.createStatement().executeQuery(
                    "SELECT * FROM user WHERE id = " + newUser.getId());
            result.next();
            assertEquals(login, result.getString("login"));
            idForDeleteAfterTests.add(newUser.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        String login = "userForUpdate";
        long id = createNewUser(login);
        User user = createNewUserEntity(login);
        user.setId(id);
        user.setLogin("newLogin");
        userDao.update(user);
        User updatedUser = userDao.getById(id);
        assertEquals(user.getLogin(), updatedUser.getLogin());
    }

    @Test
    public void testDelete() {
        String login = "userForDelete";
        long id = createNewUser(login);
        try (Connection conn = dataSource.getConnection()) {
            User userForDelete = createNewUserEntity(login);
            userForDelete.setId(id);
            userDao.delete(userForDelete);
            ResultSet result = conn.createStatement().executeQuery(
                    "SELECT count(*) FROM user WHERE id =" + id);
            result.next();
            assertEquals(0, result.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}