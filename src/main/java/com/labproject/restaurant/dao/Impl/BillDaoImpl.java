package com.labproject.restaurant.dao.Impl;

import com.labproject.restaurant.dao.BillDao;
import com.labproject.restaurant.entities.Bill;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BillDaoImpl implements BillDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Bill getById(long id) {
        Bill result = new Bill();

        if (id < 1) {
            return result;
        }

        String request = "SELECT ID, ORDERID, ADMINID, AMOUNT, `DATE`, " +
                "`STATUS` FROM bill where id=?;";
        PreparedStatement ps;
        try {
            ps = dataSource.getConnection().prepareStatement(request);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result.setId(rs.getLong("id"));
                result.setOrderId(rs.getLong("OrderId"));
                result.setAdminId(rs.getLong("AdminId"));
                result.setAmount(rs.getBigDecimal("Amount"));
                result.setDate(rs.getTimestamp("Date"));
                result.setStatus(rs.getBoolean("Status"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            // TODO: exception handling
        }

        return result;
    }

    @Override
    public List<Bill> getByAll() {
        return null;
    }

    @Override
    public void insert(Bill bill) {
        String request = "INSERT INTO Bill (ID, ORDERID, ADMINID, AMOUNT, `DATE`, `STATUS`) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps;

        try {
            ps = dataSource.getConnection().prepareStatement(request);
            ps.setLong(1, bill.getId());
            ps.setLong(2, bill.getOrderId());
            ps.setLong(3, bill.getAdminId());
            ps.setBigDecimal(4, bill.getAmount());
            ps.setTimestamp(5, bill.getDate());
            ps.setBoolean(6, bill.getStatus());
        } catch (SQLException e) {
            // TODO: exception handling
        }
    }

    @Override
    public void update(Bill bill) {
        if (bill == null) {
            return;
        }

        String request = "UPDATE Bill SET `ORDERID`=?, `ADMINID`=?, `AMOUNT`=?, `DATE`=?," +
                " `STATUS`=? WHERE id=?;";
        PreparedStatement ps;

        try {
            ps = dataSource.getConnection().prepareStatement(request);
            ps.setLong(1, bill.getOrderId());
            ps.setLong(2, bill.getAdminId());
            ps.setBigDecimal(3, bill.getAmount());
            ps.setTimestamp(4, bill.getDate());
            ps.setBoolean(5, bill.getStatus());
            ps.setLong(6, bill.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            // TODO: exception handling
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id < 1) {
            return;
        }
        String request = "DELETE FROM `BILL` WHERE ID = ?;";
        PreparedStatement ps;

        try {
            ps = dataSource.getConnection().prepareStatement(request);
            ps.setLong(1, id);
            ps.close();
        } catch (SQLException e) {
            // TODO: exception handling
        }
    }
}
