package org.jfree.data.test;
import org.jfree.data.Range;
import static org.junit.Assert.*;

import org.junit.*;

public class LowerBoundaryTest {

	@Test
	public void Positives() {
		assertEquals("Lower bound should be 1", 1, new Range(1, 2).getLowerBound(), .000000001d);   //Two positive test
	}
	
	@Test
	public void Negatives() {
		assertEquals("Lower bound should be -2", -2, new Range(-2, -1).getLowerBound(), .000000001d);    //Two negative test
	}
	
	@Test
	public void Both() {
		assertEquals("Lower bound should be -5", -5, new Range(-5, 1).getLowerBound(), .000000001d);  //Pos-Neg test
	}
	
	@Test
	public void SameValPos() {
		assertEquals("Lower bound should be 1", 1, new Range(1, 1).getLowerBound(), .000000001d);  // same value (positives) bound test
	}
	
	@Test
	public void SameValNeg() {
		assertEquals("Lower bound should be 1", -1, new Range(-1, -1).getLowerBound(), .000000001d);  // same value (negatives) bound test
	}
}