package discussion1;

public class MyDog {
	public static void main(String[] args){
		int size = 27;
		String name = "Fido";
		Dog mydog = new Dog(size,name);
		int x = size-15;
		
		if (x < 15){
			mydog.bark(8);
		}
		
		int[] numList = {2,4,6,8};
		System.out.println("Hello");
		System.out.println("Dog"+name);
		
		System.out.println(numList[1]);
		
		if (numList[3] == 8){
			System.out.println("potato");
		}
	}
}
