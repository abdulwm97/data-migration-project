package com.sparta.abdul.controller;
import com.sparta.abdul.model.Employee;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
//Hashmap employee object and employee dto

public class FileReader {
    private static String inputFile = "resources/employees.csv";
    //check total number of lines


    private HashMap<Integer, Employee> employee = new HashMap<>();
    private HashMap<Integer,Employee> redundantData = new HashMap<>();


    public void storingData(){
        SimpleDateFormat dateConvert = new SimpleDateFormat("dd/mm/yyyy");
        long startTime, totalTime;
        startTime = System.nanoTime();
        String readLine;
        File file = new File(inputFile);
        System.out.println("Size of file = "+file.length());
        int i = 0;
        try(BufferedReader in = new BufferedReader(new java.io.FileReader(inputFile))){
            //if I want to change the buffer
            //byte[] buff=new byte[1024];
            while((readLine = in.readLine()) != null) {
                if(i != 0) {
                    String[] value = readLine.split(",");
                    if (employee.containsKey(Integer.parseInt(value[0]))) {
                        redundantData.put(Integer.parseInt(value[0]), new Employee(Integer.parseInt(value[0]), value[1], value[2], value[3].charAt(0), value[4], value[5].charAt(0), value[6], dateConvert.parse(value[7]), dateConvert.parse(value[8]), Integer.parseInt(value[9])));
                    } else {
                        employee.put(Integer.parseInt(value[0]), new Employee(Integer.parseInt(value[0]), value[1], value[2], value[3].charAt(0), value[4], value[5].charAt(0), value[6], dateConvert.parse(value[7]), dateConvert.parse(value[8]), Integer.parseInt(value[9])));
                    }
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        totalTime = System.nanoTime()-startTime;
        System.out.println("The number of lines in the file is "+i);
        System.out.println(("storing csv data time in ms "+totalTime/1000000));
    }

    public HashMap<Integer, Employee> getEmployee() {
        return employee;
    }

    public HashMap<Integer, Employee> getRedundantData() {
        return redundantData;
    }
}
