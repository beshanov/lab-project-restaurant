package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.BillDao;
import com.labproject.restaurant.entities.Bill;
import com.labproject.restaurant.entities.Order;
import com.labproject.restaurant.entities.User;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Data Access Object class for the {@link Bill} objects that implements the
 * {@link BillDao} interface
 *
 * @author Zhanna Fedorova
 */
public class BillDaoImpl implements BillDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Bill getById(long id) {
        String query = "SELECT ID, ORDERID, ADMINID, AMOUNT, `DATE`, " +
                "`STATUS` FROM BILL WHERE ID = ?";
        Bill bill = new Bill();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bill.setId(rs.getLong("ID"));
                Order order = new Order();
                order.setId(rs.getLong("ORDER_ID"));
                bill.setOrder(order);
                User admin = new User();
                admin.setId(rs.getLong("ADMIN_ID"));
                bill.setAdmin(admin);
                bill.setAmount(rs.getBigDecimal("AMOUNT"));
                bill.setDate(rs.getTimestamp("DATE"));
                bill.setStatus(rs.getBoolean("STATUS"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return bill;
    }

    @Override
    public List<Bill> getAll() {
        String query = "SELECT ID, ORDERID, ADMINID, AMOUNT, `DATE`, " +
                "`STATUS` FROM BILL;";

        List<Bill> bills = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getLong("ID"));
                Order order = new Order();
                order.setId(rs.getLong("ORDER_ID"));
                bill.setOrder(order);
                User admin = new User();
                admin.setId(rs.getLong("ADMIN_ID"));
                bill.setAdmin(admin);
                bill.setAmount(rs.getBigDecimal("AMOUNT"));
                bill.setDate(rs.getTimestamp("DATE"));
                bill.setStatus(rs.getBoolean("STATUS"));
                bills.add(bill);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return bills;
    }

    @Override
    public void insert(Bill bill) {
        String query = "INSERT INTO Bill (ID, ORDERID, ADMINID, AMOUNT, `DATE`, `STATUS`) " +
                "VALUES (?, ?, ?, ?, ?, ?);";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, bill.getId());
            ps.setLong(2, bill.getOrder().getId());
            ps.setLong(3, bill.getAdmin().getId());
            ps.setBigDecimal(4, bill.getAmount());
            ps.setTimestamp(5, bill.getDate());
            ps.setBoolean(6, bill.getStatus());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(Bill bill) {
        String query = "UPDATE Bill SET `ORDERID`=?, `ADMINID`=?, `AMOUNT`=?, `DATE`=?," +
                " `STATUS`=? WHERE id=?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, bill.getOrder().getId());
            ps.setLong(2, bill.getAdmin().getId());
            ps.setBigDecimal(3, bill.getAmount());
            ps.setTimestamp(4, bill.getDate());
            ps.setBoolean(5, bill.getStatus());
            ps.setLong(6, bill.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(long id) {
        String query = "DELETE FROM `BILL` WHERE ID = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
