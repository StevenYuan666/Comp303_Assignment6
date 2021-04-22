package Code;

public class Grab extends BasicAction{
    //Since the action is not for a specific robot, we can implement singleton here
    private static final Grab aInstance = new Grab();

    private Grab(){}

    public static Grab getInstance(){
        return Grab.aInstance;
    }

    @Override
    public void execute(Robot r) throws RobotDamagedException {
        super.execute(r);
        //Do the action
        //Ensure the arm is retracted
        if(r.getArmState() == Robot.ArmState.EXTENDED){
            r.retractArm();
        }
        //Ensure the gripper is open
        if(r.getGripperState() != Robot.GripperState.OPEN){
            r.openGripper();
        }
        r.extendArm();
        r.closeGripper();
        r.retractArm();
        //Update the level of charge
        r.updateBatteryLevel();
    }

    @Override
    public String toString(){
        return "Grab";
    }

    @Override
    public Object accept(Visitor pVisitor) {
        return pVisitor.visitGrab(this);
    }
}
