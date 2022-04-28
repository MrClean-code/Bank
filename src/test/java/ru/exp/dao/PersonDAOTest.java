package ru.exp.dao;

import exception.PersonTestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.exp.config.SpringConfig;
import ru.exp.exception.dao.PersonDAOException;
import ru.exp.model.Person;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class })
public class PersonDAOTest {
    private final JdbcTemplate jdbcTemplate;

    private PersonDAO personDAO;
    private List<Person> listPerson;
    private Person person;
    private Person personUpdate;

    @Autowired
    public PersonDAOTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        personDAO = new PersonDAO(jdbcTemplate);
    }

    @Test
    public void resIsNullTest() {
        assertThat(jdbcTemplate.equals(null)).isFalse();
        assertThat(personDAO.equals(null)).isFalse();

        System.out.println("Person Test not null resources");
    }

    @Test
    public void indexTest() throws PersonTestException {
        listPerson = new ArrayList<>();

        listPerson = personDAO.index();

        assertThat(listPerson.stream().filter((person) -> person.getId() >= 1).findAny()
                .orElseThrow(() -> new PersonTestException("No person object with id >= 1")));

        System.out.println("Person Test method index work");
    }


    @Test
    public void showTest() throws PersonDAOException, PersonTestException {
        person = personDAO.show(2);
        try {
            assertThat(person.getId()).isEqualTo(2);
        } catch (Exception e) {
            throw new PersonTestException("No object id = 2");
        }
        System.out.println("Person Test method show work");
    }

    @Test
    public void saveTest() throws PersonTestException {
        person = new Person();

        person.setName("Nick");
        person.setPassport("e1dsa");
        person.setMail("dsad@mail.ru");
        person.setNumber(3123123L);

        personDAO.save(person);

        assertThat(personDAO.index().stream().filter((p) -> (p.getName() == "Nick")).findAny()
                .orElseThrow(() -> new PersonTestException("No person object with id = " + person.getId() +
                        " Name = " + person.getName() + " Mail = " + person.getMail())));

        System.out.println("Person Test method save work");
    }

    @Test
    public void updateTest() throws PersonTestException {
        personUpdate = new Person();

        personUpdate.setName("Mike");
        personUpdate.setPassport("e1dsa");
        personUpdate.setMail("dsad@mail.ru");
        personUpdate.setNumber(23123L);

        personDAO.update(3, personUpdate);

        assertThat(personDAO.index().stream().filter((p) -> (p.getName() == "Mike")).findAny()
                .orElseThrow(() -> new PersonTestException("No person object with id = " + personUpdate.getId() +
                        " Name = " + personUpdate.getName() + " Mail = " + personUpdate.getMail())));

        System.out.println("Person Test method update work");
    }

    @Test
    public void deleteTest() throws PersonDAOException {

        int lastElementList  = personDAO.index().size();

        personDAO.delete(lastElementList);

        lastElementList = personDAO.index().size();

        assertThat(personDAO.show(lastElementList)).isNotNull();

        int nextElementForTable = lastElementList + 1;

        // error don't could edit id after delete object
        // jdbcTemplate.update("ALTER TABLE DEPT ALTER COLUMN id RESTART WITH ?", nextElementForTable);

        System.out.println("Person Test method delete work");
    }
}
