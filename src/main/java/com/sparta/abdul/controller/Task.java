package com.sparta.abdul.controller;

import com.sparta.abdul.model.EmployeeDTO;
import com.sparta.abdul.model.EmployeesDAO;

import java.util.HashMap;


public class Task implements Runnable {
    HashMap<Integer, EmployeeDTO> employees;
    public Task(HashMap<Integer, EmployeeDTO> employees){
        this.employees = employees;
    }
    @Override
    public void run() {
        EmployeesDAO employeesDAO = new EmployeesDAO();
        employeesDAO.truncateTable();
        for(HashMap.Entry<Integer, EmployeeDTO> entry : employees.entrySet()) {
            employeesDAO.addToTable(entry.getValue());
        }
    }
}
