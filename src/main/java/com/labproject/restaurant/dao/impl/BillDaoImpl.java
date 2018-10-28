package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.BillDao;
import com.labproject.restaurant.dao.mapping.BillMapper;
import com.labproject.restaurant.entities.Bill;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;


/**
 * Data Access Object class for the {@link Bill} objects that implements the
 * {@link BillDao} interface
 *
 * @author Zhanna Fedorova
 */
public class BillDaoImpl implements BillDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    @PostConstruct
    public void init(){
        simpleJdbcInsert.withTableName("BILL").usingGeneratedKeyColumns("ID");
    }

    @Override
    public Bill getById(long id) {
        final String query = "SELECT ID, `ORDER_ID`, `ADMIN_ID`, `AMOUNT`, `DATE`, " +
                "`STATUS` FROM BILL WHERE ID = ?";
        List<Bill> bills = jdbcTemplate.query(query, new BillMapper(), id);
        return bills.isEmpty() ? new Bill() : bills.get(0);
    }

    @Override
    public List<Bill> getByAdminId(long adminId) {
        final String query = "SELECT ID, `ORDER_ID`, `ADMIN_ID`, `AMOUNT`, `DATE`, " +
                "`STATUS` FROM BILL WHERE `ADMIN_ID`=?;";
        return jdbcTemplate.query(query, new BillMapper(), adminId);
    }

    @Override
    public void insert(Bill bill) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("ORDER_ID", bill.getOrder().getId())
                .addValue("ADMIN_ID", bill.getAdmin().getId())
                .addValue("AMOUNT", bill.getAmount())
                .addValue("DATE", bill.getDate())
                .addValue("STATUS", bill.getStatus());
        bill.setId(simpleJdbcInsert.executeAndReturnKey(parameters).longValue());
    }

    @Override
    public void update(Bill bill) {
        final String query = "UPDATE BILL SET `ORDER_ID`=?, `ADMIN_ID`=?, `AMOUNT`=?, `DATE`=?, " +
                "`STATUS`=? WHERE ID=?;";

        jdbcTemplate.update(query, bill.getOrder().getId(), bill.getAdmin().getId(),
                bill.getAmount(), bill.getDate(), bill.getStatus(), bill.getId());
    }

    @Override
    public void deleteById(long id) {
        final String query = "DELETE FROM BILL WHERE ID = ?;";
        jdbcTemplate.update(query, id);
    }
}
