package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class toStringTest {
    private Range exampleRange;
    
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }

/**
* This test creates a range
**/
    @Before
    public void setUp() throws Exception { exampleRange = new Range(3.0,11.0);
    }

/**
* Here we check to see whether the correct string representation of range is returned
**/

    @Test
    public void toStringTest() {
    	assertEquals("The strings are not equal", "Range[3.0,11.0]" , this.exampleRange.toString());
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
