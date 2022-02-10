package org.jfree.data.test;
import org.jfree.data.Range;
import static org.junit.Assert.*;

import org.junit.*;

public class upperBoundaryTest {
	
	/*
	 * Test using two positive values within a range.
	 * Both lower bound value and upper bound value are positive.
	 */

	@Test
	public void Positives() {
		assertEquals("Upper bound should be 1", 1, new Range(0, 1).getUpperBound(), .000000001d);   //Two positive test
	}
	
	/*
	 * Test using two negative values within a range.
	 * Both lower bound value and upper bound value are negative.
	 */
	
	@Test
	public void Negatives() {
		assertEquals("Upper bound should be -1", -1, new Range(-2, -1).getUpperBound(), .000000001d);    //Two negative test
	}
	
	/*
	 * Test using a positive and a negative value within a range.
	 * A negative lower bound and a positive upper bound taken for test.
	 */
	
	@Test
	public void Both() {
		assertEquals("Upper bound should be 1", 1, new Range(-5, 1).getUpperBound(), .000000001d);  //Pos-Neg test
	}
	

	/*
	 * Test using same positive values within a range.
	 * Both lower bound value and upper bound value are positive and same.
	 */
	
	@Test
	public void SameValPos() {
		assertEquals("Upper bound should be 1", 1, new Range(1, 1).getUpperBound(), .000000001d);  // same value (positives) bound test
	}
	
	/*
	 * Test using same negative values within a range.
	 * Both lower bound value and upper bound value are negative and same.
	 */
	
	@Test
	public void SameValNeg() {
		assertEquals("Upper bound should be 1", -1, new Range(-1, -1).getUpperBound(), .000000001d);  // same value (negatives) bound test
	}
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
