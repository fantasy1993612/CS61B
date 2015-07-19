package lec3;

public class IntList {
	public int head;
	public IntList tail;
	
	public IntList(int h,IntList t){
		head = h;
		tail = t;
	}
	
	/**返回链表大小*/
	public int get(int i){
		if (this.tail == null){
			return 1;
		}
		return this.tail.get(i-1);
		
	}
	
	/*迭代版本返回链表大小*/
	public int iteractiveSize(){
		IntList p = this;
		int size = 0;
		while(p!=null){
			size = size + 1;
			p = p.tail;
		}
		return size;
	}
	
	public String toString(){
		if (tail == null){
			return Integer.toString(head);
		}
		return Integer.toString(head)+ " " + tail.toString();
	}
	
	public static void main(String[] args){
		IntList L = new IntList(10,null);
		L.tail = new IntList(15,null);
	    int l = L.iteractiveSize();
		System.out.println("The size of the list "+l);
		System.out.println("The head of the list "+L.head);
		System.out.println("The second elem in the list "+L.tail.head);
		System.out.println(L.get(1));
	}
	
}
