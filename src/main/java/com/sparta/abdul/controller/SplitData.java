package com.sparta.abdul.controller;

import com.sparta.abdul.model.Employee;

import java.util.HashMap;

public class SplitData {
    private HashMap<Integer,Employee> allData;
    private HashMap<Integer,Employee> employeeFirstThird = new HashMap<>();
    private HashMap<Integer,Employee> employeeSecondThird = new HashMap<>();
    private HashMap<Integer,Employee> employeeThirdThird = new HashMap<>();

    public SplitData(HashMap<Integer,Employee> allData){
        this.allData = allData;
    }


    public void splitData(){
        int linePositionCounter = 0;
        for(HashMap.Entry<Integer, Employee> entry : allData.entrySet()){
            if(linePositionCounter < allData.size()/3) {
                employeeFirstThird.put(entry.getKey(), entry.getValue());
            }
            else if(linePositionCounter >= allData.size()/3 && linePositionCounter < allData.size()*(2/3)){
                employeeSecondThird.put(entry.getKey(), entry.getValue());
            }
            else{
                employeeThirdThird.put(entry.getKey(), entry.getValue());
            }
            linePositionCounter++;
        }
    }
    public HashMap<Integer, Employee> getEmployeeFirstThird() {
        return employeeFirstThird;
    }

    public HashMap<Integer, Employee> getEmployeeSecondThird() {
        return employeeSecondThird;
    }

    public HashMap<Integer, Employee> getEmployeeThirdThird() {
        return employeeThirdThird;
    }

}
