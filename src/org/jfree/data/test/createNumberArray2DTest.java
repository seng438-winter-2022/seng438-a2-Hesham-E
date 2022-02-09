package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.Range; import org.junit.*;

public class createNumberArray2DTest {
    private double [][] a,b = {
    		        {10.0,20.0,30.0,40.0},
			{20.0,40.0,60.0,80.0},
			{30.0,60.0,90.0,120.0}
    		};
    private Number [][] c,d = {
    		        {10.0,20.0,30.0,40.0},
			{20.0,40.0,60.0,80.0},
			{30.0,60.0,90.0,120.0}
			
};
    public void NullTest() {
    try {
		c = DataUtilities.createNumberArray2D(null);
		fail("null cannot be passed as a parameter");
	}
	catch(Exception e) {
		assertEquals("Null does not throw " + "an InvalidParameterException",InvalidParameterException.class,e.getClass());
	}
}

    public void createNumberArray2DWithValid2DArrayOfDoublesTest() {
	c = DataUtilities.createNumberArray2D(b);
	assertArrayEquals("The number 2D array doesn't have the same values"+ " as the 2D array of doubles", d, c);
}


    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
