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

public class GetCentralValueTest  {
	private Range testRange;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
}


@Before
public void setUp() throws Exception {
}

@Test
public void valueShouldBe2(){
	testRange = new Range(1,3);
	assertEquals("The value between 1 and 3 should be 2", 2, testRange.getCentralValue(), 0);
	
}

@Test
public void valueShouldBe1_5(){
	testRange = new Range(1,2);
	assertEquals("The value between 1 and 2 should be 1.5", 1.5, testRange.getCentralValue(), 0);
	
}
@Test
public void valueShouldBe1(){
	testRange = new Range(1,1);
	assertEquals("The value between 1 and 1 should be 1", 1, testRange.getCentralValue(), 0);
	
}

@Test
public void valueShouldBe35_9(){
	testRange = new Range(21.3,50.5);
	assertEquals("The value between 21.3 and 50.5 should be 35.9", 35.9, testRange.getCentralValue(), 0);
}

@Test public void valueShouldBe0() {
	testRange = new Range(0,0);
	assertEquals("The value between 0 and 0 should be 0", 0, testRange.getCentralValue(), 0);
}
@After
public void tearDown() throws Exception{
	
}

@AfterClass
public static void tearDownAfterClass() throws Exception{
	
}
}
