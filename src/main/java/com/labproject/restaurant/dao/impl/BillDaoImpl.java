package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.BillDao;
import com.labproject.restaurant.entities.Bill;
import com.labproject.restaurant.entities.Order;
import com.labproject.restaurant.entities.User;
import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl implements BillDao {

    private DataSource dataSource;

    private final Logger logger = Logger.getLogger(UserDaoImpl.class);

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Bill getById(long id) {
        String request = "SELECT ID, ORDERID, ADMINID, AMOUNT, `DATE`, " +
                "`STATUS` FROM bill where id=?;";
        Bill result = new Bill();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(request)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result.setId(rs.getLong("id"));
                Order order = new Order();
                order.setId(rs.getLong("OrderId"));
                result.setOrder(order);
                User admin = new User();
                admin.setId(rs.getLong("AdminId"));
                result.setAdmin(admin);
                result.setAmount(rs.getBigDecimal("Amount"));
                result.setDate(rs.getTimestamp("Date"));
                result.setStatus(rs.getBoolean("Status"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Bill> getAll() {
        String request = "SELECT ID, ORDERID, ADMINID, AMOUNT, `DATE`, " +
                "`STATUS` FROM bill;";

        List<Bill> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(request)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getLong("id"));
                Order order = new Order();
                order.setId(rs.getLong("OrderId"));
                bill.setOrder(order);
                User admin = new User();
                admin.setId(rs.getLong("AdminId"));
                bill.setAdmin(admin);
                bill.setAmount(rs.getBigDecimal("Amount"));
                bill.setDate(rs.getTimestamp("Date"));
                bill.setStatus(rs.getBoolean("Status"));
                result.add(bill);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void insert(Bill bill) {
        String request = "INSERT INTO Bill (ID, ORDERID, ADMINID, AMOUNT, `DATE`, `STATUS`) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(request)) {
            ps.setLong(1, bill.getId());
            ps.setLong(2, bill.getOrder().getId());
            ps.setLong(3, bill.getAdmin().getId());
            ps.setBigDecimal(4, bill.getAmount());
            ps.setTimestamp(5, bill.getDate());
            ps.setBoolean(6, bill.getStatus());
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(Bill bill) {
        String request = "UPDATE Bill SET `ORDERID`=?, `ADMINID`=?, `AMOUNT`=?, `DATE`=?," +
                " `STATUS`=? WHERE id=?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(request)) {
            ps.setLong(1, bill.getOrder().getId());
            ps.setLong(2, bill.getAdmin().getId());
            ps.setBigDecimal(3, bill.getAmount());
            ps.setTimestamp(4, bill.getDate());
            ps.setBoolean(5, bill.getStatus());
            ps.setLong(6, bill.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String request = "DELETE FROM `BILL` WHERE ID = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(request)) {
            ps.setLong(1, id);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
