public class MaxPQ<K extends Comparable<K>> implements Iterable<K>{

	private K[] pq;     //heap-ordered complete tree
	private int N; //p[1...N] with pq[0] unused

	public MaxPQ(){
		this(1);
	}

	public MaxPQ(int initCapacity){
		pq = (K[]) new Object[initCapacity+1]; 
		N = 0;
	}

	public boolean isEmpty(){
		return N == 0;
	}

	public int size(){
		return N;
	}

	private boolean less(int i,int j){
		return pq[i].CompareTo(pq[j]);
	}

	private void exch(int i,int j){
		int t;
		t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}

	//bottom-up reheapify
	private void swim(int k){
		while(k > 1 && less(k/2,k)){
			exch(k/2,k);
			k = k/2;
		}
	}

	//Top-Down reheapify
	private void sink(int k){
		while(2*k <= N){
			int j = 2*k;
			if(j < N && less(j,j+1)){
				j++;
			}
			if(!less(k,j)){
				break;
			}
			exch(k,j);
			k = j;
		}
	}

	public void insert(K v){
		pq[++N] = v;
		swim(N);
	}

	public K delMax(){
		//pq[1] = null;
		K max = pq[1]; //retirve 
		exch(1,N--);
		pq[N+1] = null;
		sink(1);
		return max; 
	}

	public Iterator<K> iterator(){
		return new heapIterator();
	}

	private class heapIterator
} 