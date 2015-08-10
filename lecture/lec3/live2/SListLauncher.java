/**
 * Demonstrates the power and elegance of the SList
 */
public class SListLauncher{
	public static void main(String[] args){
		SList x = new SList(100);
		x.insertBack(5);
		System.out.println(x.front.next.item);

		//x : 100 -> 5
		 SList y = x;
		x.insertFront(99);
		System.out.println(x.front.item);

		// x: 99 -> 100 -> 3
	   // y: 100 -> 5

		//x = new SList();
		//System.out.println(x.size());
 	}

}