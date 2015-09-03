import java.util.Set;
import java.util.Iterator;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{	

	private Node root;
	private int size;

	@Override
	public int size(){
		return size(root);
	}

	@Override
	public V get(K key){
		return get(root,key);
	}

	@Override
	public void put(K key,V val){
		root = put(root,key,val);
	}

	@Override
	public boolean containsKey(K key){
		if (root == null) {
			return false;
		}

		if(get(key) == null){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void clear(){
		root = null;
	}

	 @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }


	private V get(Node x,K key){
		if(x == null){
			return null;
		}

		int cmp = key.compareTo(x.key);
		if(cmp < 0){
			return get(x.left,key);
		}else if(cmp > 0){
			return get(x.right,key);
		}else{
			return x.val;
		}
	}

	private Node put(Node x,K key,V val){
		if(x == null){
			return new Node(key,val,1);
		}

		int cmp = key.compareTo(x.key);
		if(cmp < 0){
			x.left = put(x.left,key,val);
		} else if(cmp > 0){
			x.right = put(x.right,key,val);
		}else{
			x.val = val;
		}

		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	
	private int size(Node x){
		if(x == null){
			return 0;
		}else{
			return x.N;
		}
	}

  public Iterable<K> levelOrder() {
        Queue<K> keys = new Queue<K>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }

	private class Node{
		private K key;
		private V val;
		private Node left,right;
		private int N;//nodes in subtree

		public Node(K key,V val,int N){
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	public static void main(String[] args){
		BSTMap<String, Integer> st = new BSTMap<String, Integer>();
		
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.levelOrder())
            StdOut.println(s + " " + st.get(s));

        StdOut.println();
	} 

}