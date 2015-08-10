/** Array based list.
 *  @author Josh Hug
 */

public class AList {

    private int[] items;
    private int size;

    /**resize the array in capacity of C*/
    private void Resize(int c){
        int[] s = new int[c];
        for (int i = 0 ; i < items.length; i++){
            s[i] = items[i];
        }

        items = s;
    }

    /** Creates an empty list. */
    public AList() {
        items = new int[100];
        size = 0;
    }

    /** Inserts X into the back of the list. */
    public void insertBack(int x) {
        if(size == items.length){
            Resize(items.length*2);
        }
        items[size] = x;
        size += 1;
    }

    /** Returns the item from the back of the list. */
    public int getBack() {
        return items[size-1];        
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        if (i > size){
            throw new IllegalArgumentException("bound exception");
        }
        return items[i];        
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public int deleteBack() {
        int oldBack = getBack();
        size = size-1;
        return oldBack;
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;        
    }
} 