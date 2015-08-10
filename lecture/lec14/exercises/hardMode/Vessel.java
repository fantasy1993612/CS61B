/** Your job: Convert this into a generic Vessel so that TestGoalOne works.
 *  @author Josh Hug
 */
/*integer is the actual parameter ,fill in Fantasy*/
public class Vessel<Fantasy> {
    Fantasy occupant;

    public void put(Fantasy x) {
        occupant = x;
    }

    public Fantasy peek() {
        return occupant;
    }
} 