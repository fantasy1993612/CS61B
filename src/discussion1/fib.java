package discussion1;

public class fib {
	/** fib(N) returns the Nth Fibonacci number, for N >= 0.
	* The Fibonacci sequence is 0, 1, 1, 2, 3, 5, 8, 13, 21, ... */
	public static int fib(int N){
		if (N == 0)
			return 0;
		if (N == 1)
			return 1;
		return fib(N-1)+fib(N-2);
	}
	/*expert */
	public static int fib2(int n, int k, int f0, int f1){
		
		return 1; 
	}
	public static void main(String[] args){
		int N = fib(7);
		System.out.println(N);
	}
}
