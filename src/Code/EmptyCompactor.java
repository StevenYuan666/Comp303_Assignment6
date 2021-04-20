package Code;

public class EmptyCompactor extends BasicAction{
    @Override
    public void execute(Robot r) throws RobotDamagedException {
        if(r.getCompactorLevel() > 0){
            super.execute(r);
            //Do the action
            r.emptyCompactor();
            //Update the level of charge
            r.updateBatteryLevel();
        }
        else{
            throw new RobotDamagedException("Warning: Compactor has been emptied.");
        }
    }

    @Override
    public String toString(){
        return "EmptyCompactor";
    }

    @Override
    public Object accept(Visitor pVisitor) {
        return pVisitor.visitEmptyCompactor(this);
    }
}
