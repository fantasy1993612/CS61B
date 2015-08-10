import org.junit.Test;
import static org.junit.Assert.*;

public class VesselTest{
	
	@Test
	public void testVessel(){
		Vessel<Integer> osv = new Vessel<Integer>();
		int x = 5;
		osv.put(x);//autoboxes into an integer,integer is-an object
		//assertEquals(5,osv.peek());//no on boxing
		assertEquals((Integer)5,osv.peek());

	}

	 public static void main(String[] args) {
        jh61b.junit.textui.runClasses(VesselTest.class);
    }
}