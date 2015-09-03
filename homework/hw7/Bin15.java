import java.lang.Math;
// Don't forget to answer the follow-up question!
public class Bin15 {

    // A string of exactly 15 characters, each a 0 or 1.
    private String myBinStr;

    // A constantly-whining constructor for your testing purposes.
    public Bin15(String input) {

        // Check for null input
        if (input == null) {
            String msg = "Your binary string is null";
            throw new NullPointerException(msg);
        }

        // Check for length
        if (input.length() != 15) {
            String msg = "Your binary string isn't of length 15";
            throw new IllegalArgumentException(msg);
        }

        // Check for illegal characters
        for (int count = 0; count < 15; count++) {
            char c = input.charAt(count);
            // Careful with comparing vs 0 and comparing vs '0'
            if (c != '0' && c != '1') {
                String msg = "Your binary string contains a non-binary character";
                throw new IllegalArgumentException(msg);
            }
        }

        // The input is good. Let's roll.
        this.myBinStr = input;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Bin15) {
             Bin15 other = (Bin15) o;
             return this.hashCode() == other.hashCode(); 
        }

        return false;
           // YOUR CODE HERE
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        for(int i = 0; i < 15; i++){
            hash = hash<<1;
            if(myBinStr.charAt(i)== '1')
                hash += 1;
        }
        return hash; // YOUR CODE HERE
    }

    /* DO THIS LAST, AFTER IMPLEMENTING EVERYTHING
    Follow-up question: The current length of our myBinStr is 15. What is the
    longest length possible for this String such that we still can produce a
    perfect hash (assuming we can rewrite the hash function)? Write your answer
    in the method followUpAnswer(). 
    */
    public static final int followUpAnswer() {
        return 42; // YOUR CODE HERE. THIS MAY OR MAY NOT BE CORRECT.
    }
    
    public static void main(String[] args) {
        // Optional testing here. Potentially useless information:
        Bin15 test1 = new Bin15("101100101001101");
        Bin15 test2 = new Bin15("111111111111111");
        Bin15 test3 = new Bin15("000000000000000");
        Bin15 test4 = new Bin15("101100101001101");
        Bin15 test5 = new Bin15("101010101010111");
        
        System.out.println(test1.hashCode());
        System.out.println(test2.hashCode());
        System.out.println(test3.hashCode());
        System.out.println(test4.hashCode());
        System.out.println(test5.hashCode());
        
        System.out.println(test1.equals(test1));
        System.out.println(test1.equals(test4));
        System.out.println(test1.equals(test2));
        System.out.println(test1.equals(test3));
        System.out.println(test1.equals(test5));
        System.out.println(test2.equals(test3));
        System.out.println(test3.equals(test3));
        
    }
}

