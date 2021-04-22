package Code;

//Define a new type of Exception to handle the condition that the client violate the pre-condition
public class RobotDamagedException extends Exception{
    public RobotDamagedException(String pMessage){
        super(pMessage);
    }
}
