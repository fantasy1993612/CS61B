//package synthesizer;

import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue(34.5);
        arb.enqueue(344.5);
        arb.enqueue(33.5);
        arb.dequeue();
        double a = arb.peek();
        //assertEquals(34.5,a);
        System.out.println(a);

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 