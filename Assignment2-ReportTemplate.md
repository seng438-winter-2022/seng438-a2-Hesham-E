**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group \#:  12    |     |
| -----------------| --- |
| Tom Altankhuyag  |     |
| Hasan Mahtab     |     |
| Asma Hashmi      |     |
| Hesham Elkaliouby|     |

# 1 Introduction

The objective of this assignment is to continue to build the foundation of testing knowledge that was provided in assignment one. However, in this situation, there is a strong emphasis on black box testing; specifically, unit testing using the Junit framework. This framework was used inconjunction with a modified version of JFreeChart provided for the specific use of this lab.

Before heading into this lab, our group had some preliminary knowledge about JUnit testing from a previous class. Additionally, we expanded upon the concepts we learned from this previous class, ENSF 409, during the SENG 438 lectures. At the end of this lab, we gained some experience with set up and troubleshooting before getting started on the tasks. Furthermore, we got to apply equivlance testing on top of boundary value testing that we were familiar with before.

# 2 Detailed description of unit test strategy

For the ten methods we have chosen to develop our equivalence classes first. In this way we are able to isolate boundary cases quickly as well. Each method tested below begins by testing the unique equivalence classes first. This is to verify that each equivalence class is in fact unique and that we have not missed any boundary cases from our initial development of the equivalence classes. Finally, we will test the planned boundary cases as well as any unplanned boundary cases that we come across. These boundary values were added as further classes, if they were discovered.

# 3 Test cases developed

Text…

// write down the name of the test methods and classes. Organize the based on
the source code method // they test. identify which tests cover which partitions
you have explained in the test strategy section //above

# org.jfree.data.DataUtilities:
#### static double calculateColumnTotal(Values2D data, int column)
Returns the length of the range
* Ranges of values:
    * Values2D data has (0)(1-infinity) rows OR data is (null)
    * int column index is (inside data’s indices)(outside data’s indices)(negative)
* Number of equivalence classes:
    * Values2D data has 3 equivalence classes which are (null)(data with 0 rows)(data with 1-infinity rows)
    * int column has 3 equivalence classes which are index is (inside data’s indices)(outside data’s indices)(negative)
* Strong vs Weak equivalence classes:
    * In this case the equivalence classes are weak since the inputs are mutually exclusive because they can be any value and only the result will change. For example, there is no boundary case between the two inputs that only appears when both have a specific value.
    * Given this explanation, there will be six (3 + 3) tests developed for this function. However, since there is some overlap between the test cases we have only created 5 test cases. This is because, testing (inside data’s indices) and (data with 1-infinity rows) are tested in the exact same way. Furthermore, there is no boundary value that may cause an aberrant value that is not already covered by the equivalnce classes.
~~~java
//Test Case #1:
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
~~~
* Test case #1 accounts for both the equivalence class (1-infinity) from Values2D data and (inside data's indices) from int column. The mocking object sets the expectation for calculateColumnTotal() that there are 2 rows available. In this test, there is no clear downside to using mocking since calculateColumnTotal() expects a certain number of values which it is given. In the end, this test passes.

~~~java
//Test Case #2:
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
~~~
* Test case #2 accounts for the equivalence class (outside data's indices) from int column. The documentation specifies that when column is out of range that an IndexOutofBoundsException will be thrown by getValue(). The use of mocking is disadvantagous here, however, since these the expectations defeat the purpose of testing the repsonse of calculateColumnTotal() because all testing is based on the expectations. In the end, this test passes.

~~~java
//Test Case #3:
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
~~~
* Test case #3 tests equivalence (negative) from int column. A mocking object is set up with the incorrect index to observe if calculateColumnTotal() does accurate error checking on the index value. A mocking object is extremely useful here since a negative index does not mean the last entry in the table, but rather the only entry. In the documentation, a negative index should also result in an IndexOutOfBoundsException. In the end, this test also passes.

