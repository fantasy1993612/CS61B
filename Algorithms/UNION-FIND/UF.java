public class UF{

	private int[] id;
	private int count;

	public UF(int N){
		id = new int[N];

		for(int i = 0 ;i < N; i++){
			id[i] = i;
		}

		count = N;
	} 

	public void union(int p,int q){
		int i = find(p);
		int j = find(q);

		if(p == q)
			return;

		for(int index = 0 ; index < id.length; index++){
			if(id[index] == i){
				id[index] = j;
			}
		}

		count--;
	}

	public int find(int p){
		return id[p];
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