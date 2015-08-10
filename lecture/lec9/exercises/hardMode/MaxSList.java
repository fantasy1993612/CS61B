/** 
 *  @author Josh Hug
 */

public class MaxSList extends SList {
	public String flavor = "MaxSList Flavor";
	 
	private int max = -Integer.MIN_VALUE;

	@Override
	 public void printFlavor(){
        System.out.println("MaxSList flavor is "+ flavor);
    }

    
	 public void printSuperFlavor(){
      super.printFlavor();
     }

    /* your code here */
    public MaxSList(){
    	super();
    	System.out.println("MaxSList");
    }

    /**Makes a call to the super class's constructor that takes an int
    Since MaxSlist is-an SList ,SList is the super class of the MaxSList*/
    public MaxSList(int x){
    	super(x);
    	max = x;
    }

    /**return max item in list */
    public int max(){
    	return max;
    }

    @Override
    /**insert into front (using super class method) update max is necessary*/
    public void insertFront(int x){
    	super.insertFront(x);
    	if(x > max){
    		max = x;
    	}
    }

    @Override
    public void insertBack(int x){
    	super.insertBack(x);
    	if(x > max){
    		max = x;
    	}
    }
}