~~~java
//Test Case #4:
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
~~~
* This Test Case #4 tests the expected value of when a table is empty. This is the equivalnce class (0) from Values2D data. According to the documentation this should return 0.0. The use of a mocking object is useful here to ensure that the constructor of Values2D is not interferring with our testing. In the end, this test passes.

~~~java
//Test Case #5:
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
~~~
* Test Case #5 corresponds to the final equivalnce class (null) from Values 2D. Unlike the previous two tests when a table is empty, if the object is just null then calculateColumnTotal() throws an exception. Mocking is absolutely unnecessary in this case since null is passed rather than Values2D data. This test passes.


#### static double calculateRowTotal(Values2D data, int row)
Returns the length of the range
* Ranges of values:
    * Values2D data has (0)(1-infinity) columns or is (null)
    * int row index is (inside data’s indices), (outside data’s indices), or (negative)
* Number of equivalence classes:
    * Values2D data has 3 equivalence classes which are (null)(data with 0 columns)(data with 1-infinity columns)
    * int column has 3 equivalence classes which are index is (inside data’s indices)(outside data’s indices)(negative)
* Strong vs Weak equivalence classes:
    * In this case the equivalence classes are weak since the inputs are mutually exclusive because they can be any value and only the result will change. For example, there is no boundary case between the two inputs that only appears when both have a specific value.
    * Given this explanation, there will be six (3 + 3) tests developed for this function. However, since there is some overlap between the test cases we have only created 5 test cases. This is because, testing (inside data’s indices) and (data with 1-infinity columns) are tested with the exact same method.
* Supplementary Notes:
    * The way this function was tested is almost identical to calculateColumnTotal() since their expected functionality is the same except for the fact that one is for rows and the other is for columns. Thus, the equivalance classes are identical.

~~~java
//Test Case #1:
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
~~~
* Test case #1 accounts for both the equivalence class (1-infinity) from Values2D data and (inside data's indices) from int row. The mocking object sets the expectation for calculateRowTotal() that there are 2 rows available. In this test, there is no clear downside to using mocking since calculateRowTotal() expects a certain number of values which it is given. In the end, this test passes.

~~~java
//Test Case #2:
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
	
~~~
* Test case #2 accounts for the equivalence class (outside data's indices) from int row. The documentation specifies that when row is out of range that an IndexOutofBoundsException will be thrown by getValue(). The use of mocking is disadvantagous here, however, since these the expectations defeat the purpose of testing the repsonse of calculateRowTotal() because all testing is based on the expectations. In the end, this test passes.

~~~java
//Test Case #3:
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
	
~~~
* Test case #3 tests equivalence (negative) from int row. A mocking object is set up with the incorrect index to observe if calculateRowTotal() does accurate error checking on the index value. A mocking object is extremely useful here since a negative index does not mean the last entry in the table, but rather the only entry. In the documentation, a negative index should also result in an IndexOutOfBoundsException. In the end, this test also passes.

~~~java
//Test Case #4:
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
~~~
* This Test Case #4 tests the expected value of when a table is empty. This is the equivalnce class (0) from Values2D data. According to the documentation this should return 0.0. The use of a mocking object is useful here to ensure that the constructor of Values2D is not interferring with our testing. In the end, this test passes.

~~~java
//Test Case #5:
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
~~~
* Test Case #5 corresponds to the final equivalnce class (null) from Values 2D. Unlike the previous two tests when a table is empty, if the object is just null then calculateRowTotal() throws an exception. Mocking is absolutely unnecessary in this case since null is passed rather than Values2D data. This test passes.



#### static java.lang.Number[] createNumberArray(double[] data)
Constructs an array of Number objects from an array of double 

* Ranges of values:
    * data could have (0)(1-infinity) length
* Number of equivalence classes:
    * There are two equivalence classes here. One when data is empty and the other when it is not.
