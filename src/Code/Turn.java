package Code;

public class Turn extends BasicAction{
    private final double degree;
    public Turn(Direction pDirection){
        if(pDirection == Direction.Left){
            this.degree = -90.0;
        }
        else if(pDirection == Direction.Right){
            this.degree = 90.0;
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
