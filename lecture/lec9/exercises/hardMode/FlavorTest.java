public class FlavorTest{

	public static void main(String[] args) {
		/*When an overridden method is invoked, the actual method that
		executes is based on dynamic type, not static type.*/
		MaxSList msl = new MaxSList(100);
		SList msl2 = msl;//msl2 static type of dog dynmic type msl2 is MaxSList caus msl2 points to msl
		System.out.println(msl.flavor);
		System.out.println(msl2.flavor);
		msl.printFlavor();
		msl2.printFlavor();
		//msl.printSuperFlavor();
	}
}