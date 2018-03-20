package edu.utdallas.main;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class SumTest {
	
	private Sum sum;
	
	public String convert(int[] array) {
		return Arrays.toString(array);
	}
	
	@Before
	public void setup() {
		sum = new Sum();
	}

	@Test
	public void test() {
		assertEquals(convert(new int[] {0, 2}), convert(sum.twoSum(new int[] {2, 5, 9, 17, 20}, 11)));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testIllegalArgumentException() {
		sum.twoSum(new int[] {2, 5, 9, 17, 20}, 1);
	}
}
