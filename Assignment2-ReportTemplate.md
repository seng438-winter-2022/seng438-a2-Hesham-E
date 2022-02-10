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

org.jfree.data.DataUtilities:
static double calculateColumnTotal(Values2D data, int column)
Returns the sum of the values in one column of the supplied data table. With invalid input, a total of zero will be returned.

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
~~~
* Test case #2 accounts for the equivalence class (outside data's indices) from int column. The documentation specifies that when column is out of range that 0 will be returned by calculateColumnTotal(). Thus, a mocking object is set up with a table of 1 row to ensure that 0 is not returned because there is no table to begin with. The use of mocking is not necessary here, however, since these the expectations set up are not used. So a normal Values2D object could have been used with just one column. In the end, this test passes.

~~~java
//Test Case #3:
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
~~~
* Test case #3 tests equivalence (negative) from int column. A mocking object is set up with the incorrect index to observe if calculateColumnTotal() does accurate error checking on the index value. A mocking object is extremely useful here since a negative index does not mean the last entry in the table, but rather the only entry. In the documentation, a negative index should also return zero like the previous test case because the column technically does not exist if this was a real object. In the end, this test passes.

~~~java
//Test Case #4:
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
//Test Case #5
@Test(expected = IllegalArgumentException.class)
public void dataObjectInvalid()
{	
	DataUtilities.calculateColumnTotal(null, 0);
}
~~~
* Test Case #5 corresponds to the final equivalnce class (null) from Values 2D. Unlike the previous two tests when a table is empty, if the object is just null then calculateColumnTotal() throws an exception. Mocking is absolutely unnecessary in this case since null is passed rather than Values2D data. This test passes.


static double calculateRowTotal(Values2D data, int row)
Returns the sum of the values in one row of the supplied data table.

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
~~~
* Test case #2 accounts for the equivalence class (outside data's indices) from int row. The documentation specifies that when row is out of range that 0 will be returned by calculateRowTotal(). Thus, a mocking object is set up with a table of 1 row to ensure that 0 is not returned because there is no table to begin with. The use of mocking is not necessary here, however, since these the expectations set up are not used. So a normal Values2D object could have been used with just one row. In the end, this test passes.

~~~java
//Test Case #3:
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
~~~
* Test case #3 tests equivalence (negative) from int row. A mocking object is set up with the incorrect index to observe if calculateRowTotal() does accurate error checking on the index value. A mocking object is extremely useful here since a negative index does not mean the last entry in the table, but rather the only entry. In the documentation, a negative index should also return zero like the previous test case because the row technically does not exist if this was a real object. In the end, this test passes.

~~~java
//Test Case #4:
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
~~~
* This Test Case #4 tests the expected value of when a table is empty. This is the equivalnce class (0) from Values2D data. According to the documentation this should return 0.0. The use of a mocking object is useful here to ensure that the constructor of Values2D is not interferring with our testing. In the end, this test passes.

~~~java
//Test Case #5:
@Test(expected = IllegalArgumentException.class)
public void dataObjectInvalid()
{
	DataUtilities.calculateRowTotal(null, 0);
}
~~~
* Test Case #5 corresponds to the final equivalnce class (null) from Values 2D. Unlike the previous two tests when a table is empty, if the object is just null then calculateRowTotal() throws an exception. Mocking is absolutely unnecessary in this case since null is passed rather than Values2D data. This test passes.



static java.lang.Number[] createNumberArray(double[] data)
Constructs an array of Number objects from an array of double primitives.

    Ranges of values:
      data could have (0)(1-infinity) length
    Number of equivalence classes:
      There are two equivalence classes here. One when data is empty and the other when it is not.
    Strong vs Weak equivalence classes:
      In this case the equivalence classes are weak since they are not related to each other. Thus, there are 1 + 1 test cases based on the equivalence classes.
    Supplementary Notes:




# 4 How the team work/effort was divided and managed

To divide the work, the team initally met to discuss if we wanted to work in pairs similar to the previous lab. In the end, we decided against this and went with a individual approach. To be more specific, since there were 10 methods that needed testing, the team decided and assigned a 3/3/2/2 split of methods to test. Furthermore, the people testing three methods were given functions that were thought to be simpler at first glance.

Assembling our work was also an agreed upon ordeal. Since we decided to use equivalnce class testing, we came up with a format for each report as seen above.

# 5 Difficulties encountered, challenges overcome, and lessons learned

Text…

# 6 Comments/feedback on the lab itself

Text…
