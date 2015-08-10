import org.junit.Test;
import static org.junit.Assert.*;

public class OldSchoolVesselTest{
	
	@Test
	public void testVessel(){
		OldSchoolVessel osv = new OldSchoolVessel();
		int x = 5;
		osv.put(x);//autoboxes into an integer,integer is-an object
		//assertEquals(5,osv.peek());//no on boxing
		int y = (Integer)osv.peek();

	}

	 public static void main(String[] args) {
        jh61b.junit.textui.runClasses(OldSchoolVesselTest.class);
    }
}