package ru.exp.dao;

import org.springframework.stereotype.Component;
import ru.exp.model.Dept;
import ru.exp.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DeptDAO {
    private List<Dept> credit;

    private static Connection connection;

    public List<Dept> index(){
        connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        credit = new ArrayList<>();
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:C:\\HSQL\\test","SA","SA");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM DEPT");
            while (resultSet.next()) {
                Dept dept = new Dept();
                dept.setId(resultSet.getInt("id"));
                dept.setLimit(resultSet.getInt("limit"));
                dept.setPercent(resultSet.getInt("percent"));

                credit.add(dept);
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
        return credit;
    }

    public Dept show(int id) {
        Dept dept = null;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:C:\\HSQL\\test","SA","SA");
            preparedStatement = connection.prepareStatement("SELECT * FROM DEPT WHERE id=?");

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            dept = new Dept();

            dept.setId(resultSet.getInt("id"));
            dept.setLimit(resultSet.getInt("limit"));
            dept.setPercent(resultSet.getInt("percent"));

        } catch (SQLException | ClassNotFoundException e) {
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
        return dept;
    }

    public void save(Dept dept){
        connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:C:\\HSQL\\test","SA","SA");
            preparedStatement = connection.prepareStatement("INSERT INTO DEPT (LIMIT, PERCENT) VALUES" +
                    " (?, ?)");
            preparedStatement.setInt(1, dept.getLimit());
            preparedStatement.setInt(2, dept.getPercent());

            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if (connection != null){
                    connection.close();
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(int id, Dept updateDept){
        connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:C:\\HSQL\\test","SA","SA");
            preparedStatement = connection.prepareStatement("UPDATE DEPT SET LIMIT=?, PERCENT=? WHERE id=?");

            preparedStatement.setInt(1, updateDept.getLimit());
            preparedStatement.setInt(2, updateDept.getPercent());
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null){
                    connection.close();
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int id) {
        PreparedStatement preparedStatement = null;
        connection = null;
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:C:\\HSQL\\test","SA","SA");
            preparedStatement = connection.prepareStatement("DELETE FROM DEPT WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch  (SQLException | ClassNotFoundException e)  {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null){
                    connection.close();
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
