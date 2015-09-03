package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;

public class Clorus extends Creature{

	/** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;
    /*move lose energy*/
    private static final double loseEnergy = 0.03;
    /*stay  energy*/
    private static final double stayEnergy = 0.01;

	public Clorus(double e){
		super("clorus");
		r = 34;
		g = 0;
		b = 231;
		energy = e;
	}

	public Clorus(){
		this(1);
	}

	public Color color() {
		b = (int)(10 * energy + 120);
        if (b > 231) {
            b = 231;
        }
        return color(r, g, b);
    }

    public void attack(Creature c) {
    	energy += c.energy();
    }

    public void move() {
    	energy -= loseEnergy;
    }

    public void stay() {
    	energy -= stayEnergy;
    }

    public Clorus replicate(){
    	
       energy= energy/2;
       return new Clorus(energy);
    }

    /*
	If there are no empty squares the Clorus will STAY (even if there are Plips nearby they could attack).
	Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
	Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
	Otherwise, the Clorus will MOVE.
    */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
    	List<Direction> empties = getNeighborsOfType(neighbors, "empty");
    	List<Direction> plips = getNeighborsOfType(neighbors,"plip");

    	if(empties.size() == 0){
    		return new Action(Action.ActionType.STAY);
    	}else if(plips.size() >= 1){
    		Direction attackDir = HugLifeUtils.randomEntry(plips);
            return new Action(Action.ActionType.ATTACK, attackDir);
    	}else if(energy >= 1){
    		Direction replicateDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, replicateDir);
    	}else {
	    	Direction moveDir = HugLifeUtils.randomEntry(empties);
	        return new Action(Action.ActionType.MOVE, moveDir);
	    }
        
    }

}