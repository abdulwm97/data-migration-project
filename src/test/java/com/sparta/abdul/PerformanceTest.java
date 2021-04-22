package com.sparta.abdul;
import com.sparta.abdul.view.Starter;
import org.junit.Test;

public class PerformanceTest {
    Starter starter = new Starter();
    @Test
    public void threeThreadsTest(){
        long start = System.nanoTime();
        starter.start();
        long stop = System.nanoTime();
        System.out.println("Total latency = "+((stop-start)/1000000)+"ms");
    }
}
