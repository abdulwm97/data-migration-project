package com.sparta.abdul;
import com.sparta.abdul.controller.BatchTransferTask;
import com.sparta.abdul.controller.FileReader;
import com.sparta.abdul.controller.SplitData;
import com.sparta.abdul.controller.Task;
import com.sparta.abdul.model.EmployeeDTO;
import com.sparta.abdul.model.EmployeesDAO;
import org.junit.Test;

import java.util.HashMap;

public class PerformanceTest {
    @Test
    public void sequentialTransferringOneAtATime(){
        EmployeesDAO employeesDAO = new EmployeesDAO();
        employeesDAO.truncateTable();
        long start = System.nanoTime();
        FileReader fileReader = new FileReader();
        fileReader.storingData();
        for(HashMap.Entry<Integer, EmployeeDTO> entry : fileReader.getEmployee().entrySet()) {
            employeesDAO.addToTable(entry.getValue());
        }
        long stop = System.nanoTime();
        System.out.println("Total latency = "+((stop-start)/1000000)+"ms");
    }
    @Test
    public void sequentialTestTransferringBatch(){
        EmployeesDAO employeesDAO = new EmployeesDAO();
        employeesDAO.truncateTable();
        long start = System.nanoTime();
        FileReader fileReader = new FileReader();
        fileReader.storingData();
        employeesDAO.addingBatchEmployee(fileReader.getEmployee());
        long stop = System.nanoTime();
        System.out.println("Total latency = "+((stop-start)/1000000)+"ms");
    }
    @Test
    public void threeThreadsTestTransferringOneAtATime(){
        long start = System.nanoTime();
        FileReader fileReader = new FileReader();
        fileReader.storingData();
        SplitData splitData = new SplitData(fileReader.getEmployee());
        splitData.splitData();
        Task taskOne = new Task(splitData.getEmployeeFirstThird());
        Task taskTwo = new Task(splitData.getEmployeeSecondThird());
        Task taskThree = new Task(splitData.getEmployeeThirdThird());
        Thread threadOne = new Thread(taskOne);
        Thread threadTwo = new Thread(taskTwo);
        Thread threadThree = new Thread(taskThree);
        threadOne.start();
        threadTwo.start();
        threadThree.start();
        try {
            threadThree.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long stop = System.nanoTime();
        System.out.println("Total latency = "+((stop-start)/1000000)+"ms");
    }
    @Test
    public void threeThreadsTestTransferringBatch(){
        long start = System.nanoTime();
        FileReader fileReader = new FileReader();
        fileReader.storingData();
        SplitData splitData = new SplitData(fileReader.getEmployee());
        splitData.splitData();
        BatchTransferTask taskOne = new BatchTransferTask(splitData.getEmployeeFirstThird());
        BatchTransferTask taskTwo = new BatchTransferTask(splitData.getEmployeeSecondThird());
        BatchTransferTask taskThree = new BatchTransferTask(splitData.getEmployeeThirdThird());
        Thread threadOne = new Thread(taskOne);
        Thread threadTwo = new Thread(taskTwo);
        Thread threadThree = new Thread(taskThree);
        threadOne.start();
        threadTwo.start();
        threadThree.start();
        try {
            threadThree.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long stop = System.nanoTime();
        System.out.println("Total latency = "+((stop-start)/1000000)+"ms");
    }
}
