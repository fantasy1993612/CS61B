/** Additional operations on an IntList.
 *  @author Josh Hug
 */

public class IntListOps {
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

    /** Destructively squares each element of the given IntList L.
   * Don’t use ’new’; modify the original IntList.
   * Should be written iteratively. */
    public static IntList SquareDestructive(IntList L) {

        IntList P = L;

        while(P != null){
            P.head *= P.head;
            P = P.tail;
        }
        return L;

    }

 
    public static IntList SquareNonDestructive(IntList L) {

        if(L == null){
            return L;
        }else{
            IntList tail = SquareNonDestructive(L.tail);
            IntList M = new IntList(L.head*L.head,tail); 

            return M;
        }

    }

    public static IntList reverseNonDestructive(IntList L) {

       IntList L2 = null;

       while(L != null){
            L2 = new IntList(L.head,L2);
            L = L.tail;
       }

       return L2
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L.tail = new IntList(7, null);    
        L.tail.tail = new IntList(9, null);
        IntList M = reverseNonDestructive(L);
        //IntList M = dincrList(L, 3);
        //IntList M =  incrList(L, 5);
        System.out.println(M.head);
        System.out.println(M.tail.head);
         System.out.println(M.tail.tail.head);
        //System.out.println(incrList(L, 3));
       // System.out.println(dincrList(L, 3));


    }
} 