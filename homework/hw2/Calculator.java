import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
     public EquationList e;
     public EquationList g;
     public Boolean flag = true;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        // YOUR CODE HERE
        int sum = 0;
        while(y != 0){
            sum = x^y;
            y = (x&y)<<1;
            x = sum;
        }
        return sum;
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) {
        // YOUR CODE HERE
        
        int sum = 0;
        if(y < 0){
            y = y * (-1);
        
            while(y != 0){
                if((y&1) != 0){
                    sum = add(sum,x);
                }
                x = x << 1;
                y = y >> 1;
            }
            return -1*sum;
        }else {

            while(y != 0){
                if((y&1) != 0){
                    sum = add(sum,x);
                }
                x = x << 1;
                y = y >> 1;
            }
            return sum;
        }
        
    }

    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and 
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for 
     * details.
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     **/
    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
       
       EquationList k = null;
       EquationList m  = new EquationList(equation,result,k);
    
       if (flag) {
           g = m;
           flag=false;
           e = m;
       }

       e.next = m;

       if (flag == false){
         e = m;
       }

    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        // YOUR CODE HERE
        int i = 0;
        EquationList m = g;
        EquationList k = g;
        while(k != null){
            k = k.next;
            i++;
        }

        String[] s = new String[i];
       
        
        for (int j = 0;j < i; j++){
            s[j] = m.equation + " = " + m.result;
            m = m.next;
        }

        for (int j = i-1; j >= 0;j--){
            System.out.println(s[j]);
        }

    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        // YOUR CODE HERE
        EquationList k = g;
        EquationList m = g;
        int i = 0;

         while(k != null){
            k = k.next;
            i++;
        }

        int t = i - n;
        String[] s = new String[n];
        int j = 0;

        while(j < t){
            m = m.next;
            j++;
        }

        for (j = 0;j < n; j++){
            s[j] = m.equation + " = " + m.result;
            m = m.next;
        }

        for (j = n - 1; j >= 0;j--){
            System.out.println(s[j]);
        }


    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        EquationList k = g;
        EquationList m = g;

        m = m.next;
        while(m.next != null){
            k = k.next;
            m = m.next;
        }

        k.next = null;
        
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        //EquationList k = g;
        g = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList p = g;
        int sum = 0;

        if(g == null){
            return 0;
        }

        while(p != null){
            sum = sum + p.result;
            p = p.next;
        }
        return sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int sum = 1;
        EquationList p = g;

        if (g == null){
            return 1;
        }

        if (p != null){
            sum = sum*(p.result);
            p = p.next;
        }
        return sum;
    }
}