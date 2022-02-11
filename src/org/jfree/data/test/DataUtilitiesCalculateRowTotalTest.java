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
	 
	
	/** 
	 * This test is testing both equivalence classes
	 * Values2D data: (1-infinity) columns
	 * int row: (inside data’s indices)
	 * The reason these are both in the same test
	 * is because they are testing in the exact same manner
	 */
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
	
	/** 
	 * This test is testing equivalence class
	 * int row: (outside data’s indices)
	 * 
	 * The test setups up a table of one column
	 * through mocking. getValue(1, 0) will
	 * return throw an exception 
	 * based on the java documentation.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void calculateRowIndexOutofRange()
	{
		mocking.checking(new Expectations()
		{
			{
				one(values).getColumnCount();
				will(returnValue(1));
				one(values).getValue(1, 0); 
				will(throwException(new IndexOutOfBoundsException("Index out of range"))); 
			}
		});
		
		DataUtilities.calculateRowTotal(values, 1);
	}
	
	/** 
	 * This test is testing equivalence class
	 * int row: (negative)
	 * 
	 * The test setups up a table of one column
	 * through mocking. getValue(-1, 0) will
	 * return 0 based on the java documentation.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void calculateRowIndexNegative()
	{
		mocking.checking(new Expectations()
		{
			{
				one(values).getColumnCount();
				will(returnValue(1)); 
				one(values).getValue(-1, 0); 
				will(throwException(new IndexOutOfBoundsException("Index out of range")));  
			}
		});
		
		DataUtilities.calculateRowTotal(values, -1);
	}
	
	/** 
	 * This test is testing equivalence class
	 * Values2D data: (0) columns
	 * 
	 * The test setups up an empty table.
	 */
	@Test
	public void tableHasZeroColumns()
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
