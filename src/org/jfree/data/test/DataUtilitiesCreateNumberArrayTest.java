package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class DataUtilitiesCreateNumberArrayTest extends DataUtilities {

	public static void setUpBeforeClass() 
	throws Exception 
	{ 
		
	} 
 
 
	@Before 
	public void setUp() throws Exception 
	{

	} 
			
	@Test
	public void compareValidArray() 
	{
		double[] testData = {1, 2, 3};
		Double[] expected = {1.0, 2.0, 3.0};
		Number[] result = DataUtilities.createNumberArray(testData);
		
		Assert.assertArrayEquals("The returned array should be {1, 2, 3}", expected, result); 
	}
	
	@Test
	public void compareEmptyArray() 
	{
		double[] testData = {};
		Double[] expected = {};
		Number[] result = DataUtilities.createNumberArray(testData);
		
		Assert.assertArrayEquals("The returned array should be {}", expected, result); 
	}
	
	@Test
	public void testReturnType()
	{
		double[] testData = {};
		Number[] result = DataUtilities.createNumberArray(testData);
		
		Assert.assertTrue("Return type should be Number[]", result instanceof Number[]); 
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
