package ru.exp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.exp.exception.dao.DeptDAOException;
import ru.exp.model.Dept;

import java.util.List;

@Component
public class DeptDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DeptDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Dept> index() {
        return jdbcTemplate.query("SELECT * FROM DEPT", new BeanPropertyRowMapper<>(Dept.class));
    }

    public Dept show(int id) throws DeptDAOException {
        return jdbcTemplate.query("SELECT * FROM DEPT WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Dept.class))
            .stream().findAny().orElseThrow(() -> new DeptDAOException("Don's see object dept id = " + id));
    }

    public void save(Dept dept) {
        jdbcTemplate.update("INSERT INTO DEPT (LIMIT, PERCENT) VALUES (?, ?)", dept.getLimit(), dept.getPercent());
    }

    public void update(int id, Dept updateDept) {
        jdbcTemplate.update("UPDATE DEPT SET LIMIT=?, PERCENT=? WHERE id=?", updateDept.getLimit(), updateDept.getPercent(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM DEPT WHERE id=?", id);
    }

}
