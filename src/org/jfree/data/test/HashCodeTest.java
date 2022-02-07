package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class HashCodeTest {
    private Range a,b;
    
    @Test
    public void TwoSimiliarTest() {
       a= new Range(0,22);
       b= new Range(0,22);
    	assertEquals("The HashCode for the two Ranges are not the same", b.hashCode(), a.hashCode());
    }

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
