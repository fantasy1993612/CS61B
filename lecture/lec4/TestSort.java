package lec4;
import static org.junit.Assert.*;
import org.junit.Test;
public class TestSort {
	/*Tests the Sort.sort*/
	@Test
	public void testSort(){
		String[] input = {"a","huge","tiny","scorpion"};
		Sort.sort(input);
		String[] expected = {"a","huge","tiny","scorpion"};
		assertArrayEquals(expected,input);
		
	}
	
	@Test
	public void testIndexOfSmallest(){
		String[] input = {"a","huge","tiny","scorpion"};
		//int actual = Sort.indexOfSmallest(input, 0);
		int expected =0;
		//assertEquals(expected, actual);
	}
	public static void main(String[] args){
		//testSort();
		//testIndexOfSmallest();
	}
}
//terrible responter
/**Test to see if the input is same as expected
if not print someting useful to progammer
if(input.length != expected.length){
	System.out.println("length does not match");
	System.out.println("input length"+input.length);
	System.out.println("expected length"+expected.length);
}
*/
