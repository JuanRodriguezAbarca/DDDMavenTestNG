package test.java;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Juan_Rodriguez on 10/6/2015.
 */
public class RegularExpressionGroupTest {

    @Test(groups = {"include-test-one"},  enabled = true)
    public void testMethodOne(){
        System.out.println("Test method one");
    }

    @Test(groups = {"include-test-one"},priority = 2)
    public void methodAdded(){
        Assert.assertTrue(true, "Test failed in purpose");
        System.out.println("Added method on group include-test-one");
    }

    @Test(groups = {"include-test-two"},priority = 1)
    public void testMethodTwo(){
        System.out.println("Test method two");
    }

    @Test(groups = {"test-one-excluded"},priority = 2)
    public void testMethodThree(){
        System.out.println("Test method three");
    }

    @Test(groups = {"test-two-excluded"},priority = 3)
    public void testMethodFour(){
        System.out.println("Test method four");
    }
}
