package Code;

public class Compact extends BasicAction{
    //Since the action is not for a specific robot, we can implement singleton here
    private static final Compact aInstance = new Compact();

    private Compact(){}

    public static Compact getInstance(){
        return Compact.aInstance;
    }

    @Override
    public void execute(Robot r) throws RobotDamagedException {
        //Ensure the gripper is holding something
        if(r.getGripperState() != Robot.GripperState.HOLDING_OBJECT){
            throw new RobotDamagedException("Warning: Gripper should hold an object.");
        }
        //Ensure we don't need empty the compactor firstly
        if(r.getCompactorLevel() >= 10){
            throw new RobotDamagedException("Warning: Compactor should contain less than 10 objects.");
        }
        super.execute(r);
        //Do the action
        r.compact();
        //Update the level of charge
        r.updateBatteryLevel();
    }

    @Override
    public String toString(){
        return "Compact";
    }

    @Override
    public Object accept(Visitor pVisitor) {
        return pVisitor.visitCompact(this);
    }
}
