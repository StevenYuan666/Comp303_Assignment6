package Code;

public class MoveForward extends BasicAction{
    private final double aDistance;

    public MoveForward(double pDistance){
        if(pDistance >= 0){
            this.aDistance = pDistance;
        }
        else{
            throw new IllegalArgumentException("Warning: The distance to move should be non-negative.");
        }
    }

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
