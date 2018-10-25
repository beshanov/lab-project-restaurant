package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.BillDao;
import com.labproject.restaurant.dao.mapping.BillMapper;
import com.labproject.restaurant.entities.Bill;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

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
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public BillDaoImpl(DataSource dataSource) {
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("BILL").usingGeneratedKeyColumns("ID");
    }

    @Override
    public Bill getById(long id) {
        String query = "SELECT ID, `ORDER_ID`, `ADMIN_ID`, `AMOUNT`, `DATE`, " +
                "`STATUS` FROM BILL WHERE ID = ?";
        Bill bill;
        try {

            bill = jdbcTemplate.queryForObject(query, new BillMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
        return bill;
    }

    @Override
    public List<Bill> getByAdminId(Long adminId) {
        String query = "SELECT ID, `ORDER_ID`, `ADMIN_ID`, `AMOUNT`, `DATE`, " +
                "`STATUS` FROM BILL WHERE `ADMIN_ID`=?;";

        List<Bill> bills;
        try {
            bills = jdbcTemplate.query(query, new BillMapper(), adminId);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e.getMessage());
            return null;
        }

        return bills;
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
        String query = "UPDATE BILL SET `ORDER_ID`=?, `ADMIN_ID`=?, `AMOUNT`=?, `DATE`=?, " +
                "`STATUS`=? WHERE ID=?;";

        jdbcTemplate.update(query, bill.getOrder().getId(), bill.getAdmin().getId(),
                bill.getAmount(), bill.getDate(), bill.getStatus(), bill.getId());
    }

    @Override
    public void deleteById(long id) {
        String query = "DELETE FROM BILL WHERE ID = ?;";
        jdbcTemplate.update(query, id);
    }
}
