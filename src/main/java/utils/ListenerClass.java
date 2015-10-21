package main.java.utils;

import org.testng.IClass;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by Juan_Rodriguez on 10/15/2015.
 */
public class ListenerClass extends TestListenerAdapter{

    public ListenerClass() throws FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream("./Logs/logsOut.txt"));
        System.setOut(out);
        System.out.println(out);
    }

    @Override
    public void onTestStart(ITestResult tr){
        log("Test Started...");
    }

    @Override
    public void onTestSuccess(ITestResult tr){
        log("Test '"+tr.getName()+"' PASSED");

        try {
            log(tr.getTestClass());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        log("Priority of this method is: "+tr.getMethod().getPriority());

        System.out.println("............\n");
    }

    @Override
    public void onTestFailure(ITestResult tr){
        log("Test '"+tr.getName()+"' FAILED");
        log("Priority of this method is: "+tr.getMethod().getPriority());
        System.out.println("...........\n");
    }

    @Override
    public void onTestSkipped(ITestResult tr){
        log("Test '"+tr.getName()+"' SKIPPED");
        System.out.println("............\n");
    }

    private void log(String methodName){
        System.out.println(methodName);
    }

    private void log(IClass testClass) throws FileNotFoundException {
        System.out.println(testClass);
    }
}
