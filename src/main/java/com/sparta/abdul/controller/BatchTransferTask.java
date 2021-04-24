package com.sparta.abdul.controller;

import com.sparta.abdul.model.EmployeeDTO;
import com.sparta.abdul.model.EmployeesDAO;

import java.util.HashMap;

public class BatchTransferTask implements Runnable{
    HashMap<Integer, EmployeeDTO> employees;
    public BatchTransferTask(HashMap<Integer, EmployeeDTO> employees){
        this.employees = employees;
    }
    public void run(){
        EmployeesDAO employeesDAO = new EmployeesDAO();
        employeesDAO.truncateTable();
        employeesDAO.addingBatchEmployee(employees);
    }
}
