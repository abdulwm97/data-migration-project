package com.sparta.abdul.model;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

//DAO is a pattern for the persistence layer. All the database operations
//and connections take place within the file
public class EmployeesDAO {
    private final String URL = "jdbc:mysql://localhost:3306/workers_db?serverTimerzone=GMT";
    private String addingToTable = "INSERT INTO employees VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private String truncatingTable = "TRUNCATE TABLE employees;";
    private Connection connection;
    private static Properties properties = new Properties();

    private Connection connectToDatabase(){
        try {
            properties.load(new FileReader("resources/login.properties"));
            connection = DriverManager.getConnection(URL, properties.getProperty("username"),properties.getProperty("password"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public void addToTable(EmployeeDTO employeeDTO){
        try (PreparedStatement preparedStatement = connectToDatabase().prepareStatement(addingToTable)) {
            preparedStatement.setInt(1, employeeDTO.getEmpId());
            preparedStatement.setString(2, employeeDTO.getNamePrefix());
            preparedStatement.setString(3, employeeDTO.getFirstName());
            preparedStatement.setString(4,Character.toString(employeeDTO.getMiddleInitial()));
            preparedStatement.setString(5, employeeDTO.getLastName());
            preparedStatement.setString(6,Character.toString(employeeDTO.getGender()));
            preparedStatement.setString(7, employeeDTO.geteMail());
            preparedStatement.setDate(8, new java.sql.Date(employeeDTO.getDateOfBirth().getTime()));
            preparedStatement.setDate(9, new java.sql.Date(employeeDTO.getDateOfJoining().getTime()));
            preparedStatement.setInt(10, employeeDTO.getSalary());
            preparedStatement.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addingBatchEmployee(HashMap<Integer, EmployeeDTO> employeeDTO) {
        try (PreparedStatement preparedStatement = connectToDatabase().prepareStatement(addingToTable)) {
            for (EmployeeDTO employee : employeeDTO.values()) {
                preparedStatement.setInt(1, employee.getEmpId());
                preparedStatement.setString(2, employee.getNamePrefix());
                preparedStatement.setString(3, employee.getFirstName());
                preparedStatement.setString(4,Character.toString(employee.getMiddleInitial()));
                preparedStatement.setString(5, employee.getLastName());
                preparedStatement.setString(6,Character.toString(employee.getGender()));
                preparedStatement.setString(7, employee.geteMail());
                preparedStatement.setDate(8, new java.sql.Date(employee.getDateOfBirth().getTime()));
                preparedStatement.setDate(9, new java.sql.Date(employee.getDateOfJoining().getTime()));
                preparedStatement.setInt(10, employee.getSalary());
                preparedStatement.addBatch();
            }


            preparedStatement.executeBatch();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void truncateTable(){
        try (PreparedStatement preparedStatement = connectToDatabase().prepareStatement(truncatingTable)) {
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
