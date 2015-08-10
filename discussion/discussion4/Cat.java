
/*
 author: fantasy

 Given the following classes, fill in the definition of the Cat class so that when greet() is called,
the label "Cat" (instead of "Animal") is printed to the screen. Assume that a Cat will make a
"Meow!" noise, and that this is all caps for cats who are less than 5 years old.
*/

public class Cat extends Animal{

	public Cat(String name,int age){
		super(name,age);
		this.noise = "Meow";

	}

	@Override 
	public void greet(){
		System.out.println("Cat"+name+"says: "+ makeNoise());
	}

	public static void main(String[] args){
		
		Cat c = new Cat("fan",4);
		System.out.println(c.makeNoise());
		


	}

}