* Strong vs Weak equivalence classes:
    * In this case the equivalence classes are weak since they are not related to each other. Thus, there are 1 + 1 test cases based on the equivalence classes. One more test case can be added to this though to ensure that the return type is correct. However, since data is of type double[] boundary value cases cannot be developed based on type conversions since double fits all integers.

~~~java
// Test Case #1:
/**
 * This class is testing
 * equivalence class "data could
 * have (1-infinity) length"
 */
@Test
public void compareValidArray() 
{
	double[] testData = {1, 2, 3};
	Double[] expected = {1.0, 2.0, 3.0};
	Number[] result = DataUtilities.createNumberArray(testData);
	
	Assert.assertArrayEquals("The returned array should be {1, 2, 3}", expected, result); 
}
~~~
* In this test case, the equivalnce class of (1-infinity) length was tested. The use of mocking was not necessary in this case since the input and return values are standard Java types. In the end, this test passes since the value of the numbers do not change but the type does.

~~~java
// Test Case #2:
/**
 * This class is testing
 * equivalence class "data could
 * have (0) length"
 */
@Test
public void compareEmptyArray() 
{
	double[] testData = {};
	Double[] expected = {};
	Number[] result = DataUtilities.createNumberArray(testData);
	
	Assert.assertArrayEquals("The returned array should be {}", expected, result); 
}
~~~
* For test case #2, the equivalnce class of (0) length is being tested. Again, there is no need for a mocking object since these are all standard Java variable types. This test also passes since the returned array is also empty.

~~~java
// Test Case #3:
/**
 * This class is based on an additional
 * test case where it ensures the return
 * type is correct.
 */
@Test
public void testReturnType()
{
	double[] testData = {};
		
	Assert.assertTrue("Return type should be Number[]", DataUtilities.createNumberArray(testData) instanceof Number[]);  
}
~~~
* For test case #3, this was a precaution taken and not a specific equivalnce class. Testing the return type meant testing if the polymorphism worked to create a Number object from a primitive type double. In this test as well, there is no need for mocking. Finally, this test passes since the correct return type was detected.

#### static Number[][] createNumberArray2D(double[][] data)
Constructs an array of Number objects from an array of double 

* Ranges of values:
    * data could have (0)(1-infinity) length
* Number of equivalence classes:
    * There are two equivalence classes here. One when data is empty and the other when it is not.
* Strong vs Weak equivalence classes:
    * In this case the equivalence classes are weak since they are not related to each other. Thus, there are 1 + 1 test cases based on the equivalence classes. One more test case can be added to this though to ensure that the return type is correct. However, since data is of type double[] boundary value cases cannot be developed based on type conversions since double fits all integers.

~~~Java
// Test Case #1:

 public void NullTest() {
    try {
		c = DataUtilities.createNumberArray2D(null);
		fail("null cannot be passed as a parameter");
	}
	catch(Exception e) {
		assertEquals("Null does not throw " + "an InvalidParameterException",InvalidParameterException.class,e.getClass());
	}
~~~
For Test Case #1, illegal test is implemented. Null was inputted to ensure an expected exception was caught.

~~~Java
 // Test Case #2:
 
public void createNumberArray2DWithValid2DArrayOfDoublesTest() {
	c = DataUtilities.createNumberArray2D(b);
	assertArrayEquals("The number 2D array doesn't have the same values"+ " as the 2D array of doubles", d, c);
}

~~~
For Test Case #2, value testing is implemented. If the array is not a double, it should return saying that the array does not have the same values as the 2D array of doubles.

#### static KeyedValues getCumulativePercentages(KeyedValues data)
Returns the length of the range
* Ranges of values:
    * KeyedValues data: (non-null KeyedValues object)
* Number of equivalence classes:
	* KeyedValues has 1 equivalence class which is a KeyedValues object that is not a null
* Strong vs Weak equivalence classes:
    * Since the method takes one argument, we can approach it with a strong equivalence class. Since we only have one equivalent class, we can extensively make sure the bounds are correct and the numeric types within the KeyedValues are tested correctly. The minimum tests should be atleast one, however, we will be doing exploratory tests extensively to make sure the method functions properly
* Supplementary Notes:
    * Hefty Java Doc pre-reading was required to properly test the method.

~~~java
//Test Case #1:
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
~~~
* Test case #1, Makes sure the getCumulativePercentages(KeyedValues data) is working properly with the given correct input. To simulate and properly test the method, mocking was used to replicate the behaviour of KeyedValues data as input. In this test case, the KeyedValues object contains four values that are all ones. With the proper input and uniform values, simple median and boundary values can be derived from the getCumulativePercentages(KeyedValues data) method. The expected value from this test case is 0.25, in which case the test passes.
~~~Java
//Test case #2;
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
		assertEquals("Check" , 1.00, result,0);
	}
