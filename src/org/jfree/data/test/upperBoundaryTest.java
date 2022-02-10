package org.jfree.data.test;
import org.jfree.data.Range;
import static org.junit.Assert.*;

import org.junit.*;

public class upperBoundaryTest {

	@Test
	public void Positives() {
		assertEquals("Upper bound should be 1", 1, new Range(0, 1).getUpperBound(), .000000001d);   //Two positive test
	}
	
	@Test
	public void Negatives() {
		assertEquals("Upper bound should be -1", -1, new Range(-2, -1).getUpperBound(), .000000001d);    //Two negative test
	}
	
	@Test
	public void Both() {
		assertEquals("Upper bound should be 1", 1, new Range(-5, 1).getUpperBound(), .000000001d);  //Pos-Neg test
	}
	
	@Test
	public void SameValPos() {
		assertEquals("Upper bound should be 1", 1, new Range(1, 1).getUpperBound(), .000000001d);  // same value (positives) bound test
	}
	
	@Test
	public void SameValNeg() {
		assertEquals("Lower bound should be 1", -1, new Range(-1, -1).getLowerBound(), .000000001d);  // same value (negatives) bound test
	}
}
