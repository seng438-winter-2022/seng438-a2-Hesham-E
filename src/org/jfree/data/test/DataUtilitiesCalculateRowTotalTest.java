package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataUtilitiesCalculateRowTotalTest extends DataUtilities {

	private Mockery mocking;
	private Values2D values;
	
	@BeforeClass
	public static void setUpBeforeClass() 
	throws Exception 
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
				one(values).getColumnCount();
				will(returnValue(2)); 
				one(values).getValue(0, 0); 
				will(returnValue(10)); 
				one(values).getValue(0, 1); 
				will(returnValue(13));
			}
		});
		
		double result = DataUtilities.calculateRowTotal(values, 0);
		
		assertEquals("The total value of row 0 should be 23.0", 23.0 , result, .000000001d); 
	}
	
	@Test
	public void calculateRowIndexOutofRange()
	{
		mocking.checking(new Expectations()
		{
			{
				one(values).getColumnCount();
				will(returnValue(1));
				one(values).getValue(1, 0); 
				will(returnValue(0)); 
			}
		});
		
		double result = DataUtilities.calculateRowTotal(values, 1);
		
		assertEquals("The total value of row 1 should be 0 because it does not exist", 0.0, result, .000000001d); 
	}
	
	@Test
	public void calculateRowIndexNegative()
	{
		mocking.checking(new Expectations()
		{
			{
				one(values).getColumnCount();
				will(returnValue(1)); 
				one(values).getValue(-1, 0); 
				will(returnValue(0)); 
			}
		});
		
		double result = DataUtilities.calculateRowTotal(values, -1);
		
		assertEquals("The total value of row -1 should be 0 because it does not exist", 0.0, result, .000000001d); 
	}
	
	@Test
	public void tableHasZeroRows()
	{
		mocking.checking(new Expectations()
		{
			{
				one(values).getColumnCount();
				will(returnValue(0)); 
			}
		});
		
		double result = DataUtilities.calculateRowTotal(values, 0);
		
		assertEquals("The total value of should be 0 because this table has no rows", 0.0, result, .000000001d); 
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void dataObjectInvalid()
	{
		DataUtilities.calculateRowTotal(null, 0);
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
