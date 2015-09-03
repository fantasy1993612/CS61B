public class MaxArrayList<T extends Comparable<T>> extends ArrayList61B<E> {

	T currentMax;

	public MaxArrayList61B() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public MaxArrayList61B(int initialCapacity){
		super(initialCapacity);
	}
	

	@Override
	public boolean add(T item){
		if(currentMax != null && item.compareTo(T)<=0 || item == null){
			return false;
		}

		currentMax = item;
		return super.add(item);
	}
}