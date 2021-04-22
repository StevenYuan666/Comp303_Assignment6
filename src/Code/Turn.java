package Code;

import java.util.HashMap;

public class Turn extends BasicAction{
    /*
    Since the action is not for a specific robot, we can implement flyweight here
    Using Direction as key, and Turn object as value. There are only two instances.
     */
    private static final HashMap<Direction, Turn> aInstances = new HashMap<Direction, Turn>();
    static{
        Turn.aInstances.put(Direction.Left, new Turn(Direction.Left));
        Turn.aInstances.put(Direction.Right, new Turn(Direction.Right));
    }
    private final double degree;
    private Turn(Direction pDirection){
        if(pDirection == Direction.Left){
            this.degree = -90.0;
        }
        else {
            this.degree = 90.0;
        }
    }

    public static Turn getInstance(Direction pDirection){
        if(Turn.aInstances.containsKey(pDirection)) {
            return Turn.aInstances.get(pDirection);
        }
        else{
            //For any invalid input, we will not turn the robot
            throw new IllegalArgumentException("Warning: The direction can be either Right or Left only.");
        }
    }

    public Direction getDirection(){
        if(this.degree == -90.0){
            return Direction.Left;
        }
        else{
            return Direction.Right;
        }
    }

    @Override
    public void execute(Robot r) throws RobotDamagedException {
        super.execute(r);
        //Do the action
        //If the arm is extended, then retract first
        if(r.getArmState() == Robot.ArmState.EXTENDED){
            r.retractArm();
        }
        r.turnRobot(this.degree);
        //Update the charge level
        r.updateBatteryLevel();
    }

    @Override
    public String toString(){
        return "Turn";
    }

    @Override
    public Object accept(Visitor pVisitor) {
        return pVisitor.visitTurn(this);
    }
}
