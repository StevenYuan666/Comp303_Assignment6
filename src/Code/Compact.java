package Code;

public class Compact extends BasicAction{
    @Override
    public void execute(Robot r) throws RobotDamagedException {
        if(r.getGripperState() != Robot.GripperState.HOLDING_OBJECT){
            throw new RobotDamagedException("Warning: Gripper should hold an object.");
        }
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
