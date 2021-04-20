package Code;

public interface Action extends Element{
    void execute(Robot r) throws RobotDamagedException;
}
