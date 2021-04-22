package com.sparta.abdul.controller;

import com.sparta.abdul.model.Employee;

import java.util.HashMap;


public class Task implements Runnable {
    HashMap<Integer,Employee> employees;
    public Task(HashMap<Integer,Employee> employees){
        this.employees = employees;
    }
    @Override
    public void run() {
        EmployeesDAO employeesDAO = new EmployeesDAO();
        for(HashMap.Entry<Integer, Employee> entry : employees.entrySet()) {
            employeesDAO.addToTable(entry.getValue());
        }
    }
}
