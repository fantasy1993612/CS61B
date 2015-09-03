import java.util.AbstractList;
// We will use two private instance variables: an array that
// will store the elements of the list and an integer that will 
// store the current number of elements in the list.

public class ArrayList61B<E> extends AbstractList<E>{

	private E[] items;
	private int size;

	public ArrayList61B(){
		items = (E[]) new Object[1];
		size = 0;
	}

	
	public ArrayList61B(int initialCapacity){
		if(initialCapacity < 1){
			throw new  IllegalArgumentException("initialCapacity less than 1");
		}

		items = (E[])new Object[initialCapacity];
		size = 0;

	}

	private void resize(int c) {
        E[] newItems = (E[])new Object[c];
        for (int i = 0; i < items.length; i += 1) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

	@SuppressWarnings("unchecked")
	@Override
	public E get(int i){
		if(i<0){
			throw new IllegalArgumentException("i is less than 0");
		}

		if(i >= items.length){
			throw new IllegalArgumentException("out of bound");
		}

		return items[i];
	}


	public boolean add(E item){
		if(size == items.length){
			resize(size*2);
		}

		items[size] = item;
		size = size + 1;

		return true;
	}

	@Override
	public int size(){
		return size;
	}

}