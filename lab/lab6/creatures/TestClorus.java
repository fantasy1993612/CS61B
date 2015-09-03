package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;
import java.util.List;
import huglife.Creature;
/*
The Clorus should obey the following rules exactly:

All Cloruses must have a name equal to exactly "clorus" (no capitalization or spaces)!
Cloruses should lose 0.03 units of energy on a move action, and lose 0.01 units of energy on a stay action.
Cloruses have no restrictions on their maximum energy.
The color method for Cloruses should always return the color R = 34, G = 0, B = 231.
If a Clorus attacks another Creature, it should gain that Creature's energy. 
This should happen in the attack() method, not in chooseAction(). 
You do not need to worry about making sure the attacked() creature dies — the simulator does that for you.
When replicating, Cloruses should receive half of their energy and their offspring should receive the other half.
Cloruses should obey exactly the following behavioral rules:

If there are no empty squares the Clorus will STAY (even if there are Plips nearby they could attack).
Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
Otherwise, the Clorus will MOVE.
As before, write a TestClorus class. 
You probably don't need to test the move(), stay(), or color() methods, 
but you're welcome to. Instead, it's probably only necessary to test the Choose() action. 
Your tests for TestClorus should involve at least one of each type of action.

Once you've written tests, write the Clorus class itself, again from scratch.
*/
public class TestClorus{

	@Test
	public void testBasics(){
		Clorus c = new Clorus(2);
		assertEquals(2, c.energy(), 0.01);
                assertEquals(new Color(34, 0, 231), c.color());
                c.move();
                assertEquals(1.97, c.energy(), 0.01);
                c.move();
                assertEquals(1.94, c.energy(), 0.01);
                c.stay();
                assertEquals(1.93, c.energy(), 0.01);
                c.stay();
                assertEquals(1.92, c.energy(), 0.01);
	}
        
        @Test 
        public void testAttack(){
                Clorus c = new Clorus(2);
                Plip p = new Plip(2);
                c.attack(p);
                assertEquals(4,c.energy(),0.01);
        }
        
       // @Test
        public void testAction(){
                Clorus c = new Clorus(2);
                Clorus c1 = new Clorus(1.5);
                Clorus c2 = new Clorus(1.3);
                Clorus c3 = new Clorus(1.7);
                Clorus c4 = new Clorus(0.5);
                Plip p = new Plip(2);
              
                /*
                HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
                surrounded.put(Direction.TOP, new Impassible());
                surrounded.put(Direction.BOTTOM, new Impassible());
                surrounded.put(Direction.LEFT, new Impassible());
                surrounded.put(Direction.RIGHT, new Impassible());
                assertEquals(new Action(Action.ActionType.STAY),c.chooseAction(surrounded));

                HashMap<Direction, Occupant> surrounded2 = new HashMap<Direction, Occupant>();
                surrounded2.put(Direction.TOP, new Plip(2));
                surrounded2.put(Direction.BOTTOM, new Plip(1.5));
                surrounded2.put(Direction.LEFT, new Impassible());
                surrounded2.put(Direction.RIGHT, new Impassible());
                assertEquals(new Action(Action.ActionType.STAY),c1.chooseAction(surrounded2));
                */
/*
                HashMap<Direction, Occupant> surrounded3 = new HashMap<Direction, Occupant>();
                surrounded3.put(Direction.TOP, p);
                surrounded3.put(Direction.BOTTOM, new Empty());
                surrounded3.put(Direction.LEFT, new Empty());
                surrounded3.put(Direction.RIGHT, new Empty());
               // List<Direction> cloruses = getNeighborsOfType(surrounded3,"plip");
                assertEquals(new Action(Action.ActionType.ATTACK,cloruses.get(0)),c2.chooseAction(surrounded3));*/
        /*
                HashMap<Direction, Occupant> surrounded4 = new HashMap<Direction, Occupant>();
                surrounded4.put(Direction.TOP, new Empty());
                surrounded4.put(Direction.BOTTOM, new Empty());
                surrounded4.put(Direction.LEFT, new Empty());
                surrounded4.put(Direction.RIGHT, new Empty());
                assertEquals(new Action(Action.ActionType.REPLICATE),c3.chooseAction(surrounded4));

                HashMap<Direction, Occupant> surrounded5 = new HashMap<Direction, Occupant>();
                surrounded5.put(Direction.TOP, new Empty());
                surrounded5.put(Direction.BOTTOM, new Empty());
                surrounded5.put(Direction.LEFT, new Empty());
                surrounded5.put(Direction.RIGHT, new Impassible());
                assertEquals(new Action(Action.ActionType.MOVE),c4.chooseAction(surrounded5));
               */
        }

        public static void main(String[] args){
                System.exit(jh61b.junit.textui.runClasses(TestClorus.class));
    
        }
}