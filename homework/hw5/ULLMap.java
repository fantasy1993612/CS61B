import java.util.Set; /* java.util.Set needed only for challenge problem. */
import java.util.Iterator;
import java.util.NoSuchElementException;

/** A data structure that uses a linked list to store pairs of keys and values.
 *  Any key must appear at most once in the dictionary, but values may appear multiple
 *  times. Supports get(key), put(key, value), and contains(key) methods. The value
 *  associated to a key is the value in the last call to put with that key. 
 *
 *  For simplicity, you may assume that nobody ever inserts a null key or value
 *  into your map.
 */ 
public class ULLMap<K,V> implements Map61B<K, V>, Iterable<K> { //FIX ME
    /** Keys and values are stored in a linked list of Entry objects.
      * This variable stores the first pair in this linked list. You may
      * point this at a sentinel node, or use it as a the actual front item
      * of the linked list. 
      */
    private Entry<K,V> front;
    int size = 0;

    public Iterator<K> iterator() {
        return new ULLMapIter();
    }

    @Override
    public V get(K key) { //FIX ME
    //FILL ME IN
        Entry<K,V> curr = front.get(key);
        return curr.val;
    }

    @Override
    public void put(K key, V val) { 
        if (!containsKey(key)) {
            front = new Entry<K,V>(K,V,front);
        }
    }

    @Override
    public boolean containsKey(K key) { //FIX ME
        if (front == null)
            return false;
        return front.get(key) != null;
    
    }

    @Override
    public int size() {
      if(front == null)
            return 0;
        int count = 0;
        Entry<K,V> current = front;
        while(current!=null){
            count += 1;
            current = current.next;
    }

    
    public void clear() {
       front = null;
    }

    public static <K, V> ULLMap<V, K> invert(ULLMap<K, V> dict) {
        ULLMap<V, K> inverted = new ULLMap<V, K>();
        for (K key : dict) {
            inverted.put(dict.get(key), key);
        }
        return inverted;
    }
    
    private class ULLMapIter implements Iterator<K>{

        private Entry curr;
        //private Entry p; 

        public ULLMapIter(){
            curr = front;
        }

        @Override
        public K next(){
           
                K ret = curr.key; 
                curr = curr.next;

                return ret;
        }

        @Override
        public boolean hasNext(){
            return curr != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    /** Represents one node in the linked list that stores the key-value pairs
     *  in the dictionary. */
    private class Entry<K,V>{
    
        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        public Entry(K k, V v, Entry<k,V> n) { //FIX ME
            key = k;
            val = v;
            next = n;
        }

        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        public Entry<K,V> get(K k) { 
           
            if (k != null && k.equals(key)) 
                return this;
            if (next == null) 
                return null;
            return next.get(key);
           
        }

        /** Stores the key of the key-value pair of this node in the list. */
        private K key; //FIX ME
        /** Stores the value of the key-value pair of this node in the list. */
        private V val; //FIX ME
        /** Stores the next Entry in the linked list. */
        private Entry<K,V> next;
    
    }
    /* Methods below are all challenge problems. Will not be graded in any way. 
     * Autograder will not test these. */

    
    @Override
    public V remove(K key) { //FIX ME SO I COMPILE
       //throw new UnsupportedOperationException();
        if(front == null){
            return null;
        }

        if(key.equals(front.key)){
            V val = front.val;
            front = front.next;
            return val;
        }
       
        Entry<K,V> curr = front;

        while(curr.next != null){
            if(key.equals(curr.next.key)){
                V value = curr.val;
                curr = curr.next.next;
                return val;
            }
            curr = curr.next;
        }

        return null;
    }

    @Override
    public V remove(K key, V value) { //FIX ME SO I COMPILE
         if(front == null){
            return null;
        }

        if(key.equals(front.key)&&key.equals(front.val)){
            V val = front.val;
            front = front.next;
            return val;
        }
       
        Entry<K,V> curr = front;

        while(curr.next != null){
            if(key.equals(curr.next.key)&&key.equals(front.val)){
                V value = curr.val;
                curr = curr.next.next;
                return val;
            }
            curr = curr.next;
        }

        return null;
        
    }

    @Override
    public Set<K> keySet() { //FIX ME SO I COMPILE
        //throw new UnsupportedOperationException();
        if(front == null){
            return null;
        }

        Set<K> keys = new HashSet<K>();

        Entry<K,V> curr = front;

        while(front!=null){
            keys.add(curr.key);
            curr = curr.next;
        }

        
    }
    

}