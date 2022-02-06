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
	
	@Test
	public void calculateColumnIndexOutofRange()
	{
		mocking.checking(new Expectations()
		{
			{
				one(values).getRowCount();
				will(returnValue(1));
				one(values).getValue(0, 1);
				will(returnValue(0));
			}
		});
		
		double result = DataUtilities.calculateColumnTotal(values, 1);
		
		assertEquals("The total value of column 1 should be 0 because it does not exist", 0.0, result, .000000001d); 
	}
	
	@Test
	public void calculateColumnIndexNegative()
	{
		mocking.checking(new Expectations()
		{
			{
				one(values).getRowCount();
				will(returnValue(1)); 
				one(values).getValue(0, -1); 
				will(returnValue(0));
			}
		});
		
		double result = DataUtilities.calculateColumnTotal(values, -1);
		
		assertEquals("The total value of column -1 should be 0 because it does not exist", 0.0, result, .000000001d); 
	}
	
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

