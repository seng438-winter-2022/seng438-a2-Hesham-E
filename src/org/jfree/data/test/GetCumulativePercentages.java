/*
 * Name: Dagvadorj (Tom) Altankhuyag
 * Course/Lab : SENG 438 Lab 2
 */
package org.jfree.data.test;

import org.jfree.data.DataUtilities;

import java.security.InvalidParameterException;
import static org.junit.Assert.*;
import org.jfree.data.*;
import org.jmock.*;
import org.junit.*;
public class GetCumulativePercentages  {
	private	Mockery mockingContext; 
	private KeyedValues values ;
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	
	public void setUp() throws Exception{
		mockingContext = new Mockery();
		values = mockingContext.mock(KeyedValues.class);
	} 
	
	
	
	@Test
	public void firstValueShouldBe0_25() {
		
		mockingContext.checking(new Expectations(){
			{
				allowing(values).getItemCount();
				will(returnValue(4));
				
				allowing(values).getValue(0);
				will(returnValue(1));
				allowing(values).getValue(1);
				will(returnValue(1));
				allowing(values).getValue(2);
				will(returnValue(1));
				allowing(values).getValue(3);
				will(returnValue(1));
				
				
			
				allowing(values).getKey(0);
				will(returnValue(0));
				allowing(values).getKey(1);
				will(returnValue(1));
				allowing(values).getKey(2);
				will(returnValue(2));
				allowing(values).getKey(3);
				will(returnValue(3));
		
				
			}
			
		});
		double result = DataUtilities.getCumulativePercentages(values).getValue(0).doubleValue();
		//pain.
		int check = values.getValue(1).intValue();
		assertEquals("Check" , 0.25, result,0);
	}
	@Test
	public void lastValueShouldBe1() {
		
		mockingContext.checking(new Expectations(){
			{
				allowing(values).getItemCount();
				will(returnValue(4));
				
				allowing(values).getValue(0);
				will(returnValue(1));
				allowing(values).getValue(1);
				will(returnValue(1));
				allowing(values).getValue(2);
				will(returnValue(1));
				allowing(values).getValue(3);
				will(returnValue(1));
				
				
			
				allowing(values).getKey(0);
				will(returnValue(0));
				allowing(values).getKey(1);
				will(returnValue(1));
				allowing(values).getKey(2);
				will(returnValue(2));
				allowing(values).getKey(3);
				will(returnValue(3));
		
				
			}
			
		});
		double result = DataUtilities.getCumulativePercentages(values).getValue(3).doubleValue();
		
		int check = values.getValue(1).intValue();
		assertEquals("Check" , 1, result,0);
	}
	
	@Test(expected = IllegalStateException.class)
	public void nullArgument() {
		mockingContext.checking(new Expectations() {
			{
				allowing(values);
				will(returnValue(null));
			}	
			
				
				
		});
		
		 DataUtilities.getCumulativePercentages(values);
	}
	@Test
	public void negativeValueTest() {
		mockingContext.checking(new Expectations()
				{
			{
				allowing(values).getItemCount();
				will(returnValue(5));
				
				allowing(values).getValue(0);
				will(returnValue(-1));
				allowing(values).getValue(1);
				will(returnValue(1));
				allowing(values).getValue(2);
				will(returnValue(1));
				allowing(values).getValue(3);
				will(returnValue(1));
				allowing(values).getValue(4);
				will(returnValue(1));
				
				
			
				allowing(values).getKey(0);
				will(returnValue(0));
				allowing(values).getKey(1);
				will(returnValue(1));
				allowing(values).getKey(2);
				will(returnValue(2));
				allowing(values).getKey(3);
				will(returnValue(3));
				allowing(values).getKey(4);
				will(returnValue(4));
		
			}
				}
				);
	double result = DataUtilities.getCumulativePercentages(values).getValue(1).doubleValue();
		assertEquals("Cumulative percentage at index 1 should be 0", 0, result,0);
	}
	@Test
	public void allZeroesTest() {
		mockingContext.checking(new Expectations() {
			{
				allowing(values).getItemCount();
				will(returnValue(3));
				
				allowing(values).getValue(0);
				will(returnValue(0));
				allowing(values).getValue(1);
				will(returnValue(0));
				allowing(values).getValue(2);
				will(returnValue(0));
				
				allowing(values).getKey(0);
				will(returnValue(0));
				allowing(values).getKey(1);
				will(returnValue(1));
				allowing(values).getKey(2);
				will(returnValue(2));
				
			}
		});
		
		double result = DataUtilities.getCumulativePercentages(values).getValue(1).doubleValue();
	assertEquals("The cumulative percentage at index 1 should be undefined(NaN)", Double.longBitsToDouble(0x7ff8000000000000L),result, 0);
	}
	
	
	@After
	public void tearDown() throws Exception{
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		
	}
}
