/** Defines a recursive list of integers.
 *  @author Josh Hug
 */

public class IntList {
    public int head;
    public IntList tail;


    public IntList(int h, IntList t) {
        /* your code here */
        head = h;
        tail = t;

    }

    /** Retuns the size of this IntList */
    public int size() {

        if (tail == null) {
            return 1;
        }
        return 1 + tail.size();
    }

    /** Returns the size of this IntList. */
   public int iterativeSize() {
        IntList p = this;
        int size = 0;
        while (p != null) {
            size += 1;
            p = p.tail;
        }
        return size;
    }

    /** In class exercise 1: 
      * Returns ith item of this IntList. For 
      * simplicity, assume the item exists. */
    public int get(int i) {
        IntList L = this;
        if (i == 0){
            return head;
        }
        else if(i > 0 ){
            while(L.tail != null && i > 0){
                i--;
                L = L.tail;
            }
        }
        return L.head;
        
    }

    /** Returns an IntList identical to L, but with
      * each element incremented by x. L is not allowed
      * to change. */
    public static IntList incrList(IntList L, int x) {
        /* your code here */
       if (L == null)
            return null;
        
       int newHead = x + L.head;
       IntList newTail = incrList(L.tail,x);
       return new IntList(newHead,newTail);
    }

    /** Returns an IntList identical to L, but with
      * each element incremented by x. Not allowed to use
      * the 'new' keyword. */
    public static IntList dincrList(IntList L, int x) {
        /* your code here */
        if(L == null){
            return null;
        }

        L.head = L.head + x;
        return dincrList(L.tail,x);
    }

   
      
    }
    public String toString() {
        if (tail == null)
            return Integer.toString(head);
        return Integer.toString(head) + " " + tail.toString();
    }

    public static void main(String[] args) {
       IntList L = new IntList(5, null);
       L.tail = new IntList(7, null);
       L.tail.tail = new IntList(8,null);
       //L = new IntList(8,L);
      // IntList M = dincrList(L, 3);
       IntList M = incrList(L,3);
       // System.out.println(L.size());
       //System.out.println(L.iterativeSize());
       System.out.println(M.toString());
    }
} 