package com.labproject.restaurant.test.dao;

import com.labproject.restaurant.dao.Impl.RoleDaoImpl;
import com.labproject.restaurant.entities.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RoleDaoImplTest {
    private DataSource dataSource;
    private RoleDaoImpl roleDao;
    private List<Long> idForDeleteAfterTests = new ArrayList<>();

    private long createNewRole(String name) {
        long id = 0;
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO role (name) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
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

    @Before
    public void setUp() throws Exception {
        dataSource = new DriverManagerDataSource(
                "jdbc:mysql://localhost:3306/restaurant?useSSL=false&serverTimezone=UTC",
                "root",
                "password");
        roleDao = new RoleDaoImpl();
        roleDao.setDataSource(dataSource);
    }

    @After
    public void tearDown() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM role WHERE id = ?");
            for (Long id : idForDeleteAfterTests) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetByIdTest() {
        String name = "newRole";
        long id = createNewRole(name);
        Role role = roleDao.getById(id);
        assertEquals(id, role.getId());
        assertEquals(name, role.getName());
    }

    @Test
    public void testInsert() {
        String name = "roleForInsert";
        Role newRole = new Role();
        newRole.setName(name);
        roleDao.insert(newRole);
        assertFalse(newRole.getId() == 0);
        try (Connection conn = dataSource.getConnection()) {
            ResultSet result = conn.createStatement().executeQuery(
                    "SELECT * FROM role WHERE id = " + newRole.getId());
            result.next();
            assertEquals(name, result.getString("name"));
            idForDeleteAfterTests.add(newRole.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testUpdate() {

        String name = "roleForUpdate";
        long id = createNewRole(name);
        Role role = new Role();
        role.setId(id);
        role.setName("UpdatedRoleForUpdate");
        roleDao.update(role);
        Role updatedRole = roleDao.getById(id);
        assertEquals(role.getName(), updatedRole.getName());
    }

    @Test
    public void testDelete() {
        String name = "roleForDelete";
        long id = createNewRole(name);
        try (Connection conn = dataSource.getConnection()) {
            Role roleForDelete = new Role();
            roleForDelete.setId(id);
            roleForDelete.setName(name);
            roleDao.delete(roleForDelete);
            ResultSet result = conn.createStatement().executeQuery(
                    "SELECT count(*) FROM role WHERE id =" + id);
            result.next();
            assertEquals(0, result.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}