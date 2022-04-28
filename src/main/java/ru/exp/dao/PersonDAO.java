package ru.exp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.exp.exception.dao.PersonDAOException;
import ru.exp.model.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM PERSON", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) throws PersonDAOException {
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElseThrow(() ->new PersonDAOException("Don's see object dept id = " + id));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO PERSON (name, number, mail, passport) VALUES (?, ?, ?, ?)",
                person.getName(), person.getNumber(), person.getMail(), person.getPassport());
    }

    public void update(int id, Person updatePerson) {

     jdbcTemplate.update("UPDATE PERSON SET NAME=?, NUMBER=?, MAIL=?, PASSPORT=? WHERE id=?",
             updatePerson.getName(), updatePerson.getNumber(), updatePerson.getMail(), updatePerson.getPassport(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
