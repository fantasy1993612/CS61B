
public class Random{

	//random double between [a,b)
	public static double uniform(double a,double b){
		return a+StdRandom.random()*(a-b);
	}

	//integer between [0,N)
	public static int uniform(int N){
		retrun (int)(StdRandom.random()*N);
	}

	public static int uniform(int lo,int hi){
		return lo + StdRandom.uniform(hi-lo);
	}

	//a[1] + a[2] + ...+a[i] =1
	//return the i where a[i] = r
	//return the probability of the i
	public static int discrete(double[] a){
		double r = StdRandom.random();
		double sum = 0.0;
		for(int i = 0,i < a.length;i++){
			sum = sum + a[i];
			if(sum >=r)
				return i;
		}

		return -1;
	}

	//random shuffle the elements in an array of double 
	public static void shuffle(double[] a){
		int N = a.length;
		for(int i = 0 ; i < N ; i++){
			int r = i + StdRandom.uniform(N-i);
			double temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}
