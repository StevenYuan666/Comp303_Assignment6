package Code;

public class Release extends BasicAction{
    @Override
    public void execute(Robot r) throws RobotDamagedException {
        super.execute(r);
        //DO the action
        //ensure the arm is retracted
        if(r.getArmState() == Robot.ArmState.EXTENDED){
            r.retractArm();
        }
        //ensure the gripper is closed
        if(r.getGripperState() == Robot.GripperState.OPEN){
            r.closeGripper();
        }
        r.openGripper();
        //Update the level of charge
        r.updateBatteryLevel();
    }

    @Override
    public String toString(){
        return "Release";
    }

    @Override
    public Object accept(Visitor pVisitor) {
        return pVisitor.visitRelease(this);
    }
}
