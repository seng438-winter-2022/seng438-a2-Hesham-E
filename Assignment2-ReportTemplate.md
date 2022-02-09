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

    Ranges of values:
      Values2D data:
      data has (0)(1-infinity) rows
      data is (null)
      int column:
      column index is (inside data’s indices)(outside data’s indices)(negative)
    Number of equivalence classes:
      Values2D data:
      Has 3 equivalence classes which are (null)(data with 0 rows)(data with 1-infinity rows)
      int column:
      Has 3 equivalence classes which are index is (inside data’s indices)(outside data’s indices)(negative)
    Strong vs Weak equivalence classes:
      In this case the equivalence classes are weak since the inputs are mutually exclusive because they can be any value and only the result will change. For example, there is no boundary case between the two inputs that only appears when both have a specific value.
      Given this explanation, there will be six (3 + 3) tests developed for this function. However, since there is some overlap between the test cases we have only created 5 test cases. This is because, testing (inside data’s indices) and (data with 1-infinity rows) are tested with the exact same method.
    Supplementary Notes:



static double calculateRowTotal(Values2D data, int row)
          Returns the sum of the values in one row of the supplied data table.

    Ranges of values:
      Values2D data:
      data has (0)(1-infinity) columns
      data is (null)
      int row:
      row index is (inside data’s indices)(outside data’s indices)(negative)
    Number of equivalence classes:
      Values2D data:
      Has 3 equivalence classes which are (null)(data with 0 columns)(data with 1-infinity columns)
      int column:
      Has 3 equivalence classes which are index is (inside data’s indices)(outside data’s indices)(negative)
    Strong vs Weak equivalence classes:
      In this case the equivalence classes are weak since the inputs are mutually exclusive because they can be any value and only the result will change. For example, there is no boundary case between the two inputs that only appears when both have a specific value.
      Given this explanation, there will be six (3 + 3) tests developed for this function. However, since there is some overlap between the test cases we have only created 5 test cases. This is because, testing (inside data’s indices) and (data with 1-infinity columns) are tested with the exact same method.
    Supplementary Notes:



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
