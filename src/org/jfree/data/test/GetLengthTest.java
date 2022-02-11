/*
 * Name: Dagvadorj (Tom) Altankhuyag
 * Course/Lab : SENG 438 Lab 2
 */

package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GetLengthTest {

	private Range testRange;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
}


@Before
public void setUp() throws Exception {}

@Test
public void lengthBetweenNegatuveOneAndOne() {
	testRange = new Range(-1,1);
	assertEquals("value should be 2", 2, testRange.getLength(),0);
}
@Test
public void lengthBetweenZeroAndZero() {
	testRange = new Range(0,0);
	assertEquals("value should be 0", 0, testRange.getLength(),0);
}

@Test
public void lengthBetweenZeroAndBigNumber() {
	testRange = new Range(0,300);
	assertEquals("value should be 300", 300, testRange.getLength(),0);
}
@Test
public void lengthBetweenNonIntegers() {
	testRange = new Range(-1.9,20.5);
	assertEquals("value should be 22.4",22.4,testRange.getLength(),0);
}




@After
public void tearDown() throws Exception{
	
}

@AfterClass
public static void tearDownAfterClass() throws Exception{
	
}

}
