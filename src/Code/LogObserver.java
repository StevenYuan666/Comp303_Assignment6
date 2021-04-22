package Code;

//Implement the Logging system by the Observer design pattern, we need a notify method here
public interface LogObserver {
    public void logging(Action pAction, Robot pRobot);
}
