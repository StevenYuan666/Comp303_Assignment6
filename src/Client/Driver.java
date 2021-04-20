package Client;

import Code.*;

public class Driver {
    public static void main(String[] args) throws RobotDamagedException {
        WallE myRobot = new WallE();
        MoveForward mf1 = new MoveForward(10);
        Turn turnRight = new Turn(Direction.Right);
        Program myProgram = new Program("Go Forward then Turn Right");
        myProgram.addAction(mf1);
        myProgram.addAction(turnRight);
        LogToConsoloe consoleObserver = new LogToConsoloe();
        myProgram.addObserver(consoleObserver);
        myProgram.executeProgram(myRobot);
        GetDistance distanceVisitor = new GetDistance();
        Double distance = (Double) myProgram.accept(distanceVisitor);
        System.out.println("Total Distance Moved: " + distance);
    }
}
