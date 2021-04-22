package com.sparta.abdul.controller;

import com.sparta.abdul.model.Employee;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

//DAO is a pattern for the persistence layer. All the database operations
//and connections take place within the file
public class EmployeesDAO {
    private final String URL = "jdbc:mysql://localhost:3306/workers_db?serverTimerzone=GMT";
    private String addingToTable = "INSERT INTO employees VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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

    public void addToTable(Employee employee){
        try (PreparedStatement preparedStatement = connectToDatabase().prepareStatement(addingToTable)) {
            preparedStatement.setInt(1,employee.getEmpId());
            preparedStatement.setString(2,employee.getNamePrefix());
            preparedStatement.setString(3,employee.getFirstName());
            preparedStatement.setString(4,Character.toString(employee.getMiddleInitial()));
            preparedStatement.setString(5,employee.getLastName());
            preparedStatement.setString(6,Character.toString(employee.getGender()));
            preparedStatement.setString(7,employee.geteMail());
            preparedStatement.setDate(8, new java.sql.Date(employee.getDateOfBirth().getTime()));
            preparedStatement.setDate(9, new java.sql.Date(employee.getDateOfJoining().getTime()));
            preparedStatement.setInt(10,employee.getSalary());
//            if(preparedStatement.executeUpdate()!=-1){
//                System.out.println("added");
//            }
//            else{
//                System.out.println("Not added");
//            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
