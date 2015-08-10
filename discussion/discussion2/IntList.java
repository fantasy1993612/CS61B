public class IntList{

	public int head;
	public IntList tail;

	public IntList(int head,IntList tail){
			this.head = head;
			this.tail = tail;
	}

	public void PrintIntList(IntList L){
		while(L != null){
			System.out.print(" " + L.head);
			L = L.tail;
		}
	}
	
	public static IntList SquareDestructive(IntList L) {
		IntList l = L;
		while(L != null){
			L.head = L.head * L.head;
			L = L.tail; 
		}
		return l;
	}

	public static IntList SquareNonDestructive(IntList L) {
		if(L == null){
			return null;
		}else {
			IntList N = new IntList(L.head*L.head,SquareNonDestructive(L.tail));
			return N;
		}
		
	}
	
	public static IntList reverseNonDestructive(IntList L) {
			IntList K = L;
			IntList M = L;
			IntList N = L;
			L = L.tail;
			while(L != null){
				N = N.tail;
				L = L.tail;
				N.tail = K;
				K = K.tail;
			}
			M.tail = null;
			return N;
	}

	public static void main(String args[]){
	//1 practice with linked list 
	 	IntList L = new IntList(4, null);
	 	L = new IntList(3, L);
	 	L = new IntList(2, L);
	 	L = new IntList(1, L);
	 	//System.out.println("The linked list is ");
	 	//L.PrintIntList(L);
	 	//System.out.println("\nThe destructively square of the linked list is");
	 	//L = L.SquareDestructive(L);
	 	//L.PrintIntList(L);
	 	//System.out.println("\n");
	 	//L = L.SquareNonDestructive(L);
	 	//L.PrintIntList(L);
	 	//L = L.SquareDestructiveRecursive(L);
	 	L = L.reverseNonDestructive(L);
	 	L.PrintIntList(L);
	 }
}
