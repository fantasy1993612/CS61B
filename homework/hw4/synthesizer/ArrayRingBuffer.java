// Make sure to make this class a part of the synthesizer package
//package synthesizer;

public class ArrayRingBuffer extends AbstractBoundedQueue {
  /* Index for the next dequeue or peek. */
  private int first;           
  /* Index for the next enqueue. */
  private int last;             
  /* Array for storing the buffer data. */
  private double[] rb;

  /** Create a new ArrayRingBuffer with the given capacity. */
  public ArrayRingBuffer(int capacity) {
    // TODO: Create new array with capacity elements.
    //       first, last, and fillCount should all be set to 0. 
    //       this.capacity should be set appropriately. Note that the local variable
    //       here shadows the field we inherit from AbstractBoundedQueue.
    this.rb = new double[capacity];
    this.first = 0;
    this.last = 0;
    this.capacity = capacity;
  }

  /** Adds x to the end of the ring buffer. If there is no room, then
    * throw new RuntimeException("Ring buffer overflow") 
    */
  public void enqueue(double x) {
    // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
    // is there room?
    int oldLast = this.last;

    if(!this.isFull()){

      this.rb[this.last] = x;
      fillCount = fillCount + 1; 

      if(this.last == this.capacity - 1){
        this.last = 0;
      }else{
        this.last = oldLast + 1;
      }
     
    }else {
      throw new RuntimeException("Ring buffer overflow");
    }

    rb[last] = x;
    last = last + 1;
   

  }

  /** Dequeue oldest item in the ring buffer. If the buffer is empty, then
    * throw new RuntimeException("Ring buffer underflow");
    */
  public double dequeue() {
    // TODO: Dequeue the first item. Don't forget to decrease fillCount and update first.
    int oldFirst = 0;

    if(!isEmpty()){
      fillCount = fillCount - 1;  
      if(this.first == this.capacity - 1){
        this.first = 0;
      }else{
        this.first = oldFirst + 1;
      }

      return rb[oldFirst];
    }else{
      throw new RuntimeException("Ring buffer underflow");
    }
    
  }

  /** Return oldest item, but don't remove it. */
  public double peek() {
    // TODO: Return the first item. None of your instance variables should change.
    return rb[first];
  }

}
