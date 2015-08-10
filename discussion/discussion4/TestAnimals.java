public class TestAnimals{
public static void main(){
	Animal a = new Animal("Pluto", 10);
	Cat c = new Cat("Garfield", 6);
	Dog d = new Dog("Fido", 4);
	a.greet();
	c.greet();
	d.greet(); 
	a = c;
	a.greet();
	((Cat) a).greet();
}