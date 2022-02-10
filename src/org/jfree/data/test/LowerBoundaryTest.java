package org.jfree.data.test;
import org.jfree.data.Range;
import static org.junit.Assert.*;

import org.junit.*;

public class LowerBoundaryTest {

	/*
	 * Test using two positive values within a range.
	 * Both lower bound value and upper bound value are positive.
	 */
	
	@Test
	public void Positives() {
		assertEquals("Lower bound should be 1", 1, new Range(1, 2).getLowerBound(), .000000001d);   //Two positive test
	}
	
	/*
	 * Test using two negative values within a range.
	 * Both lower bound value and upper bound value are negative.
	 */
	
	@Test
	public void Negatives() {
		assertEquals("Lower bound should be -2", -2, new Range(-2, -1).getLowerBound(), .000000001d);    //Two negative test
	}
	
	/*
	 * Test using a positive and a negative value within a range.
	 * A negative lower bound and a positive upper bound taken for test.
	 */
	
	@Test
	public void Both() {
		assertEquals("Lower bound should be -5", -5, new Range(-5, 1).getLowerBound(), .000000001d);  //Pos-Neg test
	}
	
	/*
	 * Test using same positive values within a range.
	 * Both lower bound value and upper bound value are positive and same.
	 */
	
	@Test
	public void SameValPos() {
		assertEquals("Lower bound should be 1", 1, new Range(1, 1).getLowerBound(), .000000001d);  // same value (positives) bound test
	}
	
	/*
	 * Test using same negative values within a range.
	 * Both lower bound value and upper bound value are negative and same.
	 */
	
	@Test
	public void SameValNeg() {
		assertEquals("Lower bound should be 1", -1, new Range(-1, -1).getLowerBound(), .000000001d);  // same value (negatives) bound test
	}
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}