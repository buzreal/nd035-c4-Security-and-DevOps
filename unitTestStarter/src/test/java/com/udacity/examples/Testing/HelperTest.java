package src.test.java.com.udacity.examples.Testing;

import org.junit.Test;
import src.main.java.com.udacity.examples.Testing.Helper;

import java.util.Arrays;
import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.List;

import static org.junit.Assert.*;

public class HelperTest {

    @Test
	public void test(){
        assertNotEquals(4, 3);
    }

    @Test
    public void getCount(){
        List<String> empNames = Arrays.asList("sareeta","John" );
        final long actual = Helper.getCount(empNames);
        assertEquals(2,actual);

    }

    @Test
    public void getStats(){
        List<Integer> yrsOfExperience = Arrays.asList(13,4,15,6,17,8,19,1,2,3);
        List<Integer> expectedList = Arrays.asList(13,4,15,6,17,8,19,1,2,3);
        IntSummaryStatistics stats = Helper.getStats(yrsOfExperience);
        assertEquals(19, stats.getMax());
        assertEquals(expectedList, yrsOfExperience);
    }

    @Test
    public void compare_arrays(){
        int[] yrs = {10, 14, 2};
        int[] expectedYrs = {10, 14, 2};
        //assertEquals(expectedYrs, yrs); assertEquals does not compare the array value, instead it compares the object.
        // That's why the test fails because the 2 arrays are different objects, even though they have same values.
        assertArrayEquals(expectedYrs, yrs); //compares if the values are equal and pass the test or otherwise.
    }

    @Test
    public void mergedList(){
        List<String> empNames = Arrays.asList("sareeta", "", "john","");
        String mergedList = Helper.getMergedList(empNames);
        assertEquals("sareeta, john", mergedList);
    }

    @Test
    public void getSquareList(){
        List<Integer> empLevel = Arrays.asList(3,3,3,5,7,2,2,5,7,5);
        List<Integer> expectedList = Arrays.asList(9, 25, 49, 4);
        List<Integer> squareList = Helper.getSquareList(empLevel);
        assertEquals(expectedList, squareList);
    }
}
