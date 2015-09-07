public class WeightedQuickUionUF{

	private int[] id;
	private int[] sz;
	private int count;

	public WeightedQuickUionUF(int N){

		id = new int[N];
		sz = new int[N];

		for(int i = 0; i < N; i++){
			id[i] = i;
			sz[i] = 1;
		}
	}

	private void validate(int p) {
        int N = id.length;
        if (p < 0 || p >= N) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + N);
        }
    }

	public int find(int p){
	   validate(p);
	   while(id[p] != p){
	   	p = id[p];
	   }

	   return p;
	}

	public void union(int p,int q){
		int i = find(p);
		int j = find(q);

		if(i == j){
			return;
		}

		if(sz[i] < sz[j]){
			id[i] = j;
			sz[j] += sz[i];
		}else{
			id[j] = i;
			sz[i] += sz[j]; 
		}

		count--;
	}

	public boolean connected(int p,int q){
		return find(p) == find(q);
	}

	public int count(){
		return count;
	}

	public static void main(String[] args){
		int N = StdIn.readInt();
		UF uf = new UF(N);

		while(!StdIn.isEmpty()){
			int p = StdIn.readInt();
			int q = StdIn.readInt();

			if(uf.connected(p,q)){
				continue;
			}

			uf.union(p,q);
		}

		System.out.println(uf.count()+" component");
	}
}