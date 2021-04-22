package Code;

import java.util.HashMap;

public class MoveForward extends BasicAction{
    /*
    Since the action is not for a specific robot, we can implement flyweight here
    By using the distance to move as key, and the MoveForward object as value
     */
    private static final HashMap<Double, MoveForward> aInstances = new HashMap<Double, MoveForward>();
    private final double aDistance;

    private MoveForward(double pDistance){
        if(pDistance >= 0){
            this.aDistance = pDistance;
        }
        else{
            throw new IllegalArgumentException("Warning: The distance to move should be non-negative.");
        }
    }

    //Factory method
    public static MoveForward getInstance(double pDistance){
        if(MoveForward.aInstances.containsKey(pDistance)){
            return MoveForward.aInstances.get(pDistance);
        }
        else{
            MoveForward toReturn = new MoveForward(pDistance);
            MoveForward.aInstances.put(pDistance, toReturn);
            return toReturn;
        }
    }

    //Getters for the Distance
    public double getDistance(){
        return this.aDistance;
    }

    @Override
    public void execute(Robot r) throws RobotDamagedException {
            super.execute(r);
            //Do the action
            //If the arm is extended, then retract first
            if(r.getArmState() == Robot.ArmState.EXTENDED){
                r.retractArm();
            }
            r.moveRobot(this.aDistance);
            //Update the Battery level
            r.updateBatteryLevel();
    }

    @Override
    public String toString(){
        return "MoveForward";
    }

    @Override
    public Object accept(Visitor pVisitor) {
        return pVisitor.visitMoveForward(this);
    }
}
