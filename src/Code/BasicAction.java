package Code;

public abstract class BasicAction implements Action{

    //All basic action should in same protocol
    @Override
    public void execute(Robot r) throws RobotDamagedException {
        //Check the state of battery
        int charge = r.getBatteryCharge();
        //if the charge <= 5, then recharge
        if(charge <= 5){
            r.rechargeBattery();
        }
    }
}
