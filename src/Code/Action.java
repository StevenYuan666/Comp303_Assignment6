package Code;


//All Action should be a kind of Element
public interface Action extends Element{
    //All action should be able to execute
    void execute(Robot r) throws RobotDamagedException;
}
