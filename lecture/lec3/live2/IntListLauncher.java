public class IntListLauncher{

	public static void main(String[] args){
		IntList x = new IntList(100,null);
		x.tail = new IntList(5,null);

		//x : 100 -> 5

		IntList y = x;

		//how do we add front of x

		x = new IntList(99,x);

		// x: 99 -> 100 -> 3
	   // y: 100 -> 5

		System.out.println(x.size());
 	}
}