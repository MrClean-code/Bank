package ru.exp.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.exp.config.SpringConfig;
import ru.exp.exception.dao.DeptDAOException;
import exception.DeptTestException;
import ru.exp.model.Dept;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class })
public class DeptDAOTest {

    private final JdbcTemplate jdbcTemplate;

    private DeptDAO deptDAO;
    private List<Dept> listDept;
    private Dept dept;
    private Dept deptUpdate;

    @Autowired
    public DeptDAOTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        deptDAO = new DeptDAO(jdbcTemplate);
    }

    @Test
    public void resIsNullTest() {
        assertThat(jdbcTemplate.equals(null)).isFalse();
        assertThat(deptDAO.equals(null)).isFalse();

        System.out.println("Dept Test not null resources");
    }

    @Test
    public void indexTest() throws DeptTestException {
        listDept = new ArrayList<>();

        listDept = deptDAO.index();

        assertThat(listDept.stream().filter((dept) -> dept.getId() >= 1).findAny()
                .orElseThrow(() -> new DeptTestException("No dept object with id >= 1")));

        System.out.println("Dept Test method index work");
    }

    @Test
    public void showTest() throws DeptDAOException, DeptTestException {
        dept = deptDAO.show(2);
        try {
            assertThat(dept.getId()).isEqualTo(2);
        } catch (Exception e) {
            throw new DeptTestException("No object id = 2");
        }
        System.out.println("Dept Test method show work");
    }


    @Test
    public void saveTest() throws DeptTestException {
        dept = new Dept();

        dept.setLimit(11111);
        dept.setPercent(23);

        deptDAO.save(dept);

        assertThat(deptDAO.index().stream().filter((d) -> (d.getPercent() == 23) && (d.getLimit() == 11111))
                .findAny().orElseThrow(() -> new DeptTestException("No dept object with id = " + dept.getId() +
                        " Limit = " + dept.getLimit() + " Percent = " + dept.getPercent())));

        System.out.println("Dept Test method save work");

    }

    @Test
    public void updateTest() throws DeptTestException {
        deptUpdate = new Dept();

        deptUpdate.setLimit(440000);
        deptUpdate.setPercent(11);

        deptDAO.update(4, deptUpdate);

        assertThat(deptDAO.index().stream().filter((d) -> (d.getPercent() == 11) && (d.getLimit() == 440000))
                .findAny().orElseThrow(() -> new DeptTestException("No dept object with id = " + deptUpdate.getId() +
                        " Limit = " + deptUpdate.getLimit() + " Percent = " + deptUpdate.getPercent())));

        System.out.println("Dept Test method update work");
    }



    @Test
    public void deleteTest() throws DeptDAOException {

        int lastElementList  = deptDAO.index().size();

        deptDAO.delete(lastElementList);

        lastElementList = deptDAO.index().size();

        assertThat(deptDAO.show(lastElementList)).isNotNull();

        int nextElementForTable = lastElementList + 1;

        // error don't could edit id after delete object
        // jdbcTemplate.update("ALTER TABLE DEPT ALTER COLUMN id RESTART WITH ?", nextElementForTable);

        System.out.println("Dept Test method delete work");
    }
}
