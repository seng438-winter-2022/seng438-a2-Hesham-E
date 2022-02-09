package org.jfree.data.test;
import org.jfree.data.Range;

import org.junit.*;

public class LowerBoundaryTest{
	
	private Range range;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		range = new Range(1, 10);
	}
	
	@Test
	public void test() {
		Assert.assertEquals("Lower values are not equal", 1, this.range.getLowerBound(),.000000001d);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}