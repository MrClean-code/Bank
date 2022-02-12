package ru.exp.dao;

import org.springframework.stereotype.Component;
import ru.exp.model.Offer;
import ru.exp.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> people;

    public List<Person> index() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        people = new ArrayList<>();
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:C:\\HSQL\\test","SA","SA");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM PERSON");
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setNumber(resultSet.getLong("number"));
                person.setMail(resultSet.getString("mail"));
                person.setPassport(resultSet.getString("passport"));

                people.add(person);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null){
                    resultSet.close();
                    statement.close();
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return people;
    }


    public Person show(int id) {
        Person person = null;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:C:\\HSQL\\test","SA","SA");
            preparedStatement = connection.prepareStatement("SELECT * FROM PERSON WHERE id=?");

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setNumber(resultSet.getLong("number"));
            person.setMail(resultSet.getString("mail"));
            person.setPassport(resultSet.getString("passport"));

        } catch (SQLException | ClassNotFoundException e)  {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    resultSet.close();
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
//        for (Person p:people) {
//            if (p.getId() == id) {
//                return p;
//            }
//        }
        return person;
    }


    public void save(Person person) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:C:\\HSQL\\test","SA","SA");
            preparedStatement = connection.prepareStatement("INSERT INTO PERSON (name, number, mail, passport) VALUES" +
                    " (?, ?, ?, ?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setLong(2, person.getNumber());
            preparedStatement.setString(3, person.getMail());
            preparedStatement.setString(4, person.getPassport());

            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(int id, Person updatePerson) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:C:\\HSQL\\test","SA","SA");
            preparedStatement = connection.prepareStatement("UPDATE PERSON SET NAME=?, NUMBER=?, MAIL=?, PASSPORT=? WHERE id=?");

            preparedStatement.setString(1, updatePerson.getName());
            preparedStatement.setLong(2, updatePerson.getNumber());
            preparedStatement.setString(3, updatePerson.getMail());
            preparedStatement.setString(4, updatePerson.getPassport());
            preparedStatement.setInt(5, id);

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:C:\\HSQL\\test","SA","SA");
            preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch  (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
