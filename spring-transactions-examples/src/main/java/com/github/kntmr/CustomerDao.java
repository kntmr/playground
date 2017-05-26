package com.github.kntmr;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDao {

    private final JdbcTemplate jdbcTemplate;

    public CustomerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> findAll() {
        return jdbcTemplate.query("SELECT name FROM customer", (rs, rowNum) -> rs.getString("name"));
    }

    public void insert(String... names) {
        for (String name : names) {
            jdbcTemplate.update("INSERT INTO customer(name) values (?)", name);
        }
    }

}
