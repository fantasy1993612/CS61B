public class SentinelSList{
	private static SNode front;
	private static SNode back;

	public SentinelSList(){
		this.back = new SNode(0,null); 
		this.front = new SNode(0,back);
	}

	public void insert(double val,int position){
		
			SNode cur = front;
			int curPos = 0;
			while(curPos < position && cur.next != back){
				curPos++;
				cur = cur.next;
			}
			SNode temp = cur.next;
			cur.next = new SNode(val,temp);
		
	}

	public static void main(String[] args){
		SentinelSList s = new SentinelSList();
		s.insert(1,0);
		s.insert(2,1);
		s.insert(3,2);
		s.insert(4,3);
		s.insert(5,4);
		s.insert(7,2);
		SNode p = front;
		p = p.next;

		while(p != back){
			System.out.print(p.val + " ");
			p = p.next;
		}
		    System.out.print("\n");
	}
}