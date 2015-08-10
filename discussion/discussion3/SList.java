public class SList{

	private static SNode head;

	public void insert(double val,int position){
		if(head == null && position == 0){
			head = new SNode(val,head);
		}else{
			SNode cur = head;
			while(position > 1 && cur.next != null){
				position--;
				cur = cur.next;
			}
			SNode temp = cur.next;
			cur.next = new SNode(val,temp);
		}

	}

	public static void main(String[] args){

		SList l = new SList();

		l.insert(1,0);
		l.insert(2,1);
		l.insert(4,2);
		l.insert(3,3);
		l.insert(5,2);
		l.insert(7,4);
		l.insert(8,5);
		l.insert(9,4);

		SNode p = head;
		while(p != null){
			System.out.print(p.val+" ");
			p = p.next;
		}
		System.out.print("\n");

	}
}