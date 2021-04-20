package Code;

public class LogToConsoloe implements LogObserver{
    @Override
    public void logging(Action pAction, Robot pRobot) {
        System.out.println(pAction.toString() + " action performed, battery level is " + pRobot.getBatteryCharge());
    }
}
