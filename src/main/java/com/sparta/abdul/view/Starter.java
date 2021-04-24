package com.sparta.abdul.view;

import com.sparta.abdul.controller.BatchTransferTask;
import com.sparta.abdul.controller.FileReader;
import com.sparta.abdul.controller.SplitData;
import com.sparta.abdul.controller.Task;

public class Starter {
    public void start(){
        FileReader fileReader = new FileReader();
        fileReader.storingData();
        SplitData splitData = new SplitData(fileReader.getEmployee());
        splitData.splitData();
//        Task taskOne = new Task(splitData.getEmployeeFirstThird());
//        Task taskTwo = new Task(splitData.getEmployeeSecondThird());
//        Task taskThree = new Task(splitData.getEmployeeThirdThird());
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
    }
}
