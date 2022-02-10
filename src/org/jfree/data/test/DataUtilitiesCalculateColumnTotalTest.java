package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.*;
import org.jmock.*;
import org.junit.*;

public class DataUtilitiesCalculateColumnTotalTest 
{ 
	private Mockery mocking;
	private Values2D values;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{ 
		
	} 
 
 
	@Before 
	public void setUp() throws Exception 
	{
		mocking = new Mockery();
		values = mocking.mock(Values2D.class);
	} 
	 
	/** 
	 * This test is testing both equivalence classes
	 * Values2D data: (1-infinity) rows
	 * int column: (inside data’s indices)
	 * The reason these are both in the same test
	 * is because they are testing in the exact same manner
	 */
	@Test 
	public void calculateTwoValues() 
	{
		mocking.checking(new Expectations()
		{
			{
				one(values).getRowCount();
				will(returnValue(2)); 
				one(values).getValue(0, 0); 
				will(returnValue(10)); 
				one(values).getValue(1, 0); 
				will(returnValue(13));
			}
		});
		
		double result = DataUtilities.calculateColumnTotal(values, 0);
		
		assertEquals("The total value of column 0 should be 23.0", 23.0 , result, .000000001d); 
	}
	
	/** 
	 * This test is testing equivalence class
	 * int column: (outside data’s indices)
	 * 
	 * The test setups up a table of one column
	 * through mocking. getValue(0, 1) will
	 * return throw an exception 
	 * based on the java documentation.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void calculateColumnIndexOutofRange()
	{
		mocking.checking(new Expectations()
		{
			{
				one(values).getRowCount();
				will(returnValue(1));
				one(values).getValue(0, 1);
				will(throwException(new IndexOutOfBoundsException("Index out of range"))); 
			}
		});
		
		DataUtilities.calculateColumnTotal(values, 1);
	}
	
	
	/** 
	 * This test is testing equivalence class
	 * int column: (negative)
	 * 
	 * The test setups up a table of one column
	 * through mocking. getValue(0, -1) will
	 * return 0 based on the java documentation.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void calculateColumnIndexNegative()
	{
		mocking.checking(new Expectations()
		{
			{
				one(values).getRowCount();
				will(returnValue(1)); 
				one(values).getValue(0, -1); 
				will(throwException(new IndexOutOfBoundsException("Index out of range")));  
			}
		});
		
		DataUtilities.calculateColumnTotal(values, -1);
	}
	
	
	/** 
	 * This test is testing equivalence class
	 * Values2D data: (0) rows
	 * 
	 * The test setups up an empty table.
	 */
	@Test
	public void tableHasZeroRows()
	{
		mocking.checking(new Expectations()
		{
			{
				one(values).getRowCount();
				will(returnValue(0)); 
			}
		});
		
		double result = DataUtilities.calculateColumnTotal(values, 0);
		
		assertEquals("The total value of should be 0 because this table has no rows", 0.0, result, .000000001d); 
	}
	
	/** 
	 * This test is testing equivalence class
	 * Values2D data: (null)
	 * 
	 * The test has no setup and just passes null
	 * value rather than a Values2D object.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void dataObjectInvalid()
	{	
		DataUtilities.calculateColumnTotal(null, 0);
	}
	
	@After 
	public void tearDown() throws Exception 
	{ 
	
	}
	
	@AfterClass 
	public static void tearDownAfterClass() throws Exception 
	{ 
	
	} 
 } 

