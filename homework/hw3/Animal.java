
class Animal {
    String name;
    public Animal() {
        name = "SuperAnimal";
        System.out.println(name);
    }
    public void speak() {
        System.out.println("I'm an animal");
    }

    public static void main(String args[]){
        //Animal a0 = new Animal();   // Line 1
        //Fox f0 = new Fox();         // Line 2
        //a0.speak();                 // Line 3
        //f0.speak();                 // Line 4
        //((Animal) f0).speak();      // Line 5
        //((Fox) a0).speak();         // Line 6

        //Q2
        //Animal a1 = new Fox();
        //a1.speak();
        //((Animal) a1).speak();
        //Fox f1 = new Animal();

        //Q3
        Animal a2 = new Animal();
        System.out.println(a2.name);
        Animal a3 = new Fox("SuperFox");
        System.out.println(a3.name);
        System.out.println(((Animal) a3).name);
    }
}

class Fox extends Animal{
    String name;
    public Fox() {
    }
    public Fox(String s) {
        name = s;
        System.out.println(name);
    }
    public void speak() {
        System.out.println("Ringding");
    }
}