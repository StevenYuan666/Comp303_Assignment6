package Code;

public class RechargeBeforeAction implements Action{
    //Always point to the same reference merely
    private final Action aAction;
    public RechargeBeforeAction(Action pAction){
        /*
        Directly store the reference, since we may modify the composite action later
        And all basic action are unmodifiable
         */
        this.aAction = pAction;
    }

    @Override
    public void execute(Robot r) throws RobotDamagedException {
        //Enforce the recharging before executing an action
        r.rechargeBattery();
        //Then do the action
        this.aAction.execute(r);
    }

    public Action getAction(){
        return this.aAction;
    }

    @Override
    public String toString(){
        return "Forcing Recharge First: " + this.aAction.toString();
    }

    @Override
    public Object accept(Visitor pVisitor) {
        return pVisitor.visitRechargeBeforeAction(this);
    }
}
