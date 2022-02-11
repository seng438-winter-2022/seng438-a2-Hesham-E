package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class HashCodeTest {
    private Range a,b;
   
/**
* This test checks whether two similiar ranges produce the same hashcode
**/
    
    @Test
    public void TwoSimiliarTest() {
       a= new Range(0,22);
       b= new Range(0,22);
    	assertEquals("The HashCode for the two Ranges are not the same", b.hashCode(), a.hashCode());
    }
/**
* This test checks whether two different ranges produce a different hashcode
**/
    
    @Test
    public void TwoDifferentTest(){
    a= new Range(1,42);
    b= new Range(22,55);
    assertFalse("The HashCode for the two different Ranges are the same", a.hashCode()==b.hashCode());
    }
    
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
