import java.util.Arrays;

public class exR1{


	//1.1.16
	public static String ex(int n){
		if(n <= 0)
			return "";
		return ex(n-3) + n + ex(n-2) + n;
	}

	//1.1.17
	public static String exR2(int n){
		String  s = exR2(n-3) + n + exR2(n-2) + n;
		if(n <= 0)
			return "";
		return s;
	}

	//1.1.18
	public static int mystery(int a,int b){
		if(b == 0){
			return 0;
		}

		if(b%2 == 0){
			return mystery(a+a,b/2);
		}

		return mystery(a+a,b/2)+a;
	}

	//1.1.19
	public static long F(int N){
		if(N == 0){
			return 0;
		}

		if(N == 1){
			return 1;
		}

		return F(N-1) + F(N-2);
	}

	//1.1.20
	public static long I(int N){

		if(N == 1){
			return 1;
		}

		return N*I(N-1);
	}

	//1.1.21
	public static int rank(int key,int[] a){
		return rank(key,a,0,a.length-1,1);
	}

	public static int rank(int key,int[] a,int lo,int hi,int height){
		if(lo > hi){
			return -1;
		}

		int mid = lo + (hi-lo)/2;
		if(a[mid] > key){
			System.out.println(lo+" "+hi+" "+height);
			return rank(key,a,lo,mid-1,height+1);
		}else if(a[mid] < key){
			System.out.println(lo+" "+hi+" "+height);
			return rank(key,a,mid+1,hi,height+1);
		}else{
			System.out.println(lo+" "+hi+" "+height);
			return mid;
		}
	}

	//1.124 Euclid
	public static int gcd(int p,int q){
		if(q == 0){
			return p;
		}

		int r = p%q;

		return gcd(q,r);
	} 

	//1.1.27 Binomial distribution
	public static double binomial(int  N,int k,d){
		if((N==0) || (k < 0))
			return 1.0;
		return (1.0-p)*binomial(N-1,k)+p*binomial(N-1,k-1);
	} 

	public static void main(String[] args){
		
		//System.out.println(Math.log(I(8)));
		/*int[] a = new int[10000];
		for(int i = 0 ; i < 10000 ; i++){
			a[i] = i+1;
		}

		rank(456,a);
		*/

		//System.out.println(gcd(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
		System.out.println(binomial(100,50,0.2));
	}
}