~~~
* Test case #2, Makes sure the getCumulativePercentages(KeyedValues data) is working properly with the given correct input. To simulate and properly test the method, mocking was used to replicate the behaviour of KeyedValues data as input. In this test case, the KeyedValues object contains four values that are all ones. With the proper input and uniform values, simple median and boundary values can be derived from the getCumulativePercentages(KeyedValues data) method. The expected last boundary value from this test case is 1.00, in which case the test passes.

~~~Java
//Test Case #3
public void secondtValueShouldBe0_50() {
		
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
		double result = DataUtilities.getCumulativePercentages(values).getValue(1).doubleValue();
		//pain.
		
		assertEquals("Check" , 0.50, result,0);
	}
~~~
* Test case #3, Makes sure the getCumulativePercentages(KeyedValues data) is working properly with the given correct input. To simulate and properly test the method, mocking was used to replicate the behaviour of KeyedValues data as input. In this test case, the KeyedValues object contains four values that are all ones. With the proper input and uniform values, simple median and boundary values can be derived from the getCumulativePercentages(KeyedValues data) method. The expected median value from this test case is 0.50, in which case the test passes.

~~~Java
//Test Case #4
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
~~~
* Test Case #4, Null value was inputted to ensure an expected exception was caught. The getCumulativePercentages(KeyedValues data) does not permit null values as input. To simulate and properly test the method, mocking was used to replicate the behaviour of KeyedValues data as input. In this test case, the object it self is mocked to act as a null KeyedValues object. The test case expects an Illegal State exception, in which case the test passes.
~~~Java

//Test Case #5
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
		assertEquals("Cumulative percentage at index 1 should be 0", 0.00, result,0);
	}
~~~
* Test case #5, Makes sure the getCumulativePercentages(KeyedValues data) is working properly with the given correct input. To simulate and properly test the method, mocking was used to replicate the behaviour of KeyedValues data as input. In this test case, the KeyedValues object contains five values, first value being a negative one and the rest of the four being ones. With the proper input and uniform values (magnitude wise). This exploratory test ensures that a negative value is accomdated properly within the getCumulativePercentages(KeyedValues data) method. The test case expects a 0.00 in the second value of the returned KeyedValues object, in which case the test passes.

~~~Java
//Test Case #6
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
	assertEquals("The cumulative percentage at index 1 should be undefined(NaN)", 0,result, 0);
	}
~~~
* Test case #6, Makes sure the getCumulativePercentages(KeyedValues data) method is working properly with an input that contains all zeroes. To simulate and properly test the method, mocking was used to replicate the behaviour of KeyedValues data as input. In this test case, the KeyedValues object contains three values, all consisting of zeroes. In this context, zeroes should be expected for all the values in the returned KeyedValues object, however, the test case fails. The values inside the returned KeyedValues object are all "NaN", instead of zeroes.


#### double getCentralValue()
Returns the length of the range
* Range of values:
	* There are no arguments for this method, within Range class there is double Upper and double Lower
	* Upper: a double value that has to be >=Lower
	* Lower: a double value that has to be <= Upper
