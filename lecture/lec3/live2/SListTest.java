import org.junit.Test;
import static org.junit.Assert.*;

public class  SListTest{


    @Test
    public void testGetting(){
    	SList L = new SList();
    	L.insertBack(100);
    	L.insertFront(99);

    	assertEquals(100,L.getBack());
    	assertEquals(99,L.getFront());
    }

	@Test
	public void testEmptyList(){
		SList l = new SList();
		assertEquals(0,l.size());
	}
	@Test
	public void testSize(){
		SList s = new SList(100);
		s.insertBack(99);

		assertEquals(2,s.size());
	}
	
	public static void main(String[] args){
		jh61b.junit.textui.runClasses(SListTest.class);
	}
}