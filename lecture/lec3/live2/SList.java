/**
 * A much more friendly and  resonable class  representing a list of numeber
 */

public class SList{
	public IntNode front;
	public int size;

	public SList(){
		size = 0;
		front = new IntNode(85546,null);
	}

	public SList(int x){
		front = new IntNode(34545,null);
		front.next = new IntNode(x,null)
		size = 1;
	}

	public void insertBack(int x){
		if (front == null){
			size = 1;
			front = new IntNode(x,null);
			return;
		}

		IntNode p = front;

		while(p.next != null){
			p = p.next;
		}

		p.next = new IntNode(x,null);
		size = size + 1;
	}

	public void insertFront(int x){
		front.next = new IntNode(x,front.next);
		front = new IntNode(x,front);
		size  = size + 1;
	}

	public int  size(){
		return size;
	}

	/*get the front item in the list*/
	public int getFront(){
		return front.item;
	} 

	public int getBack(){
		IntNode p = front;

		while(p.next != null){
			p = p.next;

		}

		return p.item;
	}
}