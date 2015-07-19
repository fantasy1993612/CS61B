package lec3;

public class IntListOps {
	
	public static IntList incrList(IntList L,int x){
		if (L == null){
			return null;
		}
		int newHead = L.head + x;
		IntList newTail = incrList(L.tail,x);
		return new IntList(newHead,newTail);
	}
   public static void main(String[] args){
	   IntList L = new IntList(10,null);
	   L.tail = new IntList(15,null);
	   IntListOps Lp = new IntListOps();
	   IntList l = Lp.incrList(L, 30);
	   System.out.println(l.head);
	   System.out.println(incrList(L,30));
	   
   }
}
