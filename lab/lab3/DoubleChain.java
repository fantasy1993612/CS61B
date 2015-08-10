
public class DoubleChain {
	
	private DNode head;
	
	public DoubleChain(double val) {
		/* your code here. */
		head = new DNode(val); 
		
	}

	public DNode getFront() {
		return head;
	}

	/** Returns the last item in the DoubleChain. */		
	public DNode getBack() {
		/* your code here */
		DNode p = head;

		if(p.next == null){
			return p;
		}

		while(p.next != head.prev){
			p = p.next;
		}
		
		return p;
	}
	
	/** Adds D to the front of the DoubleChain. */	
	public void insertFront(double d) {

			if(head.prev == null && head.next == null){
				DNode p = new DNode(head,d,head);
				head.next = p;
				head.prev = p;
				head = p;
			}else{
				head = head.prev;
				DNode p = new DNode(head,d,head.next);
				head.next = p;
				p.next.prev = p;
				head = p;
			}
	}
	
	/** Adds D to the back of the DoubleChain. */	
	public void insertBack(double d) {
		/* your code here */
		DNode q = head;

		if(head.prev == null && head.next == null){
				DNode p = new DNode(head,d,head);
				head.next = p;
				head.prev = p;
				head = p;
		}else{
			head = head.prev;
			while(q != head.prev){
				q = q.next;
			}
			DNode p = new DNode(q,d,q.next);
			q.next = p;
			head.prev = p;
			head = head.next;
		}
	}
	
	/** Removes the last item in the DoubleChain and returns it. 
	  * This is an extra challenge problem. */
	public DNode deleteBack() {
		/* your code here */
		DNode p = head;

		while(p.next != head.prev){
			p = p.next;
		}
		DNode q = p;
		q = q.next;
		head.prev = p;
		p.next = p.next.next ;

		return q;
	}
	
	/** Returns a string representation of the DoubleChain. 
	  * This is an extra challenge problem. */
	public String toString() {
		/* your code here */		
		return null;
	}

	public static class DNode {
		public DNode prev;
		public DNode next;
		public double val;
		
		private DNode(double val) {
			this(null, val, null);
		}
		
		private DNode(DNode prev, double val, DNode next) {
			this.prev = prev;
			this.val = val;
			this.next =next;
		}
	}

	
}