* Number of equivalence classes:
	* One equivalent class, since there are no arguments and null is not possible. The tests will be will be used with the Upper and Lower value inside Range.
* Strong vs Weak equivalence classes:
	* Since there are very limited inputs, strong equivalence class test can be done without as much work. 

~~~Java
//Test Case #1
@Test
public void valueShouldBe2(){
	//Test Case #1
	testRange = new Range(1,3);
	assertEquals("The value between 1 and 3 should be 2", 2.00, testRange.getCentralValue(), 0);
	
}
~~~
* Test Case #1, ensuring the method works properly with correct data. Inside the Range object we setup Upper to be 3 and Lower to be 1. Working with limited equivalent classes and no null or invalid Upper and Lower values permitted through the construction, getCentralValue() can be tested with exploratory and boundary cases.  No mocking is necessary for this test. Since the two values inside the testRange object are valid (Upper double being 3 and Lower double being 1), the expected value is 2.00, in which case the test passes.

~~~Java
//Test Case #2
@Test
public void lengthBetweenZeroAndZero() {
	//Test Case #2
	testRange = new Range(0,0);
	assertEquals("value should be 0", 0, testRange.getLength(),0);
}
~~~
* Test Case #2, ensuring the method works properly with various data. Inside the Range object we setup Upper to be 0 and Lower to be 0. Working with limited equivalent classes and no null or invalid Upper and Lower values permitted through the construction, getCentralValue() can be tested with exploratory and boundary cases.  No mocking is necessary for this test. Since the two values inside the testRange object are valid (Upper double being 0 and Lower double being 0), the expected value is 0.00, in which case the test passes.


~~~Java
@Test
public void lengthBetweenZeroAndBigNumber() {
	//Test Case #3
	testRange = new Range(0,300);
	assertEquals("value should be 300", 300, testRange.getLength(),0);
}
~~~
* Test Case #3, ensuring the method works properly with correct data. Inside the Range object we setup Upper to be 300 and Lower to be 0. Working with limited equivalent classes and no null or invalid Upper and Lower values permitted through the construction, getCentralValue() can be tested with exploratory and boundary cases.  No mocking is necessary for this test. This is a part of exploratory testing, ensuring a zero value does not affect the calculation. Since the two values inside the testRange object are valid (Upper double being 300 and Lower double being 0), the expected value is 300.00, in which case the test passes.


~~~Java
//Test Case #4
@Test
public void lengthBetweenNonIntegers() {
	testRange = new Range(-1.9,20.5);
	assertEquals("value should be 22.4",22.4,testRange.getLength(),0);
}
~~~
* Test Case #4, ensuring the method works properly with correct data. Inside the Range object we setup Upper to be 20.5 and Lower to be -1.9. Working with limited equivalent classes and no null or invalid Upper and Lower values permitted through the construction, getCentralValue() can be tested with exploratory and boundary cases.  No mocking is necessary for this test. This is a part of exploratory testing, ensuring negative values does not affect the calculation. Since the two values inside the testRange object are valid (Upper double being 20.5 and Lower double being -1.9), the expected value is 22.4, in which case the test passes.



#### double getLength()
Returns the length of the range
* Range of values:
	* There are no arguments for this method, within Range class there is double Upper and double Lower
	* Upper: a double value that has to be >=Lower
	* Lower: a double value that has to be <= Upper
* Number of equivalence classes:
	* One equivalent class, since there are no arguments and null is not possible. The tests will be will be used with the Upper and Lower value inside Range.
* Strong vs Weak equivalence classes:
	* Since there are very limited inputs, strong equivalence class test can be done without as much work. 
* Supplementary Notes:
	* The same ideology of method testing can be derived from the previous method getCentralValue()

