package discussion1;

public class Dog {
	private int size;
	private String name;
	
	public Dog(int size,String name){
		this.name = name;
		this.size = size;
	}
	
	public void bark(int count){
		for (int i = 0 ; i < count ; i++){
			System.out.println("woooooo!");
		}
	}
}
