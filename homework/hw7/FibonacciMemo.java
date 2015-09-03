import java.util.HashMap; // Import Java's HashMap so we can use it
import java.math.BigInteger;
import java.math.BigDecimal;

public class FibonacciMemo {

    private static HashMap<Integer,BigInteger> fibonacci = new HashMap<Integer,BigInteger>();
    /**
     * The classic recursive implementation with no memoization. Don't care
     * about graceful error catching, we're only interested in performance.
     * O(a^n)
     * @param n
     * @return The nth fibonacci number
     */
    public static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibNoMemo(n - 2) + fibNoMemo(n - 1);
    }

    /**
     * Your optimized recursive implementation with memoization. 
     * You may assume that n is non-negative.
     * 
     * @param n
     * @return The nth fibonacci number
     */
    public static BigInteger fibRecursive(int n) {
       
        if(n <= 1){
            return BigInteger.valueOf(n);
        }

        if(fibonacci.containsKey(n)){
            return fibonacci.get(n);
        }

        BigInteger answer = fibRecursive(n-1).add(fibRecursive(n-2));
        fibonacci.put(n,answer);
      
        return answer;
       
    }

    /**
     * Your optimized NonRecursive implementation . 
     * You may assume that n is non-negative.
     * O(N)
     * @param n
     * @return The nth fibonacci number
     */
    public static BigInteger fibNonRecursive(int n){
        BigInteger result = BigInteger.valueOf(1);
        BigInteger p_result = BigInteger.valueOf(1);//prev result
        BigInteger pp_result = BigInteger.valueOf(0);//prev prev result

        for(;n > 2;n--){

            pp_result = p_result;
            p_result = result;
    
            result = p_result.add(pp_result); 
        }

        return result;
    }

    public static double fibTimeRecursive(int N){
            Stopwatch s = new Stopwatch();
            System.out.println("The Recursive verson");
            System.out.println(N+" : "+fibRecursive(N));
            return s.elapsedTime();
    }

    public static double fibTimeNonRecursive(int N){
            Stopwatch s = new Stopwatch();
            System.out.println("The NonRecursive verson");
            System.out.println(N+" : "+fibNonRecursive(N));
            return s.elapsedTime();
    }

    public static void main(String[] args) {
         int fib = Integer.parseInt(args[0]);
         System.out.println("Cost Time"+" : "+FibonacciMemo.fibTimeRecursive(fib));
         System.out.println("Cost Time"+" : "+FibonacciMemo.fibTimeNonRecursive(fib));
        // 46th Fibonacci = 1,836,311,903
        // 47th Fibonacci = 2,971,215,073
    }
}
