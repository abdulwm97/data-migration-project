package com.sparta.abdul.controller;

import com.sparta.abdul.model.EmployeeDTO;

import java.util.HashMap;

public class SplitData {
    private HashMap<Integer, EmployeeDTO> allData;
    private HashMap<Integer, EmployeeDTO> employeeFirstThird = new HashMap<>();
    private HashMap<Integer, EmployeeDTO> employeeSecondThird = new HashMap<>();
    private HashMap<Integer, EmployeeDTO> employeeThirdThird = new HashMap<>();

    public SplitData(HashMap<Integer, EmployeeDTO> allData){
        this.allData = allData;
    }


    public void splitData(){
        int linePositionCounter = 0;
        for(HashMap.Entry<Integer, EmployeeDTO> entry : allData.entrySet()){
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
    public HashMap<Integer, EmployeeDTO> getEmployeeFirstThird() {
        return employeeFirstThird;
    }

    public HashMap<Integer, EmployeeDTO> getEmployeeSecondThird() {
        return employeeSecondThird;
    }

    public HashMap<Integer, EmployeeDTO> getEmployeeThirdThird() {
        return employeeThirdThird;
    }

}
