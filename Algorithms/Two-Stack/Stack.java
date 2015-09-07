import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item>{

	private Item[] a;
	private int N;

	public Stack(){
		a = (Item[]) new Object[2];
	}

	private void resize(int max){
		Item[] temp = (Item[]) new Object[max];

		for(int i = 0 ; i < N;i++){
			temp[i] = a[i];
		}

		a = temp;
	}

	public void push(Item item){
		if(N == a.length){
			resize(2*a.length);
		}

		a[N++] = item;
	}

	public Item pop(){
		if(isEmpty()){
			throw new NoSuchElementException("stack underflow");
		}
		Item item =  a[--N];
		a[N] = null;//avoid loitering
		if(N > 0 && N == a.length/4){
			resize(a.length/2);
		}
		return item;
	}

	boolean isEmpty(){
		return N == 0;
	}

	public int size(){
		return N;
	}

	public Iterator<Item> iterator(){
		return new ReverseArray();
	}

	private class ReverseArray implements Iterator<Item>{
		private int i = N;

		public boolean hasNext(){
			return i > 0;
		} 

		public Item next(){
			if (!hasNext()){
			 	throw new NoSuchElementException();
			}
			return a[--i];
		}

		public void remove(){
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args){
		Stack<String> s = new Stack<String>();
		while(!StdIn.isEmpty()){
			String item = StdIn.readString();
			if(!item.equals("-")){
				s.push(item);
			}else if(!s.isEmpty()){
				StdOut.print(s.pop()+" ");
			}
		}
		StdOut.println("(" + s.size() + " left on stack)");
	}
}