~~~Java
//Test Case #1
@Test
public void lengthBetweenNegatuveOneAndOne() {
	testRange = new Range(-1,1);
	assertEquals("value should be 2", 2, testRange.getLength(),0);
}
~~~
* 

~~~Java
//Test Case #2
@Test
public void lengthBetweenZeroAndZero() {
	testRange = new Range(0,0);
	assertEquals("value should be 0", 0, testRange.getLength(),0);
}
~~~
* 

~~~Java
//Test Case #3

@Test
public void lengthBetweenZeroAndBigNumber() {
	testRange = new Range(0,300);
	assertEquals("value should be 300", 300, testRange.getLength(),0);
}
~~~
* 
~~~Java
//Test Case #4

@Test
public void lengthBetweenNonIntegers() {
	testRange = new Range(-1.9,20.5);
	assertEquals("value should be 22.4",22.4,testRange.getLength(),0);
}
~~~
* 
~~~Java
//Test Case #5
@Test
public void lengthBetweenIntegerAndNonInteger() {
	testRange = new Range(0,69.420);
	assertEquals("value should be 69.420",69.420,testRange.getLength(),0);
}
~~~

#### int hashCode()
Returns a hash code

* This function will return a hash code value for the object. The general idea is, the hashCode method must return the same integer. If two objects are equals, then calling the hashCode method on each of the two objects will return the same integer as a result.
* If the two objects are not equal, then calling the hashCode method on each of the two objects will produce distinct integer results. 
* Value Testing is used for this function. The first test was to check for two similar test ranges, and the second was two different ranges.

~~~Java
//Test Case #1
 public void TwoSimiliarTest() {
       a= new Range(0,22);
       b= new Range(0,22);
    	assertEquals("The HashCode for the two Ranges are not the same", b.hashCode(), a.hashCode());
    }
~~~
* Test Case #1 shows what will happen when two ranges are the same. This implements value testing.
~~~Java
//Test Case #2
public void TwoDifferentTest(){
    a= new Range(1,42);
    b= new Range(22,55);
    assertFalse("The HashCode for the two different Ranges are the same", a.hashCode()==b.hashCode());
    }
~~~
* Test Case #2 shows what will happen when two ranges are different. This implements value testing.

#### String toString()
Returns a string representation of this range

* This function, toString, returns a string. 
* This function will return a string “Range[lower,upper]” where lower is the lower ranger and upper is the upper range
toStringTest: Create a range object {3.0, 11.0} and call toString(). The expected output is telling the user that {3.0,11.0} is not equal.
* Value Checking was used for this function.

~~~Java
//Test Case #1
 public void toStringTest() {
    	assertEquals("The strings are not equal", "Range[3.0,11.0]" , this.exampleRange.toString());
    }
~~~

# 4 How the team work/effort was divided and managed

To divide the work, the team initally met to discuss if we wanted to work in pairs similar to the previous lab. In the end, we decided against this and went with a individual approach. To be more specific, since there were 10 methods that needed testing, the team decided and assigned a 3/3/2/2 split of methods to test. Furthermore, the people testing three methods were given functions that were thought to be simpler at first glance.

Assembling our work was also an agreed upon ordeal. Since we decided to use equivalnce class testing, we came up with a format for each report as seen above.

# 5 Difficulties encountered, challenges overcome, and lessons learned

There quite a few difficulties encountered when setting up the Eclipse environment for this lab. Our group encountered various errors. However, we were able to solve this by helping each other or reaching out to our TAs.

In terms of lessons learned, mocking is not always a best practice. This is because when testing the kinds of exceptions thrown, or boundary values, the tester must introduce the expectations based on the java documentation. This means that the test will almost certainly pass because the setup and end result are set by the tester themselves. Instead, a tester should use the object directly without mocking to observe behavior within the tested method. However, this assumes that the object that is no longer being mocked performs exactly as expected and any errors that arise are from the tested method. 

# 6 Comments/feedback on the lab itself

Text…
