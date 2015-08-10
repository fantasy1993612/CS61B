public class DNode{

	public DNode prev;
	public DNode next;
	public double val;

	public DNode(DNode prev,DNode next,double val){
		this.prev = prev;
		this.next = next;
		this.val = val;
	}
}