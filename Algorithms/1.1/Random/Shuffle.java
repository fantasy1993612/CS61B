
class Shuffle {

	public static void shuffleCheck(int M,int N){
		double[] a = new double[M];
		for(int i = 0; i < M; i++){
			a[i] = i;
		}
		for(int i = 0 ; i < N;i++){

			StdRandom.shuffle(a);

			for(int j = 0; j < M;j++){
				System.out.print(a[j]+" ");
				if(M % (j+1) == 0)
					System.out.println("");
			
			}
		}
	}

	public static void main(String[] args){
		shuffleCheck(5,4);
	}
}