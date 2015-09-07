import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class MyHashMapTests {

	@Test
	public void Ctor_DefaultCtor_DoesNotCrash() {
		new MyHashMap<Integer, Integer>();
	}
	
	@Test
	public void Ctor_SizedCtor_DoesNotCrash() {
		new MyHashMap<Integer, Integer>(10);
	}
	
	@Test
	public void Ctor_SizeLoadCtor_DoesNotCrash() {
		new MyHashMap<Integer, Integer>(10, 2.0f);
	}
	
	@Test
	public void Put_AddValue_SizeIncreases() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(10, 2.0f);
		
		//Act
		map.put(1, 1);
		
		//Assert
		assertEquals(1, map.size());
	}
	
	@Test
	public void Put_AddDuplicateValue_SizeStaysSame() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(10, 2.0f);
		
		//Act
		map.put(1, 1);
		map.put(1, 1);
		
		//Assert
		assertEquals(1, map.size());
	}
	
	@Test
	public void Put_TwoUniqueKeys_SizeIsTwo() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(10, 2.0f);
		
		//Act
		map.put(1, 1);
		map.put(2, 1);
		
		//Assert
		assertEquals(2, map.size());
	}
	
	@Test
	public void Put_ExceedsLoadFactor_ResizeSuccessful() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(1, 0.25f);
		
		//Act
		map.put(1, 1);
		map.put(2, 1);
		map.put(3, 1);
		map.put(4, 1);
		
		//Assert
		assertEquals(4, map.size());
	}
	
	@Test
	public void Get_NothingInMap_ReturnsNull() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(1, 1.0f);
		
		//Act
		Integer result = map.get(0);
		
		//Assert
		assertNull(result);
	}
	
	@Test
	public void Get_KeyNotInMap_ReturnsNull() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(1, 1.0f);
		map.put(1, 1);
		
		//Act
		Integer result = map.get(0);
		
		//Assert
		assertNull(result);
	}

	
	@Test
	public void Get_KeyInMap_ReturnsValue() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(1, 1.0f);
		map.put(1, 100);
		
		//Act
		int result = map.get(1);
		
		//Assert
		assertEquals(100, result);
	}
	
	@Test
	public void Get_KeyInMapWithOtherKeys_ReturnsValue() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(1, 1.0f);
		map.put(1, 100);
		map.put(2, 101);
		map.put(3, 102);
		map.put(4, 103);
		map.put(5, 104);
		map.put(6, 105);
		
		//Act
		int result1 = map.get(1);
		int result2 = map.get(6);
		
		//Assert
		assertEquals(100, result1);
		assertEquals(105, result2);
	}
	
	@Test
	public void RemoveK_KeyInMapWithOtherKeys_ReturnsValue() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(1, 1.0f);
		map.put(1, 100);
		map.put(2, 101);
		map.put(3, 102);
		map.put(4, 103);
		map.put(5, 104);
		map.put(6, 105);
		
		//Act
		int result1 = map.remove(1);
		int result2 = map.remove(6);
		
		//Assert
		assertEquals(100, result1);
		assertEquals(105, result2);
	}
	
	@Test
	public void RemoveK_KeyInMapWithOtherKeys_ReturnsCorrectSize() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(1, 1.0f);
		map.put(1, 100);
		map.put(2, 101);
		map.put(3, 102);
		map.put(4, 103);
		map.put(5, 104);
		map.put(6, 105);
		
		//Act
		map.remove(1);
		map.remove(6);
		
		//Assert
		assertEquals(4, map.size());
	}
	
	@Test
	public void RemoveKV_KeyInMapWithOtherKeys_ReturnsValue() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(1, 1.0f);
		map.put(1, 100);
		map.put(2, 101);
		map.put(3, 102);
		map.put(4, 103);
		map.put(5, 104);
		map.put(6, 105);
		
		//Act
		Integer result1 = map.remove(1, 100);
		Integer result2 = map.remove(6, 105);
		
		//Assert
		Integer expect1 = 100;
		Integer expect2 = 105;
		assertEquals(expect1, result1);
		assertEquals(expect2, result2);
	}
	
	@Test
	public void RemoveKV_KeyInMapWithOtherKeys_ReturnsCorrectSize() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(1, 1.0f);
		map.put(1, 100);
		map.put(2, 101);
		map.put(3, 102);
		map.put(4, 103);
		map.put(5, 104);
		map.put(6, 105);
		
		//Act
		map.remove(1, 100);
		map.remove(6, 105);
		
		//Assert
		assertEquals(4, map.size());
	}
	
	@Test
	public void RemoveKV_KeyInMapValueDiff_ReturnsNull() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(1, 1.0f);
		map.put(1, 100);
		map.put(2, 101);
		map.put(3, 102);
		map.put(4, 103);
		map.put(5, 104);
		map.put(6, 105);
		
		//Act
		Integer result1 = map.remove(1, 100);
		Integer result2 = map.remove(6, 999);
		
		//Assert
		Integer expect1 = 100;
		assertEquals(expect1, result1);
		assertEquals(5, map.size());
		assertNull(result2);	
	}
	
	@Test
	public void KeySet_KeysInMap_ReturnsKeys() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(1, 1.0f);
		map.put(1, 100);
		map.put(2, 101);
		map.put(3, 102);
		map.put(4, 103);
		map.put(5, 104);
		map.put(6, 105);
		
		//Act
		Set<Integer> result = map.keySet();
		
		//Assert
		assertEquals("[1, 2, 3, 4, 5, 6]", result.toString());	
	}
	//Interesting, but not a well formed unit test
	@Test
	public void ToString_RandomData_ReturnsMapStructuredString() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(1, 4.0f);
		
		for(int i = 0; i < 100; i += 1){
			map.put((int)(Math.random() * 1000), i);
			if(i % 20 == 0)
				System.out.print(map.toString() + "\n");
		}
		
	}
}